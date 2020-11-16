package payroll

import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<Employee, Long>