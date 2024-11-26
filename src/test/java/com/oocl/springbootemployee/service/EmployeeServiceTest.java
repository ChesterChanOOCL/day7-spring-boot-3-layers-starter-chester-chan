package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import com.oocl.springbootemployee.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        when(mockedEmployeeRepository.getAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() throws EmployeeAgeNotValidException, EmployeeNotEnoughSalaryException {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        Employee createdEmployee = employeeService.create(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }
    @Test
    void should_throw_Employee_Age_Not_Valid_Exception_when_create_given_a_employee_with_age_6 () {
        //given
        IEmployeeRepository mockEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Kitty",6,  Gender.FEMALE, 8000.0);
        when(mockEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockEmployeeRepository);

        //When and then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(lucy));
        verify(mockEmployeeRepository, never()).addEmployee(any());
    }


    @Test
    void should_return_null_when_create_given_a_employee_is_over_30_years_old_below_20000(){
        IEmployeeRepository mockEmployeeRepository = mock(IEmployeeRepository.class);
        Employee Bosco = new Employee(2, "Bosco", 35, Gender.MALE, 19999.9);
        when(mockEmployeeRepository.addEmployee(any())).thenReturn(Bosco);
        EmployeeService employeeService = new EmployeeService(mockEmployeeRepository);

        //When and then
        assertThrows(EmployeeNotEnoughSalaryException.class, () -> employeeService.create(Bosco));
        verify(mockEmployeeRepository, never()).addEmployee(any());
    }



}
