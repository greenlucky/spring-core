package com.devopslam.oauth2;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAuthorization {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @LocalServerPort
    private int ramdomPort;

    private Gson gson = new Gson();

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void testGetAccessToken() throws Exception {

        String uri = "http://localhost:" + ramdomPort + "/oauth/token";
        String clientId = "spring-security-oauth2-read-write-client";
        String clientSecret = "spring-security-oauth2-read-write-client-password1234";
        String username = "admin";
        String password = "admin1234";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("username", username);
        params.add("password", password);

        ResultActions result = mockMvc.perform(
                post(uri)
                .params(params)
                .with(httpBasic(clientId, clientSecret))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        String resultString = result.andReturn().getResponse().getContentAsString();

        System.out.println(gson.toJson(resultString));
    }

    @Test
    public void testGetRefreshToken() throws Exception {

        String uri = "http://localhost:8080/oauth/token";
        String clientId = "spring-security-oauth2-read-write-client";
        String clientSecret = "spring-security-oauth2-read-write-client-password1234";

        String refreshToken = "870445aa-5d4e-461d-8126-8e57e5957c56";
        MultiValueMap<String, String> paramsRefreshToken = new LinkedMultiValueMap<>();
        paramsRefreshToken.add("grant_type", "refresh_token");
        paramsRefreshToken.add("client_id", clientId);
        paramsRefreshToken.add("refresh_token", refreshToken);

        ResultActions resultRefreshToken = mockMvc.perform(
                post(uri)
                        .params(paramsRefreshToken)
                        .with(httpBasic(clientId, clientSecret))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        System.out.println(gson.toJson(resultRefreshToken));

        Thread.sleep(2000);
    }

    //fail
    @Test
    public void testGetAccessToken1() throws Exception {

        String uri = "http://localhost:" + ramdomPort + "/oauth/token";
        String clientId = "spring-security-oauth2-read-write-client";
        String clientSecret = "spring-security-oauth2-read-write-client-password1234";
        String username = "admin";
        String password = "admin1234";

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);

        Response response = RestAssured.given().auth()
        .preemptive().basic(clientId, clientSecret)
                .and().with().params(params)
                .when().post(uri);

        System.out.println(gson.toJson(response.jsonPath()));
    }
}
