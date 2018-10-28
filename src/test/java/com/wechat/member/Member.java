package com.wechat.member;

import java.util.List;

public class Member {
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Integer> getOrder() {
        return order;
    }

    public void setOrder(List<Integer> order) {
        this.order = order;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isIsleader() {
        return isleader;
    }

    public void setIsleader(boolean isleader) {
        this.isleader = isleader;
    }



    public boolean isTo_invite() {
        return to_invite;
    }

    public void setTo_invite(boolean to_invite) {
        this.to_invite = to_invite;
    }

    public String getExternal_profile() {
        return external_profile;
    }

    public void setExternal_profile(String external_profile) {
        this.external_profile = external_profile;
    }

    public String getExternal_position() {
        return external_position;
    }

    public void setExternal_position(String external_position) {
        this.external_position = external_position;
    }
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getExpect() {
        return expect;
    }
    public void setExpect(int expect) {
        this.expect = expect;
    }

    private String userid;
    private String name;
    private List<Integer> department;
    private  String alias;
    private String mobile;
    private List<Integer> order;
    String position;
    //todo 性别就为1或者2的应该怎么设置呢？
    private int gender;
    private String email;
    private String telephone;
    private  boolean isleader;
    private  boolean enable;
    private  boolean to_invite;
    private String external_profile;
    private String external_position;
    private int expect;
}

