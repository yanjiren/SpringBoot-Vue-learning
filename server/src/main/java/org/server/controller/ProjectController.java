package org.server.controller;

import org.server.bean.Project;
import org.server.bean.RespBean;
import org.server.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/entpm",method = RequestMethod.GET)
    public List<Project> getProjectsByUsername(@RequestParam(defaultValue = "") String username,@RequestParam(defaultValue = "") String keywords) {
        return projectService.getProjectsByUsername(username,keywords);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Map<String, Object> getProjectsByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String keywords) {
        Map<String, Object> map = new HashMap<>();
        List<Project> ProjectsByPage = projectService.getProjectsByPage(page, size, keywords);
        int count = projectService.getCountByKeywords(keywords);
        for(Project p:ProjectsByPage){
            p.setEntName(projectService.getEntNameByEid(p.getEid()));
        }
        map.put("projects", ProjectsByPage);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/entpm",method = RequestMethod.POST)
    public RespBean addProject(int eid, String name, String pCode, String principal, int scale, String industry, String location, String startDate,String endDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Project project = new Project(eid,name,pCode,principal,scale,industry,location,simpleDateFormat.parse(startDate),simpleDateFormat.parse(endDate));
        if(projectService.addProject(project)==1){
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }

    @RequestMapping(value = "/all/{ids}",method = RequestMethod.DELETE)
    public RespBean deleteProjectById(@PathVariable String ids){
        if(projectService.deleteProjectById(ids)) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
