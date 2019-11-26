package com.ahzl.model;

import lombok.Getter;
import lombok.Setter;

public class Door {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String doorId;//户室id

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    private String gridCode;//所属网络编号
    private String buildingId;//所属建筑id
    private String noSuffix;//室号后缀
    private String no;//室号
    private String usage;// 用途
    private String area;// 面积
    private String usageForm;//使用形式
    private String roomCount;//房间数量
    private String ownerName;//房主姓名
    private String ownerPhone;//房主手机号
    private String ownerCardtype;//房主证件类型
    private String onerCardCode;//房主证件号码
    private String propertyNote;//产权说明
    private String address;//地址
    private String status;//状态
    private String orgId;//所在处法人id
    private int peopleCount;//人员数量
    private String partyMemberCount;//党员数量
    private String unitNo;//单元号
    private String unitSuffix;//单元后缀
    private String unitIndex;//单元索引
    private String floorNo;//楼层号
    private String floorSuffix;//楼层后缀
    private String floorIndex;//楼层索引
    private String doorIdasdasd;//楼层索引

    public String getDoorIdasdasd() {
        return doorIdasdasd;
    }

    public void setDoorIdasdasd(String doorIdasdasd) {
        this.doorIdasdasd = doorIdasdasd;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getGridCode() {
        return gridCode;
    }

    public void setGridCode(String gridCode) {
        this.gridCode = gridCode;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getNoSuffix() {
        return noSuffix;
    }

    public void setNoSuffix(String noSuffix) {
        this.noSuffix = noSuffix;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsageForm() {
        return usageForm;
    }

    public void setUsageForm(String usageForm) {
        this.usageForm = usageForm;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        this.roomCount = roomCount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerCardtype() {
        return ownerCardtype;
    }

    public void setOwnerCardtype(String ownerCardtype) {
        this.ownerCardtype = ownerCardtype;
    }

    public String getOnerCardCode() {
        return onerCardCode;
    }

    public void setOnerCardCode(String onerCardCode) {
        this.onerCardCode = onerCardCode;
    }

    public String getPropertyNote() {
        return propertyNote;
    }

    public void setPropertyNote(String propertyNote) {
        this.propertyNote = propertyNote;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getPartyMemberCount() {
        return partyMemberCount;
    }

    public void setPartyMemberCount(String partyMemberCount) {
        this.partyMemberCount = partyMemberCount;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getUnitSuffix() {
        return unitSuffix;
    }

    public void setUnitSuffix(String unitSuffix) {
        this.unitSuffix = unitSuffix;
    }

    public String getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(String unitIndex) {
        this.unitIndex = unitIndex;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getFloorSuffix() {
        return floorSuffix;
    }

    public void setFloorSuffix(String floorSuffix) {
        this.floorSuffix = floorSuffix;
    }

    public String getFloorIndex() {
        return floorIndex;
    }

    public void setFloorIndex(String floorIndex) {
        this.floorIndex = floorIndex;
    }
}
