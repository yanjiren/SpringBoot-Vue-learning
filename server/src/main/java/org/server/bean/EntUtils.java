package org.server.bean;

import org.springframework.security.core.context.SecurityContextHolder;

//返回当前登录用户的信息
public class EntUtils {
    public static Ent getCurrentEnt() {
        return (Ent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
