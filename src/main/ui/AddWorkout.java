package ui;

import model.ListsMaker;
import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// Represents an Add Workout Window for the user
public class AddWorkout extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    ListsMaker workoutsAndMeals;
    private JTextField name;
    private JTextField sets;
    private JTextField reps;
    private JTextField day;
    private String days;
    private JButton cancelButton;
    private JLabel addWorkoutGif;
    private JLabel errorWorkoutGif;
    private JLabel casualMealGif;

    // EFFECTS: constructs the GUI for the Add Workouts window
    public AddWorkout(ListsMaker workoutsAndMeals) {
        super("Add Workout");
        this.workoutsAndMeals = workoutsAndMeals;

        mainWindow();
        setVisible(true);
    }

    // EFFECTS: constructs the main window for the Add Workouts
    private void mainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel();
        add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        errorWorkoutGif = new JLabel();
        addWorkoutGif = new JLabel();
        casualMealGif = new JLabel();
        // I did the below 4 lines because if you remove this,
        // then I need to press the Add Button for two times for the Gif to appear
        addWorkoutGif();
        addWorkoutGif.setVisible(false);
        errorWorkoutGif();
        errorWorkoutGif.setVisible(false);
        casualMealGif();
        casualMealGif.setVisible(true);


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


        JLabel sets = new JLabel("Sets: ");
        sets.setBounds(40, 120, 80, 25);
        sets.setFont(new Font(sets.getFont().getFontName(), Font.PLAIN, 15));
        panel.add(sets);

        JLabel reps = new JLabel("Reps: ");
        reps.setBounds(40, 180, 80, 25);
        reps.setFont(new Font(reps.getFont().getFontName(), Font.PLAIN, 15));
        panel.add(reps);

        JLabel day = new JLabel("Day: ");
        day.setBounds(40, 240, 80, 25);
        day.setFont(new Font(day.getFont().getFontName(), Font.PLAIN, 15));
        panel.add(day);

        addTextBoxes();
    }

    // EFFECTS: adds the text boxes for the user to insert data
    private void addTextBoxes() {
        nameTextBox();
        setsTextBox();
        repsTextBox();
        dayTextBox();
    }

    // EFFECTS: adds the Name Text Box to the window
    //          to add the name to the workout
    private void nameTextBox() {
        name = new JTextField(" Enter Workout Name");
        name.setForeground(Color.lightGray);
        name.setBounds(110, 60, 165, 25);
        name.setBackground(Color.white);

        name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (name.getText().equals(" Enter Workout Name")) {
                    name.setText("");
                    name.setForeground(Color.black);
                    name.setBackground(Color.white);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (name.getText().equals("")) {
                    name.setText(" Enter Workout Name");
                    name.setForeground(Color.lightGray);
                }
            }
        });
        panel.add(name);
    }

    // EFFECTS: adds the Sets Text box to the window
    //          to add the reps of the workouts
    private void setsTextBox() {
        sets = new JTextField(" Enter No. of Sets");
        sets.setForeground(Color.lightGray);
        sets.setBounds(110, 120, 165, 25);
        sets.setBackground(Color.white);

        sets.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (sets.getText().equals(" Enter No. of Sets")) {
                    sets.setText("");
                    sets.setForeground(Color.black);
                    sets.setBackground(Color.white);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (sets.getText().equals("")) {
                    sets.setText(" Enter No. of Sets");
                    sets.setForeground(Color.lightGray);
                }
            }
        });
        panel.add(sets);
    }

    // EFFECTS: adds the Reps Text box to the window
    //          to add the reps of the workouts
    private void repsTextBox() {
        reps = new JTextField(" Enter No. of Reps");
        reps.setForeground(Color.lightGray);
        reps.setBounds(110, 180, 165, 25);
        reps.setBackground(Color.white);

        reps.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (reps.getText().equals(" Enter No. of Reps")) {
                    reps.setText("");
                    reps.setForeground(Color.black);
                    reps.setBackground(Color.white);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (reps.getText().equals("")) {
                    reps.setText(" Enter No. of Reps");
                    reps.setForeground(Color.lightGray);
                }
            }
        });
        panel.add(reps);
    }


    // EFFECTS: adds the Day Text box to the window
    //          to add the day of the workout
    private void dayTextBox() {
        day = new JTextField(" Enter Day of the Week");
        day.setForeground(Color.lightGray);
        day.setBounds(110, 240, 165, 25);
        day.setBackground(Color.white);

        day.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (day.getText().equals(" Enter Day of the Week")) {
                    day.setText("");
                    day.setForeground(Color.black);
                    day.setBackground(Color.white);
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
                errorMsgs();
                if (errorMsgs()) {
                    makeWorkout();
                    addWorkoutGif();
                    textsBackToNormal();

                    if (workoutsAndMeals.getWorkouts().size() != 0) {
                        cancelButton.setVisible(false);
                        doneButton();
                    }
                } else {
                    errorWorkoutGif();
                }


//                try {
//                    Double.parseDouble(sets.getText().strip());
//                    Double.parseDouble(reps.getText().strip());
//                    errorMsgs();
//                } catch (NumberFormatException a) {
//                    JOptionPane.showMessageDialog(null,
//                            "Please Enter Valid Input For Sets/Reps");
//                }

            }
        });
    }


    // EFFECTS: gives the error messages to the user to insert the correct details
    private boolean errorMsgs() {
        nameErrorMsg();
        setsErrorMsg();
        repsErrorMsg();
        dayErrorMsg();

        return nameErrorMsg() && setsErrorMsg() && repsErrorMsg() && dayErrorMsg();


//        if (name.getText().strip().equals("Enter Workout Name")) {
//            JOptionPane.showMessageDialog(null, "Please Enter Workout Name");
//        } else if (sets.getText().strip().equals("Enter No. of Sets")
//                || (Double.parseDouble(sets.getText().strip()) % 1 != 0)
//                || (sets.getText().strip().equals("0"))) {
//            JOptionPane.showMessageDialog(null, "Please Enter Valid No. Of Sets");
//        } else if (reps.getText().strip().equals("Enter No. of Reps")
//                || (Double.parseDouble(reps.getText().strip()) % 1 != 0)
//                || (reps.getText().strip().equals("0"))) {
//            JOptionPane.showMessageDialog(null, "Please Enter Valid No. Of Reps");
//        } else if (day.getText().strip().equals("Enter Day of the Week")
//                || (days.equals(day.getText().toLowerCase()))) {
//            JOptionPane.showMessageDialog(null, "Please Enter A Valid Day of the Week");
//        } else {
////            elseMethods();
//            try {
//                Integer.parseInt(sets.getText().strip());
//                Integer.parseInt(reps.getText().strip());
//                makeWorkout();
//                textsBackToNormal();
//
//                if (workoutsAndMeals.getWorkouts().size() != 0) {
//                    cancelButton.setVisible(false);
//                    doneButton();
//                }
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(null, "Please Enter Valid Input For Sets/Reps");
//            }
////            for (Workout w : workoutsAndMeals.getWorkouts()) {
////                System.out.println(w.getWorkout());
////                System.out.println(w.getDay());
////            }
//        }
    }


    // EFFECTS: makes the name textField Red if there is any error
    private boolean nameErrorMsg() {
        if (name.getText().strip().equals("Enter Workout Name")) {
            textBoxError(name);
            return false;
        } else {
            name.setBackground(Color.white);
            return true;
        }
    }


    // EFFECTS: makes the sets textField Red if there is any error
    private boolean setsErrorMsg() {
        if (!sets.getText().strip().equals("Enter No. of Sets")) {
            try {
                Double.parseDouble(sets.getText().strip());
                Integer.parseInt(sets.getText().strip());
                sets.setBackground(Color.white);
                return true;
            } catch (Exception e) {
                textBoxError(sets);
                return false;
            }
        } else if (sets.getText().strip().equals("Enter No. of Sets")
                || (Double.parseDouble(sets.getText().strip()) % 1 != 0)
                || (sets.getText().strip().equals("0"))) {
            textBoxError(sets);
            return false;
        } else {
            sets.setBackground(Color.white);
            return true;
        }
    }

    // makes the reps textField RED if there is any error
    private boolean repsErrorMsg() {
        if (!reps.getText().strip().equals("Enter No. of Reps")) {
            try {
                Double.parseDouble((reps.getText().strip()));
                Integer.parseInt(reps.getText().strip());
                reps.setBackground(Color.white);
                return true;
            } catch (Exception e) {
                textBoxError(reps);
                return false;
            }
        } else if (reps.getText().strip().equals("Enter No. of Reps")
                || (Double.parseDouble(reps.getText().strip()) % 1 != 0)
                || (reps.getText().strip().equals("0"))) {
            textBoxError(reps);
            return false;
        } else {
            reps.setBackground(Color.white);
            return true;
        }
    }


    // EFFECTS: makes the day textField Red if there is any error
    private boolean dayErrorMsg() {
        if (day.getText().strip().equals("Enter Day of the Week")
                || (days.equals(day.getText().strip().toLowerCase()))) {
            textBoxError(day);
            return false;
        } else {
            day.setBackground(Color.white);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: if there are any errors in the TextFields,
    //          then changes the textField colour to RED
    private void textBoxError(JTextField textField) {
        textField.setBackground(Color.decode("#E5AAAA"));
        textField.setForeground(Color.black);
//        textField.setForeground(Color.RED);

    }


//    // EFFECTS: gives the error messages for the sets and reps
//    //          to make sure the user inputs the correct data
//    private void elseMethods() {
//        try {
//            Integer.parseInt(sets.getText().strip());
//            Integer.parseInt(reps.getText().strip());
//            makeWorkout();
//            textsBackToNormal();
//
//            if (workoutsAndMeals.getWorkouts().size() != 0) {
//                cancelButton.setVisible(false);
//                doneButton();
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Please Enter Valid Input For Sets/Reps");
//        }
//    }

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

    // EFFECTS: construct a new Workouts everytime the user adds a Workout
    private void makeWorkout() {
//        int set = 0;
//        int rep = 0;
//        try {
//            set = Integer.parseInt(sets.getText().strip());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Please Enter Valid No. Of Sets");
//
//        }
//
//        try {
//            rep = Integer.parseInt(reps.getText().strip());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Please Enter Valid No. Of Reps");
//        }

        Workout workout = new Workout(name.getText(), Integer.parseInt(sets.getText().strip()),
                Integer.parseInt(reps.getText().strip()), days);
        workoutsAndMeals.addWorkout(workout);

    }

    // EFFECTS: used to input the full name of the Day after
    //          inputting the first three letters of the day
    private void getDay() {
        days = day.getText().toLowerCase();
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

    // EFFECTS: sets the text boxes to normal after adding each Meal
    private void textsBackToNormal() {
        name.setText(" Enter Workout Name");
        sets.setText(" Enter No. of Sets");
        reps.setText(" Enter No. of Reps");
        day.setText(" Enter Day of the Week");
        name.setForeground(Color.lightGray);
        sets.setForeground(Color.lightGray);
        reps.setForeground(Color.lightGray);
        day.setForeground(Color.lightGray);
    }

    // EFFECTS: adds a confirmation GIF onto the panel
    private void addWorkoutGif() {
        casualMealGif.setVisible(false);
        errorWorkoutGif.setVisible(false);
        ImageIcon icon = new ImageIcon("./data/Images/AddWorkout.gif");

        addWorkoutGif.setIcon(icon);
        addWorkoutGif.setBounds(400, 60, 250, 250);
        addWorkoutGif.setVisible(true);
        panel.add(addWorkoutGif);
    }

    // EFFECTS: adds an Error GIF onto the panel
    private void errorWorkoutGif() {
        casualMealGif.setVisible(false);
        addWorkoutGif.setVisible(false);
        ImageIcon icon = new ImageIcon("./data/Images/ErrorWorkout.gif");

        errorWorkoutGif.setIcon(icon);
        errorWorkoutGif.setBounds(410, 60, 250, 250);
        errorWorkoutGif.setVisible(true);
        panel.add(errorWorkoutGif);
    }

    // EFFECTS: adds a Casual GIF onto the panel
    private void casualMealGif() {
        ImageIcon icon = new ImageIcon("./data/Images/CasualWorkout.gif");
//        Image image = icon.getImage();
//        Image resizeImage = image.getScaledInstance(400, 300, Image.SCALE_DEFAULT);
//        icon = new ImageIcon(resizeImage);

        casualMealGif.setIcon(icon);
        casualMealGif.setBounds(410, 60, 250, 250);
        casualMealGif.setVisible(true);
        panel.add(casualMealGif);
    }


}
