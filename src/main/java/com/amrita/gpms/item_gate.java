package com.amrita.gpms;

/**
 * Created by Sumanth on 8/29/2018.
 */

public class item_gate {
    private String name;
    private String appr;
    private  int gid;
    public item_gate()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppr() {
        return appr;
    }

    public void setAppr(String appr) {
        this.appr = appr;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public item_gate(String name, String appr, int gid) {

        this.name = name;
        this.appr = appr;
        this.gid = gid;
    }
}
