package com.springcourse.httphandler;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HttpHandlerTest {

    @Test
    public void doPostTest(){
        HttpHandler http = new HttpHandler();
        String path = "http://172.17.0.3:8080/users";
        String result = http.doPost(path);
        System.out.println(result);
    }

    @Test
    public void doGetTest(){
        try {
            HttpHandler http = new HttpHandler();
            String path = "http://172.17.0.3:8080/users";
            HttpResponse response = http.doGet(path);
            System.out.println(response.getConn().getHeaderFields());
            Assert.assertEquals(200, response.getConn().getResponseCode());
        }catch (IOException ex){
            ex.getMessage();
        }
    }
}
