package persistence;

import model.Meal;
import model.Workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkWorkout(String name, int sets, int reps, String day, Workout workout) {
        assertEquals(name, workout.getName());
        assertEquals(sets, workout.getSets());
        assertEquals(reps, workout.getReps());
        assertEquals(day, workout.getDay());
    }

    protected void checkMeal(String mealName, double calories, String mealTime, String day, Meal meal) {
        assertEquals(mealName, meal.getMealName());
        assertEquals(calories, meal.getCalories());
        assertEquals(mealTime, meal.getMealTime());
        assertEquals(day, meal.getDay());
    }
}
