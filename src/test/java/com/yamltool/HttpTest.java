package com.yamltool;

import com.alibaba.fastjson.JSONObject;
import io.restassured.specification.RequestSpecification;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class HttpTest {
    @Parameterized.Parameters
    public static List<Step> data() throws IOException {
        Yaml yaml = new Yaml();
        Object load = yaml.load(HttpTest.class.getResourceAsStream("/yamlTool.yaml"));
        String jsonString = JSONObject.toJSONString(load);
        List<Step> steps = JSONObject.parseArray(jsonString, Step.class);
        return steps;
    }


    @Parameterized.Parameter
    public Step data;
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void run() throws IOException {
        System.out.println(this.data.getGiven().getQueryParam().get("key"));
        for (Step data : data()) {
            RequestSpecification requestSpecification = given();
            requestSpecification.queryParams(data.getGiven().getQueryParam());
            if (data.getGiven().getHeader() != null) {
                if (data.getGiven().getHeader().getContentType() != null) {
                    requestSpecification.contentType(data.getGiven().getHeader().getContentType());
                }
                if (data.getGiven().getHeader().getCookie() != null) {
                    requestSpecification.cookie(data.getGiven().getHeader().getCookie());
                }
            }
            if (data.getWhen().getRequest() == "get") {
                requestSpecification.when().get(data.getWhen().getUrl());
            }
            if (data.getWhen().getRequest() == "post") {
                requestSpecification.when().post(data.getWhen().getUrl());
            }
            requestSpecification.then().statusCode(200);
        }
    }
}
