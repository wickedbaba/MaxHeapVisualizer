
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class runTime extends Application {

    //---border pane helps in setting the Hboxes on the top and bottom
    BorderPane pane = new BorderPane();
    ArrayList<Circle> circleArray = new ArrayList<>();
    ArrayList<Text> textArray = new ArrayList<>();
    ArrayList<Line> lineArray = new ArrayList<>();
    ArrayList<Integer> dataArray = new ArrayList<>();
    ArrayList<Integer> xCoordinate = new ArrayList<>();
    ArrayList<Integer> yCoordinate = new ArrayList<>();
    int x = 1400;
    int y = 800;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Max Heap");

        pane.setStyle("-fx-background-color: lightblue;");
        pane.setPrefSize(1000, 800);

        HBox layoutUp1 = new HBox();
        layoutUp1.setStyle("-fx-background-color: #0B4F6C;");
        layoutUp1.setPadding(new Insets(10));

        HBox layoutDown = new HBox();
        layoutDown.setStyle("-fx-background-color: #0B4F6C;");
        layoutDown.setPadding(new Insets(10));

        // Setting textField and labels for showing vertices and height
        TextField showHeight = new TextField();
        showHeight.setPrefWidth(50);

        showHeight.setStyle("-fx-font-weight: BOLD;-fx-text-fill: #fcfbfb");
        showHeight.setStyle("-fx-control-inner-background: #ffffff;");
        //showHeight.setDisable(true); Color combination is not contrasting, hence we did not use it.

        Label height = new Label("Height of the tree");
        height.setStyle("-fx-font-weight: BOLD;-fx-text-fill: #fcfbfb");

        TextField showVertices = new TextField();
        showVertices.setPrefWidth(50);

        showVertices.setStyle("-fx-font-weight: BOLD;-fx-text-fill: #fcfbfb;");
        showVertices.setStyle("-fx-control-inner-background: #ffffff;");
        Label vertices = new Label("Number of Vertices");
        vertices.setStyle("-fx-font-weight: BOLD;-fx-text-fill: #fcfbfb");
        //showVertices.setDisable(true); Color combination is not contrasting, hence we did not use it.

        //------------------- insert----------------------------------------------------
        //----------insert textField and Button-------------------------------------
        TextField insert = new TextField();
        insert.setPrefWidth(50);

        Button insertB = new Button("Insert");
        insertB.setOnAction(event -> {
            try {
                int h, v;//declaring variables for storing height and vertices value
                clearPane();
                addNode(Integer.parseInt(insert.getText()));
                showTree();

                // code to update vertices and height everytime a element is inserted
                v = dataArray.size();
                h = getHeight(v) + 1;
                showHeight.setText(h + " ");
                showVertices.setText(v + " ");

            } catch (NumberFormatException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Value must be an integer.");
                a.show();
            }
        });

        //------------------delete Root ------------------------------------------------------
        Button deleteB = new Button("Delete Root Node");//delete  button
        deleteB.setOnAction(event -> {
            try{
                int h, v;
                clearPane();
                // pane.getChildren().clear();
                deleteNode();
                showTreeAD();

                // code to update vertices and height everytime a element(root node) is deleted
                v = dataArray.size();
                h = getHeight(v) + 1;
                showHeight.setText(h + " ");
                showVertices.setText(v + " ");
            }
            catch (IndexOutOfBoundsException ex){
                Alert a = new Alert(Alert.AlertType.ERROR, "Heap is empty. Please enter values to continue.");
                a.show();
            }
        });

        //------------------------Finding Value-----------------------------------------
        //---------------text field and button for finding value -----------------------

        // insert.setPrefWidth(50);
        Button findB = new Button("Find");
        findB.setOnAction(event -> {
            try {
                int h, v;
                clearPane();
                searchNode(Integer.parseInt(insert.getText()));
                if(!dataArray.contains(Integer.parseInt(insert.getText()))){
                    Alert a = new Alert(Alert.AlertType.ERROR, "Value must be present in Heap.");
                    a.show();
                }

                // code to update vertices and height
                v = dataArray.size();
                h = getHeight(v) + 1;
                showHeight.setText(h + " ");
                showVertices.setText(v + " ");

            } catch (NumberFormatException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Value must be an integer.");
                a.show();
            }
        });

        //------------------------printing the elements in preorder--------------------
        //Label printB = new Label("Prints the tree automatically");

        // adding Hbox at the top of border pane

        layoutUp1.getChildren().addAll(insert, insertB, findB, deleteB);
        layoutUp1.setSpacing(20.0);
        layoutUp1.setAlignment(Pos.TOP_CENTER);
        pane.setTop(layoutUp1);

        // adding Hbox at the bottom
        layoutDown.getChildren().addAll(showHeight, height, showVertices, vertices);
        layoutDown.setSpacing(20.0);
        layoutDown.setAlignment(Pos.BASELINE_CENTER);
        pane.setBottom(layoutDown);



        insertB.setStyle("-fx-font-weight: BOLD;-fx-background-color: #01BAEF;-fx-text-fill: #fcfbfb");
        findB.setStyle("-fx-font-weight: BOLD;-fx-background-color: ORANGE;-fx-text-fill: #fcfcfb");
        deleteB.setStyle("-fx-font-weight: BOLD;-fx-background-color: #e94040;-fx-text-fill: #fcfbfb");

        //layoutMid.getChildren.add(printTF);
        //--------------------setting the scene------------------------------------------------

        Scene scene = new Scene(pane, x, y);
        stage.setScene(scene);
        stage.show();
    }

    //-------------------creates an arraylist full of coordinates for placing the nodes----------
    public void getCoordinates() {
        int rows = 0;
        xCoordinate.clear();
        yCoordinate.clear();
        if (dataArray.size() == 1) {
            rows = 1;
        } else if (dataArray.size() >= 2 && dataArray.size() <= 3) {
            rows = 2;
        } else if (dataArray.size() >= 4 && dataArray.size() <= 7) {
            rows = 3;
        } else if (dataArray.size() >= 8 && dataArray.size() <= 15) // if there are 9-15 nodes, then total number of rows would be 4
        {
            rows = 4;
        } else if (dataArray.size() >= 16 && dataArray.size() <= 31)// if there are 16-32 nodes, then total number of rows would be 5
        {
            rows = 5;
        }else if (dataArray.size() >= 32 && dataArray.size() <= 63){ // if there are 33-64 nodes, then total number of rows would be 6
            rows = 6;
        }else if(dataArray.size() >= 64 && dataArray.size() <= 128){
            rows = 7;
        }

        int index = 0, i, j, xCord, children;
        for (i = 0; i < rows; i++) {
            xCord = 0;
            //in = 0;
            children = (int) Math.pow(2, i);
            for (j = 0; j < children; j++) { // every parent has two children, so if we know the total number of parents(rows), then their children will be = parent^2
                xCord += (x/2) / children; // putting the coordinate from the center (500)pixels and then adding respectively(kind off changing the origin), so that no two nodes overlap
                int yCord = 100 + i * 100;
                index++;
                xCoordinate.add(xCord);
                yCoordinate.add(yCord);

                // if the index exceeds the array size, i.e. all required number of nodes have been entered for a particular row/level, then it breaks out of the loop and does not change the  coordinate
                if (index > dataArray.size()) {
                    break;
                }
                xCord += 750 / children;
            }
        }
    }

    //-----------a method used to show Tree, by adding circles , text and lines in the desired coodinates
    public void showTree() {
        //this function updates an arrayList which has the X and y coordinates 
            //the updated arrayList is used to set coordinates of nodes
        getCoordinates();


        int i, k = dataArray.size() - 1;

        // these loops removes all the existing elements present on the pane 
        for (i = 0; i < circleArray.size(); i++) {
            pane.getChildren().remove(circleArray.get(i));
        }
        for (i = 0; i < textArray.size(); i++) {
            pane.getChildren().remove(textArray.get(i));
        }
        // this code is used to add circles and text accordingly
        Circle c1 = new Circle(25, Color.TEAL);
        c1.setStrokeWidth(3);
        c1.setStroke(Color.DARKSLATEGREY);
        c1.setCenterX(xCoordinate.get(k));
        c1.setCenterY(yCoordinate.get(k));
        Text t1 = new Text(Integer.toString(dataArray.get(k)));
        t1.setFill(Color.WHITE);
        t1.setFont(Font.font("Calibri", 20));
        t1.setX(xCoordinate.get(k) - 15);
        t1.setY(yCoordinate.get(k) +7);
        circleArray.add(c1);
        textArray.add(t1);
        //calls a function calls a function , which sets line o the pane 
        makeLine();

        for (i = 0; i < dataArray.size(); i++) {
            textArray.get(i).setText(Integer.toString(dataArray.get(i)));
        }
        // adds all the new and exisitng elements(circle,text,line) back into the pane
        for (i = 0; i < dataArray.size(); i++) {
            pane.getChildren().addAll(circleArray.get(i), textArray.get(i));

        }
        for (i = 0; i < lineArray.size(); i++) {
            pane.getChildren().addAll(lineArray.get(i));

        }

    }
    //-------------------function used to make Lines -------------------------------------
    public void makeLine(){
        int l,r,i;
        double x1,x2,y1,y2;
        for (i = 0; i < lineArray.size(); i++) {
            pane.getChildren().remove(lineArray.get(i));
        }
        lineArray.clear();
        for(i=0;i<dataArray.size()-1;i++){
            l = 2*i+1;
            r = 2*i+2;
            if(!(l>= dataArray.size()))
            
            {
                x1 = xCoordinate.get(i);
                y1 = circleArray.get(i).getRadius() + yCoordinate.get(i) ;
                x2 = xCoordinate.get(l);

                y2 = yCoordinate.get(l) - circleArray.get(l).getRadius();
                Line l1 = new Line(x1,y1,x2,y2);
                l1.setStrokeWidth(3);
                l1.setStroke(Color.DARKSLATEGREY);
                lineArray.add(l1);
            }
            if(!(r >= dataArray.size()))
            {
                x1 = xCoordinate.get(i);
                y1 = circleArray.get(i).getRadius() + yCoordinate.get(i) ;
                x2 = xCoordinate.get(r);
                y2 = yCoordinate.get(r) - circleArray.get(r).getRadius();
                Line l1 = new Line(x1,y1,x2,y2);
                l1.setStrokeWidth(3);
                l1.setStroke(Color.DARKSLATEGREY);
                lineArray.add(l1);
            }
        }

    }
    //----------this function is similar to showTree, the only difference being, then logic used to remove and set new elements back to the pane
    public void showTreeAD() {
        getCoordinates();
        int i;

        for (i = 0; i < circleArray.size(); i++) {
            pane.getChildren().remove(circleArray.get(i));
        }
        for (i = 0; i < textArray.size(); i++) {
            pane.getChildren().remove(textArray.get(i));
        }

        circleArray.remove(0);
        textArray.remove(0);
        makeLine();

        for (i = 0; i < dataArray.size(); i++) {
            circleArray.get(i).setCenterX(xCoordinate.get(i));
            circleArray.get(i).setCenterY(yCoordinate.get(i));
            textArray.get(i).setX(xCoordinate.get(i) - 9);
            textArray.get(i).setY(yCoordinate.get(i) + 9);

            textArray.get(i).setText(Integer.toString(dataArray.get(i)));
        }

        for (i = 0; i < dataArray.size(); i++) {
            pane.getChildren().addAll(circleArray.get(i), textArray.get(i));
        }
        for (i = 0; i < lineArray.size(); i++) {
            pane.getChildren().addAll(lineArray.get(i));

        }
    }

    //-------------recursive function used to return the height----------------------------
    public int getHeight(int N) {

        return (int) Math.ceil(Math.log(N + 1) / Math.log(2)) - 1;

    }

    public void addNode(int data){
        dataArray.add(data);

        int i = this.dataArray.lastIndexOf(data);

        while(i > 0 && this.getParent(i) < data){
            swap(i,i/=2);

        }



    }
    
    public void swap(int one, int two){
        Collections.swap(dataArray, one, two);
    }

    public int getParent(int index){
        return dataArray.get(index/2);
    }

    public void deleteNode() {
        if(!dataArray.isEmpty()){
            ArrayList<Integer> tempArray = (ArrayList<Integer>) dataArray.clone();
            tempArray.remove(0);

            dataArray.clear();

            for(int data : tempArray){
                dataArray.add(data);

                int i = this.dataArray.lastIndexOf(data);

                while(i > 0 && this.getParent(i) < data){
                    swap(i,i/=2);

                }
            }

        }

    }

//----------this function chelps in finding the node and then changing the color of found node to orange
    public void searchNode(int find) {

        final ArrayList<Integer> indexList = new ArrayList<>();
        // this loop creates an arrayList which contains all the indexes of the desired value to be found 
        for (int i = 0; i < dataArray.size(); i++) {
            if (find == (dataArray.get(i))) {
                indexList.add(i);
            }
        }
        //sends the arrayList containing indexes to change color and add to the pane
        showSearchedTree(indexList);



    }

    public void showSearchedTree(ArrayList<Integer> indexList) {
        getCoordinates();

        for(int index: indexList){
            int i;
            //this function sets the desired circles color to orange
            setC(index);

            for (i = 0; i < dataArray.size(); i++) {
                pane.getChildren().addAll(circleArray.get(i), textArray.get(i));
            }
        }

    }
    
    public void setC(int index) {
        int i, k = dataArray.size() - 1;

        for (i = 0; i < circleArray.size(); i++) {
            pane.getChildren().remove(circleArray.get(i));
        }
        for (i = 0; i < textArray.size(); i++) {
            pane.getChildren().remove(textArray.get(i));
        }
        circleArray.get(index).setFill(Color.ORANGE);

    }

    //-----------function to clear the pane everytime a button is pressed--------------
    public void clearPane() {
        getCoordinates();
        int i;
        for (i = 0; i < circleArray.size(); i++) {
            pane.getChildren().remove(circleArray.get(i));
        }
        for (i = 0; i < textArray.size(); i++) {
            pane.getChildren().remove(textArray.get(i));
        }

        for (i = 0; i < dataArray.size(); i++) {
            circleArray.get(i).setFill(Color.TEAL);
            pane.getChildren().addAll(circleArray.get(i), textArray.get(i));
        }
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}
