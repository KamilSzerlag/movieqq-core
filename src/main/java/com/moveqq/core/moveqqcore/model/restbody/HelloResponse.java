package com.moveqq.core.moveqqcore.model.restbody;

public class HelloResponse {

    private String hello;
    private String world;

    public HelloResponse(String hello, String world) {
        this.hello = hello;
        this.world = world;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
