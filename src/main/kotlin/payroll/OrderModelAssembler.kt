package payroll

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class OrderModelAssembler: RepresentationModelAssembler<Order, EntityModel<Order>> {

    override fun toModel(order: Order): EntityModel<Order>{

        // Unconditional Links to single-item resource and aggregate root

        val orderModel = EntityModel.of(
                order,
                linkTo(methodOn(OrderController::class.java).one(order.id!!)).withSelfRel(),
                linkTo(methodOn(OrderController::class.java).all()).withRel("orders"))

        if (order.status == Status.IN_PROGRESS){
            orderModel.add(linkTo(methodOn(OrderController::class.java).cancel(order.id!!)).withRel("cancel"))
            orderModel.add(linkTo(methodOn(OrderController::class.java).complete(order.id!!)).withRel("complete")) // todo: work out how to avoid NPE
        }
        return orderModel
    }
}