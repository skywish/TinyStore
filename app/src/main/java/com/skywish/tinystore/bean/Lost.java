package com.skywish.tinystore.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by skywish on 2015/3/17.
 */
public class Lost extends BmobObject {

    private String describe;
    private String phone;
    private String title;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
