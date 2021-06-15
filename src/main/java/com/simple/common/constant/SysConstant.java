package com.simple.common.constant;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName SysConstant.java
 * @Description TODO
 * @createTime 2021年06月02日 14:35:00
 */
public class SysConstant {
    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";

    /**
     * 常用接口
     */
    public static class Url {
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
