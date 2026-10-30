package com.empresa.resource;

import jakarta.ws.rs.core.Response;
import java.util.Map;

public class Respostas {
    public static Response erro(Response.Status status, String msg) {
        return Response.status(status).entity(Map.of("erro", msg)).build();
    }

    public static boolean vazio(String s) {
        return s == null || s.isBlank();
    }
}
