package com.ahzl.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Org {
    @Getter
    @Setter
    private String id;//
    @Getter
    @Setter
    private String orgId;//
    @Getter
    @Setter
    private String gridCode;//所属网格编号
    @Getter
    @Setter
    private String name;//名称
    @Getter
    @Setter
    private String certificateType;//证件类型
    @Getter
    @Setter
    private String socialCreditCode;//证件号码
    @Getter
    @Setter
    private String regAddress;//注册地址
    @Getter
    @Setter
    private String legalPersonName;//法人
    @Getter
    @Setter
    private String legalPersonCardType;//法人证件类型
    @Getter
    @Setter
    private String legalPersonCardCode;//法人证件号码
    @Getter
    @Setter
    private String phone;//联系电话
    @Getter
    @Setter
    private Date establishDate;//成立日期
    @Getter
    @Setter
    private String brand;//店招店牌
    @Getter
    @Setter
    private String image;//照片
    @Getter
    @Setter
    private String createUserId;//创建者
    @Getter
    @Setter
    private Date createTime;//创建时间
    @Getter
    @Setter
    private String updateUserId;//更新者
    @Getter
    @Setter
    private Date updateTime;//更新时间
    @Getter
    @Setter
    private String establishPartyOrg;//是否成立党组织
    @Getter
    @Setter
    private String industryCategory;//行业分类
    @Getter
    @Setter
    private String active_address;//经营地址

}
