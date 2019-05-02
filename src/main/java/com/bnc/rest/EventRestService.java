package com.bnc.rest;

import com.bnc.dao.EventDao;
import com.bnc.entity.Event;
import com.bnc.websocket.EchoWebSocket;
import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.util.List;

public class EventRestService {

    private static EventDao eventDao = new EventDao();
    private static EchoWebSocket ws = new EchoWebSocket();

    public EventRestService(){

    }

    public static Event postEvent(Request request, Response response){
        response.status(HttpStatus.CREATED_201);
        Event user = new Gson().fromJson(request.body(), Event.class);
        eventDao.saveStudent(user);
        ws.NotifyEvent(user);
        return user;
    }

    public static List<Event> getEvents(Request request, Response response){
        response.status(HttpStatus.OK_200);
        return eventDao.getEvent();
    }

    public static String fail(Request request, Response response) throws Exception{
        throw new Exception("toto");

    }

}
