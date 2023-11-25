package ui;

import model.ListsMaker;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

// Represents a Remove Workout Window to the user
public class RemoveWorkout extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    private JTextField remove;
    ListsMaker workoutsAndMeals;
    private JTable workoutTable;
    private ArrayList<String> workouts;
    private DefaultTableModel workoutTableModel;
    private int serialNo = 1;

    // EFFECTS: constructs the GUI for the Remove Workouts window
    public RemoveWorkout(ListsMaker workoutsAndMeals) {
        super("Remove Workout");
        this.workoutsAndMeals = workoutsAndMeals;

        mainWindow();
        setVisible(true);
    }

    // EFFECTS: constructs the main window for the Remove Workouts
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
    //          to select a workout to remove
    private void arrangeOnMainWindow() {
        panel = new JPanel();
        add(panel);
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout());
//        panel.setLayout(new GridLayout(1,2));
        removeGif();
        makeListsOnPanel();
        makeButtonsOnPanel();
    }

    // EFFECTS: prints out the list of workouts onto the window
    //          to select a workout to remove
    private void makeListsOnPanel() {
        workouts = new ArrayList<>();
        workoutTableModel = new DefaultTableModel();
        workoutTable = new JTable(workoutTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        workoutTableConditions();

//        workoutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        workoutTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
//                null, null, null, null));

        workoutTableModel.addColumn("");

        workoutTableModel.addRow(new String[]{"Choose From The Following Workouts To Remove: \n"});
        if (workoutsAndMeals.getWorkouts().size() == 0) {
            workoutTableModel.addRow(new Object[]{"  There Are No Workouts To Be Removed !"});
        } else {

            for (Workout workout : workoutsAndMeals.getWorkouts()) {
                workoutTableModel.addRow(new Object[]{"  " + serialNo + ". " + workout.getWorkout()
                        + " on " + workout.getDay()});
                workouts.add("  " + serialNo + ". " + workout.getWorkout()
                        + " on " + workout.getDay());
                serialNo++;
            }
        }

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        panel.add(new JScrollPane(workoutTable), BorderLayout.CENTER);
    }

    // EFFECTS: sets the conditions for the printed out lists
    private void workoutTableConditions() {
        workoutTable.setRowSelectionAllowed(false);
        workoutTable.setFocusable(false);
        workoutTable.setFont(new Font("Arial", Font.PLAIN, 17));
        workoutTable.setRowHeight(23);
    }

    // EFFECTS: adds all the buttons to the window
    private void makeButtonsOnPanel() {
        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.setBackground(Color.white);


        buttonPanel.add(removeTextLabel());
        buttonPanel.add(removeTextField());
        buttonPanel.add(removeButton());
        buttonPanel.add(removeAllButton());
        buttonPanel.add(doneButton());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

//        buttonPanel.setBounds(400, 10, 250, 100);

        panel.add(buttonPanel, BorderLayout.PAGE_END);

    }

    // EFFECTS: adds the Remove Text label to the window
    private JLabel removeTextLabel() {
        JLabel remove = new JLabel("Remove: ");
//        remove.setBounds(40, 60, 80, 25);
        remove.setFont(new Font(remove.getFont().getFontName(), Font.PLAIN, 15));
//        panel.add(remove);
        return remove;
    }

    // EFFECTS: adds the Remove Text Field to the window
    private JTextField removeTextField() {
        remove = new JTextField(" Enter S.No");
        remove.setForeground(Color.lightGray);
        remove.setBackground(Color.white);
//        remove.setBounds(110, 60, 165, 25);
        remove.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (remove.getText().equals(" Enter S.No")) {
                    remove.setText("");
                    remove.setForeground(Color.black);
                    remove.setBackground(Color.white);
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

    // EFFECTS: shows the error messages on the window
    //          to make sure the user inputs the correct data
    private void printErrorMsgs() {
        if (remove.getText().strip().equals("Enter S.No")
//                || (Double.parseDouble(remove.getText().strip()) % 1 != 0)
                || (remove.getText().strip().equals("0"))
//                || (Double.parseDouble(remove.getText().strip()) > workouts.size())
        ) {
            textBoxError(remove);
//            JOptionPane.showMessageDialog(null, "Please Enter A Valid S.No");
        } else if (!remove.getText().strip().equals("Enter S.No")) {
            try {
                Integer.parseInt(remove.getText());

                if (Double.parseDouble(remove.getText().strip()) % 1 != 0
                        || Double.parseDouble(remove.getText().strip()) > workouts.size()) {
                    textBoxError(remove);
                } else {
                    printChangedWorkouts();
                }
            } catch (Exception e) {
                textBoxError(remove);
            }
        }
//        else {
//            printChangedWorkouts();
//        }
    }

    // EFFECTS: makes a new window after removing one Workout
    //          to make sure the numbering is correct on the list of workouts
    private void printChangedWorkouts() {
//        try {
        int rowNum = Integer.parseInt(remove.getText());

        if (rowNum > 0) {
            if (workouts.contains(workoutTableModel.getValueAt(rowNum, 0))) {
                workoutsAndMeals.removeWorkout(workoutsAndMeals.getWorkouts().get(rowNum - 1));
                workoutTableModel.removeRow(rowNum);
                dispose();
                if (!isVisible()) {
                    new RemoveWorkout(workoutsAndMeals);
                }
            }
        }
//        } catch (NumberFormatException e) {
//            textBoxError(remove);
////            JOptionPane.showMessageDialog(null, "Please Enter Valid S.No");
//        }
    }

    // EFFECTS: adds a Remove All button onto the window to remove all the workouts
    private JButton removeAllButton() {
        JButton removeButton = new JButton("Remove All");
        removeButton.setCursor(handCursor);
//        removeButton.setBounds(300, 90, 80, 25);


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (workoutsAndMeals.getWorkouts().size() >= 1) {
                    workoutsAndMeals.getWorkouts().removeAll(workoutsAndMeals.getWorkouts());
                    while (workoutTableModel.getRowCount() > 0) {
                        workoutTableModel.removeRow(0);
                    }
                    dispose();
                    if (!isVisible()) {
                        new RemoveWorkout(workoutsAndMeals);
                    }
                }
            }
        });

        return removeButton;
    }


    // MODIFIES: this
    // EFFECTS: if there are any errors in the TextFields,
    //          then changes the textField colour to RED
    private void textBoxError(JTextField textField) {
        textField.setBackground(Color.decode("#E5AAAA"));
        textField.setForeground(Color.black);
//        textField.setForeground(Color.RED);

    }


    // EFFECTS: adds a Remove GIF onto the panel
    private void removeGif() {
        ImageIcon icon = new ImageIcon("./data/Images/Remove.gif");
//        Image image = icon.getImage();
//        Image resizeImage = image.getScaledInstance(400, 300, Image.SCALE_DEFAULT);
//        icon = new ImageIcon(resizeImage);

        JLabel removeGif = new JLabel();
        removeGif.setIcon(icon);
        removeGif.setBounds(410, 60, 350, 250);
        removeGif.setVisible(true);
        panel.add(removeGif);
    }

}
