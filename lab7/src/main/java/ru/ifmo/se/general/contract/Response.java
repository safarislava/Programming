package ru.ifmo.se.general.contract;

import java.io.Serializable;

/**
 * Class for transfer data. Need for response from server to client.
 *
 * @since 3.0
 * @author safarislava
 */
public class Response implements Serializable {
    private int size;
    private String content;

    /**
     * Standard constructor.
     */
    public Response() {}

    /**
     * Standard constructor.
     *
     * @param content Value of content
     */
    public Response(String content) {
        this.size = content.getBytes().length;
        this.content = content;
    }

    /**
     * Getter of size.
     *
     * @return Value of size
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter of context.
     *
     * @return Value of context
     */
    public String getContent() {
        return content;
    }
}
