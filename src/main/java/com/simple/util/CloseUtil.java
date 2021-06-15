package com.simple.util;

import java.io.Closeable;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName CloseUtil.java
 * @Description TODO
 * @createTime 2021年06月02日 14:46:00
 */
public class CloseUtil {
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    public static void close(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }
}
