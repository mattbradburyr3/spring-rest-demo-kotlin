package payroll

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "CUSTOMER_ORDER")
class Order {

    lateinit var description: String
    lateinit var status: Status
    @Id
    @GeneratedValue
    var id: Long? = null

    constructor( description: String, status: Status) {
        this.description = description
        this.status = status
    }

}