package chronographer;

public class DurativeEvent extends Event {

	String endDate;
	
	public DurativeEvent(String n, String c, String s, String d, String e) {
		super(n,c,s,d);
		setEndDate(e);
	}
	
	public void setEndDate(String s){
		this.endDate = s;
	}
	
	public String getEndDate(){
		return this.endDate;
	}
}
