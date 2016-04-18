package org.fojut.sample.data.service;

import org.fojut.sample.data.api.UserApi;
import org.fojut.sample.data.entity.UserResponseEntity;
import org.fojut.sample.data.service.base.BaseApiService;

import java.util.HashMap;
import rx.Observable;

public class UserApiService extends BaseApiService<UserApi> {

    private static class SingletonHolder{
        private static UserApiService instance = new UserApiService();
    }

    public static UserApiService getInstance(){
        return SingletonHolder.instance;
    }

    public Observable<UserResponseEntity> getUserList(){
        HashMap<String, String> params = new HashMap<>(2);
        params.put("page", "1");
        params.put("pages", "20");
        return getService(UserApi.class).getUserList(params);
    }
}
