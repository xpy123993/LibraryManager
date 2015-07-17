package gui;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import struct.*;

public class MyTableModel implements TableModel {

	public ArrayList<Book> bookSet;
	
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

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
