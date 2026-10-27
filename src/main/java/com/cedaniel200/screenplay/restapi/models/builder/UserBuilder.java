package com.cedaniel200.screenplay.restapi.model.builder;

import com.cedaniel200.screenplay.restapi.model.User;
import com.cedaniel200.screenplay.restapi.util.Builder;

public class UserBuilder implements Builder <User> {

    private String name;
    private String job;

    private UserBuilder(String name) {
        this.name = name;
    }

    public static UserBuilder withName(String name){
        return new UserBuilder(name);
    }

    public User andJob(String job) {
        this.job = job;
        return build();
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    @Override
    public User build() {
        return new User(this);
    }
}
