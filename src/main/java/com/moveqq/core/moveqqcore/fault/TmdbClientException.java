package com.moveqq.core.moveqqcore.fault;


public class TmdbClientException extends Exception {

    public TmdbClientException(TmdbClientErrors message) {
        super(message.getMsg());
    }
}
