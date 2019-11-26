package com.ahzl.dao;


import com.ahzl.model.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PeopleDao {
        //存入人口数据
        public void insertPeople(People people);

        //id
        public List<String> selectAllPeopleId();
}
