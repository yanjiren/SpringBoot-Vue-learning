package org.server.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.server.bean.Ent;
import org.server.bean.Project;
import org.server.bean.Role;

@Mapper
public interface EntMapper {
    Ent loadUserByUsername(String username);

    int EntReg(@Param("username") String username, @Param("password") String password);

    List<Ent> getEntByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords );

    int getCountByKeywords(@Param("keywords") String keywords);

    Ent getEntBasicById(Integer id);

}
