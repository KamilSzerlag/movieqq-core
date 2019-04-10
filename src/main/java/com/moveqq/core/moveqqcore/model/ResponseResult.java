package com.moveqq.core.moveqqcore.model;

public class ResponseResult {

    private ResultType result = ResultType.OK;

    public enum ResultType {
        OK("OK"),
        FAILED("FAILED");

        private String msg;

        ResultType(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    public ResponseResult() {
    }

    public ResponseResult(ResultType result) {
        this.result = result;
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }
}
