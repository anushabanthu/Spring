
package com.cjss.ecommerce.repository;

import com.cjss.ecommerce.entity.OrderEntity;
import com.cjss.ecommerce.entity.ProductQuantityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}
