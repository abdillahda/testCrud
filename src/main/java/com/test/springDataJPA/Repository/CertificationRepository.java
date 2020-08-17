package com.test.springDataJPA.Repository;

import com.test.springDataJPA.Model.Certification;
import com.test.springDataJPA.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification,Long> {
    List<Certification> findAllByEmployeeId(Employee employee);
}
