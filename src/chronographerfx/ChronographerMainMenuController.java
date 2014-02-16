
package chronographerfx;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

//import com.sun.java.util.jar.pack.Package.File;
import com.thoughtworks.xstream.XStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Brian & Leanne
 */

//Main Menu Controller
public class ChronographerMainMenuController implements Initializable {

	@FXML
	private Button newTimeline;
	private Button loadTimeline;
	private Button viewTimeline;
	private Button exit;
	private TextField inputFileName;
	private TextField inputEventName;
        private TextField inputStartDate;
        private TextField inputEndDate;
        private TextField inputCategory;
        private TextField inputDescription;
        private Button addEvent;
        private CheckBox durativeEvent;
        
        private Timeline workingTimeline;
               
	@FXML
	private void handleButtonActionNewTimeline(ActionEvent event) throws Exception {
                String timelineName = inputFileName.getText();
                workingTimeline = new Timeline(timelineName);
                saveTimeline(workingTimeline);
                //stuff to display events
	}

	@FXML
	private void handleButtonActionLoadTimeline(ActionEvent event) {
                String timelineName = inputFileName.getText();
                workingTimeline = loadTimeline(timelineName);
                //Stuff to display events
	}

	@FXML
	private void handleButtonActionViewTimeline(ActionEvent event) throws Exception {
                String timelineName = inputFileName.getText();
                workingTimeline = loadTimeline(timelineName);
                //Stuff to render timeline
	}

	@FXML
	private void handleButtonActionQuit(ActionEvent event) {
		System.exit(0);
	}
        
        private void handleButtonActionNewEvent(ActionEvent event) throws Exception {
                Event baseEvent;
                String eventName = inputEventName.getText();
                String startDate = inputStartDate.getText();
                String category = inputCategory.getText();
                String description = inputDescription.getText();
                if (durativeEvent.isSelected()) {
                    String endDate = inputEndDate.getText();
                    baseEvent = new DurativeEvent(eventName, category, startDate, description, endDate, workingTimeline);
                } else {
                    baseEvent = new AtomicEvent(eventName, category, startDate, description, workingTimeline);
                }
                workingTimeline.addEvent(baseEvent);
        }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Placeholder
	}    
	
	static void saveTimeline(Timeline tl){
		TreeMap<String, Event> events = tl.getEvents();
		//Make a directory for the timeline
		String timeline = tl.getName();
		File dir = new File(timeline);
		dir.mkdir();
		//Save each event to the timeline's folder
		for(String name : events.keySet()){
			Event e = events.get(name);
			saveEvent(e, e.getName(), timeline);
		}
	}
	
	static Timeline loadTimeline(String timelineName){
		Timeline timeline = new Timeline(timelineName);
		//Get a list of all files(events) in timeline directory, then load each one
		File dir = new File(timelineName);
		File[] files = dir.listFiles();
		for(File f : files){
			if(f.isFile()){
				timeline.addEvent(loadEvent(timelineName + "\\" + f.getName()));			
			}
		}
		return timeline;
	}

	//Ref: http://stackoverflow.com/questions/13063815/save-xml-file-with-xstream
	static void saveEvent(Event event, String filename, String timeline){
		XStream xstream = new XStream(); 

		xstream.alias(event.getName(), Event.class);
		String xml = xstream.toXML(event);
		try{
			FileOutputStream out = new FileOutputStream(timeline + "\\" + filename + ".xml");
			//out.write("<?xml version=\"1.4.6\"?>".getBytes("UTF-8")); //write XML header, as XStream doesn't do that for us
			byte[] bytes = xml.getBytes("UTF-8");
			out.write(bytes);
			out.close();

		}catch(Exception e){ //Refine
			e.printStackTrace();
			System.out.println("Could not save.");
		}
	}

	static Event loadEvent(String filename){
		XStream xstream = new XStream();
		Event event = null;
		String path = System.getProperty("user.dir");
		
		try{
			File xmlFile = new File(path + "\\" + filename);
			event = (Event)xstream.fromXML(xmlFile);       
		}catch(Exception e){
			System.err.println("Error in XML Read: " + e.getMessage());
		}
		
		return event;
	}
}
