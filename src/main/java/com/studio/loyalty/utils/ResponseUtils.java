package com.studio.loyalty.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    static Map<String, Object> res = new HashMap<>();
    public static ResponseEntity<?> generate(HttpStatus status, String message, Object data){
        res.put("status", status.value());
        res.put("message", message);
        res.put("data", data);
        return new ResponseEntity<>(res, status);
    }
}
