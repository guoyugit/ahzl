package com.ahzl.dao;

import com.ahzl.model.Build;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BuildDao {
    //存数据
    public  void insertBuild(Build build);
    //查询所有BuildId
    public List<String> selectAllBuildId();

    public List<String> selectAllBuildIdForUpdate();

    //更新数据
    public  void updateBuild(Build build);

    //根据id更新数据
    public  void updateBuildById(Build build);

}
