
package A3;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class A3_ideaNode extends Application{
    //---border pane helps in setting the hboxes on the top and bottom
    BorderPane pane = new BorderPane();
    ArrayList<Circle> circleA = new ArrayList<Circle>();
    ArrayList<Text> textA = new ArrayList<Text>();
    ArrayList<Line> lineA = new ArrayList<Line>();
    ArrayList<Integer> dataArray = new ArrayList<Integer>();
    ArrayList<Integer> xCoord = new ArrayList<Integer>();
    ArrayList<Integer> yCoord = new ArrayList<Integer>();
    int numberOfNodes = 0;
    int initial = 0;

   

    @Override
    public void start(Stage stage) throws Exception {
      
        stage.setTitle("Max Heap");

        pane.setStyle("-fx-background-color: lightblue;");
        pane.setPrefSize(1000, 800);

        HBox layoutUp = new HBox();
        layoutUp.setStyle("-fx-background-color: lightgray;");
       
        HBox layoutDown = new HBox();
       layoutDown.setStyle("-fx-background-color: lightgray;");
       
       

        

        // Setting textField and labels for showing vertices and height
        TextField showHeight = new TextField();
        showHeight.setPrefWidth(50);
        Label height = new Label("Height of the tree");

        TextField showVertices = new TextField();
        showVertices.setPrefWidth(50);
        Label vertices = new Label("Number of Vertices");


        //------------------- insert----------------------------------------------------
            //----------insert textField and Button-------------------------------------
        TextField insert = new TextField();
        insert.setPrefWidth(50);
        
        Button insertB = new Button("Insert");
        insertB.setOnAction(event -> {
            try {
                int h=0, v=0;//declaring variables for storing height and vertices value
                clearPane();
               addNode(Integer.parseInt(insert.getText())); 
               showTree();
               
               // code to update vertics and height everytime a element is inserted
               v = dataArray.size();
               h = getHeight(v) +1;
               showHeight.setText(h+" ");
               showVertices.setText(v+" ");
               
            } catch (NumberFormatException ex) {
                System.out.println("The entered number should be integer");
            }
        });

        //------------------delete Root ------------------------------------------------------
        Button deleteB = new Button("Delete Root Node");//delete  button
        deleteB.setOnAction(event -> {
                int h=0,v=0;
               clearPane();
                deleteNode();
                 showTreeAD();
                
               // code to update vertics and height everytime a element(root node) is deleted
               v = dataArray.size();
               h = getHeight(v) +1;
               showHeight.setText(h+" ");
               showVertices.setText(v+" ");   
        });
        
        //------------------------Finding Value-----------------------------------------
        //---------------text field and button for finding value -----------------------
        TextField findN = new TextField();
        findN.setPrefWidth(50);
        Button findB = new Button("Find");
        findB.setOnAction(event -> {
            try {
                int h=0,v=0;
               clearPane();
                searchNode(Integer.parseInt(findN.getText()));
               
               // code to update vertics and height 
               v = dataArray.size();
               h = getHeight(v) +1;
               showHeight.setText(h+" ");
               showVertices.setText(v+" ");
                
            } catch (NumberFormatException ex) {
               System.out.println("The entered number should be integer");
            }
        });

        //------------------------printing the elements in preorder--------------------
        Button printB = new Button("Print");
        printB.setOnAction(event -> {
            try {
               int v=0,h=0;
               
               // code to update vertics and height 
               v = dataArray.size();
               h = getHeight(v) +1;
               showHeight.setText(h+" ");
               showVertices.setText(v+" ");
                
            } catch (NumberFormatException ex) {
                System.out.println("The entered number should be integer");
            }
       });
        // adding Hbox at the top of border pane   
        layoutUp.getChildren().addAll(insert, insertB,  deleteB, findN, findB, printB);
        layoutUp.setSpacing(20.0);
        layoutUp.setAlignment(Pos.TOP_LEFT);
        pane.setTop(layoutUp);

        // adding hbox at the bottom
        layoutDown.getChildren().addAll(showHeight, height, showVertices, vertices);
        layoutDown.setSpacing(20.0);
        layoutDown.setAlignment(Pos.BASELINE_CENTER);
        pane.setBottom(layoutDown);



        
        //--------------------setting the scene------------------------------------------------
        Scene scene = new Scene(pane, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }
    
    //-------------------creates an arrraylist full of coordinates for placing the nodes----------
    public void getCoordinates(){
        int rows = 0;
        xCoord.clear();
        yCoord.clear();
        if(dataArray.size() == 1){
            rows = 1;
        } else if (dataArray.size() >= 2 && dataArray.size() <= 3) {
            rows = 2;
        } else if (dataArray.size() >= 4 && dataArray.size() <= 7) {
            rows = 3;
        } else if (dataArray.size() >= 8 && dataArray.size() <= 15) // if there are 9-15 nodes, then total number of rows would be 4
        {
            rows = 4;
        } else if (dataArray.size() <= 16 && dataArray.size() >= 32)// if there are 16-32 nodes, then total number of rows would be 5
        {
            rows = 5;
        }

        int index = 0, i, j, xCord, childs;
        for (i = 0; i < rows; i++) {
            xCord = 0;
            //in = 0;
            childs = (int) Math.pow(2, i);
            for (j = 0; j < childs ; j++) { // every parent has two childs, so if we know the total number of parents(rows), then their children will be = parent^2 
                xCord += 500 / childs; // putting the coordinate from the center (500)pixels and then adding respectively(kind off changing the origin), so that no two nodes overlap
                int yCord = 100 + i * 100; 
                index++;
                xCoord.add(xCord);
                yCoord.add(yCord);
                
                // if the index excedes the array size, i.e. all required number of nodes have been entered for a particular row/lvel, then it breaks out of the loop and does not change the  coordinate
                if (index > dataArray.size()) {
                    break;
                }
                xCord += 500 / Math.pow(2, i);
    }
        }
    }
    
     public void showTree() {
        getCoordinates();
        int i;
        
        setCandT();
        //setLines();
        
        for(i=0;i<dataArray.size();i++){
        pane.getChildren().addAll(circleA.get(i),textA.get(i));    
        }
        
      }
    
     public void setCandT(){
        int i,k = dataArray.size()-1;
//        circleA.clear();
//        textA.clear();
        for(i=0;i<circleA.size();i++){
             pane.getChildren().remove(circleA.get(i));
         }
         for(i=0;i<textA.size();i++){
             pane.getChildren().remove(textA.get(i));
         }
        Circle c1 = new Circle(36, Color.NAVY);
        c1.setCenterX(xCoord.get(k));
        c1.setCenterY(yCoord.get(k));
        Text t1 = new Text(Integer.toString(dataArray.get(k)));
        t1.setFill(Color.WHITE);
        t1.setFont(Font.font("Calibri", 20));
        t1.setX(xCoord.get(k)-9);
        t1.setY(yCoord.get(k)+9);
        circleA.add(c1);
        textA.add(t1); 
        for(i=0;i<dataArray.size();i++){
            textA.get(i).setText(Integer.toString(dataArray.get(i)));
        }
         
     }
     
     //public void setLines(){
         
     //}
     
      public void showTreeAD(){
         getCoordinates();
        int i;
        
        deleteCandT();
        
        for(i=0;i<dataArray.size();i++){
        pane.getChildren().addAll(circleA.get(i),textA.get(i));    
        }
     }
      
     public void deleteCandT(){

         int i;
//        circleA.clear();
//        textA.clear();
        for(i=0;i<circleA.size();i++){
             pane.getChildren().remove(circleA.get(i));
         }
         for(i=0;i<textA.size();i++){
             pane.getChildren().remove(textA.get(i));
         }
        circleA.remove(0);
        textA.remove(0);
         
        for(i=0;i<dataArray.size();i++){
        circleA.get(i).setCenterX(xCoord.get(i));
        circleA.get(i).setCenterY(yCoord.get(i));
        textA.get(i).setX(xCoord.get(i)-9);
        textA.get(i).setY(yCoord.get(i)+9);
        
            textA.get(i).setText(Integer.toString(dataArray.get(i)));
        }
     }
     
     public int getHeight(int N){
         
        return (int)Math.ceil(Math.log(N + 1) / Math.log(2)) - 1; 
     
     }
     
    public void addNode(int data){
        dataArray.add(data);

        //----------------------PRINTING PURPOSES------------------------------
        for(int i : dataArray)
            System.out.print(i + " ");
        
        System.out.println("");
        //--------------------------------------------------------------------
         
         if(dataArray.size()>=2){
        buildHeap(dataArray.size());
        }
         //----------------------PRINTING PURPOSES------------------------------
         for(int i : dataArray)
            System.out.print(i + " ");
         System.out.println("");
         //--------------------------------------------------------------------
    }
    
    public void deleteNode() {
        int i;
        
        //----------------------PRINTING PURPOSES------------------------------
        for(int k : dataArray)
            System.out.print(k + "before ");
        
        //--------------------------------------------------------------------

        
                //deleteCandT();
                dataArray.remove(0);
                //initial = dataArray.size();
             
        
        //----------------------PRINTING PURPOSES------------------------------
        for(int k : dataArray)
            System.out.print(k + "deletion ");
        
        System.out.println("");
        //--------------------------------------------------------------------
       
        
        if (dataArray.size() >= 2) {
            buildHeap(dataArray.size());

           
        }
        //----------------------PRINTING PURPOSES------------------------------
        for(int k : dataArray)
            System.out.print(k + "after ");
        
        System.out.println("");
        //--------------------------------------------------------------------
        
        
    }
    
    public void searchNode(int find){
        int index=0;
        index = dataArray.indexOf(find);
        if(index == -1 ){
            System.out.println("Element not found");
            return;
        }
        showsearchedTree(index);
        
    }
    
    public void showsearchedTree(int index) {
        getCoordinates();
        int i;
        
        setC(index);
                
        for(i=0;i<dataArray.size();i++){
        pane.getChildren().addAll(circleA.get(i),textA.get(i));    
        }
        
      }
    
     public void setC(int index){
        int i,k = dataArray.size()-1;
//        circleA.clear();
//        textA.clear();
        for(i=0;i<circleA.size();i++){
             pane.getChildren().remove(circleA.get(i));
         }
         for(i=0;i<textA.size();i++){
             pane.getChildren().remove(textA.get(i));
         }
         circleA.get(index).setFill(Color.ORANGE);
        
         
     }
     
     //-----------fucntion to cler the pane everytime a button is pressed--------------
     
     public void clearPane(){
        getCoordinates();
        int i;
        for(i=0;i<circleA.size();i++){
             pane.getChildren().remove(circleA.get(i));
         }
         for(i=0;i<textA.size();i++){
             pane.getChildren().remove(textA.get(i));
         }
        
                
        for(i=0;i<dataArray.size();i++){
            circleA.get(i).setFill(Color.NAVY);
        pane.getChildren().addAll(circleA.get(i),textA.get(i)); 
     }
     }
    
    public void buildHeap(int n) 
    { 
        int startIdx = (n / 2) - 1; 

        for (int i = startIdx; i >= 0; i--) { 
            heapify(n, i); 
        } 
      
    }
    
     public void heapify(int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2 * i + 1; // left = 2*i + 1 
        int r = 2 * i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && dataArray.get(1) > dataArray.get(largest)) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && dataArray.get(r) > dataArray.get(largest)) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) { 
            int swap = dataArray.get(i); 
            dataArray.set(i,dataArray.get(largest)); 
            dataArray.set(largest,swap); 
  
            // Recursively heapify the affected sub-tree 
            heapify(n, largest); 
        } 
    } 
    
    
    
     public static void main(String args[]) {
         Application.launch(args);
    }

    
}
