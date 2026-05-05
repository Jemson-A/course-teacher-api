package com.example.controller;

import com.example.dto.EmployeeReportDto;
import com.example.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/report/active-high-salary")
    public ResponseEntity<Page<EmployeeReportDto>> getActiveEmployeesWithHighSalary(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("salary").descending());
        Page<EmployeeReportDto> employees = employeeService.getActiveEmployeesWithHighSalary(pageable);
        return ResponseEntity.ok(employees);
    }
}