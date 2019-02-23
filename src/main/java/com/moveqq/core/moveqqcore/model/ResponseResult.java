package com.moveqq.core.moveqqcore.model;

public class ResponseResult {

    protected enum  ResultType {
        OK("OK"),
        FAILED("FAILED");

        private String msg;

        private ResultType(String msg){
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    protected ResultType result;
}
