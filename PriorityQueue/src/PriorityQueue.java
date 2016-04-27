import java.util.ArrayList;

public class PriorityQueue<T extends Comparable<? super T>> extends ArrayList<T> {
	
	public boolean offer(T e){
		//return add(e);
		//myBoolean b = new myBoolean();
		return this.add(e);
		//return b.getBoolean();
	}
	
	public boolean add(T e){
		if(e == null) throw new NullPointerException();
		
		super.add(e);
		int index = (this.size() -1);
		int oldindex = (index - 1)/2;
		T old = this.get(oldindex);
		
		while(index > 0 && e.compareTo(old) < 0){ 
			 
			this.set(oldindex, e);
			this.set(index, old);
			index = oldindex;
			oldindex = (index - 1)/2;
			old = this.get(oldindex);
		}
		return true;

	}
	
	public T peek(){
		return findMin();
	}
	
	public T poll(){
		T temp = findMin();
		this.remove(temp);
		return temp;
	}

	private T findMin(){
//		if (size() == 0) return null;
//		T temp = get(0);
//		for (int i = 1; i < size(); i++) {
//			if (temp.compareTo(get(i)) > 0) {
//				temp = get(i);
//			}
//		}
//		return temp;
		if (size() == 0) return null;
		return this.get(0);
	}
	
	
	public boolean remove(T e){
		T children  = null;
		int childrenIndex = -1;
		int index = this.indexOf(e);
		int size = this.size();
		if(index == -1){
			return false;
		}else{
			while(index < size){
				if(2*index + 1 < size){
					if(2*index + 2 < size){
						int com = this.get(2*index + 1).compareTo(this.get(2*index + 2));
						if(com <= 0){
							childrenIndex = 2 * index + 1;
							children = this.get(childrenIndex);
						}
						else{
							childrenIndex = 2 * index + 2;
							children = this.get(childrenIndex);
						}
						this.set(index, children);
						this.set(childrenIndex, e);
						index = childrenIndex;
					}
					else{
						childrenIndex = 2 * index + 1;
						children = this.get(childrenIndex);
						this.set(index, children);
						this.remove(size-1);
						return true;
					}
				}else{
					this.set(index, this.get(size-1));
					this.remove(size-1);
					return true;
				}
			}
			return true;
		}
	}
	
}

