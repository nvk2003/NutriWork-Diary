package ui;

import model.Event;
import model.EventLog;
import model.ListsMaker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// Represents a NutriWorkDairyApp's Main Window for the user to start using the application
public class NutriWorkDiaryApp {
    private static final int WIDTH = 850;
    private static final int HEIGHT = 500;
    private static final String JSON_LOCATION = "./data/workoutsAndMeals.json";
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JFrame frame;
    private JDialog choose;
    ListsMaker workoutsAndMeals;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    private AddWorkout addWorkoutWindow;
    private AddMeal addMealWindow;
    private RemoveWorkout removeWorkoutWindow;
    private RemoveMeal removeMealWindow;
    private ViewWorkouts viewWorkoutWindow;
    private ViewMeals viewMealWindow;
    private ViewAll viewAllWindow;
    private ViewAllByDay viewAllByDayWindow;


    // EFFECTS: constructs the GUI for the application
    public NutriWorkDiaryApp() {
        workoutsAndMeals = new ListsMaker("GUI Workouts And Meals");
        jsonReader = new JsonReader(JSON_LOCATION);
        jsonWriter = new JsonWriter(JSON_LOCATION);
        mainWindow();
        frame.setVisible(true);
    }

    // EFFECTS: creates a frame with main window and also with all the buttons
    private void mainWindow() {
        frame = new JFrame("NutriWork Diary");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.white);


        allButtons();
        mainLogo();
        workoutGif();
        mealGif();
    }

    // EFFECTS: adds the logo onto the Main Window
    private void mainLogo() {
        ImageIcon icon = new ImageIcon("./data/Images/Logo.png");
        Image image = icon.getImage();
        Image resizeImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImage);


        JLabel logo = new JLabel("");
        logo.setIcon(icon);
        logo.setBounds(210, -160, 400, 400);
        frame.getContentPane().add(logo);
    }


    // EFFECTS: adds a Workout Logo onto the Main Window
    private void workoutGif() {
        ImageIcon icon = new ImageIcon("./data/Images/MainWorkout.gif");
        Image image = icon.getImage();
        Image resizeImage = image.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        icon = new ImageIcon(resizeImage);


        JLabel workoutGif = new JLabel(icon);
//        workoutGif.setIcon(icon);
        workoutGif.setBounds(-60, 120, 400, 400);
        frame.getContentPane().add(workoutGif);
    }


    // EFFECTS: adds a Meal Logo onto the Main Window
    private void mealGif() {
        ImageIcon icon = new ImageIcon("./data/Images/MainMeal.gif");
        Image image = icon.getImage();
        Image resizeImage = image.getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        icon = new ImageIcon(resizeImage);


        JLabel mealGif = new JLabel(icon);
//        workoutGif.setIcon(icon);
        mealGif.setBounds(585, 195, 250, 250);
        frame.getContentPane().add(mealGif);
    }

    // EFFECTS: adds all the buttons to the main window
    private void allButtons() {
        addButton();
        removeButton();
        viewButton();
        viewAllButton();
        viewAllByDayButton();
        loadButton();
        saveButton();
        quitButton();
    }

    // EFFECTS: adds the Add Button onto the main window
    private void addButton() {
        JButton addButton = new JButton("Add");
        addButton.setBounds(200, 100, 120, 50); // y = 100 or 310
        frame.getContentPane().add(addButton);
        addButton.setCursor(handCursor);
//        addButton.setBorder(BorderFactory.createEmptyBorder());

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addWorkoutWindow != null && (e.getSource() == addButton)) {
                    addWorkoutWindow.dispose();
                }
                if (addMealWindow != null && (e.getSource() == addButton)) {
                    addMealWindow.dispose();
                } else if (choose != null && (e.getSource() == addButton)) {
                    choose.dispose();
                }
                addDialogBox();
                addWorkoutButton();
                addMealButton();
            }
        });
    }

    // EFFECTS: creates a dialog box for the add button
    //          to choose between meals and workouts
    private void addDialogBox() {
        choose = new JDialog();
        choose.setTitle("Choose What You Want To Add");
        choose.setSize(290, 150);
        choose.setLocationRelativeTo(null);
        choose.setLayout(null);
        choose.setResizable(false);
        choose.setVisible(true);
        choose.getContentPane().setBackground(Color.white);
    }

    // EFFECTS: creates the Add Workout button onto the dialog box
    //          to add workouts
    private void addWorkoutButton() {
        JButton addWorkout = new JButton("Workout");
        addWorkout.setBounds(100, 10, 90, 50);
        addWorkout.setCursor(handCursor);
//                addWorkout.setVisible(true);
//        ImageIcon icon = new ImageIcon("./data/Images/MainMeal.gif");
//        Image image = icon.getImage();
//        Image resizeImage = image.getScaledInstance(90, 50, Image.SCALE_DEFAULT);
//        icon = new ImageIcon(resizeImage);
//        addWorkout.setIcon(icon);
//
        choose.add(addWorkout);
//        addWorkoutGif();
        addWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (addWorkoutWindow != null) {
//                    addWorkoutWindow.dispose();
//                }

                choose.dispose();
                addWorkoutWindow = new AddWorkout(workoutsAndMeals);
                addWorkoutWindow.setVisible(true);
            }
        });
    }

    // EFFECTS: creates the Add Meal button onto the dialog box
    //          to add meals
    private void addMealButton() {
        JButton addMeal = new JButton("Meal");
        addMeal.setBounds(100, 60, 90, 50);
        addMeal.setCursor(handCursor);
//                addMeal.setVisible(true);
        choose.add(addMeal);

        addMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choose.dispose();
                addMealWindow = new AddMeal(workoutsAndMeals);
                addMealWindow.setVisible(true);
            }
        });
    }


    // EFFECTS: adds the Remove Button onto the main window
    private void removeButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(360, 100, 120, 50); // y == 100 or 310
        frame.getContentPane().add(removeButton);
        removeButton.setCursor(handCursor);
//        removeButton.setBorder(BorderFactory.createEmptyBorder());


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (removeWorkoutWindow != null && (e.getSource() == removeButton)) {
                    removeWorkoutWindow.dispose();
                }
                if (removeMealWindow != null && (e.getSource() == removeButton)) {
                    removeMealWindow.dispose();
                } else if (choose != null && (e.getSource() == removeButton)) {
                    choose.dispose();
                }

                removeDialogBox();
                removeWorkoutButton();
                removeMealButton();


            }
        });
    }

    // EFFECTS: creates the dialog box for the Remove Button
    //          to choose between Workouts and Meals
    private void removeDialogBox() {
        choose = new JDialog();
        choose.setTitle("Choose What You Want To Remove");
        choose.setSize(300, 150);
        choose.setLocationRelativeTo(null);
        choose.setLayout(null);
        choose.setResizable(false);
        choose.setVisible(true);
        choose.getContentPane().setBackground(Color.white);
    }

    // EFFECTS: adds the Remove Workout button for the dialog box
    //          to remove workouts
    private void removeWorkoutButton() {
        JButton removeWorkout = new JButton("Workout");
        removeWorkout.setBounds(105, 10, 90, 50);
        removeWorkout.setCursor(handCursor);
//                removeWorkout.setVisible(true);
        choose.add(removeWorkout);


        removeWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choose.dispose();
                removeWorkoutWindow = new RemoveWorkout(workoutsAndMeals);
                removeWorkoutWindow.setVisible(true);
            }
        });
    }

    // EFFECTS: adds the Remove Meal button for the dialog box
    //          to remove meals
    private void removeMealButton() {
        JButton removeMeal = new JButton("Meal");
        removeMeal.setBounds(105, 60, 90, 50);
        removeMeal.setCursor(handCursor);
//                removeMeal.setVisible(true);
        choose.add(removeMeal);


        removeMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choose.dispose();
                removeMealWindow = new RemoveMeal(workoutsAndMeals);
                removeMealWindow.setVisible(true);
            }
        });
    }


    // EFFECTS: adds the View Button onto the main window
    //          to view Workouts and meals
    private void viewButton() {
        JButton viewButton = new JButton("View");
        viewButton.setBounds(520, 100, 120, 50); // y == 100 or 310
        frame.getContentPane().add(viewButton);
        viewButton.setCursor(handCursor);
//        viewButton.setBorder(BorderFactory.createEmptyBorder());

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewWorkoutWindow != null && (e.getSource() == viewButton)) {
                    viewWorkoutWindow.dispose();
                }
                if (viewMealWindow != null && (e.getSource() == viewButton)) {
                    viewMealWindow.dispose();
                } else if (choose != null && (e.getSource() == viewButton)) {
                    choose.dispose();
                }

                viewDialogBox();
                viewWorkoutButton();
                viewMealButton();


            }
        });
    }


    // EFFECTS: creates a dialog box for the View Button
    //          to choose between Meals and Workouts
    private void viewDialogBox() {
        choose = new JDialog();
        choose.setTitle("Choose What You Want To View");
        choose.setSize(300, 150);
        choose.setLocationRelativeTo(null);
        choose.setLayout(null);
        choose.setResizable(false);
        choose.setVisible(true);
        choose.getContentPane().setBackground(Color.white);
    }


    // EFFECTS: adds a View Workout button onto the dialog box
    //          to view the workouts
    private void viewWorkoutButton() {
        JButton viewWorkout = new JButton("Workout");
//                b.addActionListener( new StoreItem1Handler() );
        viewWorkout.setBounds(105, 10, 90, 50);
        viewWorkout.setCursor(handCursor);
//                viewWorkout.setVisible(true);
        choose.add(viewWorkout);

        viewWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choose.dispose();
                viewWorkoutWindow = new ViewWorkouts(workoutsAndMeals);
                viewWorkoutWindow.setVisible(true);
            }
        });

    }


    // EFFECTS: adds a View Meals button onto the dialog box
    //          to view the meals
    private void viewMealButton() {
        JButton viewMeal = new JButton("Meal");
//                b.addActionListener( new StoreItem1Handler() );
        viewMeal.setBounds(105, 60, 90, 50);
        viewMeal.setCursor(handCursor);
//                viewMeal.setVisible(true);
        choose.add(viewMeal);


        viewMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choose.dispose();
                viewMealWindow = new ViewMeals(workoutsAndMeals);
                viewMealWindow.setVisible(true);
            }
        });

    }


    // EFFECTS: adds the View All button onto the main window
    //          to view both Meals and Workouts at the same time
    private void viewAllButton() {
        JButton viewAllButton = new JButton("View All");
        viewAllButton.setBounds(280, 170, 120, 50);
        frame.getContentPane().add(viewAllButton);
        viewAllButton.setCursor(handCursor);
//        viewAllButton.setBorder(BorderFactory.createEmptyBorder());

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewAllWindow != null) {
                    viewAllWindow.dispose();
                }
                viewAllWindow = new ViewAll(workoutsAndMeals);
                viewAllWindow.setVisible(true);
            }
        });
    }

    // EFFECTS: adds the View All By Day button onto the Main Window
    //          to view both Meals and Workouts by Day at the same time
    private void viewAllByDayButton() {
        JButton viewAllByDayButton = new JButton("View All By Day");
        viewAllByDayButton.setBounds(440, 170, 120, 50);
        frame.getContentPane().add(viewAllByDayButton);
        viewAllByDayButton.setCursor(handCursor);
//        viewAllByDayButton.setBorder(BorderFactory.createEmptyBorder());

        viewAllByDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewAllByDayWindow != null) {
                    viewAllByDayWindow.dispose();
                }
                viewAllByDayWindow = new ViewAllByDay(workoutsAndMeals);
                viewAllByDayWindow.setVisible(true);
            }
        });

    }


    // EFFECTS: adds the Load Button onto the main Window
    //          to load previously saved workouts and meals
    private void loadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setBounds(280, 240, 120, 50);
        frame.getContentPane().add(loadButton);
        loadButton.setCursor(handCursor);
//        loadButton.setBorder(BorderFactory.createEmptyBorder());


        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    workoutsAndMeals = jsonReader.read();
//                    JOptionPane.showMessageDialog(null, "Successfully Loaded Data !");
                    loadDialog();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to read from file: " + JSON_LOCATION);
                }
            }
        });
    }

    // EFFECTS: adds the Save Button onto the main window
    //          to save the new changes made to the Meals and Workouts
    private void saveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(440, 240, 120, 50);
        frame.getContentPane().add(saveButton);
        saveButton.setCursor(handCursor);
//        saveButton.setBorder(BorderFactory.createEmptyBorder());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(workoutsAndMeals);
                    jsonWriter.close();

//                    JOptionPane.showMessageDialog(null, "Successfully Saved Data !");
                    saveDialog();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to read from file: " + JSON_LOCATION);
                }
            }
        });

    }

    // EFFECTS: adds the Quit Button onto the main window
    //          to quit the application
    private void quitButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(360, 310, 120, 50); // y == 310 or 100
        frame.getContentPane().add(quitButton);
        quitButton.setCursor(handCursor);
//        quitButton.setBorder(BorderFactory.createEmptyBorder());

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                System.exit(0);
                printEventLog(EventLog.getInstance());
                quitDialog();
            }
        });
    }


    // EFFECTS: creates the dialog box that is displayed when Save button is clicked
    public void saveDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(300, 190);
        JLabel saveGif = new JLabel("");
        ImageIcon icon = new ImageIcon("./data/Images/Save.gif");
//        Image image = icon.getImage();
//        Image resizeImage = image.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
//        icon = new ImageIcon(resizeImage);
        saveGif.setIcon(icon);
        saveGif.setBounds(400, -10, 450, 400);
        dialog.add(saveGif);
        dialog.setLocationRelativeTo((Component) null);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    // EFFECTS: creates the dialog box that is displayed when Load button is clicked
    public void loadDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(393, 300);
        JLabel loadGif = new JLabel("");
        loadGif.setIcon(new ImageIcon("./data/Images/Load.gif"));
        loadGif.setBounds(400, -10, 450, 400);
        dialog.add(loadGif);
        dialog.setLocationRelativeTo((Component) null);
        dialog.setResizable(false);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    // EFFECTS: creates the dialog box that is displayed when Quit button is clicked
    public void quitDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(348, 240);
        JLabel quitGif = new JLabel("");
        quitGif.setIcon(new ImageIcon("./data/Images/Quit.gif"));
        quitGif.setBounds(400, -10, 450, 400);
        dialog.add(quitGif);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo((Component) null);
        dialog.setVisible(true);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
                System.exit(0);
            }
        });
    }


    // EFFECTS: prints out the EventLog that is recorded
    public void printEventLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}
