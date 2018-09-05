package statCreator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{

	private TextField txtName = new TextField();    
    
	private Label lblName = new Label("Name: ");
    private Label lblStrength = new Label("Strength (Str): ");
    private Label lblDexterity = new Label("Dexterity (Dex): ");
    private Label lblConstitution = new Label("Constitution (Con): ");
    private Label lblIntelligence = new Label("Intelligence (Int): ");
    private Label lblWisdom = new Label("Wisdom (Wis): ");
    private Label lblCharisma = new Label("Charisma (Cha): ");
    
    private Label lblStrengthScore = new Label();
    private Label lblDexterityScore = new Label();
    private Label lblConstitutionScore = new Label();
    private Label lblIntelligenceScore = new Label();
    private Label lblWisdomScore = new Label();
    private Label lblCharismaScore = new Label();
    
    private Button btnStrengthDiceRoll = new Button("Roll Dice");
    private Button btnDexterityDiceRoll = new Button("Roll Dice");
    private Button btnConstitutionDiceRoll = new Button("Roll Dice");
    private Button btnIntelligenceDiceRoll = new Button("Roll Dice");
    private Button btnWisdomDiceRoll = new Button("Roll Dice");
    private Button btnCharismaDiceRoll = new Button("Roll Dice");
    
    private Button btnRead = new Button("Load Character");
    private Button btnWrite = new Button("Save Stats");
    private Button btnClear = new Button("Clear Stats");
    private Button btnExit = new Button("Quit");

    private TextArea taSelectedData = new TextArea();

    @Override
    public void start(Stage primaryStage) {

        BorderPane border = new BorderPane(); 
        border.setCenter(addGridPane());
        HBox bottomPane = addHBox();
        border.setBottom(bottomPane);
        bottomPane.setPadding(new Insets(5, 0, 5, 11));
        
        primaryStage.setOnCloseRequest(e -> {
        	e.consume();
        	CloseApplication.closeApplication();        		
    	});
        
        btnStrengthDiceRoll.setOnAction( e -> DiceRoller.getDiceTotal(lblStrengthScore));
        btnDexterityDiceRoll.setOnAction( e -> DiceRoller.getDiceTotal(lblDexterityScore));
        btnConstitutionDiceRoll.setOnAction( e -> DiceRoller.getDiceTotal(lblConstitutionScore));
        btnIntelligenceDiceRoll.setOnAction( e -> DiceRoller.getDiceTotal(lblIntelligenceScore));
        btnWisdomDiceRoll.setOnAction( e -> DiceRoller.getDiceTotal(lblWisdomScore));
        btnCharismaDiceRoll.setOnAction( e -> DiceRoller.getDiceTotal(lblCharismaScore));
         
        btnExit.setOnAction( e -> CloseApplication.closeApplication());
        btnRead.setOnAction( e -> readData());
        btnWrite.setOnAction( e -> writeData());
        btnClear.setOnAction( e -> clearFields());   
             
        Scene scene = new Scene(border, 450, 450);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private HBox addHBox() {
        
        HBox  bottomPane = new HBox();
        bottomPane.setSpacing(10);
        bottomPane.getChildren().addAll(btnRead, btnWrite, btnClear, btnExit);
        bottomPane.setAlignment(Pos.BOTTOM_CENTER);
                
        return bottomPane;        
    }
    
    private GridPane addGridPane(){                       
              
        ScrollPane scrollPane = new ScrollPane(taSelectedData);
        scrollPane.setMaxSize(200, 135);
        scrollPane.setFitToWidth(true); 
        taSelectedData.setWrapText(true);
        taSelectedData.setEditable(false);
        
        GridPane gridPane = new GridPane();
  
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        
        gridPane.add(lblName, 0, 0);
        gridPane.add(txtName, 1, 0);
        
        gridPane.add(lblStrength, 0, 1);
        gridPane.add(lblStrengthScore, 1, 1);
        gridPane.add(btnStrengthDiceRoll, 2, 1);
        
        gridPane.add(lblDexterity, 0, 2);
        gridPane.add(lblDexterityScore, 1, 2);
        gridPane.add(btnDexterityDiceRoll, 2, 2);
        
        gridPane.add(lblConstitution, 0, 3);
        gridPane.add(lblConstitutionScore, 1, 3);
        gridPane.add(btnConstitutionDiceRoll, 2, 3);
        
        gridPane.add(lblIntelligence, 0, 4);
        gridPane.add(lblIntelligenceScore, 1, 4);
        gridPane.add(btnIntelligenceDiceRoll, 2, 4);
        
        gridPane.add(lblWisdom, 0, 5);
        gridPane.add(lblWisdomScore, 1, 5);
        gridPane.add(btnWisdomDiceRoll, 2, 5);
        
        gridPane.add(lblCharisma, 0, 6);
        gridPane.add(lblCharismaScore, 1, 6);
        gridPane.add(btnCharismaDiceRoll, 2, 6);
        
        gridPane.add(scrollPane, 1, 7);
        
        gridPane.setAlignment(Pos.CENTER);
        
        return gridPane;
    }
    
    
    protected void writeData() {
        PrintWriter outFile = null;
        
        try {
            outFile = new PrintWriter("data.txt");
        }
        catch(FileNotFoundException fnfe1){
            JOptionPane.showMessageDialog(null, "Error: could not find file" + fnfe1);
        }
        
        String name = txtName.getText();
        String strengthTotal = lblStrengthScore.getText();
        String dexterityTotal = lblDexterityScore.getText();
        String constitutionTotal = lblConstitutionScore.getText();
        String intlligenceTotal = lblIntelligenceScore.getText();
        String wisdomTotal = lblWisdomScore.getText();  
        String charismaTotal = lblCharismaScore.getText();  

        outFile.println(lblName.getText()+ " " + name + "\n" + lblStrength.getText() + " " + strengthTotal + "\n" + lblDexterity.getText() + " " + dexterityTotal + "\n" + lblConstitution.getText() + " " + constitutionTotal + "\n" 
                        + lblIntelligence.getText() + " " + intlligenceTotal + "\n" + lblWisdom.getText() + " " + wisdomTotal + "\n" + lblCharisma.getText() + " " + charismaTotal);
        
        outFile.close();
        clearFields();
        AlertBox.display("Saved!", "Your character stats have been saved.");
	}
    
    private void readData(){
        
        Scanner inFile = null;
        
        try {
            inFile = new Scanner(new FileReader("data.txt"));
        } 
        catch(FileNotFoundException fnfe2){
            JOptionPane.showMessageDialog(null, "Error: could not find file" + fnfe2);
        }            
        int i = 0;
        String content = "";
        while(inFile.hasNext()){
            if (i == 0) {
            	content += inFile.next() + " " + inFile.next() + "\n";  
            	i++;
            }
            else if (i <= 2) {
            	content += inFile.next() + " ";
            	i++;
            }
            else if(i <= 4) {
            	content += inFile.next() + "\n";
            	i = 1;
            }
        }
        
        taSelectedData.setText(content);

        inFile.close();               
    }        

    protected void clearFields() {
        txtName.setText("");
        lblStrengthScore.setText("");
        lblDexterityScore.setText("");
        lblConstitutionScore.setText("");
        lblIntelligenceScore.setText("");
        lblWisdomScore.setText("");
        lblCharismaScore.setText("");
        txtName.requestFocus();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}