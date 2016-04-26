package org.fojut.sample.presentation.news.presenter;

import android.view.LayoutInflater;

import org.fojut.sample.data.news.dto.NewsListDto;
import org.fojut.sample.domain.base.interactor.DefaultSubscriber;
import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.news.mapper.NewsEntityMapper;
import org.fojut.sample.presentation.news.model.NewsChannelEntity;
import org.fojut.sample.presentation.base.presenter.BasePresenter;
import org.fojut.sample.presentation.base.view.render.LoadDataView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by fojut on 2016/4/20.
 */
@PerActivity
public class NewsListPresenter extends BasePresenter<NewsListPresenter.View> {

    private int newsNum = 20;
    private int newsPage = 1;
    private int selectedPosition = 0;       //Selected ViewPager(Channel) position
    private List<NewsChannelEntity> newsChannelEntityList;

    private UseCase getNewsListUseCase;
    private NewsEntityMapper newsEntityMapper;

    @Inject
    public NewsListPresenter(@Named("getNewsList") UseCase getNewsListUseCase, NewsEntityMapper newsEntityMapper) {
        this.getNewsListUseCase = getNewsListUseCase;
        this.newsEntityMapper = newsEntityMapper;
    }

    @Override
    public void onResume() {
        this.loadNewsList();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.getNewsListUseCase.unsubscribe();
        setView(null);
    }

    /**
     * Load News channel list.
     */
    public void loadNewsChannelList(){
        newsChannelEntityList = new ArrayList<>();
        newsChannelEntityList.add(new NewsChannelEntity("社会", "social", "social", 0));
        newsChannelEntityList.add(new NewsChannelEntity("体育", "tiyu", "tiyu", 1));
        newsChannelEntityList.add(new NewsChannelEntity("娱乐", "huabian", "newtop", 2));
        newsChannelEntityList.add(new NewsChannelEntity("科技", "keji", "keji", 3));
        Collections.sort(newsChannelEntityList, new Comparator<NewsChannelEntity>() {
            @Override
            public int compare(NewsChannelEntity entity1, NewsChannelEntity entity2) {
                return entity1.getPriority() - entity2.getPriority();
            }
        });
    }

    /**
     * Get News Channel View List
     */
    public List<android.view.View> getNewsChannelViewList(){
        List<android.view.View> viewList = new ArrayList<>();
        for (int i=0; i<getNewsChannelEntityList().size(); i++){
            viewList.add(LayoutInflater.from(getView().context()).inflate(getView().getViewPagerLayoutId(), null));
        }
        return viewList;
    }

    /**
     * Get News Channel Title List
     */
    public List<String> getNewsChannelTitleList(){
        List<String> titleList = new ArrayList<>();
        for (NewsChannelEntity newsChannelEntity : getNewsChannelEntityList()){
            titleList.add(newsChannelEntity.getTitle());
        }
        return titleList;
    }

    /**
     * Load news list.
     */
    public void loadNewsList(){
        getView().showLoading();
        this.getNewsList();
    }

    private void getNewsList(){
        NewsChannelEntity selectedChannel = newsChannelEntityList.get(selectedPosition);
        this.getNewsListUseCase.execute(new NewsListSubscriber(), selectedChannel.getUrlType(), selectedChannel.getUrlPath(), newsNum, newsPage);
    }


    private final class NewsListSubscriber extends DefaultSubscriber<NewsListDto> {

        @Override public void onCompleted() {
            getView().hideLoading();
        }

        @Override public void onError(Throwable e) {
            getView().showError(e.getMessage());
        }

        @Override public void onNext(NewsListDto newsListDto) {
            getView().loadData(newsEntityMapper.transform(newsListDto.getNewslist()));
        }
    }

    public interface View<D> extends LoadDataView<D> {
        int getViewPagerLayoutId();
        void initViewPages();
        void initTabLayout();
    }


    /*
     * ############  Getter and Setter Start ##############
     */

    public int getNewsNum() {
        return newsNum;
    }

    public void setNewsNum(int newsNum) {
        this.newsNum = newsNum;
    }

    public int getNewsPage() {
        return newsPage;
    }

    public void setNewsPage(int newsPage) {
        this.newsPage = newsPage;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public List<NewsChannelEntity> getNewsChannelEntityList() {
        return newsChannelEntityList;
    }

    public void setNewsChannelEntityList(List<NewsChannelEntity> newsChannelEntityList) {
        this.newsChannelEntityList = newsChannelEntityList;
    }

    /*
     * ############  Getter and Setter End ##############
     */
}
