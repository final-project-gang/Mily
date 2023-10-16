package com.mily.standard.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Ut {
    public static class url {
        public static String encode(String message) {
            try {
                return URLEncoder.encode(message, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
    public static class DataNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public DataNotFoundException(String message) {
            super(message);
        }
    }
}