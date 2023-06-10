/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxexamplefinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Javafxexamplefinal extends Application {
    public static Button btn_calculate,btn_export;
    public static Label lbl_intitialIntrest,lbl_intrestRateTitle,lbl_years,
            lbl_yearhint,lbl_cye,lbl_cyehint,lbl_totlalBalance,lbl_totalBalanceHint;
    public static TextField tf_intialBalance,tf_interestRate;
    public static TextArea ta_history;
    
    private int years=0 ;
    private double balance=0;
  
    @Override
    public void start(Stage primaryStage) {
       initialize();
       
       setPadding(5,lbl_intitialIntrest,lbl_intrestRateTitle,lbl_years,lbl_yearhint,lbl_cye,lbl_cyehint,lbl_totlalBalance,lbl_totalBalanceHint);
      bttonsettings(btn_calculate,btn_export);
       setfont(30,lbl_years,lbl_cye,lbl_totlalBalance);
       HBox hbox_input =new HBox(5,lbl_intitialIntrest,tf_intialBalance,lbl_intrestRateTitle,tf_interestRate);
      hbox_input.setAlignment(Pos.CENTER);
       VBox yearPane =new VBox (5,lbl_years,lbl_yearhint);
       yearPane.setAlignment(Pos.CENTER);
       VBox cyePane =new VBox (5,lbl_cye,lbl_cyehint);
       cyePane.setAlignment(Pos.CENTER);
       VBox balancePane =new VBox (5,lbl_totlalBalance,lbl_totalBalanceHint);
      balancePane.setAlignment(Pos.CENTER);
       HBox num =new HBox (30,yearPane,cyePane,balancePane);
      num.setAlignment(Pos.CENTER);
       
      btn_calculate.setOnAction(e->{
          
          
      double bal =Double.parseDouble(tf_intialBalance.getText());
      double intrest =Double.parseDouble(tf_interestRate.getText());
      
      
      if (years==0){balance =bal;}
      
       double cye =balance*intrest/100;
       balance +=cye;
       lbl_cye.setText(String.format("$ %.2f",cye));
       lbl_totlalBalance.setText(String.format("$ %.2f",balance));
       lbl_years.setText(String.valueOf(++years));
       ta_history.appendText("Year : "+years+" earnd :"+String.format("$ %.2f",cye)
               +" your Balance :"+String.format("$ %.2f",balance)+"\n");
       
      
      });
      
      btn_export.setOnAction(e->{
      FileChooser fc =new FileChooser();
      fc.setTitle("save");
      fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("intrestData", ".txt"));
      File f =fc.showSaveDialog(primaryStage);
      
      try(PrintWriter pr =new PrintWriter(f)){
      pr.write(ta_history.getText());
      }    catch (FileNotFoundException ex) {
          
           }
      
      });
      
      
       VBox root =new VBox(20,hbox_input,btn_calculate,num,ta_history,btn_export);
      root.setAlignment(Pos.CENTER);
       Scene scene =new Scene (root,500,400);
       
       primaryStage.setScene (scene);
       primaryStage.setTitle("Investment app");
       primaryStage.show();
       
       
    }  
    public static void initialize(){
        btn_calculate=new Button ("Calculate");
        btn_export=new Button ("Export");
        lbl_intitialIntrest=new Label ("Intial balance");
        lbl_intrestRateTitle=new Label ("Intrest Rate");
        lbl_years=new Label ("0");
        lbl_yearhint=new Label ("Years");
        lbl_cye=new Label ("0");
        lbl_cyehint=new Label ("Current year earnings");
        lbl_totlalBalance=new Label ("0");
        lbl_totalBalanceHint=new Label ("Total Balance");
        tf_intialBalance=new TextField ();
        tf_interestRate=new TextField ();
        ta_history=new TextArea ();
    }
    public static void setPadding (double padding ,Control...con){
        
    for (Control c : con){c.setPadding(new Insets(padding));}
    }
    public static void setfont (int num,Label ... lbl){
    for (Label l :lbl){
        l.setFont(new Font (num));
        l.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
    }
    }
    public static void bttonsettings(Button...btn){
        for (Button b :btn){b.setMaxHeight(100);b.setMaxWidth(100);}
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
