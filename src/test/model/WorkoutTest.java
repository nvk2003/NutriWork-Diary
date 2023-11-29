package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    private Workout testWorkout;

    @BeforeEach
    void runBefore() {
        testWorkout = new Workout("Squats", 3, 10, "Monday");
    }

    @Test
    void testConstructor() {
        assertEquals("Squats", testWorkout.getName());
        assertEquals(3, testWorkout.getSets());
        assertEquals(10, testWorkout.getReps());
        assertEquals("Monday", testWorkout.getDay());
    }

    @Test
    void testMakeWorkout() {
        assertEquals("Squats: 3 sets of 10 reps", testWorkout.getWorkout());
    }
}
