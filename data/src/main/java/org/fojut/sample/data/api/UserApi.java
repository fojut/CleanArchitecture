package org.fojut.sample.data.api;

import org.fojut.sample.data.dto.UserResponseDto;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface UserApi {

    @POST("/api/user/list")
    Observable<UserResponseDto> getUserList(@Body HashMap<String, String> params);

}
