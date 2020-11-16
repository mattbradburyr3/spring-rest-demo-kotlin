package payroll

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


/**
 * from tutorial: https://spring.io/guides/tutorials/rest/
 */

@SpringBootApplication
class PayrollApplication

fun main(args: Array<String>){
    runApplication<PayrollApplication>(*args)

}