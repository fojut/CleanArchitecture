package org.fojut.sample.data.service.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.fojut.sample.data.client.OkHttpClient;
import org.fojut.sample.data.constant.UrlConstant;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Created by fojut on 2016/4/14.
 */
public class BaseApiService<T> {

    protected RestAdapter baseApiRestAdapter;

    public BaseApiService() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override public void intercept(RequestFacade request) {
                request.addHeader("Accept-Encoding", "application/json");
            }
        };

        Gson gson = new GsonBuilder()//
                .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .serializeNulls().create();

        baseApiRestAdapter = new RestAdapter.Builder()
                .setEndpoint(UrlConstant.SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("_Data_Log"))
                .setClient(OkHttpClient.getInstance())
                .setConverter(new GsonConverter(gson))
                .build();

    }

    protected T getApi(Class<T> tClass){
        return baseApiRestAdapter.create(tClass);
    }
}
