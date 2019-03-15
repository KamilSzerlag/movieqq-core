package com.moveqq.core.moveqqcore.model;

public class ResponseResult {

    protected ResultType result = ResultType.OK;

    protected enum ResultType {
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

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }
}
