package persistence;

import model.ListsMaker;
import model.Meal;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListsMaker lm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLists() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLists.json");
        try {
            ListsMaker lm = reader.read();
            assertEquals("Empty Workouts and Meals", lm.getName());
            assertEquals(0, lm.getWorkouts().size());
            assertEquals(0, lm.getMeals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLists() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLists.json");
        try {
            ListsMaker lm = reader.read();
            assertEquals("General Workouts and Meals", lm.getName());
            List<Workout> workouts = lm.getWorkouts();
            List<Meal> meals  = lm.getMeals();
            assertEquals(2, workouts.size());
            assertEquals(1, meals.size());
            checkWorkout("Squats", 4, 10, "Monday", workouts.get(0));
            checkWorkout("Lunges", 5, 8, "Tuesday", workouts.get(1));
            checkMeal("Burger", 230.1, "Dinner", "Tuesday", meals.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
