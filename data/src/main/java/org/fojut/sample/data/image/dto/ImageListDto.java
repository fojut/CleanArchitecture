package org.fojut.sample.data.image.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

/**
 * Created by fojut on 2016/5/4.
 */
public class ImageListDto {

    @Expose @SerializedName("code")
    private int code;
    @Expose @SerializedName("msg")
    private String msg;
    @Expose @SerializedName("newslist")
    private List<ImageDto> imagelist;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ImageDto> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<ImageDto> imagelist) {
        this.imagelist = imagelist;
    }


    @Override
    public String toString() {
        return "ImageListDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", imagelist=" + imagelist +
                '}';
    }
}
