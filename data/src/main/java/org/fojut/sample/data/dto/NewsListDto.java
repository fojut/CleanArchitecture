package org.fojut.sample.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fojut on 2016/4/20.
 */
public class NewsListDto {

    @Expose @SerializedName("code")
    private int code;
    @Expose @SerializedName("msg")
    private String msg;
    @Expose @SerializedName("newslist")
    private List<NewsDto> newslist;

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

    public List<NewsDto> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsDto> newslist) {
        this.newslist = newslist;
    }
}
