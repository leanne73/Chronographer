/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chronographerfx;

import java.io.*;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

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

	//Ref: http://stackoverflow.com/questions/13063815/save-xml-file-with-xstream
	static void saveEvent(Event event, String filename){
		XStream xstream = new XStream(); 

		xstream.alias(event.getName(), Event.class);
		String xml = xstream.toXML(event);
		try{
			FileOutputStream out = new FileOutputStream(filename + ".xml");
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
		String path = "C:\\Users\\Leanne\\Documents\\GitHub\\Chronographer\\";
		try{
			File xmlFile = new File(path + filename + ".xml");
			event = (Event)xstream.fromXML(xmlFile);       
		}catch(Exception e){
			System.err.println("Error in XML Read: " + e.getMessage());
		}

		/*try{
			FileInputStream in = new FileInputStream(filename);
			/*byte[] bytes = new byte[1000000000];
			in.read(bytes);
			String xml = bytes.toString();
			e = (Event)xstream.fromXML(in);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("File not found");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Could not read file.");
		}*/
		return event;
	}
}
