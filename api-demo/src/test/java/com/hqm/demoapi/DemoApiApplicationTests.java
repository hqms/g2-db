package com.hqm.demoapi;

import com.hqm.demoapi.controller.EmployeeController;

import com.hqm.demoapi.model.Employees;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApiApplicationTests {

	@Autowired
	EmployeeController employeeController;

	@Autowired
	TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/";
	}

	@Test
	void contextLoads() {
		Assertions.assertThat(employeeController).isNotNull();
	}

	@Test
	void getAllEmployeeTest(){
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String >(null, header);

		ResponseEntity<String> response = testRestTemplate.exchange(
				getRootUrl()+"/employees/",
				HttpMethod.GET,
				entity,
				String.class);
	}

	@Test
	void registerEmployeeTest(){
		Employees emp = new Employees();
		emp.setBirth_date(LocalDate.now());
		emp.setHire_date(LocalDate.now());
		emp.setFirst_name("hakim");
		emp.setLast_name("Arif");
		emp.setGender("M");
		emp.setEmp_no(100);

		ResponseEntity<Employees> response = testRestTemplate.postForEntity(getRootUrl()+"/employees/", emp, Employees.class);

		org.junit.jupiter.api.Assertions.assertEquals(201, response.getStatusCodeValue());
	}

	@Test
	void registerEmployeeNotBirthdayTest(){
		Employees emp = new Employees();
		emp.setFirst_name("hakim");
		emp.setLast_name("Arif");
		emp.setGender("M");

		ResponseEntity<Employees> response = testRestTemplate.postForEntity(getRootUrl()+"/employees/", emp, Employees.class);

		org.junit.jupiter.api.Assertions.assertEquals(500, response.getStatusCodeValue());
	}
}
