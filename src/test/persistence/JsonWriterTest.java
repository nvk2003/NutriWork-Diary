package persistence;

import model.ListsMaker;
import model.Meal;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ListsMaker lm = new ListsMaker("No File");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLists() {
        try {
            ListsMaker lm = new ListsMaker("Empty Workouts and Meals");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLists.json");
            writer.open();
            writer.write(lm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLists.json");
            lm = reader.read();
            assertEquals("Empty Workouts and Meals", lm.getName());
            assertEquals(0, lm.getWorkouts().size());
            assertEquals(0, lm.getMeals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLists() {
        try {
            ListsMaker lm = new ListsMaker("General Workouts and Meals");
            Workout workout = new Workout("Squats", 5, 10, "Monday");
            lm.addWorkout(workout);
            Meal meal = new Meal("Rice", 50, "Lunch", "Tuesday");
            lm.addMeal(meal);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLists.json");
            writer.open();
            writer.write(lm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLists.json");
            lm = reader.read();
            assertEquals("General Workouts and Meals", lm.getName());
            List<Workout> workouts = lm.getWorkouts();
            List<Meal> meals = lm.getMeals();
            assertEquals(1, workouts.size());
            assertEquals(1, meals.size());
            checkWorkout("Squats", 5, 10, "Monday", workouts.get(0));
            checkMeal("Rice", 50, "Lunch", "Tuesday", meals.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
