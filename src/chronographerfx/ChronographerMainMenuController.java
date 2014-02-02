/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chronographerfx;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
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
public class ChronographerMainMenuController implements Initializable {
    
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
    
}
