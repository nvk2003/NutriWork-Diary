package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Makes a List with all the operations for both meals and workouts

// Based on the code structure from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class ListsMaker implements Writable {
    private ArrayList<Workout> workouts;
    private ArrayList<Workout> workoutsByDay;
    private ArrayList<Meal> meals;
    private ArrayList<Meal> mealsByDay;
    private String name;

    // EFFECTS: constructs a ListsMaker with empty workouts and meals list, and a given name
    public ListsMaker(String name) {
        this.workouts = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // WORKOUT

    // MODIFIES: this, workouts
    // EFFECTS: adds a given workout to the list of workouts
    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    // MODIFIES: this, workouts
    // EFFECTS: removes a given workout from the list of workouts
    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }

    // MODIFIES: this, workoutsByDay
    // EFFECTS: sorts out the list of workouts by the given day and produces a list of workouts for that day
    public void makeWorkoutsByDay(String day) {
        this.workoutsByDay = new ArrayList<>();
        for (Workout workout: workouts) {
            if (workout.getDay().equals(day)) {
                this.workoutsByDay.add(workout);
            }
        }
    }

    // GETTERS
    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public ArrayList<Workout> getWorkoutsByDay() {
        return workoutsByDay;
    }




    // MEAL

    // MODIFIES: this, meals
    // EFFECTS: adds a given meal to the list of meals
    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    // MODIFIES: this, meals
    // EFFECTS: removes a given meal from the list of meals
    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
    }

    // MODIFIES: this, mealsByDay
    // EFFECTS: sorts out the list of meals by the given day and produces a list of meals for that day
    public void makeMealsByDay(String day) {
        this.mealsByDay = new ArrayList<>();
        for (Meal meal: meals) {
            if (meal.getDay().equals(day)) {
                this.mealsByDay.add(meal);
            }
        }
    }

    // GETTERS
    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public ArrayList<Meal> getMealsByDay() {
        return mealsByDay;
    }


    // EFFECTS: constructs three different files in the json file
    @Override
    public JSONObject toJson() {
        JSONObject json  = new JSONObject();
        json.put("name", name);
        json.put("workouts", workoutsToJson());
        json.put("meals", mealsToJson());
        return json;
    }

    // EFFECTS: returns all workouts that are in this list as a JSONArray
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Workout workout : workouts) {
            jsonArray.put(workout.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns all meals that are in this list as a JSONArray
    private JSONArray mealsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Meal meal : meals) {
            jsonArray.put(meal.toJson());
        }
        return jsonArray;
    }
}
