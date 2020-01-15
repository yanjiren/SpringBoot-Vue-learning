package org.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.server.bean.Ent;

@Mapper
public interface EntMapper {
    Ent loadUserByUsername(String username);

    List<Ent> getEntByPage(@Param("start") int start, @Param("size") int size, @Param("keywords") String keywords );

    int getCountByKeywords(@Param("keywords") String keywords);

    Ent getEntBasicByUsername(String username);

    int isExistUsername(String username);

    int entSignUp(@Param("username") String username, @Param("password") String password);

    int addRolesForEnt(int eid);

    int updateEnt(@Param("ent") Ent ent);

    int deleteEntById(@Param("ids") String[] ids);

    int deleteOpByEid(@Param("ids") String[] ids);

    int deleteERoleByEid(@Param("ids") String[] ids);

    int deleteProbByEid(@Param("ids") String[] ids);

    int deleteStageByPid(@Param("ids") String[] ids);

    int deleteProjByEid(@Param("ids") String[] ids);

    List<String> getPidByEid(@Param("ids") String[] ids);
}
