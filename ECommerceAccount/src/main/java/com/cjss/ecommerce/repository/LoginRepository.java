
package com.cjss.ecommerce.repository;

import com.cjss.ecommerce.entity.LoginEntity;
import com.cjss.ecommerce.entity.RegisterCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {

}
