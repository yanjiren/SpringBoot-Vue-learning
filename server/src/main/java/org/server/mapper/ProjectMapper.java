package org.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.server.bean.Project;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<Project> getProjectsByUsername(@Param("username")String username,@Param("keywords")String keywords);

    List<Project> getProjectsByPage(@Param("start") int start, @Param("size") int size, @Param("keywords") String keywords );

    int getCountByKeywords(@Param("keywords") String keywords);

    String getEntNameByEid(int eid);

    int addProject(@Param("project") Project project );

    int deleteProjectById(@Param("ids") String[] ids);

    int deleteStageByPid(@Param("ids") String[] ids);
}
