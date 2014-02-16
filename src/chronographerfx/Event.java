/*
 * Brian Williamson & Leanne Miller
 * CS335, Project, Phase 1 
 * 
 */

package chronographerfx;

public abstract class Event {

	private String name;
	private String category;
	private String startDate;
	private String description;

	public Event(String n, String c, String s, String d, Timeline t){
		setName(n);
		setCategory(c, t);
		setStartDate(s);
		setDescription(d);
	}

	public void setName(String s){
		this.name = s;
	}

	public void setCategory(String s, Timeline t){
		this.category = s;
		t.getCategories().addCategory(s);
	}

	public void setStartDate(String s){
		this.startDate = s;
	}

	public void setDescription(String s){
		this.description = s;
	}

	public String getName(){
		return this.name;
	}

	public String getCategory(){
		return this.category;
	}

	public String getStartDate(){
		return this.startDate;
	}

	public String getDescription(){
		return this.description;
	}

}