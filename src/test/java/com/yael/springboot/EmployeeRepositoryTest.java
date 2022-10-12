package com.yael.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//JUnit test for saveEmployee
	@Test
	@Order(1)
	@Rollback(value = false) //rollback 처리 제외
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
	@Order(2)
	public void getEmployeeTest() {
		
		Employee employee = employeeRepository.findById(1L).get();
		
		assertThat(employee.getId()).isEqualTo(1L);
		
	}
	
	@Test
	@Order(3)
	public void getListOfEmployeeTest() {
		
		List<Employee> employees = employeeRepository.findAll();
		
		assertThat(employees.size()).isGreaterThan(0);
		
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeTest() {
		
		Employee employee = employeeRepository.findById(1L).get();
		
		employee.setEmail("kimyw2353@gmail.com");
		
		Employee employeeUpdated = employeeRepository.save(employee);
		
		assertThat(employeeUpdated.getEmail()).isEqualTo("kimyw2353@gmail.com");
		
	}
	
	@Test
	@Order(5)
	public void deleteEmployeeTest() {
		
		Employee employee = employeeRepository.findById(1L).get();
		
		employeeRepository.delete(employee);
		
		//employeeRepository.deleteById(1L);
		
		Employee employee1 = null;
		
		Optional<Employee> optionalEmployee = employeeRepository.findByEmail("kimyw2353@gmail.com");
		
		if(optionalEmployee.isPresent()) {
			employee1 = optionalEmployee.get();
		}
		
		assertThat(employee1).isNull();
		
	}
	
}
