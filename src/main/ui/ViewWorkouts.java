package ui;

import model.ListsMaker;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWorkouts extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 450;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel panel;
    ListsMaker workoutsAndMeals;
    private JTable workoutTable;
    private DefaultTableModel workoutTableModel;
    private int serialNo = 1;

    public ViewWorkouts(ListsMaker workoutsAndMeals) {
        super("View Workouts");

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

        workoutTableModel.addRow(new String[]{"All Workouts: \n"});
        if (workoutsAndMeals.getWorkouts().size() == 0) {
            workoutTableModel.addRow(new Object[]{"  There Are No Workouts To Be Viewed !"});
        } else {

            for (Workout workout : workoutsAndMeals.getWorkouts()) {
                workoutTableModel.addRow(new Object[]{"  " + serialNo + ". " + workout.getWorkout()
                        + " on " + workout.getDay()});
                serialNo++;
            }
        }

//        JScrollPane scrollPane = new JScrollPane(workoutTable);
        panel.add(new JScrollPane(workoutTable), BorderLayout.CENTER);
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


        buttonPanel.add(viewWorkoutsByDayButton());
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

    private JButton viewWorkoutsByDayButton() {
        JButton viewWorkoutsByDayButton = new JButton("View Workouts By Day");
        viewWorkoutsByDayButton.setCursor(handCursor);
//        removeButton.setBounds(300, 90, 80, 25);


        viewWorkoutsByDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new ViewWorkoutsByDay(workoutsAndMeals);
            }
        });

        return viewWorkoutsByDayButton;
    }

}
