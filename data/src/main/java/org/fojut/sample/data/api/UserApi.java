package org.fojut.sample.data.api;

import org.fojut.sample.data.entity.UserResponseEntity;

import java.util.HashMap;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface UserApi {

    @POST("/api/user/list")
    Observable<UserResponseEntity> getUserList(@Body HashMap<String, String> params);

}
