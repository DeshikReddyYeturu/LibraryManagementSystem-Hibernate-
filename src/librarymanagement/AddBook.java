package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import librarymanagement.DAO.BookDAO;
import librarymanagement.Entity.*;

public class AddBook extends JFrame implements ActionListener {
    JTextField bookIdField, bookNameField, authorField, quantityField; // Changed publicationField to quantityField
    JButton addButton;

    public AddBook() {
        setTitle("Add Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Book ID:"));
        bookIdField = new JTextField();
        panel.add(bookIdField);

        panel.add(new JLabel("Book Name:"));
        bookNameField = new JTextField();
        panel.add(bookNameField);

        panel.add(new JLabel("Author:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("Available Quantity:")); // Changed label
        quantityField = new JTextField(); // Changed field name
        panel.add(quantityField);

        addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        panel.add(addButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String bookId = bookIdField.getText();
            String bookName = bookNameField.getText();
            String author = authorField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            Book book = new Book();
            book.setBookId(bookId);
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setQuantity(quantity);

            BookDAO bookDAO = new BookDAO();
            bookDAO.saveBook(book);

            JOptionPane.showMessageDialog(this, "Book added successfully!");
            this.dispose();
        }
    }
}