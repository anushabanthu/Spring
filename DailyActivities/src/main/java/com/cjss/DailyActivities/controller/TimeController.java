package com.cjss.DailyActivities.controller;

import com.cjss.DailyActivities.entity.FoodTime;
import com.cjss.DailyActivities.model.FoodItemModel;
import com.cjss.DailyActivities.model.FoodTimeModel;
import com.cjss.DailyActivities.model.FoodModel;
import com.cjss.DailyActivities.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {
    Integer date;
    @Autowired
    private FoodService foodService;
    @PostMapping("/get-total-meal-time-by-id")
    public FoodModel getMealDetailsById(Integer date){
        return foodService.getMealDetailsById(date);
    }
    @PostMapping("/add-meal-details")
    public FoodTime addMealDetails(@RequestBody FoodModel food){
        return foodService.addMealDetails(food);
    }
}
