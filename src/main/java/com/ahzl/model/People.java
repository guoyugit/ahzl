package com.ahzl.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class People {
    @Getter
    @Setter
    private String id;//
    @Getter
    @Setter
    private String peopleId;// 人口id
    @Getter
    @Setter
    private String doorId;// 所属户室id
    @Getter
    @Setter
    private String familyId;//分户id
    @Getter
    @Setter
    private String name;//姓名
    @Getter
    @Setter
    private String sex;//性别
    @Getter
    @Setter
    private String cardType;//证件类型
    @Getter
    @Setter
    private String cardCode;//证件号码
    @Getter
    @Setter
    private Date birthday;//生日
    @Getter
    @Setter
    private String nation;//民族
    @Getter
    @Setter
    private String marriage;//婚姻状况
    @Getter
    @Setter
    private String type;//人口类型
    @Getter
    @Setter
    private String eduLevel;//教育状况
    @Getter
    @Setter
    private String politic;// 政治面貌
    @Getter
    @Setter
    private String regAddress;//户籍地址
    @Getter
    @Setter
    private int age;//年龄
    @Getter
    @Setter
    private String phone;//联系电话
    @Getter
    @Setter
    private String org;//工作单位
    @Getter
    @Setter
    private String profession;// 职业
    @Getter
    @Setter
    private String religion;//宗教信仰
    @Getter
    @Setter
    private String gridCode;//所属网格
    @Getter
    @Setter
    private String militaryStatus;//是否服兵役
    @Getter
    @Setter
    private String isLivingAlone;//是否独居
    @Getter
    @Setter
    private String isDisability;//是否残疾
    @Getter
    @Setter
    private String isTeen;//是否年轻人
    @Getter
    @Setter
    private String isElder;//是否老年人
    @Getter
    @Setter
    private String prEquals;//是否人户一致
    @Getter
    @Setter
    private String addType;//新增类型
    @Getter
    @Setter
    private String masterRelation;// 与户主关系
    @Getter
    @Setter
    private String createUserId;//创建者id
    @Getter
    @Setter
    private Date createTime;//创建时间
    @Getter
    @Setter
    private String updateUserId;// 更新者id
    @Getter
    @Setter
    private Date updateTime;//更新时间
}
