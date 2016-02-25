package org.fojut.sample.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.fojut.sample.data.api.UserApi;
import org.fojut.sample.data.client.OkHttpClient;
import org.fojut.sample.data.constant.UrlConstant;
import org.fojut.sample.data.entity.UserEntity;
import org.fojut.sample.data.entity.UserResponseEntity;


import java.util.HashMap;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import rx.Observable;

public class UserApiService {

    private final UserApi userApi;

    private static class SingletonHolder{
        private static UserApiService instance = new UserApiService();
    }

    public static UserApiService getInstance(){
        return SingletonHolder.instance;
    }

    public UserApiService() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override public void intercept(RequestFacade request) {
                request.addHeader("Accept-Encoding", "application/json");
            }
        };

        Gson gson = new GsonBuilder()//
                .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .serializeNulls().create();

        RestAdapter userApiRestAdapter = new RestAdapter.Builder()
                .setEndpoint(UrlConstant.SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("_Data_Log"))
                .setClient(OkHttpClient.getInstance())
                .setConverter(new GsonConverter(gson))
                .build();

        userApi = userApiRestAdapter.create(UserApi.class);
    }


    public Observable<UserResponseEntity> getUserList(){
        HashMap<String, String> params = new HashMap<>(2);
        params.put("page", "1");
        params.put("pages", "20");
        return userApi.getUserList(params);
    }
}
