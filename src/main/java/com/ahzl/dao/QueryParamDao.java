package com.ahzl.dao;

import com.ahzl.model.QueryInstruction;
import com.ahzl.model.QueryInstructionResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface QueryParamDao {
    public void insertQueryParam(QueryInstruction queryInstruction);

    public List<QueryInstruction> queryInstructionsByPage(Map<String,Object> page);

    public QueryInstruction queryInstructionsById(String id);

    public List<QueryInstructionResult> findQueryParamResults(Integer pageNum, Integer pageSize);
}
