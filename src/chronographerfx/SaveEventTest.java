package chronographerfx;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class SaveEventTest {

	@Test
	public void test() {
		String filename = "TestEvent";
		Event toSave = new AtomicEvent("MyEvent","Category","Now","Stuffs");
		MainMenuController.saveEvent(toSave, filename);	
		Event savedEvent = MainMenuController.loadEvent(filename);
		assert(toSave.equals(savedEvent));
	}

}
