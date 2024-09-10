package realLife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Task extends JPanel {

    JLabel index;
    JTextField taskName;
    JButton done;
    JButton clearButton;
    JTextField dueDate;
    JComboBox<String> priorityComboBox;
    
    Color green = new Color(152, 251, 152);

    private boolean checked;

    Task() {
        this.setPreferredSize(new Dimension(400, 120)); // set size of task
        this.setBackground(new Color(173, 216, 230)); // background color

        this.setLayout(new BorderLayout()); //layout of task

        checked = false;

        
        //index numbers
        index = new JLabel(""); 
        index.setPreferredSize(new Dimension(20, 40)); 
        index.setHorizontalAlignment(JLabel.CENTER);
        this.add(index, BorderLayout.WEST); 

        //priority
       
        String[] priorityLevels = {"Low", "Medium", "High"};
        priorityComboBox = new JComboBox<>(priorityLevels);
        priorityComboBox.setSelectedIndex(0); // Set default priority to Low
      
        priorityComboBox.setPreferredSize(new Dimension(80, 18));
        
        //done button
        done = new JButton("Done");
        done.setPreferredSize(new Dimension(80, 20));
        done.setBorder(BorderFactory.createEmptyBorder());
        done.setBackground(new Color(144, 238, 144)); 
        done.setFocusPainted(false);

        // name of task
        taskName = new JTextField(); // create task name text field
        taskName.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
        taskName.setBackground(new Color(173, 216, 230)); // set background color of text field
        taskName.setFont(new Font("Arial", Font.PLAIN, 14));
        
     // Add a placeholder text
        taskName.setForeground(Color.GRAY);
        taskName.setText("Write something..");
        taskName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taskName.getText().equals("Write something..")) {
                    taskName.setText("");
                    taskName.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (taskName.getText().isEmpty()) {
                    taskName.setText("Write something..");
                    taskName.setForeground(Color.GRAY);
                }
            }
        });
   
        
        //due date
        dueDate = new JTextField();
        dueDate.setBorder(BorderFactory.createEmptyBorder());
        dueDate.setBackground(new Color(173, 216, 230));
        dueDate.setFont(new Font("Arial", Font.PLAIN, 14));
        dueDate.setForeground(Color.GRAY);
        dueDate.setText("Set due date...");
        
        dueDate.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dueDate.getText().equals("Set due date...")) {
                    dueDate.setText("");
                    dueDate.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dueDate.getText().isEmpty()) {
                    dueDate.setText("Set due date...");
                    dueDate.setForeground(Color.GRAY);
                }
            }
        });
        
        
        //layout of footer
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(taskName, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.CENTER);
        topPanel.add(dueDate, BorderLayout.SOUTH);
        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(priorityComboBox, BorderLayout.SOUTH);
        eastPanel.add(done, BorderLayout.NORTH);
        this.add(eastPanel, BorderLayout.EAST); 
    
  

    }

    public String getPriorityLevel() {
        return (String) priorityComboBox.getSelectedItem();
    }
    public void changeIndex(int num) {
        this.index.setText(num + ""); // num to String
        this.revalidate(); // refresh
    }

    public JButton getDone() {
        return done;
    }

    public boolean getState() {
        return checked;
    }

    public void changeState() {
        this.setBackground(green);
        taskName.setBackground(green);
        dueDate.setBackground(green);
        checked = true;
        revalidate();
    }
}