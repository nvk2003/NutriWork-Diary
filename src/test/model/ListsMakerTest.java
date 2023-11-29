package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListsMakerTest {
    private ListsMaker testListsMaker;

    @BeforeEach
    void runBefore() {
        testListsMaker = new ListsMaker("Test List");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testListsMaker.getWorkouts().size());
        assertEquals(0, testListsMaker.getMeals().size());
        assertEquals("Test List", testListsMaker.getName());
    }

    @Test
    void testAddOneWorkout() {
        Workout workout = new Workout("Squats", 3, 10, "Tuesday");
        testListsMaker.addWorkout(workout);

        assertEquals(1, testListsMaker.getWorkouts().size());
        assertEquals(workout, testListsMaker.getWorkouts().get(0));
    }

    @Test
    void testAddMultipleWorkouts() {
        Workout workout1 = new Workout("Squats", 3, 10, "Tuesday");
        testListsMaker.addWorkout(workout1);
        assertEquals(1, testListsMaker.getWorkouts().size());
        assertEquals(workout1, testListsMaker.getWorkouts().get(0));


        Workout workout2 = new Workout("Lunges", 5, 8, "Monday");
        testListsMaker.addWorkout(workout2);
        assertEquals(2, testListsMaker.getWorkouts().size());
        assertEquals(workout2, testListsMaker.getWorkouts().get(1));

        Workout workout3 = new Workout("Bridges", 3, 15, "Thursday");
        testListsMaker.addWorkout(workout3);
        assertEquals(3, testListsMaker.getWorkouts().size());
        assertEquals(workout3, testListsMaker.getWorkouts().get(2));
    }

    @Test
    void testRemoveOneWorkout() {
        Workout workout = new Workout("Squats", 3, 10, "Tuesday");
        testListsMaker.addWorkout(workout);

        assertEquals(1, testListsMaker.getWorkouts().size());
        assertEquals(workout, testListsMaker.getWorkouts().get(0));

        testListsMaker.removeWorkout(workout);
        assertEquals(0, testListsMaker.getWorkouts().size());
    }

    @Test
    void testRemoveMultipleWorkouts() {
        Workout workout1 = new Workout("Squats", 3, 10, "Tuesday");
        Workout workout2 = new Workout("Lunges", 5, 8, "Monday");
        Workout workout3 = new Workout("Bridges", 3, 15, "Thursday");
        testListsMaker.addWorkout(workout1);
        testListsMaker.addWorkout(workout2);
        testListsMaker.addWorkout(workout3);

        testListsMaker.removeWorkout(workout2);
        assertEquals(2, testListsMaker.getWorkouts().size());
        assertEquals(workout3, testListsMaker.getWorkouts().get(1));

        testListsMaker.removeWorkout(workout1);
        assertEquals(1, testListsMaker.getWorkouts().size());
        assertEquals(workout3, testListsMaker.getWorkouts().get(0));
    }


    @Test
    void testMakeWorkoutsByDay() {
        Workout workout1 = new Workout("Squats", 3, 10, "Thursday");
        Workout workout2 = new Workout("Lunges", 5, 8, "Monday");
        Workout workout3 = new Workout("Bridges", 3, 15, "Tuesday");
        Workout workout4 = new Workout("Clamshells (Each Leg)", 3, 12, "Thursday");
        testListsMaker.addWorkout(workout1);
        testListsMaker.addWorkout(workout2);
        testListsMaker.addWorkout(workout3);
        testListsMaker.addWorkout(workout4);

        testListsMaker.makeWorkoutsByDay("Monday");
        assertEquals(1, testListsMaker.getWorkoutsByDay().size());
        assertEquals(workout2, testListsMaker.getWorkoutsByDay().get(0));

        testListsMaker.makeWorkoutsByDay("Thursday");
        assertEquals(2, testListsMaker.getWorkoutsByDay().size());
        assertEquals(workout1, testListsMaker.getWorkoutsByDay().get(0));
        assertEquals(workout4, testListsMaker.getWorkoutsByDay().get(1));
    }


    @Test
    void testAddMultipleMeals() {
        Meal meal1 = new Meal("Burger", 210.20, "Lunch", "Friday");
        Meal meal2 = new Meal("Biryani", 500, "Dinner", "Tuesday");
        Meal meal3 = new Meal("Rice", 100.10, "Breakfast", "Sunday");
        testListsMaker.addMeal(meal1);
        assertEquals(1, testListsMaker.getMeals().size());
        assertEquals(meal1, testListsMaker.getMeals().get(0));
        testListsMaker.addMeal(meal2);
        assertEquals(2, testListsMaker.getMeals().size());
        assertEquals(meal2, testListsMaker.getMeals().get(1));
        testListsMaker.addMeal(meal3);
        assertEquals(3, testListsMaker.getMeals().size());
        assertEquals(meal3, testListsMaker.getMeals().get(2));
    }


    @Test
    void testRemoveMultipleMeals() {
        Meal meal1 = new Meal("Burger", 210.20, "Lunch", "Friday");
        Meal meal2 = new Meal("Biryani", 500, "Dinner", "Tuesday");
        Meal meal3 = new Meal("Rice", 100.10, "Breakfast", "Sunday");
        testListsMaker.addMeal(meal1);
        testListsMaker.addMeal(meal2);
        testListsMaker.addMeal(meal3);

        testListsMaker.removeMeal(meal1);
        assertEquals(2, testListsMaker.getMeals().size());
        assertEquals(meal2, testListsMaker.getMeals().get(0));

        testListsMaker.removeMeal(meal3);
        assertEquals(1, testListsMaker.getMeals().size());
        assertEquals(meal2, testListsMaker.getMeals().get(0));
    }


    @Test
    void testMakeMealsByDay() {
        Meal meal1 = new Meal("Burger", 210.20, "Lunch", "Friday");
        Meal meal2 = new Meal("Biryani", 500, "Dinner", "Tuesday");
        Meal meal3 = new Meal("Rice", 100.10, "Breakfast", "Sunday");
        Meal meal4 = new Meal("Donair", 253.23, "Dinner", "Friday");
        testListsMaker.addMeal(meal1);
        testListsMaker.addMeal(meal2);
        testListsMaker.addMeal(meal3);
        testListsMaker.addMeal(meal4);

        testListsMaker.makeMealsByDay("Tuesday");
        assertEquals(1, testListsMaker.getMealsByDay().size());
        assertEquals(meal2, testListsMaker.getMealsByDay().get(0));

        testListsMaker.makeMealsByDay("Friday");
        assertEquals(2, testListsMaker.getMealsByDay().size());
        assertEquals(meal1, testListsMaker.getMealsByDay().get(0));
        assertEquals(meal4, testListsMaker.getMealsByDay().get(1));
    }
}
