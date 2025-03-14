package librarymanagement; // Assuming the package is librarymanagementsystem

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import librarymanagement.DAO.*;
import librarymanagement.Entity.*;
public class IssueBook extends JFrame implements ActionListener {
    JTextField bookIdField, bookNameField, studentIdField, studentNameField, studentContactField;
    JButton issueButton;

    IssueBook() {
        setTitle("Issue Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(50, 30, 100, 20);
        add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(150, 30, 150, 20);
        add(bookIdField);

        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setBounds(50, 60, 100, 20);
        add(bookNameLabel);

        bookNameField = new JTextField();
        bookNameField.setBounds(150, 60, 150, 20);
        add(bookNameField);

        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 90, 100, 20);
        add(studentIdLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(150, 90, 150, 20);
        add(studentIdField);

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setBounds(50, 120, 100, 20);
        add(studentNameLabel);

        studentNameField = new JTextField();
        studentNameField.setBounds(150, 120, 150, 20);
        add(studentNameField);

        JLabel studentContactLabel = new JLabel("Student Contact:");
        studentContactLabel.setBounds(50, 150, 100, 20);
        add(studentContactLabel);

        studentContactField = new JTextField();
        studentContactField.setBounds(150, 150, 150, 20);
        add(studentContactField);

        issueButton = new JButton("Issue Book");
        issueButton.setBounds(150, 200, 100, 30);
        issueButton.addActionListener(this);
        add(issueButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == issueButton) {
            String bookId = bookIdField.getText();
            String bookName = bookNameField.getText();
            String studentId = studentIdField.getText();
            String studentName = studentNameField.getText();
            String studentContact = studentContactField.getText();
            
            Student student = new Student();
            student.setStudentId(studentId);
            student.setStudentName(studentName);
            student.setStudentContact(studentContact);

            StudentDAO studentDAO = new StudentDAO();
            studentDAO.saveStudent(student);
            
            IssuedBook issuedBook = new IssuedBook();
            issuedBook.setStudent(student);
            issuedBook.setIssueDate(new Date());
            IssuedBookDAO issuedBookDAO = new IssuedBookDAO();
            issuedBookDAO.saveIssuedBook(issuedBook);

            JOptionPane.showMessageDialog(this, "Book issued successfully!");
            this.dispose();
        }
    }
}