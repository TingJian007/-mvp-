package com.example.searchphone.model;

/**
 * Created by 许东 on 2018/8/8.
 */

public class Phone {

    String telString;
    String province;
    String catName;
    String carrier;

    public String getTelString() {
        return telString;
    }

    public void setTelString(String telString) {
        this.telString = telString;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
