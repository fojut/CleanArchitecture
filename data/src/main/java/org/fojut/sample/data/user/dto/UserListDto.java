package org.fojut.sample.data.user.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListDto {

    @Expose @SerializedName("list")
    private List<UserDto> list;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserListDto{" +
                list2String(list)+
                '}';
    }

    public String list2String(List<UserDto> list){
        StringBuffer stringBuffer = new StringBuffer("");
        for (UserDto userDto :list){
            stringBuffer.append(userDto.toString()+"; ");
        }
        return stringBuffer.toString();
    }
}
