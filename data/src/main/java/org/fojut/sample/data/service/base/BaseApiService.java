package org.fojut.sample.data.service.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.fojut.sample.data.client.HttpClient;
import org.fojut.sample.data.constant.UrlConstant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by fojut on 2016/4/14.
 */
public class BaseApiService<T> {

    protected Retrofit retrofit;

    public BaseApiService() {
        retrofit = new Retrofit.Builder().baseUrl(UrlConstant.SERVER_BASE_URL)
                .client(HttpClient.getInstance()).addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    public BaseApiService(String baseUrl) {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(HttpClient.getInstance()).addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    protected Gson getGson(){
        return new GsonBuilder()//
                .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .serializeNulls().create();
    }

    protected T getService(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
