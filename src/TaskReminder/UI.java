package TaskReminder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    private final TaskReminderApp taskReminderApp = new TaskReminderApp();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UI::new);
    }

    public UI() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Task Reminder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#f0f8ff"));

        // Title
        JLabel titleLabel = new JLabel("Task Reminder", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#007acc"));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Task List
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        taskList.setBackground(Color.decode("#ffffff"));
        taskList.setBorder(BorderFactory.createLineBorder(Color.decode("#007acc"), 1));
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        loadTasksIntoList(taskListModel);

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        inputPanel.setBackground(Color.decode("#f0f8ff"));

        JLabel nameLabel = new JLabel("Task Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        JTextField nameField = new JTextField();
        JLabel timeLabel = new JLabel("Task Time (yyyy-MM-dd HH:mm):");
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        JTextField timeField = new JTextField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(Color.decode("#f0f8ff"));

        JButton addButton = new JButton("Add Task");
        addButton.setBackground(Color.decode("#4caf50"));
        addButton.setForeground(Color.white);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton deleteButton = new JButton("Delete Task");
        deleteButton.setBackground(Color.decode("#f44336"));
        deleteButton.setForeground(Color.white);
        deleteButton.setFocusPainted(false);
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = nameField.getText().trim();
                String taskTime = timeField.getText().trim();

                if (taskName.isEmpty() || taskTime.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                taskReminderApp.addTask(taskName, taskTime);
                taskListModel.addElement(taskName + " - " + taskTime);
                nameField.setText("");
                timeField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTask = taskList.getSelectedValue();
                if (selectedTask == null) {
                    JOptionPane.showMessageDialog(frame, "Please select a task to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String taskName = selectedTask.split(" - ")[0].trim();
                taskReminderApp.deleteTask(taskName);
                taskListModel.removeElement(selectedTask);
            }
        });

        // Layout Composition
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Finalize Frame
        frame.setVisible(true);
    }

    private void loadTasksIntoList(DefaultListModel<String> taskListModel) {
        for (Task task : taskReminderApp.getTasks()) {
            taskListModel.addElement(task.getName() + " - " + task.getTime().toString());
        }
    }
}