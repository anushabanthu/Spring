package com.cjss.DailyActivities.service;
import com.cjss.DailyActivities.entity.FoodItem;
import com.cjss.DailyActivities.entity.Person;
import com.cjss.DailyActivities.model.FoodItemModel;
import com.cjss.DailyActivities.model.FoodModel;
import com.cjss.DailyActivities.model.FoodTimeModel;
import com.cjss.DailyActivities.entity.FoodTime;
import com.cjss.DailyActivities.model.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjss.DailyActivities.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    public FoodModel getMealDetailsById(Integer date){
        Optional<FoodTime> foodTime = foodRepository.findById(date);    //findById is available by default from JPARepository
//        foodTime = foodRepository.findByDate(date);
//        foodTime = foodRepository.findByBreakfastTime(20);

        FoodModel foodModel = new FoodModel();
        FoodTimeModel foodTimeModel = new FoodTimeModel();
        FoodItemModel foodItemModel = new FoodItemModel();
        List<PersonModel> personModelList = new ArrayList<>();
        PersonModel personModel = new PersonModel();

        foodTimeModel.setBreakfastTime(foodTime.get().getBreakfastTime());
        foodTimeModel.setLunchTime(foodTime.get().getLunchTime());
        foodTimeModel.setDinnerTime(foodTime.get().getDinnerTime());
        foodTimeModel.setDate(foodTime.get().getDate());
        foodModel.setFoodTime(foodTimeModel);

        foodItemModel.setBreakfastItem(foodTime.get().getFoodItem().getBreakfastItem());
        foodItemModel.setLunchItem(foodTime.get().getFoodItem().getLunchItem());
        foodItemModel.setDinnerItem(foodTime.get().getFoodItem().getDinnerItem());
        foodItemModel.setDate(foodTime.get().getFoodItem().getDate());
        foodModel.setFoodItem(foodItemModel);

        System.out.println(foodTime.get().getPersons());
        foodTime.get().getPersons().forEach(person->{
            System.out.println(person);
            System.out.println(person.getId());
            System.out.println(person.getName());
            personModel.setId(person.getId());
            personModel.setName(person.getName());
            personModelList.add(personModel);
        });
        foodModel.setPersons(personModelList);

        return foodModel;
    }

    public FoodTime addMealDetails(FoodModel foodModel){
        FoodTime foodTime = new FoodTime();
        FoodItem foodItem = new FoodItem();
        List<Person> persons = new ArrayList<>();

        foodTime.setBreakfastTime(foodModel.getFoodTime().getBreakfastTime());
        foodTime.setLunchTime(foodModel.getFoodTime().getLunchTime());
        foodTime.setDinnerTime(foodModel.getFoodTime().getDinnerTime());

        foodItem.setBreakfastItem(foodModel.getFoodItem().getBreakfastItem());
        foodItem.setLunchItem(foodModel.getFoodItem().getLunchItem());
        foodItem.setDinnerItem(foodModel.getFoodItem().getDinnerItem());

        foodTime.setFoodItem(foodItem);

        foodModel.getPersons().forEach(personModel->{
            Person person = new Person();
            person.setId(personModel.getId());
            person.setName(personModel.getName());
            persons.add(person);
        });
        foodTime.setPersons(persons);

        return foodRepository.save(foodTime);
    }
}
