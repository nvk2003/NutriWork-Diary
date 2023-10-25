package persistence;

import model.ListsMaker;
import model.Meal;
import model.Workout;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads ListsMaker from JSON data stored in file

// Based on the code structure from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListsMaker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListsMaker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListMaker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListsMaker from JSON object and returns it
    private ListsMaker parseListMaker(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListsMaker lm = new ListsMaker(name);
        addWorkouts(lm, jsonObject);
        addMeals(lm, jsonObject);
        return lm;
    }

    // MODIFIES: this, lm
    // EFFECTS: parses workouts from JSON object and adds them to ListsMaker
    private void addWorkouts(ListsMaker lm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workouts");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            addWorkout(lm, nextWorkout);
        }
    }

    // MODIFIES: this, lm
    // EFFECTS: parses workout from JSON object and adds it to ListsMaker
    private void addWorkout(ListsMaker lm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        String day = jsonObject.getString("day");
        Workout workout = new Workout(name, sets, reps, day);
        lm.addWorkout(workout);
    }

    // MODIFIES: this, lm
    // EFFECTS: parses meals from JSON object and adds them to ListsMaker
    private void addMeals(ListsMaker lm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(lm, nextMeal);
        }
    }

    // MODIFIES: this, lm
    // EFFECTS: parses meal from JSON object and adds it to ListsMaker
    private void addMeal(ListsMaker lm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double calories = jsonObject.getDouble("calories");
        String mealTime = jsonObject.getString("mealTime");
        String day = jsonObject.getString("day");
        Meal meal = new Meal(name, calories, mealTime, day);
        lm.addMeal(meal);
    }

}
