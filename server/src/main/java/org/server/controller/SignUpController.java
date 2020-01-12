package org.server.controller;

import org.server.service.EntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class UsernameController {
    @Autowired
    EntService entService;

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @RequestMapping(value = "/username",method = RequestMethod.GET)
    public int isExistUsername(String username){
        return entService.isExistUsername(username);
    }
}
