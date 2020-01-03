package org.server.controller;

import org.server.bean.Ent;
import org.server.bean.Project;
import org.server.service.EntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/enterprise")
public class EntBasicController {

    @Autowired
    EntService entService;

    @RequestMapping(value = "/all/ent", method = RequestMethod.GET)
    public Map<String, Object> getEntByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String keywords) {
        Map<String, Object> map = new HashMap<>();
        List<Ent> EntByPage = entService.getEntByPage(page, size, keywords);
        int count = entService.getCountByKeywords(keywords);
        map.put("ents", EntByPage);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/basic/ent", method = RequestMethod.GET)
    public Ent getEntBasicById(@RequestParam(defaultValue = "2") Integer id){
        return entService.getEntBasicById(id);
    }
}
