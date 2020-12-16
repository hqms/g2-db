package com.hqm.demoapi;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.print.Pageable;
import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class HttpResponseTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(4)
    public void getRootApiTest(){
        JSONParser parser = new JSONParser(testRestTemplate.getForObject("http://localhost:"+port+"/api/", String.class));
        LinkedHashMap<String, Object> test = null;
        try {
            test = parser.parseObject();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(test.get("error"), "Not Found");
    }

    @Test
    @Order(3)
    public void getRootApiMVCTest(){
        try {
            mockMvc.perform(get("/"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void postEmployeeAPIMVCErrorTest(){
        try {
            mockMvc.perform(
                    post("/api/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"emp_no\": 1212, \"first_name\": \"hakim\", \"last_name\": \"arif\", \"gender\": \"M\"}")
                    ).andExpect(MockMvcResultMatchers.status().is5xxServerError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void postEmployeeAPIMVCTest(){
        try {
            mockMvc.perform(
                    post("/api/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"emp_no\": 1212, \"first_name\": \"hakim\", \"last_name\": \"arif\", " +
                                    "\"gender\": \"M\", \"birth_date\": \"2000-10-10\", \"hire_date\": \"2000-10-10\"}")
            ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void getEmployeesApiMVCTest(){
        try {
            mockMvc.perform(
                    get("/api/employees/"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
