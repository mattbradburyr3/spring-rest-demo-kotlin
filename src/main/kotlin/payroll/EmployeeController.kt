package payroll

import org.springframework.data.repository.findByIdOrNull
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class EmployeeController(val repository: EmployeeRepository, val assembler: EmployeeModelAssembler) {

    // Agregate root

//    @GetMapping("/employees")
//    fun all(): List<Employee> = repository.findAll()

    @GetMapping("/employees")
    fun all(): CollectionModel<EntityModel<Employee>> {

        val employees: List<EntityModel<Employee>> = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList())

        return CollectionModel.of(
                employees,
                linkTo(methodOn(EmployeeController::class.java).all()).withSelfRel())

    }


//    @PostMapping("/employees")
//    fun newEmployee(@RequestBody newEmployee: Employee) = repository.save(newEmployee)

    @PostMapping("/employees")
    fun newEmployee(@RequestBody newEmployee: Employee): ResponseEntity<EntityModel<Employee>> {

        val entityModel: EntityModel<Employee> = assembler.toModel(repository.save(newEmployee))
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel)

    }


    @GetMapping("/employees/{id}")
    fun one(@PathVariable id: Long): EntityModel<Employee> {
        val employee = repository.findByIdOrNull(id) ?: throw EmployeeNotFoundException(id)
        return assembler.toModel(employee)
    }


    @PutMapping("/employees/{id}")
    fun replaceEmployee(@RequestBody newEmployee: Employee, @PathVariable id: Long): ResponseEntity<EntityModel<Employee>> {

        val existingEmployee: Employee? = repository.findByIdOrNull(id)

        val updatedEmployee = if (existingEmployee != null) {
            existingEmployee.firstName = newEmployee.firstName
            existingEmployee.lastName = newEmployee.lastName
            existingEmployee.role = newEmployee.role
            repository.save(existingEmployee)
        } else {
            repository.save(newEmployee)
        }

        val entityModel: EntityModel<Employee> = assembler.toModel(updatedEmployee)
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel)
}


//    @DeleteMapping("/employees/{id}")
//    fun deleteEmployees(@PathVariable id: Long) = repository.deleteById(id)

    @DeleteMapping("/employees/{id}")
    fun deleteEmployees(@PathVariable id: Long): ResponseEntity<EntityModel<Employee>> {
        repository.deleteById(id)
        return ResponseEntity.noContent().build()

    }
}