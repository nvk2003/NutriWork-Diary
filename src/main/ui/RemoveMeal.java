package ui;

import model.ListsMaker;
import model.Meal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

// Represents a Remove Meal Window to the user
public class RemoveMeal extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    private JTextField remove;
    ListsMaker workoutsAndMeals;
    private JTable mealTable;
    private ArrayList<String> workouts;
    private DefaultTableModel mealTableModel;
    private int serialNo = 1;


    // EFFECTS: constructs the GUI for the Remove Meals window
    public RemoveMeal(ListsMaker workoutsAndMeals) {
        super("Remove Meal");
        this.workoutsAndMeals = workoutsAndMeals;

        mainWindow();
        setVisible(true);

    }

    // EFFECTS: constructs the main window for the Remove Meals
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

    // EFFECTS: arranges a list on the window for the user
    //          to select a meal to remove
    private void arrangeOnMainWindow() {
        panel = new JPanel();
        add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
//        panel.setLayout(new GridLayout(1,2));
        makeListsOnPanel();
        makeButtonsOnPanel();
    }

    // EFFECTS: prints out the list of meals onto the window
    //          to select a meal to remove
    private void makeListsOnPanel() {
        workouts = new ArrayList<>();
        mealTableModel = new DefaultTableModel();
        mealTable = new JTable(mealTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        mealTableConditions();
        mealTableModel.addColumn("");

        mealTableModel.addRow(new String[]{"Choose From The Following Meals To Remove: \n"});
        if (workoutsAndMeals.getMeals().size() == 0) {
            mealTableModel.addRow(new Object[]{"  There Are No Meals To Be Removed !"});
        } else {

            for (Meal meal : workoutsAndMeals.getMeals()) {
                mealTableModel.addRow(new Object[]{"  " + serialNo + ". " + meal.getMeal()
                        + " on " + meal.getDay()});
                workouts.add("  " + serialNo + ". " + meal.getMeal()
                        + " on " + meal.getDay());
                serialNo++;
            }
        }

        panel.add(new JScrollPane(mealTable), BorderLayout.CENTER);
    }

    // EFFECTS: sets the conditions for the printed out lists
    private void mealTableConditions() {
        mealTable.setRowSelectionAllowed(false);
        mealTable.setFocusable(false);
        mealTable.setFont(new Font("Arial", Font.PLAIN, 17));
        mealTable.setRowHeight(23);
    }

    // EFFECTS: adds all the buttons to the window
    private void makeButtonsOnPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.white);


        buttonPanel.add(removeTextLabel());
        buttonPanel.add(removeTextField());
        buttonPanel.add(removeButton());
        buttonPanel.add(removeAllButton());
        buttonPanel.add(doneButton());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
        panel.add(buttonPanel, BorderLayout.PAGE_END);

    }

    // EFFECTS: adds the Remove Text label to the window
    private JLabel removeTextLabel() {
        JLabel remove = new JLabel("Remove: ");
        remove.setFont(new Font(remove.getFont().getFontName(), Font.PLAIN, 15));
        return remove;
    }

    // EFFECTS: adds the Remove Text Field to the window
    private JTextField removeTextField() {
        remove = new JTextField(" Enter S.No");
        remove.setForeground(Color.lightGray);
        remove.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (remove.getText().equals(" Enter S.No")) {
                    remove.setText("");
                    remove.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (remove.getText().equals("")) {
                    remove.setText(" Enter S.No");
                    remove.setForeground(Color.lightGray);
                }
            }
        });

        return remove;
    }

    // EFFECTS: adds the done button to the window
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


    // EFFECTS: adds the remove button to the window
    private JButton removeButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.setCursor(handCursor);
//        removeButton.setBounds(300, 90, 80, 25);


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();

                printErrorMsgs();
//                printChangedWorkouts();
            }
        });

        return removeButton;
    }

    // EFFECTS: prints out the error messages on the window
    //          to make sure the user inputs the correct data
    private void printErrorMsgs() {
        if (remove.getText().strip().equals("Enter S.No")
                || (Double.parseDouble(remove.getText().strip()) % 1 != 0)
                || (remove.getText().strip().equals("0"))
                || (Double.parseDouble(remove.getText().strip()) > workouts.size())) {
            JOptionPane.showMessageDialog(null, "Please Enter A Valid S.No");
        } else {
            printChangedMeals();
        }
    }

    // EFFECTS: makes a new window after removing one meal
    //          to make sure the numbering is correct on the list of meals
    private void printChangedMeals() {
        try {
            int rowNum = Integer.parseInt(remove.getText());
            if (rowNum > 0) {
                if (workouts.contains(mealTableModel.getValueAt(rowNum, 0))) {
                    workoutsAndMeals.removeMeal(workoutsAndMeals.getMeals().get(rowNum - 1));
                    mealTableModel.removeRow(rowNum);
                    dispose();
                    if (!isVisible()) {
                        new RemoveMeal(workoutsAndMeals);
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please Enter Valid S.No");
        }
    }

    // EFFECTS: adds a Remove All button onto the window to remove all the meals
    private JButton removeAllButton() {
        JButton removeButton = new JButton("Remove All");
        removeButton.setCursor(handCursor);
//        removeButton.setBounds(300, 90, 80, 25);


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (workoutsAndMeals.getMeals().size() >= 1) {
                    workoutsAndMeals.getMeals().removeAll(workoutsAndMeals.getMeals());

                    while (mealTableModel.getRowCount() > 0) {
                        mealTableModel.removeRow(0);
                    }
                    dispose();
                    if (!isVisible()) {
                        new RemoveMeal(workoutsAndMeals);
                    }
                }
            }
        });

        return removeButton;
    }


}
