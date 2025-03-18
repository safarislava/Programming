package ru.ifmo.se.general.contracts;

import java.io.Serializable;

public class Response implements Serializable {
    public int countByte;
    public String content;

    public Response(String content) {
        this.countByte = content.getBytes().length;
        this.content = content;
    }
}
