package com.yany.configure.mybatis.multi.aop;

/**
 * 保存一个线程安全的DatabaseType容器
 * <p>
 * Created by yanyong on 2017/1/30.
 */
public class DatabaseContextHolder {
    private final static ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

}
