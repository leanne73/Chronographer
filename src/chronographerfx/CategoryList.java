//Brian Williamson & Leanne Miller
//CS335, Project, Phase 1

package chronographer;

import java.util.*;

/* A singleton class. 
 * Used http://howtodoinjava.com/2012/10/22/singleton-design-pattern-in-java/
 * as a reference when creating it. */
public class CategoryList {
	private static volatile CategoryList instance = null;

	private LinkedList<String> list;	
	private CategoryList() {
		list = new LinkedList<String>();
	}
	
	public static CategoryList getInstance(){
		if(instance == null){
			synchronized(CategoryList.class){
				//Double check in case 2 threads perform the first check 
				//at the same time and both try to create an instance
				if(instance == null){
					instance = new CategoryList();
				}
			}
		}
		return instance;
	}
	
	public void addCategory(String s) {
		list.add(s);
	}
	
	public void removeCategory(String s){
		list.remove(s);
	}
	
	public LinkedList<String> getCategories(){
		return list;
	}
	
}
