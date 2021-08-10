package com.jihl.supper.handle_service;

import org.springframework.beans.BeansException;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author admin
 * @since 2021/8/10
 */
@Component
public class CglibMethodInterceptor implements MethodInterceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 存放代理对象
     */
    private static ConcurrentHashMap<String, Class<?>> MAP = new ConcurrentHashMap<>(16);

    /**
     * 存放 目标对象 及其 从 Spring 容器获取的 子类关系Map
     * 使用这个的目的在于 Spring 容器每次获取返回的数据都会创建一个 指定数据的map结果集返回
     */
    private static ConcurrentHashMap<Class<?>, Map<String, ?>> objectMapToSpringContext = new ConcurrentHashMap<>(16);

    /**
     * @param object      表示要进行增强的对象
     * @param method      表示拦截的方法
     * @param objects     数组表示参数列表，基本数据类型需要传入其包装类型，如int-->Integer、long-Long、double-->Double
     * @param methodProxy 表示对方法的代理，invokeSuper方法表示对被代理对象方法的调用
     * @return 执行结果
     */
    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String name = object.getClass().getName();
        Class<?> aClass = MAP.get(name);
        Object targetParam = getTargetParam(objects, method);
        Object targetClass = getTargetClass(aClass, targetParam);
        try {
            return methodProxy.invoke(targetClass, objects);
        } catch (Throwable throwable) {
            System.out.println("执行子类方法失败");
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 从参数列表中获取标记的参数
     * <p>
     * 注意：如果有多个注解参数，则返回第一个，未进行指定，则返回最后一个参数
     *
     * @param objects 实际参数
     * @param method  执行方法
     */
    private Object getTargetParam(Object[] objects, Method method) throws Exception {
        // 获取参数类型列表
        Parameter[] parameters = method.getParameters();
        // 获取参数中指定的标记参数
        Object targetParam = null;
        // 确实是否添加指定注解
        boolean findIsAnnotation = false;
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            // 判断 方法列表中的参数是否包含该参数
            if (parameter.isAnnotationPresent(FieldAnnotation.class)) {
                // 从参数中获取对应位置的数据
                targetParam = objects[i];
                // 找到参数注解
                findIsAnnotation = true;
                break;
            }
        }
        // 如果未指定参数则 使用 最后一个参数作为标记参数
        if (!findIsAnnotation) {
            targetParam = objects[objects.length - 1];
        }
        if (targetParam == null) {
            throw new Exception("目标参数不能为空");
        }
        return targetParam;
    }

    /**
     * 从Spring容器中获取接口当前相关实现类，并缓存
     * <p>
     * 注意：如果获取时，存在子类时未被加载到spring容器时，则后续获取不到（如懒加载状态）
     */
    private Object getTargetClass(Class<?> sourceClass, Object targetParam) throws Exception {
        // 获取接口对应实现类中的 所有实现类（包括自身）
        Map<String, ?> beansOfType = objectMapToSpringContext.get(sourceClass);
        // 目标为空则从 Spring 容器中获取
        if (CollectionUtils.isEmpty(beansOfType)) {
            // 添加对应的缓存集
            beansOfType = applicationContext.getBeansOfType(sourceClass);
            objectMapToSpringContext.put(sourceClass, beansOfType);
        }
        // 对应的子类执行类
        AtomicReference<Object> targetClassAtomic = new AtomicReference<>();
        // 对是否具备同名实现类进行计数
        AtomicReference<Integer> beansByAnnotationSum = new AtomicReference<>(0);
        // 根据参数获取指定的实现类
        beansOfType.forEach((beanName, cla) -> {
            ClaAnnotation annotation = cla.getClass().getAnnotation(ClaAnnotation.class);
            if (annotation != null) {
                String value = annotation.value();
                if (value.equals(targetParam)) {
                    targetClassAtomic.set(cla);
                    beansByAnnotationSum.set(beansByAnnotationSum.get() + 1);
                }
            }
        });
        // 根据找到的实现类决定异常抛出
        Integer beansSum = beansByAnnotationSum.get();
        if (beansSum > 1) {
            throw new Exception("目标对象应该只有一个，但是发现发现" + beansSum + "个");
        }
        Object targetClass = targetClassAtomic.get();
        if (targetClass == null) {
            throw new Exception("目标对象未找到");
        }
        return targetClass;
    }

    public <T> T generateClass(Class<T> cla) {
        //设置需要代理的子类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cla);
        enhancer.setCallback(this);
        //代理对象存入临时缓存
        Object obj = enhancer.create();
        String name = obj.getClass().getName();
        MAP.put(name, cla);
        return cla.cast(obj);
    }
}
