package chronographerfx;

public class DurativeEvent extends Event {

	String endDate;
	
	public DurativeEvent(String name, String category, String startDate, String description, String endDate, Timeline t) {
		super(name, category, startDate, description, t);
		setEndDate(endDate);
	}
	
	public void setEndDate(String s){
		this.endDate = s;
	}
	
	public String getEndDate(){
		return this.endDate;
	}
}
