package gui;

import network.MainClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Õº Èπ‹¿ÌœµÕ≥ øÕªß∂À
 * 
 * @author ≈Ù”Ó
 * @version 1.2
 */

public class GUIWindow {

	private JFrame frame;
	private JTable table;
	private MyTableModel tableModel;
	private MainClient mainClient = null;
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
		frame.setFont(new Font("–¬ÀŒÃÂ", Font.PLAIN, 14));
		frame.setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF \u5BA2\u6237\u7AEF");
		frame.setBounds(100, 100, 884, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7CFB\u7EDF");
		menu.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menuBar.add(menu);
		
		JMenuItem connect = new JMenuItem("\u8FDE\u63A5");
		connect.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu.add(connect);
		
		JMenuItem shutdown = new JMenuItem("\u65AD\u5F00");
		shutdown.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu.add(shutdown);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		
		
		JMenuItem exit = new JMenuItem("\u9000\u51FA");
		exit.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu.add(exit);
		
		JMenu menu_1 = new JMenu("\u7F16\u8F91");
		menu_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menuBar.add(menu_1);
		
		JMenuItem newBook = new JMenuItem("\u65B0\u5EFA");
		newBook.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu_1.add(newBook);
		
		JMenuItem editBook = new JMenuItem("\u4FEE\u6539");
		editBook.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu_1.add(editBook);
		
		JMenuItem findBook = new JMenuItem("\u67E5\u627E");
		findBook.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu_1.add(findBook);
		
		JMenuItem delBook = new JMenuItem("\u5220\u9664");
		delBook.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu_1.add(delBook);
		
		JMenu menu_2 = new JMenu("\u5E2E\u52A9");
		menu_2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menuBar.add(menu_2);
		
		JMenuItem about = new JMenuItem("\u5173\u4E8E");
		about.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		menu_2.add(about);
		
		table = new JTable();
		
		tableModel = new MyTableModel();
		
		table.setModel(tableModel);
		table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12)); // …Ë÷√◊÷ÃÂ

		table.setFillsViewportHeight(true); // ∏ﬂ∂»∫Õπˆ∂Ø¥∞∏Òµƒ∏ﬂ∂»“ª÷¬
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setRowHeight(22);

		JScrollPane jscrollPane = new JScrollPane(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539");
		menuItem.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		popupMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u5220\u9664");
		menuItem_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		popupMenu.add(menuItem_1);
		
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				int index = table.getSelectedRow();
				if(index == -1) return;
				
				EditDialog ed = new EditDialog("–ﬁ∏ƒÕº È–≈œ¢");
				ed.setModal(true);
				ed.setBook(tableModel.bookSet.get(index));
				ed.setVisible(true);
				
				mainClient.EditBook(index, ed.getBook());
				
				tableModel.bookSet.get(index).copyFrom(ed.getBook());
				table.repaint();
			}
		});
		
		menuItem_1.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				int index = table.getSelectedRow();
				if(index == -1) return;
				
				mainClient.DelBook(index);
				tableModel.bookSet.remove(index);
				
				table.repaint();
			}
		});
		
		jscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(jscrollPane);
		
		connect.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				ConnectDialog cd = new ConnectDialog();
				cd.setModal(true);
				cd.setVisible(true);
				if(cd.ret == 0){
					mainClient.connectTo(cd.ipAddress);
					if(!mainClient.unreachble())
						tableModel.bookSet = mainClient.ListBook();
					table.repaint();
				}
					
			}
		});
		
		newBook.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditDialog ed = new EditDialog("New Book");
				ed.setModal(true);
				ed.setVisible(true);
				
				if(ed.status == 0){
					tableModel.bookSet.add(ed.getBook());
					mainClient.AddBook(ed.getBook());
					table.repaint();
				}
				
			}
			
		});
		
		editBook.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				
				int index = table.getSelectedRow();
				if(index == -1) return;
				
				EditDialog ed = new EditDialog("Edit Book");
				ed.setModal(true);
				ed.setBook(tableModel.bookSet.get(index));
				ed.setVisible(true);
				
				mainClient.EditBook(index, ed.getBook());
				
				tableModel.bookSet.get(index).copyFrom(ed.getBook());
				table.repaint();
			}
		});
		
		delBook.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				
				int index = table.getSelectedRow();
				if(index == -1) return;
				
				mainClient.DelBook(index);
				tableModel.bookSet.remove(index);
				
				table.repaint();
			}
		});
		
		shutdown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				mainClient.Close();
				tableModel.bookSet.clear();
				table.repaint();
				
			}
		});
		
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);
				frame.dispose();
				
			}
		});
		
		findBook.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				FindWindow fw = new FindWindow();
				fw.setModal(true);
				fw.setVisible(true);
				if(fw.ret == 0){
					tableModel.bookSet = mainClient.FindBook(fw.bookName);;
					table.repaint();
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
	}
	
	@Override
	public void finalize(){
		if(mainClient != null)
		mainClient.Close();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
