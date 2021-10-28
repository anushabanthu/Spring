package com.cjss.DailyActivities.repository;

import com.cjss.DailyActivities.entity.FoodTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodTime, Integer> {
//    Find entries in db by @Column in Food entity.Method name should prefix with findBy followed by column variable name with starting letter in capital
    Optional<FoodTime> findByBreakfastTime(Integer breakfastTime);
    Optional<FoodTime> findByDate(Integer date);  // This is same as findById since Date is defined as @Id in Food entity

}
