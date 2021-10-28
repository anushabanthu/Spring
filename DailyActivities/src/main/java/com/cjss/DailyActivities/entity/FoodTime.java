package com.cjss.DailyActivities.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="food_time")
public class FoodTime {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)       // Id is autogenerated in sequence(1,2,3...).Other values passed as input will be ignored.
    private Integer date;
    @Column
    private Integer breakfastTime;  //Time spent on breakfast in mins
    @Column
    private Integer lunchTime;  //Time spent on breakfast in mins
    @Column
    private Integer dinnerTime; //Time spent on breakfast in mins
    @OneToOne(cascade = CascadeType.ALL)
    private FoodItem foodItem;
    @OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "foodTime")    // Post request is working but only last entry of person is added to
//    all elements of list. In get request person is empty
//    @OneToMany(
//        mappedBy = "foodTime",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//    )
//    @OneToMany(cascade=CascadeType.ALL, mappedBy="foodTime")
    private List<Person> persons;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getBreakfastTime() {
        return breakfastTime;
    }

    public void setBreakfastTime(Integer breakfastTime) {
        this.breakfastTime = breakfastTime;
    }

    public Integer getLunchTime() {
        return lunchTime;
    }

    public void setLunchTime(Integer lunchTime) {
        this.lunchTime = lunchTime;
    }

    public Integer getDinnerTime() {
        return dinnerTime;
    }

    public void setDinnerTime(Integer dinnerTime) {
        this.dinnerTime = dinnerTime;
    }

    public void setFoodItem(FoodItem foodItem){
        this.foodItem = foodItem;
    }
    public FoodItem getFoodItem(){
        return this.foodItem;
    }

    public List<Person> getPersons() {
        return persons;
    }
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
//
//    public void addPerson(Person person) {
//        persons.add(person);
//        person.setFoodTime(this);
//    }
//
//    public void removePerson(Person person) {
//        persons.remove(person);
//        person.setFoodTime(null);
//    }
}