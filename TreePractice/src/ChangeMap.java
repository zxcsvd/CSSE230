import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ChangeMap{

	public Map<String, String> trans(Map<String, String> original){
		Map<String, String> trans = new HashMap<String, String>();
		ArrayList<String> oldInput = new ArrayList<String>();
		ArrayList<String> oldValue = new ArrayList<String>();
		Set<Map.Entry<String,String>> entries = original.entrySet();
		for(Map.Entry<String, String> a: entries){
			oldInput.add(a.getKey());
			oldValue.add(a.getValue());
		}
		
		for(int i=0; i<oldInput.size();i++){
			try{
				trans.put(oldValue.get(i), oldInput.get(i));
			}catch(Exception e){
				e.printStackTrace();
			}		
		}
		return trans;
	}
}
