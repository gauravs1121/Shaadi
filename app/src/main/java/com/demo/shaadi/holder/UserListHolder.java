package com.demo.shaadi.holder;

import java.util.List;


public class UserListHolder {

    private List<ResultHolder> results = null;
    private InfoHolder info;

    public List<ResultHolder> getResults() {
        return results;
    }

    public void setResults(List<ResultHolder> results) {
        this.results = results;
    }

    public InfoHolder getInfo() {
        return info;
    }

    public void setInfo(InfoHolder info) {
        this.info = info;
    }
}
