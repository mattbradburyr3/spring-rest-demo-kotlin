package payroll


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Employee{
        lateinit var firstName: String
        lateinit var lastName: String
        var role: String
        @Id
        @GeneratedValue
        val id: Long? = null

        constructor(name: String, role: String){
                this.name = name
                this.role = role
        }

        constructor(firstName: String, lastName: String, role: String){
                this.firstName = firstName
                this.lastName = lastName
                this.role = role
        }

        var name: String
                get() { return "$firstName $lastName"}
                set(name) {
                        val parts = name.split(" ")
                        this.firstName = parts[0]
                        this.lastName = parts[1]
                }


        // todo: establish if need to override equals(), hashcode() and to String()
}