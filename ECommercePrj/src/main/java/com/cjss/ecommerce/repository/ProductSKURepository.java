
package com.cjss.ecommerce.repository;

import com.cjss.ecommerce.entity.ProductSKUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSKURepository extends JpaRepository<ProductSKUEntity, Integer> {

}
