package org.fojut.sample.data.common.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import org.fojut.sample.data.common.db.table.NewsChannelTable;

public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(@NonNull Context context) {
        super(context, "CleanArchitecture_DB", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(NewsChannelTable.getCreateTableQuery());

        //Preload data for news channel tabs.
        db.execSQL(NewsChannelTable.preLoadData(1, "社会", "social", "social", 0));
        db.execSQL(NewsChannelTable.preLoadData(2, "体育", "tiyu", "tiyu", 1));
        db.execSQL(NewsChannelTable.preLoadData(3, "娱乐", "huabian", "newtop", 2));
        db.execSQL(NewsChannelTable.preLoadData(4, "科技", "keji", "keji", 3));
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // no impl
    }


}
