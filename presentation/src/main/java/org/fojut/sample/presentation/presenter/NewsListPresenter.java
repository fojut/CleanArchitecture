package org.fojut.sample.presentation.presenter;

import org.fojut.sample.data.dto.NewsListDto;
import org.fojut.sample.domain.interactor.GetNewsListUseCase;
import org.fojut.sample.domain.interactor.base.DefaultSubscriber;
import org.fojut.sample.domain.interactor.base.UseCase;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.mapper.NewsEntityMapper;
import org.fojut.sample.presentation.presenter.base.BasePresenter;
import org.fojut.sample.presentation.presenter.extra.HasRenderView;
import org.fojut.sample.presentation.view.render.RenderView;

import javax.inject.Inject;

/**
 * Created by fojut on 2016/4/20.
 */
@PerActivity
public class NewsListPresenter implements BasePresenter, HasRenderView<RenderView> {

    private RenderView renderView;

    private UseCase getNewsListUseCase;
    private NewsEntityMapper newsEntityMapper;

    @Inject
    public NewsListPresenter(UseCase getNewsListUseCase, NewsEntityMapper newsEntityMapper) {
        this.getNewsListUseCase = getNewsListUseCase;
        this.newsEntityMapper = newsEntityMapper;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.getNewsListUseCase.unsubscribe();
        this.renderView = null;
    }

    @Override
    public void setRenderView(RenderView renderView) {
        this.renderView = renderView;
    }

    /**
     * Load news list.
     */
    public void loadNewsList(String type, String path, int num, int page){
        this.renderView.showLoading();
        this.getNewsList(type, path, num, page);
    }

    private void getNewsList(String type, String path, int num, int page){
        this.getNewsListUseCase.execute(new NewsListSubscriber(), type, path, num, page);
    }

    private final class NewsListSubscriber extends DefaultSubscriber<NewsListDto> {

        @Override public void onCompleted() {
            renderView.hideLoading();
        }

        @Override public void onError(Throwable e) {
            renderView.showError(e.getMessage());
        }

        @Override public void onNext(NewsListDto newsListDto) {
            renderView.renderView(newsEntityMapper.transform(newsListDto.getNewslist()));
        }
    }
}
