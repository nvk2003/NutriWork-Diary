package ui;

import model.ListsMaker;
import model.Meal;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Workouts and Meal Plan Managing System Application

// Based on the code structure from TellerApp and JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class NutriWorkDiary {
    private static final String JSON_STORE = "./data/workoutsAndMeals.json";
    private ListsMaker workoutsAndMeals;
    private Scanner input;
    private String day;
    private ArrayList<String> days;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: runs the NutriWorkDiary Application
    public NutriWorkDiary() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes the user input
    private void runApp() {
        days = new ArrayList<>();
        setDays();
        workoutsAndMeals = new ListsMaker("Workouts and Meals");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        String entryValue;

        System.out.println("\nWelcome To NutriWork Diary");

        while (true) {
            displayMainMenu();
            entryValue = input.next();
            entryValue = entryValue.toLowerCase();

            if (entryValue.equals("q")) {
                break;
            } else {
                run(entryValue);
            }
        }
        System.out.println("Bye Bye!");
    }

    // EFFECTS: displays the Main Menu
    private void displayMainMenu() {
        System.out.println("\nSelect from the following options:");
        System.out.println("\tw -> Workouts");
        System.out.println("\tm -> Meals");
        System.out.println("\tv -> View Both Workout & Meals");
        System.out.println("\td -> View Both Workout & Meals By Day");
        System.out.println("\ts -> Save Workouts & Meals");
        System.out.println("\tl -> Load Workouts & Meals");
        System.out.println("\tq -> Quit");
    }

    // EFFECTS: Sets the days of the week
    private void setDays() {
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
    }

    // EFFECTS: returns the ArrayList of days
    private ArrayList<String> getDays() {
        return days;
    }

    // EFFECTS: runs the main display menu according to the user input
    private void run(String entryValue) {
        if (entryValue.equals("w")) {
            runWorkout();
        } else if (entryValue.equals("m")) {
            runMeal();
        } else if (entryValue.equals("v")) {
            viewWorkoutAndMeal();
        } else if (entryValue.equals("d")) {
            viewWorkoutAndMealByDay();
        } else if (entryValue.equals("s")) {
            saveWorkoutsAndMeals();
        } else if (entryValue.equals("l")) {
            loadWorkoutsAndMeals();
        } else {
            System.out.println("\nPlease Insert Valid Input  !!!");
        }
    }

    // MODIFIES: this
    // EFFECTS: runs  all workout operations
    private void runWorkout() {
        String select;
        while (true) {
            displayWorkoutMenu();
            select = input.next();
            select = select.toLowerCase();

            if (select.equals("x")) {
                break;
            } else if (select.equals("a")) {
                addWorkout();
            } else if (select.equals("r")) {
                removeWorkout();
            } else if (select.equals("v")) {
                viewWorkouts();
            } else if (select.equals("d")) {
                viewWorkoutsWithDays();
            } else {
                System.out.println("\nPlease Insert Valid Input !!!");
            }
        }
    }


    // EFFECTS: prints out the Workout Display Menu
    private void displayWorkoutMenu() {
        System.out.println("\nSelect from the following:");
        System.out.println("\ta -> Add Workout");
        System.out.println("\tr -> Remove Workout");
        System.out.println("\tv -> View Workouts");
        System.out.println("\td -> View Workouts With Days");
        System.out.println("\tx -> Go Back To Main Menu");
    }


    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: adds New workouts to the list
    private void addWorkout() {
        System.out.println("\nPlease Insert Workout Details:");
        System.out.println("Enter Workout Name: ");
        String name = input.next();

//        // WORK ON THIS
////        int sets = input.nextInt();
//        int sets = 0;
//        while (sets == 0) {
//
//            try {
//                System.out.println("Enter No. of Sets: ");
//                sets = input.nextInt();
//            } catch (Exception e) {
//                System.out.println("Please insert only Integers");
////                break;
//            }
//        }
//
//        // WORK ON THIS
//        int reps = 0;
//        while (reps == 0) {
//            System.out.println("Enter No. of Reps: ");
////        int reps = input.nextInt();
//            try {
//                reps = input.nextInt();
////                break;
//            } catch (Exception e) {
//                System.out.println("Please insert only Integers");
////                break;
//            }
//        }
        System.out.println("Enter No. of Sets: ");
        int sets = input.nextInt();

        System.out.println("Enter No. of Reps: ");
        int reps = input.nextInt();


        setDay();

        Workout workout = new Workout(name, sets, reps, day);

        workoutsAndMeals.addWorkout(workout);

        System.out.println("\nWorkout Added !!!");
    }


    // MODIFIES: this, day
    // EFFECTS: sets the given day to the 'day' field
    private void setDay() {
        day = "";
        do {
//            daysDisplayMenu();
            System.out.println("Enter Day Of The Week: ");
            String dayInput = input.next();
            dayInput = dayInput.toLowerCase();
            if (dayInput.startsWith("mon")) {
                day = "Monday";
//                break;
            } else if (dayInput.startsWith("tue")) {
                day = "Tuesday";
//                break;
            } else if (dayInput.startsWith("wed")) {
                day = "Wednesday";
//                break;
            } else if (dayInput.startsWith("thu")) {
                day = "Thursday";
//                break;
            } else if (dayInput.startsWith("fri")) {
                day = "Friday";
//                break;
            } else if (dayInput.startsWith("sat")) {
                day = "Saturday";
//                break;
            } else if (dayInput.startsWith("sun")) {
                day = "Sunday";
//                break;
            } else {
                System.out.println("\nPlease Insert Valid Input\n");
            }
        } while (day.equals(""));
    }


    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: processes user input and removes workout accordingly
    private void removeWorkout() {
        if (workoutsAndMeals.getWorkouts().size() == 0) {
            System.out.println("\nNo Workouts To Be Removed !!!");
        } else {
            while (true) {
                removeWorkoutDisplayMenu();
                String removeInput = input.next();
                if (removeInput.equals("a")) {
                    removeOneWorkout();
                    break;
                } else if (removeInput.equals("b")) {
                    removeAllWorkouts();
                    break;
                } else if (removeInput.equals("x")) {
                    break;
                } else {
                    System.out.println("\n Please Insert Valid Input!!!");
                }
            }
        }
    }


    // EFFECTS: prints out the Display menu for removing workouts
    private void removeWorkoutDisplayMenu() {
        System.out.println("\nSelect from the following options:");
        System.out.println("\ta -> Remove One Workout");
        System.out.println("\tb -> Remove All Workouts");
        System.out.println("\tx -> Go Back To Workout Menu");
    }

    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: removes all the workouts from the list
    private void removeAllWorkouts() {
        workoutsAndMeals.getWorkouts().removeAll(workoutsAndMeals.getWorkouts());
        System.out.println("\nRemoved All Workouts !!!");
    }

    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: removes the chosen workout from the list
    private void removeOneWorkout() {
        ArrayList<Workout> allWorkouts = workoutsAndMeals.getWorkouts();
        while (true) {
            viewWorkouts();
            System.out.println("\nEnter Workout Number: ");
            int removeInput = input.nextInt();
            if (removeInput <= allWorkouts.size() && removeInput != 0) {
                workoutsAndMeals.removeWorkout(allWorkouts.get(removeInput - 1));
                System.out.println("\nWorkout Removed !!!");
                break;
            } else {
                System.out.println("\nPlease Insert Valid Input !!!");
            }
        }
    }


    // EFFECTS: prints out all the workouts in the list
    private void viewWorkouts() {
        ArrayList<Workout> allWorkouts = workoutsAndMeals.getWorkouts();
        int x = 1;
        if (allWorkouts.size() == 0) {
            System.out.println("\nNo Workouts To View !!!");
        } else {
            System.out.println("\nAll Workouts:");
            for (Workout workout : allWorkouts) {
                System.out.println(x + ". " + workout.getWorkout() + " on " + workout.getDay());
                x++;
            }
        }
    }


    // EFFECTS: prints out all the workouts in the list by filtering them by day
    private void viewWorkoutsWithDays() {
        System.out.println("\nWorkouts For The Week:");
        for (String day : getDays()) {
            workoutsAndMeals.makeWorkoutsByDay(day);
            ArrayList<Workout> workoutsForDay = workoutsAndMeals.getWorkoutsByDay();
            System.out.println("\n" + day + ": ");
            if (workoutsForDay.size() == 0) {
                System.out.println("No Workouts For The Day !!!");
            } else {
                int x = 1;
                for (Workout workout : workoutsForDay) {
                    System.out.println(x + ". " + workout.getWorkout());
                    x++;
                }
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: runs all the operations for meals
    private void runMeal() {
        String select;
        while (true) {
            displayMealMenu();
            select = input.next();
            select = select.toLowerCase();

            if (select.equals("x")) {
                break;
            } else if (select.equals("a")) {
                addMeal();
            } else if (select.equals("r")) {
                removeMeal();
            } else if (select.equals("v")) {
                viewMeals();
            } else if (select.equals("d")) {
                viewMealsWithDays();
            } else {
                System.out.println("\nPlease Insert Valid Input !!!");
            }

        }
    }


    // EFFECTS: prints out the Display menu for operations of meals
    private void displayMealMenu() {
        System.out.println("\nSelect from the following options: ");
        System.out.println("\ta -> Add Meal");
        System.out.println("\tr -> Remove Meal");
        System.out.println("\tv -> View All Meals");
        System.out.println("\td -> View All Meals With Days");
        System.out.println("\tx -> Go Back To Main Menu");
    }


    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: adds a given meal to the list
    private void addMeal() {
        System.out.println("\nPlease Insert Meal Details: ");
        System.out.println("Enter Meal Name: ");
        String name = input.next();
        System.out.println("Enter No. of Calories: ");
        double calories = input.nextDouble();
        System.out.println("Enter Meal Time (Breakfast/Lunch/Dinner): ");
        String mealTime = input.next();

        setDay();

        Meal meal = new Meal(name, calories, mealTime, day);

        workoutsAndMeals.addMeal(meal);

        System.out.println("\nMeal Added !!!");
    }


    // MODIFIES: this
    // EFFECTS: processes user input and removes meals accordingly
    private void removeMeal() {
        if (workoutsAndMeals.getMeals().size() == 0) {
            System.out.println("\nNo Meals To Be Removed !!!");
        } else {
            while (true) {
                removeMealDisplayMenu();
                String removeInput = input.next();
                if (removeInput.equals("a")) {
                    removeOneMeal();
                    break;
                } else if (removeInput.equals("b")) {
                    removeAllMeals();
                    break;
                } else if (removeInput.equals("x")) {
                    break;
                } else {
                    System.out.println("\n Please Insert Valid Input!!!");
                }
            }
        }
    }

    // EFFECTS: prints out the display menu for removing meals
    private void removeMealDisplayMenu() {
        System.out.println("\nSelect from the following options:");
        System.out.println("\ta -> Remove One Meal");
        System.out.println("\tb -> Remove All Meals");
        System.out.println("\tx -> Go Back To Meal Menu");
    }

    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: removes all the meals from the list
    private void removeAllMeals() {
        workoutsAndMeals.getMeals().removeAll(workoutsAndMeals.getMeals());
        System.out.println("\nRemoved All Meals !!!");
    }

    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: removes the chosen meal from the list
    private void removeOneMeal() {
        ArrayList<Meal> allMeals = workoutsAndMeals.getMeals();
        while (true) {
            viewMeals();
            System.out.println("\nEnter Meal Number: ");
            int removeInput = input.nextInt();
            if (removeInput <= allMeals.size() && removeInput != 0) {
                workoutsAndMeals.removeMeal(allMeals.get(removeInput - 1));
                System.out.println("\nMeal Removed !!!");
                break;
            } else {
                System.out.println("\nPlease Insert Valid Input !!!");
            }
        }
    }

    // EFFECTS: prints out all the meals in the list
    private void viewMeals() {
        ArrayList<Meal> allMeals = workoutsAndMeals.getMeals();
        int x = 1;
        if (allMeals.size() == 0) {
            System.out.println("\nNo Meals To View !!!");
        } else {
            System.out.println("\nAll Meals:");
            for (Meal meal : allMeals) {
                System.out.println(x + ". " + meal.getMeal() + " on " + meal.getDay());
                x++;
            }
        }
    }

    // EFFECTS: prints out all the meals in the list by filtering them by day
    private void viewMealsWithDays() {
        System.out.println("\nMeals For The Week:");
        for (String day : getDays()) {
            workoutsAndMeals.makeMealsByDay(day);
            ArrayList<Meal> mealsForDay = workoutsAndMeals.getMealsByDay();
            System.out.println("\n" + day + ": ");
            if (mealsForDay.size() == 0) {
                System.out.println("No Meals For The Day !!!");
            } else {
                int x = 1;
                for (Meal meal : mealsForDay) {
                    System.out.println(x + ". " + meal.getMeal());
                    x++;
                }
            }
        }
    }


    // EFFECTS: prints out both workouts and meals together
    private void viewWorkoutAndMealByDay() {
        viewWorkoutsWithDays();

        System.out.println("\n==========================================");

        viewMealsWithDays();
    }

    // EFFECTS: prints out both workouts and meals together by filtering them by day
    private void viewWorkoutAndMeal() {
        viewWorkouts();

        System.out.println("\n==========================================");

        viewMeals();
    }


    // EFFECTS: saves all workouts and meals to the file
    private void saveWorkoutsAndMeals() {
        try {
            jsonWriter.open();
            jsonWriter.write(workoutsAndMeals);
            jsonWriter.close();
            System.out.println("\nSaved " + workoutsAndMeals.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this, workoutsAndMeals
    // EFFECTS: loads all workouts and meals from the file
    private void loadWorkoutsAndMeals() {
        try {
            workoutsAndMeals = jsonReader.read();
            System.out.println("\nLoaded " + workoutsAndMeals.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
