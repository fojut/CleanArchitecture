package org.fojut.sample.presentation.news.presenter;

import org.fojut.sample.data.common.db.table.entity.NewsChannel;
import org.fojut.sample.domain.base.interactor.DefaultSubscriber;
import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.base.presenter.BasePresenter;
import org.fojut.sample.presentation.base.view.render.LoadView;
import org.fojut.sample.presentation.news.mapper.NewsChannelEntityMapper;
import org.fojut.sample.presentation.news.model.NewsChannelEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by fojut on 2016/5/3.
 */
@PerActivity
public class NewsChannelPresenter extends BasePresenter<NewsChannelPresenter.View> {

    private static final String TAG = NewsChannelPresenter.class.getSimpleName();

    private UseCase getNewsChannelListUseCase;
    private NewsChannelEntityMapper newsChannelEntityMapper;

    @Inject
    public NewsChannelPresenter(@Named("getNewsChannelList") UseCase getNewsChannelListUseCase, NewsChannelEntityMapper newsChannelEntityMapper) {
        this.getNewsChannelListUseCase = getNewsChannelListUseCase;
        this.newsChannelEntityMapper = newsChannelEntityMapper;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.getNewsChannelListUseCase.unsubscribe();
        setView(null);
    }

    /**
     * Get News channels from database.
     */
    public void getNewsChannels(){
        this.getNewsChannelListUseCase.executeDb(new NewsChannelListSubscriber());
    }

    private final class NewsChannelListSubscriber extends DefaultSubscriber<List<NewsChannel>> {

        @Override public void onCompleted() {

        }

        @Override public void onError(Throwable e) {
            getView().showError(e.getMessage());
        }

        @Override public void onNext(List<NewsChannel> newsChannelList) {
            getView().loadChannels(newsChannelEntityMapper.transform(newsChannelList));
        }
    }

    public interface View extends LoadView {
        void loadChannels(List<NewsChannelEntity> newsChannels);
    }
}
