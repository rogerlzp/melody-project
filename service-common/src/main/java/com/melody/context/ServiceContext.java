package com.melody.context;


import com.melody.user.dto.User;

/**
 * Created by liuyw on 2015/12/11.
 */
public class ServiceContext {
    private static ThreadLocal<ServiceContext> localContext = new ThreadLocal<ServiceContext>();
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceContext(User user){
        this.user=user;
    }

    /**
     * 初始化WebContext
     */
    public static void initServiceContext(User user) {
        ServiceContext context = new ServiceContext(user);
        initServiceContext(context);
    }

    /**
     * 设置WebContext到ThreadLocal中
     *
     * @param webContext 需要设置到WebContext
     */
    public static void initServiceContext(ServiceContext webContext) {
        localContext.set(webContext);
    }

    public static ServiceContext getContext() {
        return localContext.get();
    }
}
