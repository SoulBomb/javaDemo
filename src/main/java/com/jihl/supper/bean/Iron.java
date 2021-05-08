package com.jihl.supper.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author admin
 * @since 2021/4/29
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class Iron extends Man {

    public String speed;

}
