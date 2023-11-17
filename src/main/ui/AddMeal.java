package ui;

import model.ListsMaker;
import model.Meal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// Represents an Add Meal Window for the user
public class AddMeal extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    ListsMaker workoutsAndMeals;
    private JTextField name;
    private JTextField calories;
    private JTextField mealTime;
    private JTextField day;
    private String days;
    private String mealTimes;
    private JButton cancelButton;


    // EFFECTS: constructs the GUI for the Add Meals window
    public AddMeal(ListsMaker workoutsAndMeals) {
        super("Add Meal");
        this.workoutsAndMeals = workoutsAndMeals;

        mainWindow();
        setVisible(true);
    }

    // EFFECTS: constructs the main window for the Add Meals
    private void mainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel();
        add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.white);


        addLabelsAndTextBoxes();
        addButtons();
    }


    // EFFECTS: adds the labels and text boxes for
    //          the user to insert data
    private void addLabelsAndTextBoxes() {
        JLabel name = new JLabel("Name: ");
        name.setBounds(40, 60, 80, 25);
        name.setFont(new Font(name.getFont().getFontName(), Font.PLAIN, 15));
        panel.add(name);


        JLabel sets = new JLabel("Calories: ");
        sets.setBounds(40, 120, 80, 25);
        sets.setFont(new Font(sets.getFont().getFontName(), Font.PLAIN, 15));
        panel.add(sets);

        JLabel reps = new JLabel("Meal Time: ");
        reps.setBounds(40, 180, 100, 25);
        reps.setFont(new Font(reps.getFont().getFontName(), Font.PLAIN, 15));
        panel.add(reps);

        JLabel timings = new JLabel("(Breakfast/Lunch/Dinner)");
        timings.setBounds(39, 200, 150, 25);
        timings.setFont(new Font(timings.getFont().getFontName(), Font.PLAIN, 10));
        panel.add(timings);


        JLabel day = new JLabel("Day: ");
        day.setBounds(40, 240, 80, 25);
        day.setFont(new Font(day.getFont().getFontName(), Font.PLAIN, 15));
        panel.add(day);

        addTextBoxes();
    }


    // EFFECTS: adds the text boxes for the user to insert data
    private void addTextBoxes() {
        nameTextBox();
        caloriesTextBox();
        mealTimeTextBox();
        dayTextBox();
    }

    // EFFECTS: adds the Name Text Box to the window
    //          to add the name to the meal
    private void nameTextBox() {
        name = new JTextField(" Enter Meal Name");
        name.setForeground(Color.lightGray);
        name.setBounds(125, 60, 165, 25);
        name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (name.getText().equals(" Enter Meal Name")) {
                    name.setText("");
                    name.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (name.getText().equals("")) {
                    name.setText(" Enter Meal Name");
                    name.setForeground(Color.lightGray);
                }
            }
        });
        panel.add(name);
    }

    // EFFECTS: adds the Calories Text box to the window
    //          to add the calories of the meal
    private void caloriesTextBox() {
        calories = new JTextField(" Enter No. of Calories");
        calories.setForeground(Color.lightGray);
        calories.setBounds(125, 120, 165, 25);
        calories.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (calories.getText().equals(" Enter No. of Calories")) {
                    calories.setText("");
                    calories.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (calories.getText().equals("")) {
                    calories.setText(" Enter No. of Calories");
                    calories.setForeground(Color.lightGray);
                }
            }
        });
        panel.add(calories);
    }


    // EFFECTS: adds the Meal Time Text box to the window
    //          to add the meal time of the meal
    private void mealTimeTextBox() {
        mealTime = new JTextField(" Enter Meal Time");
        mealTime.setForeground(Color.lightGray);
        mealTime.setBounds(125, 180, 165, 25);
        mealTime.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (mealTime.getText().equals(" Enter Meal Time")) {
                    mealTime.setText("");
                    mealTime.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (mealTime.getText().equals("")) {
                    mealTime.setText(" Enter Meal Time");
                    mealTime.setForeground(Color.lightGray);
                }
            }
        });
        panel.add(mealTime);
    }


    // EFFECTS: adds the Day Text box to the window
    //          to add the day of the meal
    private void dayTextBox() {
        day = new JTextField(" Enter Day of the Week");
        day.setForeground(Color.lightGray);
        day.setBounds(125, 240, 165, 25);
        day.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (day.getText().equals(" Enter Day of the Week")) {
                    day.setText("");
                    day.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (day.getText().equals("")) {
                    day.setText(" Enter Day of the Week");
                    day.setForeground(Color.lightGray);
                }
            }
        });
        panel.add(day);
    }


    // EFFECTS: adds the buttons to the window
    private void addButtons() {
        addButton();
//        doneButton();
        cancelButton();
    }

    // MODIFIES: this
    // EFFECTS: adds the Add Button to the Window
    private void addButton() {
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font(name.getFont().getFontName(), Font.PLAIN, 13));
        addButton.setBounds(100, 290, 75, 33);
        addButton.setCursor(handCursor);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDay();
                getMealTime();
                errorMsgs();
            }
        });
    }

    // EFFECTS: gives the error messages to the user to insert the correct details
    private void errorMsgs() {
        if (name.getText().strip().equals("Enter Meal Name")) {
            JOptionPane.showMessageDialog(null, "Please Enter Meal Name");
        } else if (calories.getText().strip().equals("Enter No. of Calories")) {
            JOptionPane.showMessageDialog(null, "Please Enter Valid No. Of Calories");
        } else if (mealTime.getText().strip().equals("Enter Meal Time")
                || (mealTimes.equals(mealTime.getText().strip().toLowerCase()))) {
            JOptionPane.showMessageDialog(null, "Please Enter Valid Meal Time");
        } else if (day.getText().strip().equals("Enter Day of the Week")
                || (days.equals(day.getText().strip().toLowerCase()))) {
            JOptionPane.showMessageDialog(null, "Please Enter A Valid Day of the Week");
        } else if (!calories.getText().strip().equals("Enter No. of Calories")) {
            try {
                Double.parseDouble(calories.getText());
                makeMeal();
                textsBackToNormal();

                if (workoutsAndMeals.getMeals().size() != 0) {
                    cancelButton.setVisible(false);
                    doneButton();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please Enter Valid No. Of Calories");
            }
        }

    }


    // EFFECTS: adds the done button to the window
    private void doneButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font(name.getFont().getFontName(), Font.PLAIN, 13));
        doneButton.setBounds(200, 290, 75, 33);
        doneButton.setCursor(handCursor);
        panel.add(doneButton);

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    // EFFECTS: adds the cancel button to the window
    private void cancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font(name.getFont().getFontName(), Font.PLAIN, 13));
        cancelButton.setBounds(200, 290, 75, 33);
        cancelButton.setCursor(handCursor);
        panel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    // EFFECTS: construct a new Meal everytime the user adds a Meal
    private void makeMeal() {
        Meal meal = new Meal(name.getText(), Double.parseDouble(calories.getText()),
                mealTimes, days);
        workoutsAndMeals.addMeal(meal);
    }

    // EFFECTS: used to input the full name of the Day after
    //          inputting the first three letters of the day
    private void getDay() {
        days = day.getText().strip().toLowerCase();
        if (days.startsWith("mon")) {
            days = "Monday";
        } else if (days.startsWith("tue")) {
            days = "Tuesday";
        } else if (days.startsWith("wed")) {
            days = "Wednesday";
        } else if (days.startsWith("thu")) {
            days = "Thursday";
        } else if (days.startsWith("fri")) {
            days = "Friday";
        } else if (days.startsWith("sat")) {
            days = "Saturday";
        } else if (days.startsWith("sun")) {
            days = "Sunday";
        }
    }


    // EFFECTS: used to input the correct name of the Meal Time,
    //          irrespective of the case of the letters
    private void getMealTime() {
        mealTimes = mealTime.getText().strip().toLowerCase();
        if (mealTimes.equals("breakfast")) {
            mealTimes = "Breakfast";
        } else if (mealTimes.equals("lunch")) {
            mealTimes = "Lunch";
        } else if (mealTimes.equals("dinner")) {
            mealTimes = "Dinner";
        }
    }

    // EFFECTS: sets the text boxes to normal after adding each Meal
    private void textsBackToNormal() {
        name.setText(" Enter Meal Name");
        calories.setText(" Enter No. of Calories");
        mealTime.setText(" Enter Meal Time");
        day.setText(" Enter Day of the Week");
        name.setForeground(Color.lightGray);
        calories.setForeground(Color.lightGray);
        mealTime.setForeground(Color.lightGray);
        day.setForeground(Color.lightGray);
    }
}
