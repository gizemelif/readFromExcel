package com.example.hibernate.pojo;

import com.example.Filter;

import java.util.List;

public class SuggestedFiltersDao {
    private String header;
    private List<Filter> filters = null;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

}
