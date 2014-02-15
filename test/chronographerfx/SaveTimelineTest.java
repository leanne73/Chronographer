package chronographerfx;

import chronographerfx.AtomicEvent;
import chronographerfx.DurativeEvent;
import chronographerfx.Event;
import chronographerfx.MainMenuController;
import chronographerfx.Timeline;
import static org.junit.Assert.*;

import org.junit.Test;

public class SaveTimelineTest {

	@Test
	public void test() {
		String filename = "TestTimeline";
		Timeline toSave = new Timeline(filename);
		Event e1 = new AtomicEvent("Event 1", "Category1", "Now", "Stuffs");
		Event e2 = new DurativeEvent("Event 2", "Category2", "Last Week", "Now", "Interesting things.");
		toSave.addEvent(e1);
		toSave.addEvent(e2);
		//System.out.println(toSave.getEvents());
		
		MainMenuController.saveTimeline(toSave);	
		Timeline saved = MainMenuController.loadTimeline(filename);
		assert(toSave.equals(saved));
		//System.out.println(saved.getEvents());
	}

}
