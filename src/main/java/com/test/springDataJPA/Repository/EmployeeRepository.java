package com.test.springDataJPA.Repository;

import com.test.springDataJPA.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByEmployeeId(Long valueOf);
}
