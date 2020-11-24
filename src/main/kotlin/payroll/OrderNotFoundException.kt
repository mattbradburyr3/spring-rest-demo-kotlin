package payroll

import java.lang.RuntimeException

class OrderNotFoundException(id: Long): RuntimeException("Could not find Order") {
}