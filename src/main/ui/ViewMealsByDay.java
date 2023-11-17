package ui;

import model.ListsMaker;
import model.Meal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a View Meals By Day window to the user
public class ViewMealsByDay extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    ListsMaker workoutsAndMeals;
    private JTable mealsTable;
    private DefaultTableModel mealTableModel;

    private ArrayList<String> days = new ArrayList<>();

    // EFFECTS: constructs a GUI for the View Meals By Day window
    public ViewMealsByDay(ListsMaker workoutsAndMeals) {
        super("View Meals By Day");

        this.workoutsAndMeals = workoutsAndMeals;
        makeDays();

        mainWindow();
        setVisible(true);
    }

    // EFFECTS: creates the main window for viewing the meals by day
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

    // EFFECTS: arranges the list of meals by day onto the main window
    private void arrangeOnMainWindow() {
        panel = new JPanel();
        add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
//        panel.setLayout(new GridLayout(1,2));
        makeListsOnPanel();
        makeButtonsOnPanel();
    }


    // EFFECTS: arranges the list of meals by day onto the window correctly
    private void makeListsOnPanel() {
        mealTableModel = new DefaultTableModel();
        mealsTable = new JTable(mealTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        mealsTableConditions();

        mealTableModel.addColumn("");

        makeList();

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        panel.add(new JScrollPane(mealsTable), BorderLayout.CENTER);
    }

    // EFFECTS: makes the list of meals by day that can be printed onto the window
    private void makeList() {
        mealTableModel.addRow(new Object[]{"All Meals By Day: "});
        for (String day : getDays()) {
            workoutsAndMeals.makeMealsByDay(day);
            ArrayList<Meal> mealsForDay = workoutsAndMeals.getMealsByDay();
            if (day.equals("Monday")) {
                mealTableModel.addRow(new Object[]{day + ": "});
            } else {
                mealTableModel.addRow(new Object[]{""});
                mealTableModel.addRow(new Object[]{day + ": "});
            }


            if (mealsForDay.size() == 0) {
                mealTableModel.addRow(new Object[]{"  There Are No Meals For The Day !"});
            } else {
                int x = 1;
                for (Meal meal : mealsForDay) {
                    mealTableModel.addRow(new Object[]{x + ". " + meal.getMeal()});
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
    //          to print out the list of meals by day correctly
    private void mealsTableConditions() {
        mealsTable.setRowSelectionAllowed(false);
        mealsTable.setFocusable(false);
        mealsTable.setFont(new Font("Arial", Font.PLAIN, 17));
        mealsTable.setRowHeight(23);
    }

    // EFFECTS: adds all the buttons onto the window
    private void makeButtonsOnPanel() {
        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.setBackground(Color.white);


        buttonPanel.add(viewMealsButton());
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

    // EFFECTS: creates the View Meals button that can be
    //          used by the user to view the list of meals all at once
    private JButton viewMealsButton() {
        JButton viewMealsButton = new JButton("View Meals");
        viewMealsButton.setCursor(handCursor);
//        removeButton.setBounds(300, 90, 80, 25);


        viewMealsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new ViewMeals(workoutsAndMeals);
            }
        });

        return viewMealsButton;
    }

}
