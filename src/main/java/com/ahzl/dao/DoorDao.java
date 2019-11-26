package com.ahzl.dao;

import com.ahzl.model.Door;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DoorDao {
    //存入户室数据
    public  void insertDoor(Door door);
    // //查询所有doorId
    public List<String> selectAllDoorId();
    public List<String> selectNeedUpdateBuildId();
    public List<String> selectAllNeedDoorId();
}
