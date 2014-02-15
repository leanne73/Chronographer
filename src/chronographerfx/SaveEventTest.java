package chronographerfx;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class SaveEventTest {

	@Test
	public void test() {
		//Create test timeline and event
		String eventName = "TestEvent";
		Timeline timeline = new Timeline("TestTimeline2");
		Event toSave = new AtomicEvent(eventName,"Category","Now","Stuffs", timeline);
		String filename = timeline.getName() + "\\" + eventName + ".xml";
		
		//Create timeline directory to save to
		File dir = new File(timeline.getName());
		dir.mkdir();
		ChronographerMainMenuController.saveEvent(toSave, eventName, timeline.getName());	
		Event savedEvent = ChronographerMainMenuController.loadEvent(filename);
		assert(toSave.equals(savedEvent));
		//System.out.println(savedEvent.getName() + savedEvent.getDescription());
	}

}
