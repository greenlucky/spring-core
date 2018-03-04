package com.devopslam.oauth2;

import com.devopslam.oauth2.persistence.model.Company;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCompanyController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @LocalServerPort
    private int ramdomPort;

    private Gson gson = new Gson();

    private String access_token;

    @Autowired
    private TestRestTemplate restTemplate;

    String clientId = "spring-security-oauth2-read-write-client";
    String clientSecret = "spring-security-oauth2-read-write-client-password1234";

    @Before
    public void init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();

        String uri = "http://localhost:" + ramdomPort + "/oauth/token";

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

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        access_token = jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void testCreate() throws Exception {
        System.out.println(access_token);
        String uri = "http://localhost:" + ramdomPort + "/secured/company";
        Company company = new Company();
        company.setName("Test Company");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", "Bearer " + access_token);

        HttpEntity<Company> request = new HttpEntity<>(company, headers);



        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<String> responseList = restTemplate.exchange(uri,HttpMethod.GET, request, String.class);
        System.out.println(responseList.getBody());
    }
}
