package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import struct.Book;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5281023371922480140L;
	private final JPanel contentPanel = new JPanel();
	private JTextField bookID;
	private JTextField bookName;
	private JTextField bookAuthor;
	private JTextField bookCost;
	
	public int status = 1;
	
	public Book getBook(){
		Book b = new Book();
		b.setBookID(Integer.parseInt(bookID.getText()));
		b.setBookName(bookName.getText());
		b.setBookAuthor(bookAuthor.getText());
		b.setCost(Double.parseDouble(bookCost.getText()));
		return b;
	}
	
	public void setBook(Book b){
		bookID.setText(b.getBookID()+"");
		bookCost.setText(b.getCost()+"");
		bookName.setText(b.getBookName());
		bookAuthor.setText(b.getBookAuthor());
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public EditDialog(String title) {
		
		setTitle(title);
		setBounds(100, 100, 300, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
		label.setBounds(22, 30, 86, 15);
		label.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(label);
		
		bookID = new JTextField();
		bookID.setBounds(118, 26, 138, 21);
		bookID.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(bookID);
		bookID.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		label_1.setBounds(22, 72, 86, 15);
		label_1.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(label_1);
		
		bookName = new JTextField();
		bookName.setBounds(118, 68, 138, 21);
		bookName.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(bookName);
		bookName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u4F5C\u8005\uFF1A");
		label_2.setBounds(22, 111, 86, 15);
		label_2.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(label_2);
		
		bookAuthor = new JTextField();
		bookAuthor.setBounds(118, 107, 138, 21);
		bookAuthor.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(bookAuthor);
		bookAuthor.setColumns(10);
		
		bookCost = new JTextField();
		bookCost.setColumns(10);
		bookCost.setBounds(118, 151, 138, 21);
		bookCost.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(bookCost);
		
		JLabel label_3 = new JLabel("\u4EF7\u683C\uFF1A");
		label_3.setBounds(22, 155, 86, 15);
		label_3.setFont(new Font("新宋体", Font.PLAIN, 14));
		contentPanel.add(label_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						status = 0;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						status = 1;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
