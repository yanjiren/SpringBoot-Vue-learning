package org.server.controller;

import org.server.bean.Ent;
import org.server.bean.RespBean;
import org.server.service.EntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/enterprise")
public class EntBasicController {

    @Autowired
    EntService entService;

    @RequestMapping(value = "/all/ent", method = RequestMethod.GET)
    public Map<String, Object> getEntByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String keywords) {
        Map<String, Object> map = new HashMap<>();
        List<Ent> EntByPage = entService.getEntByPage(page, size, keywords);
        int count = entService.getCountByKeywords(keywords);
        map.put("ents", EntByPage);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/basic/ent", method = RequestMethod.GET)
    public Ent getEntBasicByUsername(@RequestParam(defaultValue = "") String username){
        return entService.getEntBasicByUsername(username);
    }

    @RequestMapping(value = "/basic/ent",method = RequestMethod.PUT)
    public RespBean updateEnt(Ent ent){
        if(entService.updateEnt(ent)==1) {
            return new RespBean("success", "更新成功!");
        }
        return new RespBean("error", "更新失败!");
    }

    @RequestMapping(value = "/all/ent",method = RequestMethod.PUT)
    public RespBean adminUpdateEnt(Ent ent){
        if(entService.updateEnt(ent)==1) {
            return new RespBean("success", "更新成功!");
        }
        return new RespBean("error", "更新失败!");
    }

    @RequestMapping(value = "/all/ent/{ids}",method = RequestMethod.DELETE)
    public RespBean deleteEntById(@PathVariable String ids){
        if(entService.deleteEntById(ids)) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
