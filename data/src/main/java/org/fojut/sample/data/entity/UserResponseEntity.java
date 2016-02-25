package org.fojut.sample.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponseEntity {

    @Expose @SerializedName("code")
    private String code;
    @Expose @SerializedName("msg")
    private String msg;
    @Expose @SerializedName("data")
    private UserListEntity data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserListEntity getData() {
        return data;
    }

    public void setData(UserListEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
