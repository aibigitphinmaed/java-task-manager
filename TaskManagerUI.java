import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

public class TaskManagerUI extends JFrame {
    private JComboBox<String> output;
    private JComboBox<String> number;
    private JSpinner dateSpinner;
    private JPanel taskListPanel;
    private TaskController controller;

    public TaskManagerUI(TaskController controller) {
        this.controller = controller;
        setTitle("UniTask Manager");
        setSize(480, 720);
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("UniTask Manager", SwingConstants.CENTER);
        title.setForeground(Color.GREEN);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel description = new JLabel("Stay organized with UniTask Manager!", SwingConstants.CENTER);
        description.setForeground(Color.WHITE);
        description.setFont(new Font("Arial", Font.BOLD, 14));

        titlePanel.add(title);
        titlePanel.add(description);

        add(titlePanel, BorderLayout.NORTH);

        // Panel for task input
        JPanel addContainer = new JPanel();
        addContainer.setBorder(new EmptyBorder(10, 42, 10, 42));
        addContainer.setLayout(new GridBagLayout());
        addContainer.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 10);

        JLabel addLabel = new JLabel("Add a Task");
        addLabel.setForeground(Color.WHITE);
        addLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addContainer.add(addLabel, gbc);

        // Dropdown for category
        output = new JComboBox<>(new String[]{"Activity", "Quiz", "Assignment"});
        output.setPreferredSize(new Dimension(130, 30));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addContainer.add(new JLabel("Category"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        addContainer.add(output, gbc);

        // Dropdown for number
        number = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        number.setPreferredSize(new Dimension(130, 30));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addContainer.add(new JLabel("Number"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        addContainer.add(number, gbc);

        // Date picker
        JLabel deadlineTxt = new JLabel("Deadline");
        deadlineTxt.setForeground(Color.WHITE);
        deadlineTxt.setFont(new Font("Arial", Font.PLAIN, 16));
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setPreferredSize(new Dimension(130, 30));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        addContainer.add(deadlineTxt, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        addContainer.add(dateSpinner, gbc);

        // Add Task Button
        JButton submitButton = new JButton("Add Task");
        submitButton.addActionListener(e -> controller.addTask(output, number, dateSpinner, taskListPanel));
        submitButton.setBackground(Color.GREEN);
        
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        addContainer.add(submitButton, gbc);

        // Add the input panel to the center
        add(addContainer, BorderLayout.CENTER);

        // Task List Panel (At Bottom)
        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        taskListPanel.setBackground(Color.DARK_GRAY);

        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setPreferredSize(new Dimension(380, 200));

        JPanel listContainer = new JPanel(new BorderLayout());
        listContainer.add(new JLabel("Task List", SwingConstants.CENTER), BorderLayout.NORTH);
        listContainer.add(scrollPane, BorderLayout.CENTER);
        
        
        add(listContainer, BorderLayout.SOUTH); // Ensure task list is below

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

