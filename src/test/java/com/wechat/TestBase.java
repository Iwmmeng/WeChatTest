package com.wechat;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.junit.Before;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class TestBase {
    public  String accessToken ;
    public String addressListSecret = "xFjsMXKRQzA5l97r41UgZzHMlmxt_WJJl-O9xhK6H9Y";
    public  String auditAccessToken ;
    public String auditSecret = "bQg5fobs_mYfZ3CDHmILkghqbmUAyQkKEOq6B9LQwyc";

    @Before
    public void before(){
        useRelaxedHTTPSValidation();
        Map mapToken = new HashMap();
        mapToken.put("corpid","ww516aa64d44600560");
        mapToken.put("corpsecret",addressListSecret);
        accessToken = given().queryParams(mapToken)
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                //todo 要做返回的errcode，errmsg的断言，应该怎么加呢？
                .log().all()
                .extract().body().jsonPath().get("access_token")
                ;

        auditAccessToken = given().queryParams("corpid","ww516aa64d44600560")
                .queryParams("corpsecret",auditSecret)
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                //todo 要做返回的errcode，errmsg的断言，应该怎么加呢？
                .log().all()
                .extract().body().jsonPath().get("access_token")
        ;



    }



}
