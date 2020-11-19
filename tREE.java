
// package Assignment3;

// import javafx.application.Application;
// import javafx.stage.Stage;

// public class tREE extends Application{

//     public static void main(String args[]) {
       
//     }

//     @Override
//     public void start(Stage stage) throws Exception {
        
//     }
// }
package maxHeapImp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("trying");
        stage.setWidth(1000);
        stage.setHeight(500);
        
        HBox parent = new HBox();
        
        TextField t1 = new TextField();
        Button b1 = new Button("Insert ");
        TextField t2 = new TextField();
        Button b2 = new Button("Delete Maximum ");
        TextField t3 = new TextField();
        Button b3 = new Button("Find");
        Button b4 = new Button("Print");
        
        //making appropriate changes for the textBoxes and buttons
        parent.getChildren().addAll(t1,b1,t2,b2,t3,b3,b4);
        
        parent.setMargin(b1, new Insets(10,10,10,10));
        parent.setMargin(b2, new Insets(10,10,10,10));
        parent.setMargin(b3, new Insets(10,10,10,10));
        parent.setMargin(t1, new Insets(10,10,10,10));
        parent.setMargin(t2, new Insets(10,10,10,10));
        parent.setMargin(t3, new Insets(10,10,10,10));
        parent.setMargin(b4, new Insets(10,10,10,10));
       
        parent.setAlignment(Pos.BASELINE_CENTER);
        
        Scene scene1 = new Scene(parent);
        scene1.setCursor(Cursor.CROSSHAIR);
        
        stage.setScene(scene1);
        stage.show();
    }
    public static void main(String args[]) {
        Application.launch(args);
    }
}
