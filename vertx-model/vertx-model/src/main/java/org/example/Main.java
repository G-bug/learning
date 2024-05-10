package org.example;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class Main {
    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        /* 直接启动的方式 不推荐
        final HttpServer server = vertx.createHttpServer();
        server.requestHandler(handler -> {
            System.out.println(Thread.currentThread().getName());
            handler.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello Direct Server!");
        });
        server.listen(8099);
        */
        vertx.deployVerticle(new HttpVterticle());
    }
}