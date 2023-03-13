package ru.netology.backendservice.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int statusCode;
    private String message;

    public Response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Response() {
    }
}
