package org.server.controller;

import org.apache.ibatis.annotations.Param;
import org.server.bean.Ent;
import org.server.bean.Operation;
import org.server.bean.RespBean;
import org.server.service.EntService;
import org.server.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    OperationService operationService;
    @Autowired
    EntService entService;

    @RequestMapping(value = "/statistics/ent",method = RequestMethod.GET)
    public List<Operation> getOperationByDate(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "1996-01-01") String startDate, @RequestParam(defaultValue ="2050-01-01") String endDate){
        int eid = entService.getEntBasicByUsername(username).getId();
        return operationService.getOperationByDate(eid,startDate,endDate);
    }

    @RequestMapping(value = "/statistics/ent",method = RequestMethod.POST)
    public RespBean updateOperation(int eid,int productOutput,int outputValue,int tax,int energyUsed,String reportDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(operationService.getOperationByDate(eid,reportDate,reportDate)!=null) return new RespBean("error", "该月份已有数据!");
        Operation operation = new Operation(eid,productOutput,outputValue,tax,energyUsed,simpleDateFormat.parse(reportDate));
        if(operationService.addOperation(operation)==1){
            return new RespBean("success", "更新成功!");
        }
        return new RespBean("error", "更新失败!");
    }
}
