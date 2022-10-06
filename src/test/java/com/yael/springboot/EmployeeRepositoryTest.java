package com.yael.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//JUnit test for saveEmployee
	@Test
	public void saveEmployeeTest() {
		
		Employee employee = Employee.builder()
				.firstName("Yael")
				.lastName("Kim")
				.email("kimyw2353@naver.com")
				.build();
		
		employeeRepository.save(employee);
		
		assertThat(employee.getId()).isGreaterThan(0);
	}
	
}
