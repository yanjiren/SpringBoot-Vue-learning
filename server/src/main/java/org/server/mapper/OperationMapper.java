package org.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.server.bean.Operation;

import java.util.Date;
import java.util.List;

@Mapper
public interface OperationMapper {
    List<Operation> getOperationByDate(@Param("eid") int eid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    int addOperation(@Param("operation") Operation operation);
}
