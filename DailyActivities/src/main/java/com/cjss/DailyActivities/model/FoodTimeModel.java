package com.cjss.DailyActivities.model;

public class FoodTimeModel {
    private Integer date;
    private Integer breakfastTime;  //Time spent on breakfast in mins
    private Integer lunchTime;  //Time spent on breakfast in mins
    private Integer dinnerTime; //Time spent on breakfast in mins

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
}
