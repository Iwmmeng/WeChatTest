package com.yamltool;

import java.util.Map;

public class Given {
    private Map<String,String> queryParam;
    private Header header;

    public Map<String, String> getQueryParam() {
        return queryParam;
    }

    public Given setQueryParam(Map<String, String> queryParam) {
        this.queryParam = queryParam;
        return this;
    }

    public Header getHeader() {
        return header;
    }

    public Given setHeader(Header header) {
        this.header = header;
        return this;
    }
}
