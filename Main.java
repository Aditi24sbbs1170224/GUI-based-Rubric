import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Student App");
        f.setSize(400, 300);
        f.setLayout(new FlowLayout());

        JTextField name = new JTextField(20);
        JTextField email = new JTextField(20);
        JTextField course = new JTextField(20);

        JButton save = new JButton("Save");

        f.add(new JLabel("Name:"));
        f.add(name);
        f.add(new JLabel("Email:"));
        f.add(email);
        f.add(new JLabel("Course:"));
        f.add(course);
        f.add(save);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "root", "");
                    Statement s = c.createStatement();
                    String q = "INSERT INTO students(name,email,course) VALUES('" + name.getText() + "','" + email.getText() + "','" + course.getText() + "')";
                    s.executeUpdate(q);
                    JOptionPane.showMessageDialog(f, "Saved!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
