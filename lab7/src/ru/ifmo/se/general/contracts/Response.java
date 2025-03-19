package ru.ifmo.se.general.contracts;

import java.io.Serializable;

public class Response implements Serializable {
    private final int size;
    private final String content;

    public Response(String content) {
        this.size = content.getBytes().length;
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public String getContent() {
        return content;
    }
}
