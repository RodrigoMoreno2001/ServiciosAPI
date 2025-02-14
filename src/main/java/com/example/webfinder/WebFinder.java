package com.example.webfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebFinder {

    public static Web buscarWeb(String url,Integer puerto){
        Web web=null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url+":"+puerto).openConnection();

            conn.setRequestMethod("GET");

            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int codigoRespuesta = conn.getResponseCode();

            String motor = conn.getHeaderField("X-Powered-By");

            if (motor == null) motor = "Desconocido";

            String titulo = buscarTitulo(conn);

            String servidor = conn.getHeaderField("Server");
            if (servidor == null) servidor = "Desconocido";

            web=new Web(puerto,titulo,codigoRespuesta,motor,servidor);

        } catch (Exception e) {
        }
        return web;
    }

    public static String buscarTitulo(HttpURLConnection conn) throws IOException {

        String titulo="Desconocido";

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));){
            String linea;
            StringBuilder HTML = new StringBuilder();

            while ((linea = reader.readLine()) != null) {
                HTML.append(linea);
                if(linea.contains("</title>")) break;
            }

            titulo=HTML.indexOf("<title>")==-1?"Desconocido":HTML.substring(HTML.indexOf("<title>")+7,HTML.indexOf("</title>"));

        }

        return titulo;
    }
}
