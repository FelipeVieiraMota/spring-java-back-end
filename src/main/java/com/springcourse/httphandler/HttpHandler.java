package com.springcourse.httphandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {

    public String doPost(String path){
        String result = "";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Request-Method", "POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "");
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            System.out.println(connection.getRequestMethod());
            System.out.println(connection.getResponseMessage());

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer(9999999);
            String aux = "";
            while ((aux = br.readLine()) != null)
                response.append(aux);
            br.close();
            result = response.toString();
        } catch (MalformedURLException ex) {
            System.out.println("String of invalid URL!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error in Connection!");
            ex.printStackTrace();
        }
        return result;
    }

    public HttpResponse doGet(String path){
        HttpResponse http = new HttpResponse();
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Request-Method", "GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();
            http.setConn(connection);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer(9999999);
            String aux = "";
            while ((aux = br.readLine()) != null) {
                response.append(aux);
            }
            br.close();
            http.setResponse(response.toString());
            System.out.println(response.toString());
        } catch (MalformedURLException ex) {
            System.out.println("String of invalid URL!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error in Connection!");
            ex.printStackTrace();
        }
        return http;
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
class HttpResponse{
    private String response;
    private HttpURLConnection conn;
}