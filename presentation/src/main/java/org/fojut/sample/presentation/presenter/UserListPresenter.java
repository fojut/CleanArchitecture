package org.fojut.sample.presentation.presenter;

import org.fojut.sample.data.entity.UserResponseEntity;
import org.fojut.sample.domain.interactor.base.DefaultSubscriber;
import org.fojut.sample.domain.interactor.base.UseCase;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.mapper.UserModelDataMapper;
import org.fojut.sample.presentation.presenter.base.BasePresenter;
import org.fojut.sample.presentation.presenter.extra.HasRenderView;
import org.fojut.sample.presentation.view.render.RenderView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class UserListPresenter implements BasePresenter, HasRenderView<RenderView> {

    private RenderView renderView;

    private final UseCase getUserListUseCase;
    private final UserModelDataMapper userModelDataMapper;

    @Inject
    public UserListPresenter(@Named("userList") UseCase getUserListUseCase, UserModelDataMapper userModelDataMapper) {
        this.getUserListUseCase = getUserListUseCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    @Override
    public void setRenderView(RenderView renderView) {
        this.renderView = renderView;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.getUserListUseCase.unsubscribe();
        this.renderView = null;
    }

    /**
     * Loads all users.
     */
    public void loadUserList() {
        this.renderView.showLoading();
        this.getUserList();
    }

    private void getUserList() {
        this.getUserListUseCase.execute(new UserListSubscriber());
    }

    private final class UserListSubscriber extends DefaultSubscriber<UserResponseEntity> {

        @Override public void onCompleted() {
            renderView.hideLoading();
        }

        @Override public void onError(Throwable e) {
            renderView.showError(e.getMessage());
        }

        @Override public void onNext(UserResponseEntity userResponseEntity) {
            renderView.renderView(userModelDataMapper.transform(userResponseEntity.getData().getList()));
        }
    }
}
