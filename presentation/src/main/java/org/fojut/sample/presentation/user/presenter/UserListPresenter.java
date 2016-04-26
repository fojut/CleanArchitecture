package org.fojut.sample.presentation.user.presenter;

import org.fojut.sample.data.user.dto.UserResponseDto;
import org.fojut.sample.domain.base.interactor.DefaultSubscriber;
import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.user.mapper.UserEntityMapper;
import org.fojut.sample.presentation.base.presenter.BasePresenter;
import org.fojut.sample.presentation.base.view.render.LoadDataView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class UserListPresenter extends BasePresenter<UserListPresenter.View> {

    private final UseCase getUserListUseCase;
    private final UserEntityMapper userEntityMapper;

    @Inject
    public UserListPresenter(@Named("userList") UseCase getUserListUseCase, UserEntityMapper userEntityMapper) {
        this.getUserListUseCase = getUserListUseCase;
        this.userEntityMapper = userEntityMapper;
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
        setView(null);
    }

    /**
     * Loads all users.
     */
    public void loadUserList() {
        getView().showLoading();
        this.getUserList();
    }

    private void getUserList() {
        this.getUserListUseCase.execute(new UserListSubscriber());
    }

    private final class UserListSubscriber extends DefaultSubscriber<UserResponseDto> {

        @Override public void onCompleted() {
            getView().hideLoading();
        }

        @Override public void onError(Throwable e) {
            getView().showError(e.getMessage());
        }

        @Override public void onNext(UserResponseDto userResponseDto) {
            getView().loadData(userEntityMapper.transform(userResponseDto.getData().getList()));
        }
    }

    public interface View<D> extends LoadDataView<D> {

    }
}
