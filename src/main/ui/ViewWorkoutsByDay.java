package ui;

import model.ListsMaker;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a View Workouts By Day window to the user
public class ViewWorkoutsByDay extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    ListsMaker workoutsAndMeals;
    private JTable workoutTable;
    private DefaultTableModel workoutTableModel;

    private ArrayList<String> days = new ArrayList<>();

    // EFFECTS: constructs a GUI for the View Workouts By Day window
    public ViewWorkoutsByDay(ListsMaker workoutsAndMeals) {
        super("View Workouts By Day");

        this.workoutsAndMeals = workoutsAndMeals;
        makeDays();

        mainWindow();
        setVisible(true);
    }

    // EFFECTS: creates the main window for viewing the workouts by day
    private void mainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel();
        add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.white);

        arrangeOnMainWindow();
    }

    // EFFECTS: arranges the list of workouts by day onto the main window
    private void arrangeOnMainWindow() {
        panel = new JPanel();
        add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
//        panel.setLayout(new GridLayout(1,2));
        viewWorkoutsByDayGif();
        makeListsOnPanel();
        makeButtonsOnPanel();
    }

    // EFFECTS: arranges the list of workouts by day onto the window correctly
    private void makeListsOnPanel() {
        workoutTableModel = new DefaultTableModel();
        workoutTable = new JTable(workoutTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        workoutTableConditions();

        workoutTableModel.addColumn("");

        makeList();

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        panel.add(new JScrollPane(workoutTable), BorderLayout.CENTER);
    }

    // EFFECTS: makes the list of workouts by day that can be printed onto the window
    private void makeList() {
        workoutTableModel.addRow(new Object[]{"All Workouts By Day: "});
        for (String day : getDays()) {
            workoutsAndMeals.makeWorkoutsByDay(day);
            ArrayList<Workout> workoutsForDay = workoutsAndMeals.getWorkoutsByDay();
            if (day.equals("Monday")) {
                workoutTableModel.addRow(new Object[]{day + ": "});
            } else {
                workoutTableModel.addRow(new Object[]{""});
                workoutTableModel.addRow(new Object[]{day + ": "});
            }

            if (workoutsForDay.size() == 0) {
                workoutTableModel.addRow(new Object[]{"  There Are No Workouts For The Day !"});
            } else {
                int x = 1;
                for (Workout workout : workoutsForDay) {
                    workoutTableModel.addRow(new Object[]{x + ". " + workout.getWorkout()});
                    x++;
                }
            }
        }
    }

    // EFFECTS: makes a list of days in a week
    private void makeDays() {
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
    }

    // EFFECTS: returns the list of days of a week
    private ArrayList<String> getDays() {
        return days;
    }

    // EFFECTS: sets the conditions for the table
    //          to print out the list of workouts by day correctly
    private void workoutTableConditions() {
        workoutTable.setRowSelectionAllowed(false);
        workoutTable.setFocusable(false);
        workoutTable.setFont(new Font("Arial", Font.PLAIN, 17));
        workoutTable.setRowHeight(23);
    }

    // EFFECTS: adds all the buttons onto the window
    private void makeButtonsOnPanel() {
        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.setBackground(Color.white);


        buttonPanel.add(viewWorkoutsButton());
        buttonPanel.add(doneButton());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

//        buttonPanel.setBounds(400, 10, 250, 100);

        panel.add(buttonPanel, BorderLayout.PAGE_END);

    }


    // EFFECTS: creates the Done Button that can be added onto the window
    private JButton doneButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setCursor(handCursor);
//        doneButton.setBounds(300, 90, 80, 25);


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return doneButton;
    }


    // EFFECTS: creates the View Workouts button that can be
    //          used by the user to view the list of workouts all at once
    private JButton viewWorkoutsButton() {
        JButton viewWorkoutsButton = new JButton("View Workouts");
        viewWorkoutsButton.setCursor(handCursor);
//        removeButton.setBounds(300, 90, 80, 25);


        viewWorkoutsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new ViewWorkouts(workoutsAndMeals);
            }
        });

        return viewWorkoutsButton;
    }

    // EFFECTS: creates a View Workouts By Day Gif and adds it the panel
    private void viewWorkoutsByDayGif() {
        ImageIcon icon = new ImageIcon("./data/Images/ViewByDay.gif");
        Image image = icon.getImage();
        Image resizeImage = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        icon = new ImageIcon(resizeImage);

        JLabel viewWorkoutsByDayGif = new JLabel(icon);
//        viewMealsByDayGif.setIcon(icon);
        viewWorkoutsByDayGif.setBounds(450, 40, 300, 300);
        viewWorkoutsByDayGif.setVisible(true);
        panel.add(viewWorkoutsByDayGif);
    }
}
