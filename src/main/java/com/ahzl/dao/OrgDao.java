package com.ahzl.dao;

import com.ahzl.model.Org;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrgDao {
    public void insertOrg(Org org);
    //id
    public List<String> selectAllOrgId();
}
