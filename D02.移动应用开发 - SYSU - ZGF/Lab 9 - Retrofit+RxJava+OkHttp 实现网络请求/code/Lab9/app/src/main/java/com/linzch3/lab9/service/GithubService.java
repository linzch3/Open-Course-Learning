package com.linzch3.lab9.service;


import com.linzch3.lab9.model.Github;
import com.linzch3.lab9.model.Repos;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by linzch3 on 17/12/19.
 */

public  interface GithubService
{
    @GET("/users/{user}")
    Observable<Github> getUser(@Path("user") String user);

    @GET("/users/{user}/repos")
    Observable<List<Repos>> getRepos(@Path("user") String user);
}
