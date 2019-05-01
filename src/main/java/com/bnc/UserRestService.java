package com.bnc;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class UserRestService {

    private String toto;

    public UserRestService(){
        toto = "Adrien";
    }

    public static User postUser(Request request, Response response){
        response.status(HttpStatus.CREATED_201);
        User user = new Gson().fromJson(request.body(), User.class);
        return user;
    }

}
