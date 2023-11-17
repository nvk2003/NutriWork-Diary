package ui;

import model.ListsMaker;
import model.Meal;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAll extends JFrame {
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


    public ViewAll(ListsMaker workoutsAndMeals) {
        super("View Both Workouts & Meals");

        this.workoutsAndMeals = workoutsAndMeals;

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

        workoutTableModel.addRow(new String[]{"All Workouts: \n"});
        if (workoutsAndMeals.getWorkouts().size() == 0) {
            workoutTableModel.addRow(new Object[]{"  There Are No Workouts To Be Viewed !"});
        } else {
            int x = 1;
            for (Workout workout : workoutsAndMeals.getWorkouts()) {
                workoutTableModel.addRow(new Object[]{"  " + x + ". " + workout.getWorkout()
                        + " on " + workout.getDay()});
                x++;
            }
        }

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        listsPanel.add(new JScrollPane(workoutTable));

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

        mealsTableModel.addRow(new String[]{"All Meals: \n"});
        if (workoutsAndMeals.getWorkouts().size() == 0) {
            mealsTableModel.addRow(new Object[]{"  There Are No Meals To Be Viewed !"});
        } else {
            int a = 1;
            for (Meal meal : workoutsAndMeals.getMeals()) {
                mealsTableModel.addRow(new Object[]{"  " + a + ". " + meal.getMeal()
                        + " on " + meal.getDay()});
                a++;
            }
        }

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        listsPanel.add(new JScrollPane(mealTable));
    }

    private void mealsTableConditions() {
        mealTable.setRowSelectionAllowed(false);
        mealTable.setFocusable(false);
        mealTable.setFont(new Font("Arial", Font.PLAIN, 17));
        mealTable.setRowHeight(23);
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
