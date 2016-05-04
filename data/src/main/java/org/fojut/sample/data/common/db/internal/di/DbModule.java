package org.fojut.sample.data.common.db.internal.di;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import org.fojut.sample.data.common.db.table.entity.NewsChannel;
import org.fojut.sample.data.common.db.helper.DbOpenHelper;
import org.fojut.sample.data.common.db.table.entity.NewsChannelSQLiteTypeMapping;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fojut on 2016/4/29.
 */
@Module
public class DbModule {

    @Provides @NonNull @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(NewsChannel.class, new NewsChannelSQLiteTypeMapping())
                .build();
    }

    @Provides @NonNull @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }
}
