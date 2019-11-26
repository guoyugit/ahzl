package com.ahzl.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Build {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String buildingId;//楼栋id
    @Getter
    @Setter
    private String gateId;//所属门牌id
    @Getter
    @Setter
    private String communityName;//所属小区名称
    @Getter
    @Setter
    private String streetId;//所属街道路巷id
    @Getter
    @Setter
    private String streetName;//所属街道路巷名称
    @Getter
    @Setter
    private String gridCode;//网络编号
    @Getter
    @Setter
    private String type;//建筑类型
    @Getter
    @Setter
    private String address;//地址
    @Getter
    @Setter
    private String x;//x坐标
    @Getter
    @Setter
    private String y;//y坐标
    @Getter
    @Setter
    private String createUserId;//创建者id
    @Getter
    @Setter
    private Date createTime;//创建时间
    @Getter
    @Setter
    private String updateUserId;//更新者id
    @Getter
    @Setter
    private Date updateTime;//更新时间
    @Getter
    @Setter
    private String gateNo;//门牌号
    @Getter
    @Setter
    private String gateNoSuffix;//门牌号后缀
    @Getter
    @Setter
    private String gateNoPreffix;//门牌号前缀
    @Getter
    @Setter
    private String gateSubNo;//门牌副号
    @Getter
    @Setter
    private String gateSubNoSuffix;//门牌副号前缀
    @Getter
    @Setter
    private String gatePhoto;//门牌照片
    @Getter
    @Setter
    private String unitCount;//单元数量
    @Getter
    @Setter
    private String floorCountUp;//地上楼层数量
    @Getter
    @Setter
    private String doorCountPerFloorDefault;//地下楼层数量
    @Getter
    @Setter
    private String isMultiple;//是否多户
    @Getter
    @Setter
    private String checkStage;//核查状态
    @Getter
    @Setter
    private String checkCount;//核查次数
    @Getter
    @Setter
    private String buildingPhoto;//建筑物图片
}
