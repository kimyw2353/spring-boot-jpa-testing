package com.yael.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
	
	@Test
	public void getEmployeeTest() {
		
		Employee employee = employeeRepository.findById(1L).get();
		
		assertThat(employee.getId()).isEqualTo(1L);
		
	}
	
	@Test
	public void getListOfEmployeeTest() {
		
		List<Employee> employees = employeeRepository.findAll();
		
		assertThat(employees.size()).isGreaterThan(0);
		
	}
	
	@Test
	public void updateEmployeeTest() {
		
		Employee employee = employeeRepository.findById(1L).get();
		
		employee.setEmail("kimyw2353@gmail.com");
		
		Employee employeeUpdated = employeeRepository.save(employee);
		
		assertThat(employeeUpdated.getEmail()).isEqualTo("kimyw2353@gmail.com");
		
	}
	
}
