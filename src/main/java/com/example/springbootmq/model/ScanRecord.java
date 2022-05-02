package com.example.springbootmq.model;

import java.io.Serializable;

public class ScanRecord implements Serializable {
    private String smqy_id;
    private String id;
    private String name_sm4;//扫码人名称
    private String color;//扫码人码色 0：绿 1：黄 2：红
    private String userid;//扫码人编码
    private String scantime;//扫码时间
    private String scanjwd;//空字段
    private String scanaddress;//扫码地址
    private String scanmethod;//扫码方式
    private String scantype;//扫码类型
    private String companycode;//制码单位类型
    private String companyremark;//场所码名称
    private String companytype;//制码单位类型
    private String card_sm4;//扫码人证件码
    private String companyname;//制码单位名称
    private String isforeigner;//是否为外国人
    private String fu_qhdm;//场所码所在区
    private String dsjzx_taskid;//数据任务编码

    public String getSmqy_id() {
        return smqy_id;
    }

    public void setSmqy_id(String smqy_id) {
        this.smqy_id = smqy_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_sm4() {
        return name_sm4;
    }

    public void setName_sm4(String name_sm4) {
        this.name_sm4 = name_sm4;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getScantime() {
        return scantime;
    }

    public void setScantime(String scantime) {
        this.scantime = scantime;
    }

    public String getScanjwd() {
        return scanjwd;
    }

    public void setScanjwd(String scanjwd) {
        this.scanjwd = scanjwd;
    }

    public String getScanmethod() {
        return scanmethod;
    }

    public void setScanmethod(String scanmethod) {
        this.scanmethod = scanmethod;
    }

    public String getScantype() {
        return scantype;
    }

    public void setScantype(String scantype) {
        this.scantype = scantype;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCard_sm4() {
        return card_sm4;
    }

    public void setCard_sm4(String card_sm4) {
        this.card_sm4 = card_sm4;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getIsforeigner() {
        return isforeigner;
    }

    public void setIsforeigner(String isforeigner) {
        this.isforeigner = isforeigner;
    }

    public String getFu_qhdm() {
        return fu_qhdm;
    }

    public void setFu_qhdm(String fu_qhdm) {
        this.fu_qhdm = fu_qhdm;
    }

    public String getDsjzx_taskid() {
        return dsjzx_taskid;
    }

    public void setDsjzx_taskid(String dsjzx_taskid) {
        this.dsjzx_taskid = dsjzx_taskid;
    }

    public String getScanaddress() {
        return scanaddress;
    }

    public void setScanaddress(String scanaddress) {
        this.scanaddress = scanaddress;
    }

    public String getCompanyremark() {
        return companyremark;
    }

    public void setCompanyremark(String companyremark) {
        this.companyremark = companyremark;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }
}
