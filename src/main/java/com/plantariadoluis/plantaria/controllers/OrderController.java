package com.plantariadoluis.plantaria.controllers;

import com.plantariadoluis.plantaria.dtos.OrderDto;
import com.plantariadoluis.plantaria.models.CustomerModel;
import com.plantariadoluis.plantaria.models.OrderModel;
import com.plantariadoluis.plantaria.services.CustomerService;
import com.plantariadoluis.plantaria.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plantaria/pedidos")
public class OrderController {

    final OrderService orderService;
    final CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody @Valid @NotNull OrderDto orderDto) {
        if (orderDto.getProducts() == null || orderDto.getProducts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not Acceptable: Must be have products for create a new order.");
        }
        OrderModel orderModel = new OrderModel();
        Optional<CustomerModel> customerModelOptional = customerService.findById(orderDto.getCustomerId());

        if (!customerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: Customer not found. Can't create a new order.");
        }
        orderDto.setCustomer(customerModelOptional.get());
        BeanUtils.copyProperties(orderDto, orderModel);
        orderModel.setDateCreated(LocalDateTime.now(ZoneId.of("UTC")));
        orderModel.setDateModified(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderModel));
    }

    @GetMapping
    public ResponseEntity<Page<OrderModel>> getAllOrders(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") long id) {
        Optional<OrderModel> orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderModelOptional.get());
    }

    @GetMapping("/cliente/{customer_id}")
    public ResponseEntity<List<OrderModel>> getAllOrdersByCustomer(@PathVariable(value = "customer_id") long customer_id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrdersByCustomer(customer_id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") long id) {
        Optional<OrderModel> orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        orderService.delete(orderModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value = "id") long id, @RequestBody @Valid OrderDto orderDto) {
        Optional<OrderModel> orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        OrderModel orderModel = orderModelOptional.get();
        BeanUtils.copyProperties(orderDto, orderModel);
        orderModel.setId(orderModelOptional.get().getId());
        if ((orderModelOptional.get().getStatus() != orderDto.getStatus())) {
            orderModel.setDateModified(LocalDateTime.now(ZoneId.of("UTC")));
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderService.save(orderModel));
    }
}