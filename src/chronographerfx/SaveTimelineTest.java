package chronographerfx;

import static org.junit.Assert.*;

import org.junit.Test;

public class SaveTimelineTest {

	@Test
	public void test() {
		String filename = "TestTimeline";
		Timeline toSave = new Timeline(filename);
		Event e1 = new AtomicEvent("Event 1", "Category1", "Now", "Stuffs", toSave);
		Event e2 = new DurativeEvent("Event 2", "Category2", "Last Week", "Now", "Interesting things.", toSave);
		toSave.addEvent(e1);
		toSave.addEvent(e2);
		//System.out.println(toSave.getEvents());
		
		ChronographerMainMenuController.saveTimeline(toSave);	
		Timeline saved = ChronographerMainMenuController.loadTimeline(filename);
		assert(toSave.equals(saved));
		//System.out.println(saved.getEvents());
	}

}
