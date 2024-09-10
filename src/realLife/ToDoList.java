package realLife;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;



class TaskList extends JPanel {

    Color lightColor = new Color(252, 221, 176);

    TaskList() {

        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5); // Vertical gap
this.setVisible(true);
        this.setLayout(layout); // 10 tasks
        this.setPreferredSize(new Dimension(500, 560));
        this.setBackground(lightColor);
    }

    public void updateNumbers() {
        Component[] listItems = this.getComponents();

        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i] instanceof Task) {
                ((Task) listItems[i]).changeIndex(i + 1);
            }
        }

    }

    public void removeCompletedTasks() {

        for (Component c : getComponents()) {
            if (c instanceof Task) {
                if (((Task) c).getState()) {
                    remove(c); // remove the component
                    updateNumbers(); // update the indexing of all items
                }
            }
        }

    }
    public void sortTasksByPriority() {
        Component[] listItems = this.getComponents();

        List<Task> tasks = new ArrayList<>();

        for (Component c : listItems) {
            if (c instanceof Task) {
                tasks.add((Task) c);
            }
        }

        tasks.sort(Comparator.comparing(task -> {
            String priority = task.getPriorityLevel();
            switch (priority) {
                case "Low":
                    return 2;
                case "Medium":
                    return 1;
                case "High":
                    return 0;
                default:
                    return 0;
            }
        }));

        this.removeAll();

        for (Task task : tasks) {
            this.add(task);
        }

        this.revalidate();
        this.repaint();
    }
    
    public void searchTasks(String keyword) {
        Component[] listItems = this.getComponents();

        for (Component c : listItems) {
            if (c instanceof Task) {
                Task task = (Task) c;
                String taskName = task.taskName.getText(); // Assuming taskName is accessible
                if (taskName.toLowerCase().contains(keyword.toLowerCase())) {
                    task.setVisible(true); // Show matching tasks
                } else {
                    task.setVisible(false); // Hide non-matching tasks
                }
            }
        }
    }

    public void resetSearch() {
        Component[] listItems = this.getComponents();
        for (Component c : listItems) {
            if (c instanceof Task) {
                c.setVisible(true); // Reset visibility for all tasks
            }
        }
    }
    
}


class TitleBar extends JPanel {
	 static JButton search; // New button for searching
	    static JTextField searchField; // New text field for search input
    TitleBar() {
    	 this.setPreferredSize(new Dimension(400, 80));
    	    this.setBackground(new Color(173, 216, 230)); 
    	    JLabel titleText = new JLabel("To Do List");
    	    titleText.setPreferredSize(new Dimension(200, 60));
    	    titleText.setFont(new Font("Sans-serif", Font.BOLD, 30));
    	    titleText.setHorizontalAlignment(JLabel.CENTER);
    	    titleText.setForeground(Color.WHITE);
    	    this.add(titleText);
    	    
    	    search = new JButton("Search"); // New button for searching
            search.setFont(new Font("Sans-serif", Font.ITALIC, 20)); // set font
            search.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(192, 192, 192), 1), // Border color
                    BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding
                ));

                search.setBackground(new Color(135, 206,250)); // set background color
            this.add(search, BorderLayout.EAST);
          
            searchField = new JTextField(); // New text field for search input
            searchField.setPreferredSize(new Dimension(200, 30)); // Set preferred size
            this.add(searchField);
    }
    
    public static JButton getSearch() {
        return search;
    }

    public static JTextField getSearchField() {
        return searchField;
    }
}

class AppFrame extends JFrame {

    private TitleBar title;
    private Footer footer;
    private TaskList list;

    private JButton newTask;
    private JButton clear;
    private JButton sort; 
    
    private JButton search; 
    private JTextField searchField; // 

    AppFrame() {
        this.setSize(530, 600); // 400 width and 600 height
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(173, 216, 230));
        title = new TitleBar();
        footer = new Footer();
        list = new TaskList();
      

        this.add(title, BorderLayout.NORTH); 
        this.add(footer, BorderLayout.SOUTH); 
        this.add(list, BorderLayout.CENTER);

        newTask = footer.getNewTask();
        clear = footer.getClear();
        sort = footer.getSort(); 
        addListeners();
        this.setVisible(true);

    }

    public void addListeners() {
        newTask.addMouseListener(new MouseAdapter() {
            @override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task); // Add new task to list
                list.updateNumbers();

                task.getDone().addMouseListener(new MouseAdapter() {
                    @override
                    public void mousePressed(MouseEvent e) {

                        task.changeState(); // Change color of task
                        list.updateNumbers();
                        revalidate(); 

                    }
                });
            }

        });

        clear.addMouseListener(new MouseAdapter() {
            @override
            public void mousePressed(MouseEvent e) {
                list.removeCompletedTasks(); // Removes all tasks that are done
                repaint(); 
            }
        });
        sort.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                list.sortTasksByPriority(); 
            }
        });
        
        search = TitleBar.getSearch();
        searchField = TitleBar.getSearchField(); 

        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                list.searchTasks(searchField.getText()); 
            }
        });
        

        searchField.addActionListener(e -> {
            if (searchField.getText().isEmpty()) {
                list.resetSearch();
            }
        });
    }

}

public class ToDoList {

    public static void main(String args[]) {
        AppFrame frame = new AppFrame(); // Create the frame
    }
}

@interface override {

}