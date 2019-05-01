package com.bnc;
import com.bnc.UserRestService;
import com.google.gson.Gson;

import javax.inject.Inject;

import static spark.Spark.*;

public class Application {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        webSocket("/echo", EchoWebSocket.class);

        internalServerError("<html><body><h1>Custom 500 handling</h1></body></html>");
        path("/fail", () -> {
            before("", (q, a) -> System.out.println("Before fail Call"));
            after("", (q, a) -> {
                System.out.println("After fail Call");
            });
            get("", UserRestService::fail, gson::toJson);
        });

        get("/bonjour", (req, res) -> "Hello World");

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Before Api Call"));
            after("/*", (q, a) -> {
                System.out.println("After Api Call");
                a.type("application/json");
            });
            post("/users", UserRestService::postUser, gson::toJson);
        });
    }
}
