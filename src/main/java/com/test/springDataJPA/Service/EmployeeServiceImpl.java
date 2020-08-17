package com.test.springDataJPA.Service;

import com.test.springDataJPA.Model.Certification;
import com.test.springDataJPA.Model.Employee;
import com.test.springDataJPA.Repository.CertificationRepository;
import com.test.springDataJPA.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    @Override
    public List<Map<String, Object>> report(int i) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Employee employee : employeeRepository.findAllByEmployeeId((long) i)) {
            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("id", employee.getEmployeeId());
            item.put("name", employee.getName());
            item.put("dateOfBirth", employee.getDob());
            result.add(item);
        }
        return result;
    }
    public List<Map<String, Object>> report2(int i) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Employee employee = employeeRepository.getOne((long) i);
        for (Certification certification : certificationRepository.findAllByEmployeeId(employee)) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("user_id", certification.getEmployeeId().getEmployeeId());
            item.put("training", certification.getTrainingName());
            item.put("type", certification.getTypeCert());
            item.put("award", certification.getAward());
            item.put("subject", certification.getSubject());
            item.put("time", certification.getTime());
            item.put("remarks", certification.getRemarks());
            result.add(item);
        }
        return result;
    }
}

