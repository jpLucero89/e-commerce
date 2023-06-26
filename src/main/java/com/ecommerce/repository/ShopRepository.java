package com.ecommerce.repository;

import com.ecommerce.entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shops,Long> {

}
