package org.server.service;

import org.server.bean.Project;
import org.server.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    public List<Project> getProjectsByUsername(String username,String keywords){
        return projectMapper.getProjectsByUsername(username,keywords);
    }

    public List<Project> getProjectsByPage(int page, int size, String keywords){
        int start = (page - 1) * size;
        return projectMapper.getProjectsByPage(start, size, keywords);
    }

    public int getCountByKeywords(String keywords){
        return projectMapper.getCountByKeywords(keywords);
    }

    public String getEntNameByEid(int eid){
        return projectMapper.getEntNameByEid(eid);
    }

    public  int addProject(Project project){
        return projectMapper.addProject(project);
    }

    public boolean deleteProjectById(String ids){
        String[] split = ids.split(",");
        return projectMapper.deleteStageByPid(split) == split.length && projectMapper.deleteProjectById(split) == split.length;
    }
}
