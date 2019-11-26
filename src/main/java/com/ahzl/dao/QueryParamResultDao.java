package com.ahzl.dao;

import com.ahzl.model.QueryInstructionResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QueryParamResultDao {
    public void insertQueryParamResult(QueryInstructionResult queryInstructionResultk);
    public List<QueryInstructionResult> findQueryResults(String queryParamId);
}
