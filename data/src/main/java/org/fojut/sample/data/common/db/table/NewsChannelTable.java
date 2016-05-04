package org.fojut.sample.data.common.db.table;

import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * Created by fojut on 2016/5/3.
 */
public class NewsChannelTable {

    public static final String TABLE_NAME = "TB_NEWS_CHANNEL";

    public static final String COLUMN_ID = "_ID";

    public static final String COLUMN_TITLE = "TITLE";

    public static final String COLUMN_URL_TYPE = "URL_TYPE";

    public static final String COLUMN_URL_PATH = "URL_PATH";

    public static final String COLUMN_PRIORITY = "PRIORITY";

    // This is just class with Meta Data, we don't need instances
    private NewsChannelTable() {
        throw new IllegalStateException("No instances please");
    }

    public static String getCreateTableQuery(){
        return "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_URL_TYPE + " TEXT NOT NULL, "
                + COLUMN_URL_PATH + " TEXT NOT NULL, "
                + COLUMN_PRIORITY + " INTEGER NOT NULL"
                + ");";
    }

    public static String preLoadData(int id, String title, String urlType, String urlPath, int priority){
        return "INSERT INTO " + TABLE_NAME + " VALUES " + " ("
                + id + ", '" + title + "', '" + urlType + "', '"
                + urlPath + "', " + priority + ");";
    }
}
