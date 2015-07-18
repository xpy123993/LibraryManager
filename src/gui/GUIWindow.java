package gui;

import network.MainClient;

import javax.swing.*;

import common.CONSTANT;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Í¼Êé¹ÜÀíÏµÍ³ ¿Í»§¶Ë
 * 
 * @author ÅôÓî
 * @version 1.2
 */

public class GUIWindow {

	private JFrame frame;
	private MyTableModel tableModel;
	private MainClient mainClient = null;
	private JTable table;
	private JTextField textField;
	private JComboBox comboBox;
	private JLabel statusLabel;
	/**
	 * Launch the application.
	 */
	public static void Show(){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUIWindow window = new GUIWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		mainClient = new MainClient();
		
		frame = new JFrame();
		frame.setFont(new Font("ÐÂËÎÌå", Font.PLAIN, 14));
		frame.setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF \u5BA2\u6237\u7AEF");
		frame.setBounds(100, 100, 884, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7CFB\u7EDF");
		menu.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menuBar.add(menu);
		
		JMenuItem connect = new JMenuItem("\u8FDE\u63A5");
		connect.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu.add(connect);
		
		JMenuItem shutdown = new JMenuItem("\u65AD\u5F00");
		shutdown.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu.add(shutdown);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		
		
		JMenuItem exit = new JMenuItem("\u9000\u51FA");
		exit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu.add(exit);
		
		JMenu menu_1 = new JMenu("\u7F16\u8F91");
		menu_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menuBar.add(menu_1);
		
		JMenuItem newBook = new JMenuItem("\u65B0\u5EFA");
		newBook.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu_1.add(newBook);
		
		JMenuItem editBook = new JMenuItem("\u4FEE\u6539");
		editBook.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu_1.add(editBook);
		
		JMenuItem findBook = new JMenuItem("\u67E5\u627E");
		findBook.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu_1.add(findBook);
		
		JMenuItem delBook = new JMenuItem("\u5220\u9664");
		delBook.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu_1.add(delBook);
		
		JMenu menu_2 = new JMenu("\u5E2E\u52A9");
		menu_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menuBar.add(menu_2);
		
		JMenuItem about = new JMenuItem("\u5173\u4E8E");
		about.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		menu_2.add(about);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setRowHeight(22);
		table.setFont(new Font("SansSerif", Font.PLAIN, 12));
		table.setFillsViewportHeight(true);
		table.setBounds(0, 241, 866, 240);
		//frame.getContentPane().add(table);

		JLabel label = new JLabel("\u7B2C");
		label.setBounds(86, 10, 21, 15);
		frame.getContentPane().add(label);
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));

		JLabel label_1 = new JLabel("\u9875");
		label_1.setBounds(169, 10, 21, 15);
		frame.getContentPane().add(label_1);
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));

		comboBox = new JComboBox();
		comboBox.setBounds(109, 7, 50, 21);
		comboBox.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		frame.getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBounds(607, 6, 148, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));

		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.changeList(mainClient.FindBook(textField.getText()));
				//table.repaint();
			}
		});
		btnNewButton.setBounds(765, 5, 93, 23);
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		frame.getContentPane().add(btnNewButton);

		tableModel = new MyTableModel();
		table.setModel(tableModel);

		final JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		JMenuItem pop_edit = new JMenuItem("\u4FEE\u6539");
		pop_edit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		popupMenu.add(pop_edit);

		JMenuItem pop_new = new JMenuItem("\u65B0\u5EFA");
		pop_new.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		popupMenu.add(pop_new);

		JMenuItem pop_del = new JMenuItem("\u5220\u9664");
		pop_del.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		popupMenu.add(pop_del);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 34, 868, 477);
		frame.getContentPane().add(scrollPane);

		JButton btnPrevios = new JButton("Back");
		btnPrevios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != 0 && comboBox.getItemCount() != 0)
					comboBox.setSelectedIndex(comboBox.getSelectedIndex() - 1);
			}
		});
		btnPrevios.setBounds(10, 6, 66, 23);
		btnPrevios.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		frame.getContentPane().add(btnPrevios);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != comboBox.getItemCount() - 1 && comboBox.getItemCount() != 0)
					comboBox.setSelectedIndex(comboBox.getSelectedIndex() + 1);
			}
		});
		btnNext.setBounds(200, 6, 66, 23);
		btnNext.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		frame.getContentPane().add(btnNext);

		statusLabel = new JLabel("\u5C31\u7EEA");
		statusLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		statusLabel.setBounds(10, 521, 848, 15);
		frame.getContentPane().add(statusLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 513, 868, 2);
		frame.getContentPane().add(separator_1);
		
		connect.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				ConnectDialog cd = new ConnectDialog();
				cd.setModal(true);
				cd.setVisible(true);
				if(cd.ret == 0){

					if (mainClient.connectTo(cd.ipAddress)) {
						if (!mainClient.unreachble()) {
							int bookAmount = mainClient.getBookAmount();
							int pageAmount = (bookAmount - 1) / CONSTANT.ITEMPERPAGE;
							//tableModel.bookSet = mainClient.ListBook(0);
							comboBox.removeAllItems();
							for (int i = 0; i < pageAmount + 1; i++)
								comboBox.addItem("" + (i + 1));
						}
						//table.repaint();
						statusLabel.setText("Connect Success.");
					} else statusLabel.setText("Connect Failed.Check Internet.");
				}
					
			}
		});

		ActionListener addActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditDialog ed = new EditDialog("Add Book Info");
				ed.setModal(true);
				ed.setVisible(true);
				
				if(ed.status == 0){
					mainClient.AddBook(ed.getBook());
					tableModel.changeList(mainClient.ListBook(comboBox.getSelectedIndex()));
					//table.repaint();
				}
				
			}
		};

		ActionListener editActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				int itemIndex = table.getSelectedRow();
				int pageIndex = comboBox.getSelectedIndex();

				if (itemIndex == -1) return;

				EditDialog ed = new EditDialog("Edit Book Info");
				ed.setModal(true);
				ed.setBook(tableModel.getBook(itemIndex));
				ed.setVisible(true);
				if (ed.status == 0) {
					mainClient.EditBook(itemIndex, ed.getBook());
					tableModel.changeList(mainClient.ListBook(pageIndex));
					//table.repaint();
				}
			}
		};

		ActionListener delActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				int itemIndex = table.getSelectedRow();
				int pageIndex = comboBox.getSelectedIndex();

				if (itemIndex == -1) return;

				mainClient.DelBook(itemIndex);
				tableModel.changeList(mainClient.ListBook(pageIndex));
				//table.repaint();
			}
		};

		newBook.addActionListener(addActionListener);
		delBook.addActionListener(delActionListener);
		editBook.addActionListener(editActionListener);

		pop_new.addActionListener(addActionListener);
		pop_del.addActionListener(delActionListener);
		pop_edit.addActionListener(editActionListener);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});
		
		shutdown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				mainClient.Close();
				tableModel.clear();
				//table.repaint();
				statusLabel.setText("Client connect closed.");
			}
		});
		
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
			}
		});
		
		findBook.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				FindWindow fw = new FindWindow();
				fw.setModal(true);
				fw.setVisible(true);
				if(fw.ret == 0){
					tableModel.changeList(mainClient.FindBook(fw.bookName));
					//table.repaint();
				}
			}
		});
		
		about.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				AboutWindow aw = new AboutWindow();
				aw.setModal(true);
				aw.setVisible(true);
			}
		});

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBox.getSelectedIndex() != -1)
					tableModel.changeList(mainClient.ListBook(comboBox.getSelectedIndex()));
				//table.repaint();
			}

		});
	}
	
	@Override
	public void finalize(){
		if(mainClient != null)
		mainClient.Close();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
