package mx.unam.dgpe.sample.controller;

import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger;
import org.apache.log4j.Logger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(MyController.class);
    
    public void start(Future<Void> fut) {
        logger.info("Inicializando Vertical");
        Router router = Router.router(vertx);
        //router.route("/*").handler(StaticHandler.create("assets")); // para invocar asi: http://localhost:8080/index.html
        // el directorio "upload-folder" será creado en la misma ubicación que el jar fue ejecutado
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-folder"));
        router.get("/api/calcula").handler(this::calcula);
        
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });        
        
        logger.info("Vertical iniciada !!!");
    }  
    
    private void calcula(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int num = Integer.parseInt(request.getParam("var1"));
        BigInteger fact = fact(num);
	    int powerOfTwoCount = 0;
    BigInteger two = BigInteger.valueOf(2);
    while (fact.compareTo(BigInteger.ZERO) > 0 && fact.mod(two).equals(BigInteger.ZERO)) {
        powerOfTwoCount++;
        fact = fact.divide(two);
    }
        String sfac =String.valueOf(powerOfTwoCount);
        int resultado = sfac.length();

        String jsonResponse = resSum(resultado, num,request);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }

    private static BigInteger fact(long n) {
        BigInteger result = BigInteger.ONE;
        for (long i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }
    private String resSum(int result, int var1, HttpServerRequest request) {
        
        Map<Object, Object> cal = new HashMap<>();
        cal.put("IP:", request.localAddress().host());
        cal.put("numero", var1);
        cal.put("resultado", result);
        return Json.encodePrettily(cal);
    }

}
