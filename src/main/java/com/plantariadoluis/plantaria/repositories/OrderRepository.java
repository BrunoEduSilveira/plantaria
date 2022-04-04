package com.plantariadoluis.plantaria.repositories;

import com.plantariadoluis.plantaria.models.CustomerModel;
import com.plantariadoluis.plantaria.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    @Query(value = "SELECT * FROM `orders` WHERE customer_id = ?1",
            nativeQuery = true)
    List<OrderModel> findOrdersByCustomer(long id);


}
