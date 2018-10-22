package com.wechat.member;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static io.restassured.RestAssured.*;
import    static    io.restassured.matcher.RestAssuredMatchers.*;
import     static   org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(Parameterized.class)
public class MemberCreation {
    @Parameterized.Parameters()
    public static List<Member> data() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<Member> data = mapper.readValue(
                new File(MemberCreation.class.getResource("/memberCreate.yaml").getFile()),
                new TypeReference<List<Member>>(){}
        );
        return data;
    }
//    @Parameterized.Parameters
//    public static List<Member> dataCSV() throws IOException {
//        return readFromCSV();
//    }
//    public static List<Member> readFromCSV() throws IOException {
//        ArrayList<Member> data=new ArrayList<Member>();
//        CsvMapper mapper = new CsvMapper();
//        CsvSchema schema = mapper.schemaFor(Member.class);
//        //mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//        // TODO: 2018/10/14 以resources为根路径
//        File csvFile = new File(MemberCreation.class.getResource("/memberCreate.csv").getFile()); // or from String, URL etc
//        MappingIterator<Member> it = mapper.readerFor(Member.class).with(schema).readValues(csvFile);
//        while (it.hasNext()) {
//            Member row = it.next();
//            data.add(row);
//        }
//        return data;
//    }

    @Parameterized.Parameter
    public Member data;
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testCreateMember(){
        useRelaxedHTTPSValidation();
        Map mapToken = new HashMap();
        mapToken.put("corpid","ww516aa64d44600560");
        mapToken.put("secret","xFjsMXKRQzA5l97r41UgZzHMlmxt_WJJl-O9xhK6H9Y");

        String ACCESS_TOKEN = given().queryParams(mapToken)
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .log().all()
                //jsonPath().get("access_token")
                .extract().body().asString()
                ;
        System.out.println("ACCESS_TOKEN: "+ACCESS_TOKEN);

        Map map = new HashMap();
        map.put("userid",data.getUserid());
        map.put("name",data.getName());
        map.put("department",data.getDepartment());
        map.put("alias",data.getAlias());
        map.put("mobile",data.getMobile());
        map.put("order",data.getOrder());
        map.put("position",data.getPosition());
        map.put("gender",data.getGender());
        map.put("email",data.getEmail());
        map.put("telephone",data.getTelephone());
        map.put("isleader",data.isIsleader());
        map.put("enable",data.isEnable());
        map.put("to_invite",data.isTo_invite());
        map.put("external_profile",data.getExternal_profile());
        map.put("external_position",data.getExternal_position());


        String errocode = given().queryParams(map)
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+ACCESS_TOKEN)
                .then()
                .extract().body().jsonPath().get("errocode")
                ;
        collector.checkThat(errocode,equalTo(data.getExpect()));

    }

}
