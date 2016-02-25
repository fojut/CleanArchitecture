package org.fojut.sample.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListEntity {

    @Expose @SerializedName("list")
    private List<UserEntity> list;

    public List<UserEntity> getList() {
        return list;
    }

    public void setList(List<UserEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserListEntity{" +
                list2String(list)+
                '}';
    }

    public String list2String(List<UserEntity> list){
        StringBuffer stringBuffer = new StringBuffer("");
        for (UserEntity userEntity :list){
            stringBuffer.append(userEntity.toString()+"; ");
        }
        return stringBuffer.toString();
    }
}
