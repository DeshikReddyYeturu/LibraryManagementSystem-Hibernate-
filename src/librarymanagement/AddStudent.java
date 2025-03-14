package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import librarymanagement.DAO.StudentDAO;
import librarymanagement.Entity.Student;

public class AddStudent extends JFrame implements ActionListener {
    JTextField studentIdField, studentNameField, studentContactField;
    JButton addButton;

    public AddStudent() {
        setTitle("Add Student");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        panel.add(studentIdField);

        panel.add(new JLabel("Student Name:"));
        studentNameField = new JTextField();
        panel.add(studentNameField);

        panel.add(new JLabel("Student Contact:"));
        studentContactField = new JTextField();
        panel.add(studentContactField);

        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        panel.add(addButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String studentId = studentIdField.getText();
            String studentName = studentNameField.getText();
            String studentContact = studentContactField.getText();

            Student student = new Student();
            student.setStudentId(studentId);
            student.setStudentName(studentName);
            student.setStudentContact(studentContact);

            StudentDAO studentDAO = new StudentDAO();
            studentDAO.saveStudent(student);

            JOptionPane.showMessageDialog(this, "Student added successfully!");
            this.dispose();
        }
    }
}