package ui;

import model.ListsMaker;
import model.Meal;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewAllByDay extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    private JPanel listsPanel;
    private JPanel buttonsPanel;
    ListsMaker workoutsAndMeals;
    private JTable workoutTable;
    private DefaultTableModel workoutTableModel;
    private DefaultTableModel mealsTableModel;
    private JTable mealTable;
    private ArrayList<String> days = new ArrayList<>();


    public ViewAllByDay(ListsMaker workoutsAndMeals) {
        super("View Both Workouts & Meals By Day");

        this.workoutsAndMeals = workoutsAndMeals;
        makeDays();
        mainWindow();
        setVisible(true);
    }

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

    private void arrangeOnMainWindow() {
        panel = new JPanel();
        add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
//        panel.setLayout(new GridLayout(2,1));
        listsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel = new JPanel();

        makeWorkoutListOnPanel();
        makeMealsListOnPanel();
        makeButtonsOnPanel();

        panel.add(listsPanel, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.PAGE_END);
    }

    private void makeWorkoutListOnPanel() {
        workoutTableModel = new DefaultTableModel();
        workoutTable = new JTable(workoutTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        workoutTableConditions();

        workoutTableModel.addColumn("");

        makeWorkoutsList();

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        listsPanel.add(new JScrollPane(workoutTable), BorderLayout.CENTER);
    }

    private void makeWorkoutsList() {
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

    private void workoutTableConditions() {
        workoutTable.setRowSelectionAllowed(false);
        workoutTable.setFocusable(false);
        workoutTable.setFont(new Font("Arial", Font.PLAIN, 17));
        workoutTable.setRowHeight(23);
    }

    private void makeMealsListOnPanel() {
        mealsTableModel = new DefaultTableModel();
        mealTable = new JTable(mealsTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        mealsTableConditions();

        mealsTableModel.addColumn("");

        makeMealsList();

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        listsPanel.add(new JScrollPane(mealTable), BorderLayout.CENTER);
    }

    private void makeMealsList() {
        mealsTableModel.addRow(new Object[]{"All Meals By Day: "});
        for (String day : getDays()) {
            workoutsAndMeals.makeMealsByDay(day);
            ArrayList<Meal> mealsForDay = workoutsAndMeals.getMealsByDay();
            if (day.equals("Monday")) {
                mealsTableModel.addRow(new Object[]{day + ": "});
            } else {
                mealsTableModel.addRow(new Object[]{""});
                mealsTableModel.addRow(new Object[]{day + ": "});
            }


            if (mealsForDay.size() == 0) {
                mealsTableModel.addRow(new Object[]{"  There Are No Meals For The Day !"});
            } else {
                int x = 1;
                for (Meal meal : mealsForDay) {
                    mealsTableModel.addRow(new Object[]{x + ". " + meal.getMeal()});
                    x++;
                }
            }
        }
    }

    private void mealsTableConditions() {
        mealTable.setRowSelectionAllowed(false);
        mealTable.setFocusable(false);
        mealTable.setFont(new Font("Arial", Font.PLAIN, 17));
        mealTable.setRowHeight(23);
    }



    private void makeDays() {
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
    }


    private ArrayList<String> getDays() {
        return days;
    }



    private void makeButtonsOnPanel() {
        buttonsPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        buttonPanel.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonsPanel.setBackground(Color.white);

        buttonsPanel.add(doneButton());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

//        buttonPanel.setBounds(400, 10, 250, 100);

    }


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

}
