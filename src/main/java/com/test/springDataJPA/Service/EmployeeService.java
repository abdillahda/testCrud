package com.test.springDataJPA.Service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

   List<Map<String, Object>> report(int i);
   List<Map<String, Object>> report2(int i);

}
