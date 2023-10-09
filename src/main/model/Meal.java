package model;

// Represents a class that can make a single new Meal
public class Meal {
    private String name;
    private double calories;
    private String mealTime;
    private String day;
    private String meal;


    // REQUIRES: mealTime = Breakfast/Lunch/Dinner (Input one of these correctly)
    // EFFECTS: constructs a single new meal with the given name, calories, mealTime, and day
    public Meal(String name, double calories, String mealTime, String day) {
        this.name = name.strip();
        this.calories = calories;
        this.mealTime = mealTime.strip();
        this.day = day;
    }

    // MODIFIES: this
    // EFFECTS: makes a single String with all the details of a meal
    public void makeMeal() {
        this.meal = getMealName() + ": " + String.format("%.2f", getCalories()) + " calories for " + getMealTime();
    }

    // GETTERS
    public String getMealName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public String getMealTime() {
        return mealTime;
    }

    public String getDay() {
        return day;
    }

    public String getMeal() {
        makeMeal();
        return meal;
    }
}
