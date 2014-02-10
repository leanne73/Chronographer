/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class MainMenuController implements Initializable {

	@FXML
	private Button newTimeline;
	private Button loadTimeline;
	private Button editTimeline;
	private Button viewTimeline;
	private Button exit;
	private TextField input;

	@FXML
	private void handleButtonActionNewTimeline(ActionEvent event) throws Exception {

	}

	@FXML
	private void handleButtonActionEditTimeline(ActionEvent event) {
		//Send to next menu depending on button press
	}

	@FXML
	private void handleButtonActionLoadTimeline(ActionEvent event) {
		//Send to next menu depending on button press
	}

	@FXML
	private void handleButtonActionViewTimeline(ActionEvent event) throws Exception {

	}

	@FXML
	private void handleButtonActionQuit(ActionEvent event) {
		System.exit(0);
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
}
