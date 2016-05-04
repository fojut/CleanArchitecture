package org.fojut.sample.domain.news.interactor;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import org.fojut.sample.data.news.service.NewsChannelService;
import org.fojut.sample.domain.base.executor.PostExecutionThread;
import org.fojut.sample.domain.base.executor.ThreadExecutor;
import org.fojut.sample.domain.base.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by fojut on 2016/5/3.
 */
public class GetNewsChannelListUseCase extends UseCase {

    private final NewsChannelService newsChannelService;

    @Inject
    protected GetNewsChannelListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, StorIOSQLite storIOSQLite) {
        super(threadExecutor, postExecutionThread);
        this.newsChannelService = NewsChannelService.getInstance(storIOSQLite);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return newsChannelService.getNewsChannelList();
    }
}
