package com.devlucasmart.book.comum.model;

import lombok.Data;

@Data
public class MessageException {

    private String message;
    private String field;

    public MessageException() { }

    public MessageException(String message) {
        this.message = message;
    }

    public MessageException(String field, String message) {
        this.field = field;
        this.message = message;
    }
}