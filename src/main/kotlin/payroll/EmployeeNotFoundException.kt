package payroll

import java.lang.RuntimeException

class EmployeeNotFoundException(id: Long): RuntimeException("Could not find employee $id") {

}
