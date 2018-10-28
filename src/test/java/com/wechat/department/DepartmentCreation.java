package com.wechat.department;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wechat.TestBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class DepartmentCreation extends TestBase {

    @Parameterized.Parameters()
    public static List<Department> data() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<Department> data = mapper.readValue(
                new File(DepartmentCreation.class.getResource("/departmentCreation.yaml").getFile()),
                new TypeReference<List<Department>>() {
                }
        );
        return data;
    }

    @Parameterized.Parameter
    public Department data;
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void departmentCreateTest() {
        String body = given().contentType("application/json")
                .body(data)
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .log().all()
                .extract().body().asString();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer errcode = (Integer) jsonObject.get("errcode");
        System.out.println(errcode);
        collector.checkThat(errcode, equalTo(data.getExpect()));
    }

}
