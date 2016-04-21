package org.fojut.sample.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDto {

    @Expose @SerializedName("id")
    private int id;
    @Expose @SerializedName("name")
    private String username;
    @Expose @SerializedName("account")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
