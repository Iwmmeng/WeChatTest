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
        RequestSpecification requestSpecification = given();
        System.out.println(this.data.getGiven().getQueryParam());
        System.out.println(this.data.getGiven().getHeaders());
        for (Step data : data()) {
            requestSpecification.queryParams(data.getGiven().getQueryParam());
            if (data.getGiven().getHeaders() != null) {
                requestSpecification.headers(data.getGiven().getHeaders());
            }
            if (data.getWhen().getRequest() == "get") {
                requestSpecification.log().all()
                        .when().get(data.getWhen().getUrl());
            }
            if (data.getWhen().getRequest() == "post") {
                requestSpecification.log().all()
                .when().post(data.getWhen().getUrl());
            }
            requestSpecification.log().all()
                    .then().statusCode(200).log().all()
            ;
        }
    }
}

