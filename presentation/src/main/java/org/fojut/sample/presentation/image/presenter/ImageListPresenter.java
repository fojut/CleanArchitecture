package org.fojut.sample.presentation.image.presenter;

import org.fojut.sample.data.image.dto.ImageListDto;
import org.fojut.sample.domain.base.interactor.DefaultSubscriber;
import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.base.presenter.BasePresenter;
import org.fojut.sample.presentation.base.view.render.LoadDataView;
import org.fojut.sample.presentation.image.mapper.ImageEntityMapper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by fojut on 2016/5/4.
 */
@PerActivity
public class ImageListPresenter extends BasePresenter<ImageListPresenter.View> {

    private int pageNum = 20;

    private UseCase getImageListUseCase;
    private ImageEntityMapper imageEntityMapper;

    @Inject
    public ImageListPresenter(@Named("getImageList") UseCase getImageListUseCase, ImageEntityMapper imageEntityMapper) {
        this.getImageListUseCase = getImageListUseCase;
        this.imageEntityMapper = imageEntityMapper;
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
        this.getImageListUseCase.unsubscribe();
        setView(null);
    }

    /**
     * Load image list.
     */
    public void loadImageList(){
        getView().showLoading();
        this.getImageList();
    }

    private void getImageList(){
        this.getImageListUseCase.execute(new ImageListSubscriber(), pageNum);
    }

    private final class ImageListSubscriber extends DefaultSubscriber<ImageListDto> {

        @Override public void onCompleted() {
            getView().hideLoading();
        }

        @Override public void onError(Throwable e) {
            getView().showError(e.getMessage());
        }

        @Override public void onNext(ImageListDto imageListDto) {
            getView().loadData(imageEntityMapper.transform(imageListDto.getImagelist()));
        }
    }

    public interface View<D> extends LoadDataView<D> {

    }
}
