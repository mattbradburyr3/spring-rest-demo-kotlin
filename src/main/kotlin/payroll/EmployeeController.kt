package payroll

import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController(val repository: EmployeeRepository) {

    // Agregate root

    @GetMapping("/employees")
    fun all(): List<Employee> = repository.findAll()

    @PostMapping("/employees")
    fun newEmployee(@RequestBody newEmployee: Employee) = repository.save(newEmployee)

    @GetMapping("/employees/{id}")
    fun one(@PathVariable id: Long): Employee {

        return repository.findByIdOrNull(id) ?: throw EmployeeNotFoundException(id)

    }

    @PutMapping("/employees/{id}")
    fun replaceEmployee(@RequestBody newEmployee: Employee, @PathVariable id: Long): Employee {

        val existingEmployee: Employee? = repository.findByIdOrNull(id)

        return if (existingEmployee != null){
            repository.save(existingEmployee.copy(name = newEmployee.name, role = newEmployee.role))
        } else
            repository.save(newEmployee)
    }

    @DeleteMapping("/employees/{id}")
    fun deleteEmployees(@PathVariable id: Long) = repository.deleteById(id)

}