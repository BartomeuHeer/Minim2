package edu.upc.eetac.dsa.minim2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface APIInterface {

    @GET("{userName}")
    Call<User> getUser(@Path("userName") String userName);

    @GET("{userName}/repos")
    Call<List<Repo>> getUserRepos(@Path("userName") String userName);
}
