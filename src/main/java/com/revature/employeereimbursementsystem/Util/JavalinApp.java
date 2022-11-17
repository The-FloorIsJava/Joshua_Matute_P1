package com.revature.employeereimbursementsystem.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class JavalinApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .start(8080);



        app.get("/hello, this is a test", ctx -> ctx.html("Hello, Javalin!"));

    }
}
