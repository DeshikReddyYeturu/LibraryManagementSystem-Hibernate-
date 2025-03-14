package librarymanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import librarymanagement.DAO.*;
import librarymanagement.Entity.*;

public class ReturnBook extends JFrame implements ActionListener {
    JTextField bookIdField, bookNameField, studentIdField, studentNameField, studentContactField;
    JButton returnButton;
    private StudentList studentList;
    ReturnBook(StudentList studentList) {
        this.studentList = studentList;
        setTitle("Return Book");
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

        returnButton = new JButton("Return Book");
        returnButton.setBounds(150, 200, 100, 30);
        returnButton.addActionListener(this);
        add(returnButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            String bookId = bookIdField.getText();
            String studentId = studentIdField.getText();

            try {
                // Fetch Book, Student, and IssuedBook entities
                BookDAO bookDAO = new BookDAO();
                Book book = bookDAO.getBookById(bookId);

                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getStudentById(studentId);

                IssuedBookDAO issuedBookDAO = new IssuedBookDAO();
                IssuedBook issuedBook = issuedBookDAO.getIssuedBook(bookId, studentId);

                if (book != null && student != null && issuedBook != null) {
                    issuedBook.setReturnDate(new Date());
                    issuedBookDAO.updateIssuedBook(issuedBook);

                    book.setQuantity(book.getQuantity() + 1);
                    bookDAO.updateBook(book);

                    JOptionPane.showMessageDialog(this, "Book returned successfully!");
                    studentList.populateStudentTable();

                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Book return failed. Book ID or Student ID may be incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}