import java.util.ArrayList;

public class MyTable {
	private ArrayList<Integer>[] table;
	private int bucketWidth;
	private int offset;
	
	public MyTable(int size, int bucketWidth, int offset) {
		table = new ArrayList[size];
		this.bucketWidth = bucketWidth;
		this.offset = offset;
	}
	public void insert(int key){
		int index = getIndex(key);
		if (table[index] == null) {
			table[index] = new ArrayList();
			table[index].add(key); 
		} else {
			int k = 0;
			while (k<table[index].size() && table[index].get(k)<key) {
				k++;
			}
			table[index].add(k, key); 
		}
	}

	private int getIndex(int key){
		return (key - offset) / bucketWidth;
	}

	public int[] getArray(){
		int[] temp = new int[table.length];
		int index = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				for (int k = 0; k < table[i].size(); k++){
					temp[index++] = table[i].get(k);
				}
			}
		}
		return temp;
	}}