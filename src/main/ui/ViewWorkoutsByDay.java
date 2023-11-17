package ui;

import model.ListsMaker;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewWorkoutsByDay extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    ListsMaker workoutsAndMeals;
    private JTable workoutTable;
    private DefaultTableModel workoutTableModel;

    private ArrayList<String> days = new ArrayList<>();

    public ViewWorkoutsByDay(ListsMaker workoutsAndMeals) {
        super("View Workouts By Day");

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
//        panel.setLayout(new GridLayout(1,2));
        makeListsOnPanel();
        makeButtonsOnPanel();
    }


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

    private void workoutTableConditions() {
        workoutTable.setRowSelectionAllowed(false);
        workoutTable.setFocusable(false);
        workoutTable.setFont(new Font("Arial", Font.PLAIN, 17));
        workoutTable.setRowHeight(23);
    }

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
}
