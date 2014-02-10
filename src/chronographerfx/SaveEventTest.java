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
		String filename = "TestEvent";
		Event toSave = new AtomicEvent("MyEvent","Category","Now","Stuffs");
		//Create timeline directory to save to
		String timeline = "TestTimeline";
		File dir = new File(timeline);
		dir.mkdir();
		MainMenuController.saveEvent(toSave, filename, timeline);	
		Event savedEvent = MainMenuController.loadEvent(filename);
		assert(toSave.equals(savedEvent));
		//System.out.println(savedEvent.getName() + savedEvent.getDescription());
	}

}
