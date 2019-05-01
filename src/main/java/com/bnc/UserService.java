package com.bnc;


import spark.Request;
import spark.Response;

public interface UserService {
    String postUser (Request req, Response res);
}
