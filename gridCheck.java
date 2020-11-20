
package maxHeapImp;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class gridCheck extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Circle c1 = new Circle();
        c1.setStroke(Color.BLACK);
        c1.setFill(Color.WHITE);
        c1.setRadius(25);
        parent.add(c1, 0, 0,1,1);
        ArrayList<Circle> circles = new ArrayList();
        
        for (int i = 0; i < numberOfColumns * numberOfRows; i++) {
            circles.add(addCircle);
        }

        GridPane gridPane = new GridPane();
        addCirclesToGridPane(gridPane, circles);
//        c1.centerXProperty().bind(pane.widthProperty().divide(2));
//        c1.centerYProperty().bind(pane.heightProperty().divide(2));

//        Circle c2 = new Circle();
//        c2.setStroke(Color.BLACK);
//        c2.setFill(Color.BLUE);
//        c2.setRadius(20);
//        parent.add(c2, 1,1,1,1);
        //parent.gridLinesVisible(true);
        //parent.getChildren().add(c1);
//        parent.setAlignment(Pos.CENTER);
        
        
        primaryStage.setResizable(true);
        primaryStage.setTitle("Circle in a pane"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    int screenW = 800;
    int screenH = 1500;
    GridPane parent = new GridPane();
    Scene scene = new Scene(parent, screenH,screenW);
    
    public static  Circle addCircle(){
        Circle c = new Circle();
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);
        c.setRadius(25);
        return c;}
    
    public static void main(String args[]) {
       Application.launch(args); 
    }
}
