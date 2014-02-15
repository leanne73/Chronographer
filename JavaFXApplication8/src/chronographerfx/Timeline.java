package chronographerfx;

import java.util.TreeMap;

public class Timeline {
	
	private TreeMap<String, Event> events; //A map from event start dates to the events themselves
	private String name;
	
	public Timeline(String n){
		name = n;
		events = new TreeMap<String, Event>();
	}
	
	public Timeline(String n, TreeMap<String, Event> e){
		name = n;
		events = e;
	}
	
	//Return iterator instead?
	public TreeMap<String, Event> getEvents(){
		return events;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void changeName(String s){
		this.name = s;
	}
	
	public void addEvent(Event e){
		events.put(e.getStartDate(), e);
	}

}
