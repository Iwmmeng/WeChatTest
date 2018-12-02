package com.yamltool;
import java.util.Map;
public class Given {
    private Map<String,String> queryParam;
    private Map<String,String> headers;
    public Map<String, String> getQueryParam() {
        return queryParam;
    }
    public Given setQueryParam(Map<String, String> queryParam) {
        this.queryParam = queryParam;
        return this;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public Given setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }
}
