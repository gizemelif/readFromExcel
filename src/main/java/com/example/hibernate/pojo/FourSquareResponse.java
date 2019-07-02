package com.example.hibernate.pojo;

import com.example.hibernate.pojo.Meta;
import com.example.hibernate.pojo.Response;

public class FourSquareResponse {
    private Meta meta;
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
