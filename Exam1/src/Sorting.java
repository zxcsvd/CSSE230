import java.util.ArrayList;

class Sorting {

public static void main(String[] args) {
	int a[] = {1,2,3,4};
	sort(a);
	for (int i = 0; i < a.length; i++){
		System.out.println(a[i]);
	}
}
public static void sort(int[] a){
	int max = findMax(a);
	int min = findMin(a);
	MyTable t = new MyTable (a.length, ((max-min)/a.length)+1, min);
	for (int i=0; i < a.length; i++){
		t.insert(a[i]);
	}
	int[] temp = t.getArray();
	for (int k = 0; k < temp.length; k++) {
		a[k] = temp[k];			
	}
}
private static int findMin(int[] a) {
	int max = a[0];
	// TODO Auto-generated method stub
	for(int i=0; i<a.length; i++){
		if(max < a[i]){
			max = a[i];
		}
	}
	return max;
}
private static int findMax(int[] a) {
	// TODO Auto-generated method stub
	int min = a[0];
	for(int i=0; i<a.length; i++){
		if(min > a[i]){
			min = a[i];
		}
	}
	return min;
}

}

