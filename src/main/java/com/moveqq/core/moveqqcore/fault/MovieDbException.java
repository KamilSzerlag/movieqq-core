package com.moveqq.core.moveqqcore.fault;


public class MovieDbException extends Exception {

    public MovieDbException(MovieDbErrors message) {
        super(message.getMsg());
    }
}
