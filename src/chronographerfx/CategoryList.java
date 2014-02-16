//Brian Williamson & Leanne Miller
//CS335, Project, Phase 1

package chronographerfx;

import java.util.*;

//Maintains a list of categories for a timeline. Makes sure that there are no duplicate categories.
public class CategoryList {
	private LinkedList<String> list;
	
	public CategoryList(){
		list = new LinkedList<String>();
	}
	
	public void addCategory(String s) {
		if(!list.contains(s)){
			list.add(s);
		}	
	}
	
	public void removeCategory(String s){
		list.remove(s);
	}
	
	public boolean contains(String s){
		return list.contains(s);
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<String> getCategories(){
		return (LinkedList<String>) list.clone();
	}
	
}
