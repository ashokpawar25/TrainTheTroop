package com.amaap.trainthetroop.controller.dto;

import java.util.Objects;

public class Response {
    public HttpStatus status;
    public String message;

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return status == response.status && Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }
}
