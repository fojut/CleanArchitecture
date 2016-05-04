package org.fojut.sample.data.base.service;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;

/**
 * Created by fojut on 2016/5/4.
 */
public class BaseDbService {

    private StorIOSQLite storIOSQLite;

    public BaseDbService(StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    public StorIOSQLite getStorIOSQLite() {
        return storIOSQLite;
    }
}
