package model;

// Represents a class that can make a Single Workout
public class Workout {
    private String name;
    private int reps;
    private int sets;
    private String day;
    private String workout;


    // REQUIRES: Enter only whole numbers for sets and reps
    // EFFECTS; constructs a single new Workout with the given name, sets, reps and day
    public Workout(String name, int sets, int reps, String day) {
        this.name = name.strip();
        this.sets = sets;
        this.reps = reps;
        this.day = day;
    }

    // MODIFIES: this
    // EFFECTS: makes a single String of Workout with all the details a Workout
    public void makeWorkout() {
        this.workout = getName() + ": " + getSets() + " sets of " + getReps() + " reps";
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public String getDay() {
        return day;
    }

    public String getWorkout() {
        makeWorkout();
        return workout;
    }


}
