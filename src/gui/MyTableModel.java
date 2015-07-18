package gui;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import struct.Book;

public class MyTableModel implements TableModel {

	private ArrayList<Book> bookSet;
	private ArrayList<TableModelListener> listenerSet = new ArrayList<TableModelListener>();

	public void changeList(ArrayList<Book> list) {
		bookSet = list;
		doChange();
	}

	public Book getBook(int index) {
		return bookSet.get(index);
	}

	public void clear() {
		bookSet.clear();
		doChange();
	}
	
	public MyTableModel(){
		super();
		bookSet = new ArrayList<Book>();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return bookSet.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex){
		case 0:
			return "ID";
		case 1:
			return "BookName";
		case 2:
			return "Author";
		case 3:
			return "Cost";
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Book b = bookSet.get(rowIndex);
		switch(columnIndex){
		case 0:
			return b.getBookID();
		case 1:
			return b.getBookName();
		case 2:
			return b.getBookAuthor();
		case 3:
			return b.getCost();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		removeTableModelListener(l);
		listenerSet.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listenerSet.size(); i++) {
			if (l.equals(listenerSet.get(i))) {
				listenerSet.remove(i);
				break;
			}
		}
	}

	private void doChange() {
		for (TableModelListener tl : listenerSet)
			tl.tableChanged(new TableModelEvent(this));
	}

}
