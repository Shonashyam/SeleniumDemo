import java.util.HashMap;

public class hashmap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, String > map=new HashMap<String, String>();
		
		map.put("101", "Shyam");
		map.put("102", "Ram");
		map.put(null, "Rohit");
		//map.put(Null, "Rahul");
		System.out.println(map.keySet());
		
		

	}

}
