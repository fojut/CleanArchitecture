package org.fojut.sample.presentation.news.model;

/**
 * Created by fojut on 2016/4/21.
 */
public class NewsChannelEntity {

    private String title;
    private String urlType;
    private String urlPath;
    private int priority;

    public NewsChannelEntity(String title, String urlType, String urlPath, int priority) {
        this.title = title;
        this.urlType = urlType;
        this.urlPath = urlPath;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
