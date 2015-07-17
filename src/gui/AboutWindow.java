package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutWindow dialog = new AboutWindow();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutWindow() {
		setBounds(100, 100, 398, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblVer = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF \u5BA2\u6237\u7AEF ver 1.0");
			lblVer.setBounds(30, 44, 279, 15);
			lblVer.setFont(new Font("新宋体", Font.PLAIN, 14));
			contentPanel.add(lblVer);
		}
		{
			JLabel label = new JLabel("\u4F5C\u8005\uFF1A\u8C22\u9E4F\u5B87");
			label.setBounds(30, 82, 115, 15);
			label.setFont(new Font("新宋体", Font.PLAIN, 14));
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5B8C\u6210\u65E5\u671F\uFF1A2015-07-16");
			label.setBounds(30, 122, 162, 15);
			label.setFont(new Font("新宋体", Font.PLAIN, 14));
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
