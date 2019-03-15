package com.moveqq.core.moveqqcore.fault;

public enum  MovieDbErrors {
    MOVIE_DB_NOT_FOUND("Movie not found in DB!"),
    MOVIE_DB_BAD_PARAMETERS("Acces with bad parameters!"),
    MOVIE_DB_UNKNOWN("Error Unknown!"),
    MOVIE_DB_OTHER_BAD_ERROR("Other bad error!Fuck!");


    private String msg;

    MovieDbErrors(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
