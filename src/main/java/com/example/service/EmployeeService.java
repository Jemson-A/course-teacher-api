package com.example.service;

import com.example.dto.EmployeeReportDto;
import com.example.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<EmployeeReportDto> getActiveEmployeesWithHighSalary(Pageable pageable) {
        return employeeRepository.findActiveEmployeesWithHighSalary(pageable);
    }
}