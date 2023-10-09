package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTest {
    private Meal testMeal;

    @BeforeEach
    void runBefore() {
        testMeal = new Meal("Burger", 250.99, "Dinner", "Tuesday");
    }

    @Test
    void testConstuctor() {
        assertEquals("Burger", testMeal.getMealName());
        assertEquals(250.99, testMeal.getCalories());
        assertEquals("Dinner", testMeal.getMealTime());
        assertEquals("Tuesday", testMeal.getDay());
    }

    @Test
    void testMakeMeal() {
        assertEquals("Burger: 250.99 calories for Dinner", testMeal.getMeal());
    }
}
