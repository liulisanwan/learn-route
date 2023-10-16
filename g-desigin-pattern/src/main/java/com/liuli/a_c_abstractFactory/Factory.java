package com.liuli.a_c_abstractFactory;

/**
 * 工厂
 *
 * @author zhanghui
 * @date 2023/10/16 14:00:44
 */
public interface Factory {
    /**
     * 获取实例TV
     *
     * @return {@code TV }
     * @author zhanghui
     * @date 2023/10/16 14:00:50
     * @see TV
     * @since 1.0.0
     */
    public TV getInstanceTV();

    /**
     * 获取实例电话
     *
     * @return {@code Phone }
     * @author zhanghui
     * @date 2023/10/16 14:00:51
     * @see Phone
     * @since 1.0.0
     */
    public Phone getInstancePhone();

    /**
     * 获取实例计算机
     *
     * @return {@code Computer }
     * @author zhanghui
     * @date 2023/10/16 14:00:54
     * @see Computer
     * @since 1.0.0
     */
    public Computer getInstanceComputer();
}
