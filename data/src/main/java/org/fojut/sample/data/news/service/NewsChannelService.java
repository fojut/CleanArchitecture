package org.fojut.sample.data.news.service;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import org.fojut.sample.data.base.service.BaseDbService;
import org.fojut.sample.data.common.db.table.NewsChannelTable;
import org.fojut.sample.data.common.db.table.entity.NewsChannel;

import java.util.List;

import rx.Observable;

/**
 * Created by fojut on 2016/5/3.
 */
public class NewsChannelService extends BaseDbService {

    public NewsChannelService(StorIOSQLite storIOSQLite) {
        super(storIOSQLite);
    }

    private static volatile NewsChannelService instance;

    public static NewsChannelService getInstance(StorIOSQLite storIOSQLite) {
        if (instance == null) {
            synchronized (NewsChannelService.class) {
                if (instance == null) {
                    instance = new NewsChannelService(storIOSQLite);
                }
            }
        }
        return instance;
    }

    public Observable<List<NewsChannel>> getNewsChannelList(){
        return getStorIOSQLite().get().listOfObjects(NewsChannel.class)
                .withQuery(Query.builder().table(NewsChannelTable.TABLE_NAME).build())
                .prepare().asRxObservable();
    }
}
