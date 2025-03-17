import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.Map;

public class TaskController {

    public void addTask(JComboBox<String> output, JComboBox<String> number, JSpinner dateSpinner, JPanel taskListPanel) {
        String selectedCategory = (String) output.getSelectedItem();
        String selectedNumber = (String) number.getSelectedItem();
        String selectedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateSpinner.getValue());

        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.X_AXIS));
        taskPanel.setBackground(Color.DARK_GRAY);
        taskPanel.setMaximumSize(new Dimension(320, 35));

        JLabel taskLabel = new JLabel(selectedCategory + " " + selectedNumber + " " + selectedDate);
        taskLabel.setForeground(Color.WHITE);
        taskLabel.setPreferredSize(new Dimension(180, 25));

        JButton markDone = new JButton("Done");
        markDone.setBackground(Color.GREEN);
        markDone.addActionListener(e -> markTaskDone(taskLabel, markDone));

        JButton del = new JButton("Del");
        del.setBackground(Color.RED);
        del.addActionListener(e -> deleteTask(taskPanel, taskListPanel));

        taskPanel.add(taskLabel);
        taskPanel.add(markDone);
        taskPanel.add(del);

        taskListPanel.add(taskPanel);
        taskListPanel.revalidate();
        taskListPanel.repaint();
    }

    private void deleteTask(JPanel taskPanel, JPanel taskListPanel) {
        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Task", JOptionPane.YES_NO_OPTION);
        if (confirmDelete == JOptionPane.YES_OPTION) {
            taskListPanel.remove(taskPanel);
            taskListPanel.revalidate();
            taskListPanel.repaint();
        }
    }

    private void markTaskDone(JLabel taskLabel, JButton markDone) {
        Font font = taskLabel.getFont();
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        taskLabel.setFont(font.deriveFont(attributes));
        markDone.setBackground(Color.GRAY);
    }
}

