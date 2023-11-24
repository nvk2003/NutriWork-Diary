package ui;

import model.ListsMaker;
import model.Meal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a View Meals Window to the user
public class ViewMeals extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    ListsMaker workoutsAndMeals;
    private JTable mealTable;
    private DefaultTableModel mealsTableModel;
    private int serialNo = 1;

    // EFFECTS: constructs the GUI for the View Meals Window
    public ViewMeals(ListsMaker workoutsAndMeals) {
        super("View Meals");

        this.workoutsAndMeals = workoutsAndMeals;

        mainWindow();
        setVisible(true);
    }

    // EFFECTS: creates the main window for the View Meals
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

    // EFFECTS: arranges the buttons and list of meals onto the window
    private void arrangeOnMainWindow() {
        panel = new JPanel();
        add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
//        panel.setLayout(new GridLayout(1,2));
        makeListsOnPanel();
        makeButtonsOnPanel();
    }

    // EFFECTS: makes the list of meals that can be printed onto the window
    private void makeListsOnPanel() {
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
        if (workoutsAndMeals.getMeals().size() == 0) {
            mealsTableModel.addRow(new Object[]{"  There Are No Meals To Be Viewed !"});
        } else {

            for (Meal meal : workoutsAndMeals.getMeals()) {
                mealsTableModel.addRow(new Object[]{"  " + serialNo + ". " + meal.getMeal()
                        + " on " + meal.getDay()});
                serialNo++;
            }
        }

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        panel.add(new JScrollPane(mealTable), BorderLayout.CENTER);
    }

    // EFFECTS: sets the conditions for the list of meals to be printed onto the window
    private void mealsTableConditions() {
        mealTable.setRowSelectionAllowed(false);
        mealTable.setFocusable(false);
        mealTable.setFont(new Font("Arial", Font.PLAIN, 17));
        mealTable.setRowHeight(23);
    }

    // EFFECTS: adds the buttons onto the window
    private void makeButtonsOnPanel() {
        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.setBackground(Color.white);


        buttonPanel.add(viewMealsByDayButton());
        buttonPanel.add(doneButton());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

//        buttonPanel.setBounds(400, 10, 250, 100);

        panel.add(buttonPanel, BorderLayout.PAGE_END);

    }


    // EFFECTS: creates a Done Button that can be added onto the window
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

    // EFFECTS: creates a View Meals By Day button that can be
    //          used for the user to view the meals of a week separated by day
    private JButton viewMealsByDayButton() {
        JButton viewMealsByDayButton = new JButton("View Meals By Day");
        viewMealsByDayButton.setCursor(handCursor);
//        removeButton.setBounds(300, 90, 80, 25);


        viewMealsByDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new ViewMealsByDay(workoutsAndMeals);
            }
        });

        return viewMealsByDayButton;
    }
}
