package com.example.repository;

import com.example.dto.EmployeeReportDto;
import com.example.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.example.dto.EmployeeReportDto(e.name, e.department, e.salary) " +
           "FROM Employee e WHERE e.active = true AND e.salary > 5000 " +
           "ORDER BY e.salary DESC")
    Page<EmployeeReportDto> findActiveEmployeesWithHighSalary(Pageable pageable);
}