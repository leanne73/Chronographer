/*
 * Brian Williamson & Leanne Miller
 * CS335, Project, Phase 1 
 * 
 */

package chronographerfx;

import java.util.TreeMap;

public class Timeline {

	private TreeMap<String, Event> events; //A map from event names to the events themselves
	private String name;
	private CategoryList categories;

	public Timeline(String n){
		name = n;
		events = new TreeMap<String, Event>();
		categories = new CategoryList();
	}

	public Timeline(String n, TreeMap<String, Event> e, CategoryList c){
		name = n;
		events = e;
		categories = c;
	}

	public TreeMap<String, Event> getEvents(){
		return this.events;
	}

	public CategoryList getCategories(){
		return this.categories;
	}

	public void addCategory(String c){
		categories.addCategory(c);
	}

	public String getName(){
		return this.name;
	}

	public void changeName(String s){
		this.name = s;
	}

	public void addEvent(Event e){
		events.put(e.getName(), e);
	}

	public void deleteEvent(Event e){
		events.remove(e);
	}

}
