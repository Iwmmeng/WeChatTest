package com.wechat.message;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wechat.TestBase;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class MessageCreation extends TestBase {
    @Parameterized.Parameters()
    public static List<Message> data() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<Message> data = mapper.readValue(
                new File(MessageCreation.class.getResource("/messageCreation.yaml").getFile()),
                new TypeReference<List<Message>>() {
                }
        );
        return data;
    }

    @Parameterized.Parameter
    public Message data;
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testMessage() {
        System.out.println(data);
        ResponseBodyExtractionOptions body = given()
                .contentType("application/json")
                .body(data)
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + auditAccessToken)
                .then()
                .log().all()
                .extract()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body.asString());
        Integer errcode = (Integer) jsonObject.get("errcode");
        System.out.println(errcode);
        collector.checkThat(errcode, equalTo(data.getExpect()));


    }
}
