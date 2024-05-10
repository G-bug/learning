package org.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

public class HttpVterticle extends AbstractVerticle {
    private HttpServer httpServer;
    @Override
    public void start() throws Exception {
        httpServer = vertx.createHttpServer().requestHandler(req -> {
            System.out.println(Thread.currentThread().getName());
            req.response()
                    .putHeader("content-type","text/plain")
                    .end("Hello vterx vterticle!");
        });
        httpServer.listen(8090);
    }
}
