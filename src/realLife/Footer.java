package realLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

class Footer extends JPanel {

    JButton addTask;
    JButton clear;
    JButton sort;

    Color lightColor = new Color(252, 221, 176);
    Border emptyBorder = BorderFactory.createEmptyBorder();

    Footer() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(lightColor);

        addTask = new JButton(new ImageIcon("add_icon.png")); 
   //     System.out.println("Icon file path: " + "add_icon.png");
        addTask.setBorder(emptyBorder); // remove border
        addTask.setFont(new Font("Sans-serif", Font.ITALIC, 20)); 
        addTask.setVerticalAlignment(JButton.BOTTOM); 
        addTask.setBackground(new Color(252, 221, 176)); 
        this.add(addTask); // add to footer

        this.add(Box.createHorizontalStrut(20));// for space between items
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        clear = new JButton("Clear finished tasks"); 
        clear.setFont(new Font("Sans-serif", Font.ITALIC, 20)); // set font
     //   clear.setBorder(emptyBorder);// no border
        clear.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 110),2));// set background color
        this.add(clear);
        
        sort = new JButton("Sort by Priority"); 
        sort.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        sort.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 110),2)); 
 
        this.add(sort);
//        addTask.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent e) {
//                addTask.setBackground(new Color(255, 165, 0)); // Change color on hover
//            }
//
//            public void mouseExited(MouseEvent e) {
//                addTask.setBackground(orange); 
//            }
//        });
    }

    public JButton getNewTask() {
        return addTask;
    }

    public JButton getClear() {
        return clear;
    }
    public JButton getSort() {
        return sort;
    }
}
