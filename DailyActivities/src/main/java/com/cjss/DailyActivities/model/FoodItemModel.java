package com.cjss.DailyActivities.model;

public class FoodItemModel {
    private Integer date;
    private Integer breakfastItem;  //Time spent on breakfast in mins
    private Integer lunchItem;  //Time spent on breakfast in mins
    private Integer dinnerItem; //Time spent on breakfast in mins

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getBreakfastItem() {
        return breakfastItem;
    }

    public void setBreakfastItem(Integer breakfastItem) {
        this.breakfastItem = breakfastItem;
    }

    public Integer getLunchItem() {
        return lunchItem;
    }

    public void setLunchItem(Integer lunchItem) {
        this.lunchItem = lunchItem;
    }

    public Integer getDinnerItem() {
        return dinnerItem;
    }

    public void setDinnerItem(Integer dinnerItem) {
        this.dinnerItem = dinnerItem;
    }
}
