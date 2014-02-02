//Brian Williamson & Leanne Miller
//CS335, Project, Phase 1

//Brian Williamson & Leanne Miller
//CS335, Project, Phase 1



public abstract class Event {

	String name;
	String category;
	String startDate;
	String description;
	
	public Event(String n, String c, String s, String d){
		setName(n);
		setCategory(c);
		setStartDate(s);
		setDescription(d);
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public void setCategory(String s){
		this.category = s;
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