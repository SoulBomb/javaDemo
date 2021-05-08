package com.jihl.supper.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author admin
 * @since 2021/4/29
 */
@Accessors(chain = true)
@Data
public class Spider {

    public String speed;

    public String supper;
}
