package struct;

import java.util.ArrayList;

public class PageSplitter<T> {

    private ArrayList<T> dataSet = null;
    private ArrayList<Integer> index_table = new ArrayList<Integer>();

    public void setSource(ArrayList<T> dataSet) {
        this.dataSet = dataSet;
    }

    public void addItemIndex(int source_index) {
        index_table.add(source_index);
    }

    public ArrayList<T> getPage() {
        ArrayList<T> ret = new ArrayList<T>();
        for (Integer i : index_table) {
            ret.add(dataSet.get(i));
        }
        return ret;
    }

    public void remove(int item_index) {
        int source_index = index_table.get(item_index);
        dataSet.remove(source_index);
    }

    public void set(int item_index, T element) {
        int source_index = index_table.get(item_index);
        dataSet.set(source_index, element);
    }

    public void clear() {
        index_table.clear();
    }

    public T get(int index) {
        int source_index = index_table.get(index);
        return dataSet.get(source_index);
    }
}
