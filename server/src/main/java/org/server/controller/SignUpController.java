package org.server.controller;

import org.server.bean.RespBean;
import org.server.service.EntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    EntService entService;

    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    public int isExistUsername(String username){
        return entService.isExistUsername(username);
    }

    @RequestMapping(value = "/ent",method = RequestMethod.POST)
    public RespBean entSignUp(String username,String password){
        int flag = entService.entSignUp(username,password);
        if(flag == 1) return new RespBean("success", "注册成功!");
        else if (flag == -1)  return new RespBean("error", "用户名重复，注册失败!");
        return new RespBean("error", "注册失败!");
    }

}
