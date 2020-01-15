package org.server.service;

import org.apache.ibatis.annotations.Param;
import org.server.bean.Operation;
import org.server.mapper.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OperationService {
    @Autowired
    OperationMapper operationMapper;

    public List<Operation> getOperationByDate(int eid,String startDate,String endDate){
        return operationMapper.getOperationByDate(eid,startDate,endDate);
    }

    public int addOperation(Operation operation){
        return operationMapper.addOperation(operation);
    }

}
