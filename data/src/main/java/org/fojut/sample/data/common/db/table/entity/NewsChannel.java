package org.fojut.sample.data.common.db.table.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import org.fojut.sample.data.common.db.table.NewsChannelTable;

/**
 * Created by fojut on 2016/4/29.
 */
@StorIOSQLiteType(table = NewsChannelTable.TABLE_NAME)
public class NewsChannel {

    /**
     * If object was not inserted into db, id will be null
     */
    @Nullable
    @StorIOSQLiteColumn(name = NewsChannelTable.COLUMN_ID, key = true)
    int id;

    @NonNull
    @StorIOSQLiteColumn(name = NewsChannelTable.COLUMN_TITLE)
    String title;

    @NonNull
    @StorIOSQLiteColumn(name = NewsChannelTable.COLUMN_URL_TYPE)
    String urlType;

    @NonNull
    @StorIOSQLiteColumn(name = NewsChannelTable.COLUMN_URL_PATH)
    String urlPath;

    @NonNull
    @StorIOSQLiteColumn(name = NewsChannelTable.COLUMN_PRIORITY)
    int priority;

    // leave default constructor for AutoGenerated code!
    public NewsChannel() {
    }

    public NewsChannel(int id, @NonNull String title, @NonNull String urlType, @NonNull String urlPath, @NonNull int priority) {
        this.id = id;
        this.title = title;
        this.urlType = urlType;
        this.urlPath = urlPath;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "NewsChannelDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", urlType='" + urlType + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", priority=" + priority +
                '}';
    }
}
