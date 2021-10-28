package com.cjss.DailyActivities.model;

import com.cjss.DailyActivities.entity.Person;

import java.util.List;

public class FoodModel {
    private FoodItemModel foodItem;
    private FoodTimeModel foodTime;
    private List<PersonModel> persons;

    public FoodItemModel getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItemModel foodItem) {
        this.foodItem = foodItem;
    }

    public FoodTimeModel getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(FoodTimeModel foodTime) {
        this.foodTime = foodTime;
    }

    public List<PersonModel> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonModel> persons) {
        this.persons = persons;
    }
}
