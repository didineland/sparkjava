package com.bnc;
import com.bnc.dao.EventDao;
import com.bnc.entity.Event;
import com.bnc.rest.EventRestService;
import com.bnc.websocket.EchoWebSocket;
import com.google.gson.Gson;

import java.util.List;

import static spark.Spark.*;

public class Application {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        EventDao dao = new EventDao();


        List<Event> toto = dao.getEvent();

        webSocket("/echo", EchoWebSocket.class);

        internalServerError("<html><body><h1>Custom 500 handling</h1></body></html>");
        path("/fail", () -> {
            before("", (q, a) -> System.out.println("Before fail Call"));
            after("", (q, a) -> {
                System.out.println("After fail Call");
            });
            get("", EventRestService::fail, gson::toJson);
        });

        get("/bonjour", (req, res) -> "Hello World");

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Before Api Call"));
            after("/*", (q, a) -> {
                System.out.println("After Api Call");
                a.type("application/json");
            });
            post("/event", EventRestService::postEvent, gson::toJson);
            get("/event", EventRestService::getEvents, gson::toJson);
        });
    }
}
