package org.linlinjava.litemall.db.util;

public class IntegralStatus {

    //integral_type 积分类型
    /**
     * 积分默认类型
     */
    public static final Integer STATUS_DEFUALT = 0;
    /**
     * 注册类型
     */
    public static final Integer STATUS_REGISTER = 1;
    /**
     * 现金消费类型
     */
    public static final Integer STATUS_CASH = 2;
    /**
     * 积分消费购物
     */
    public static final Integer STATUS_USED = 3;
    /**
     * 公益，其它
     */
    public static final Integer STATUS_OUT = 4;


    /**
     * 积分有效
     */
    public static final Integer EFFECTIVE_YES = 1;

    /**
     * 积分无效
     */
    public static final Integer EFFECTIVE_NO = 0;

    /**
     * 注册赠送积分
     */
    public static final Integer REGISTERED_GIFT  = 500;

    /**
     * 消费一元等于100积分
     */
    public static final Integer CASH_GIFT_SWITCH = 100;

}
