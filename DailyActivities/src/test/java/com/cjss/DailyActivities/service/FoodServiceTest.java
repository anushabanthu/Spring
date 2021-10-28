//package com.cjss.DailyActivities.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//import com.cjss.DailyActivities.entity.Food;
//
//@RunWith(MockitoJUnitRunner.class)
//public class FoodServiceTest {
//    @InjectMocks FoodService foodService;
//    @Test
//    public void foodTest(){
//        Food food = new Food();
//
//        foodService.addBreakfastTime(200,10);
//        foodService.addLunchTime(300,10);
//        foodService.addDinnerTime(300,10);
//        food.setBreakfastTime(10);
//        food.setLunchTime(10);
//        food.setDinnerTime(10);
//        food.setDate(1);
//        foodService.saveToDB(food);
//        System.out.println(foodService.getTotalMealTime(10));
//    }
//}
