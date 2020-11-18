package payroll

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Employee(
        var name: String,
        var role: String,
        @Id
        @GeneratedValue
        val id: Long? = null) {

        // todo: establish if need to override equals(), hashcode() and to String()

}
