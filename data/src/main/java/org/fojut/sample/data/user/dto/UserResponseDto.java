package org.fojut.sample.data.user.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponseDto {

    @Expose @SerializedName("code")
    private String code;
    @Expose @SerializedName("msg")
    private String msg;
    @Expose @SerializedName("data")
    private UserListDto data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserListDto getData() {
        return data;
    }

    public void setData(UserListDto data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
