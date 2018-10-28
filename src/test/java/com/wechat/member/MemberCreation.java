package com.wechat.member;

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
import static io.restassured.RestAssured.*;
import     static   org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(Parameterized.class)
public class MemberCreation extends TestBase{
    @Parameterized.Parameters()
    public static List<Member> data() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<Member> data = mapper.readValue(
                new File(MemberCreation.class.getResource("/memberCreate.yaml").getFile()),
                new TypeReference<List<Member>>(){}
        );
        return data;
    }

    @Parameterized.Parameter
    public Member data;
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testCreateMember(){
         String body =
        given()
                .contentType("application/json")
                .body(data)
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + accessToken)
                .then()
                .log().all()
                .extract().body().asString()
                ;
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer errcode = (Integer) jsonObject.get("errcode");
        System.out.println(errcode);

        collector.checkThat(errcode,equalTo(data.getExpect()));
    }

}
