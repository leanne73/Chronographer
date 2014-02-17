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
 * @author Brian
 */

//Main Menu Controller
public class ChronographerMainMenuController implements Initializable {

	@FXML
	public Button newTimeline;
	public Button loadTimeline;
	public Button viewTimeline;
        public Button editTimeline;
	public Button exit;
	public TextField inputFileName;
	public TextField inputEventName;
        public TextField inputStartDate;
        public TextField inputEndDate;
        public TextField inputCategory;
        public TextField inputDescription;
        public Button addEvent;
        public CheckBox durativeEvent;
        
        protected Timeline workingTimeline;
               
	@FXML
	public void handleButtonActionNewTimeline(ActionEvent event) throws Exception {
                String timelineName = inputFileName.getText();
                workingTimeline = new Timeline(timelineName);
                saveTimeline(workingTimeline);
	}

	@FXML
	public void handleButtonActionLoadTimeline(ActionEvent event) {
                String timelineName = inputFileName.getText();
                workingTimeline = loadTimeline(timelineName);
	}
        
        @FXML
        public void handleButtonActionEditTimeline(ActionEvent event) throws Exception {
                String timelineName = inputFileName.getText();
                try { java.awt.Desktop.getDesktop().edit(loadTimelineFile(timelineName)); }
                catch(Exception e) {
                    System.err.println("Error in XML Read: " + e.getMessage());
                }
        }

	@FXML
	public void handleButtonActionViewTimeline(ActionEvent event) {
                String timelineName = inputFileName.getText();
                workingTimeline = loadTimeline(timelineName);
                //Stuff to render timeline
	}

	@FXML
	public void handleButtonActionQuit(ActionEvent event) {
		System.exit(0);
	}
        
        @FXML
        public void handleButtonActionAddEvent(ActionEvent event) {
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
		// TODO
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
        
        static File loadTimelineFile(String filename){
            XStream xstream = new XStream();
            Event event = null;
            String path = System.getProperty("user.dir");
            File xmlFile = new File(path + "\\" + filename);
            
            try{ event = (Event)xstream.fromXML(xmlFile); }
            catch(Exception e){ System.err.println("Error in XML Read: " + e.getMessage()); }
            
            return xmlFile;
        }
}
