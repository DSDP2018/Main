package application;

import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * An application with a zoomable and pannable canvas.
 */
public class ZoomRevisionLog extends Application {
    
    Stage window;
    static Scene scene;
    static ScrollPane pane;
    public static double numberOfFields = 2.1;
    public static int StartAtOne = 1;
    
    static Group group;
    
    static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    //static int screenWidth = (int) primaryScreenBounds.getWidth();
    //static int screenHeight = (int) primaryScreenBounds.getHeight();
    static int screenWidth = 1440;
    static int screenHeight = 1100;
    static int proportionalWidth = screenWidth/16;
    static int proportionalHeight = screenHeight/10;
    static double fieldWidthAlignment = screenWidth/45;
    
    
    static int widthBy23 = screenWidth/25;
    static int heightBy30 = screenHeight/30;
    
    public static void main(String[] args) {
        launch(args);
    }
    public static void trying(Stage stage){
        Group root = new Group();
        group = root;
        // create canvas
        PannableCanvas canvas = new PannableCanvas();

        // we don't want the canvas on the top/left in this example => just
        // translate it a bit
//        canvas.setTranslateX(100);
//        canvas.setTranslateY(100);

        // create sample nodes which can be dragged
        NodeGestures nodeGestures = new NodeGestures( canvas);

        int screenProportionHeightby25 = screenHeight/25;   
        Line line1 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenProportionHeightby25,screenHeight-80);
        Line line2 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenProportionHeightby25);
        Line line3 = LineBlackNoFill(screenWidth-screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenHeight-80);
        Line line4 = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenHeight-80, screenProportionHeightby25, screenHeight-80);
        Line line5 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25+50,screenWidth-screenProportionHeightby25,screenProportionHeightby25+50);
        canvas.getChildren().addAll(line1,line2,line3,line4,line5);
        

            
        Label lblRevisionControl = label("Variable Stress-Drivers' Signal Configurator", 175, 15, 60, 50, "revisionControl", "The Current Menu");
        Label lblProjectID = label("123456789012345678901234567890", 250, 15, proportionalWidth*9, 55, "", "Project ID");
        Label lblRevisionNum = label("R1", 75, 15, proportionalWidth*12, 55, "", "Revision Number");
        Label lblDateBorder = label("YY-MM-DD", 75, 15, proportionalWidth*13, 55, "", "Current Date");
        Label lblPageNum = label("Page 1 of 1", 75, 15, proportionalWidth*14, 55, "", "Page Number");
        canvas.getChildren().addAll(lblRevisionControl, lblProjectID,lblRevisionNum,lblDateBorder,lblPageNum);
        
        
        Label lblParameter = label("parameter", 40, 15, widthBy23, heightBy30*4, "label", "parameter");
        TextField txtparameter = textfield("12345678901234567890",105, true, widthBy23, heightBy30*5.5, "textFieldBlue");
        Button btnClearAll1 = button("clear all", widthBy23, heightBy30*6.5, "functionalButton2", true, "to refresh calculations");
        Label lblUnit = label("unit", 40, 15, widthBy23*3, heightBy30*4, "label", "unit number");
        TextField txtUnit = textfield("1234567890",55, true, widthBy23*3, heightBy30*5.5, "textFieldBlue");
        Label lblIntentRange = label("Intent range", 40, 15, widthBy23*5, heightBy30*4, "label", "Intent range");
        
        Label lblMax = label("Max", 40, 15, widthBy23*4.25, heightBy30*5.5, "label", "Intent range");
        Label lblMin = label("Min", 40, 15, widthBy23*4.25, heightBy30*6.5, "label", "Intent range");
        
        TextField txtMaxIntent = textfield("1234567890",60, true, widthBy23*5, heightBy30*5.5, "textField");
        TextField txtMinIntent = textfield("1234567890",60, true, widthBy23*5, heightBy30*6.5, "textField");
        
        Button btnRefresh = button("Refresh Calculation", widthBy23*5, heightBy30*3.25, "functionalButton2", true, "to refresh calculations");
        Label lblFaultRange = label("Fault range", 40, 15, widthBy23*6.5, heightBy30*4, "label", "Fault range");
        TextField txtMaxFault = textfield("1234567890",60, true, widthBy23*6.5, heightBy30*5.5, "textFieldDarkGreen");
        TextField txtMinFault = textfield("1234567890",60, true, widthBy23*6.5, heightBy30*6.5, "textFieldDarkGreen");
        Label lblLimitRange = label("Limit range", 40, 15, widthBy23*8, heightBy30*4, "label", "Limit range");
        TextField txtMaxLimit = textfield("1234567890",60, true, widthBy23*8, heightBy30*5.5, "textFieldDarkGreen");
        TextField txtMinLimit = textfield("1234567890",60, true, widthBy23*8, heightBy30*6.5, "textFieldDarkGreen");
        Label lblDLTCriteria = label("DLTCriteria", 40, 15, widthBy23*10.25, heightBy30*3.25, "label2", "Fault range");
        Label lblPChange = label("% change to reach limit", 40, 15, widthBy23*9.5, heightBy30*4, "label", "% change to reach limit");
        lblPChange.setWrapText(true);
        lblPChange.setMaxWidth(75);
        TextField txtMaxChange= textfield("1234567890",60, true, widthBy23*9.5, heightBy30*5.5, "textFieldDarkGreen");
        TextField txtMinChange = textfield("1234567890",60, true, widthBy23*9.5, heightBy30*6.5, "textFieldDarkGreen");
        Label lblRampReduction = label("ramp % deduction", 40, 15, widthBy23*11, heightBy30*4, "label", "Ramp % reduction");
        lblRampReduction.setWrapText(true);
        lblRampReduction.setMaxWidth(65);
        TextField txtRampReduction= textfield("1234567890",60, true, widthBy23*11, heightBy30*5.5, "textFieldDarkGreen");
        Label lblYAxisMaxYAxisMin = label("y axis max y axis min", 40, 15, widthBy23*12.5, heightBy30*4, "label", "Y axis max and y axis min");
        lblYAxisMaxYAxisMin.setWrapText(true);
        lblYAxisMaxYAxisMin.setMaxWidth(65);
        TextField txtMaxYAxisMax= textfield("1234567890",60, true, widthBy23*12.5, heightBy30*5.5, "textFieldDarkGreen");
        TextField txtMinYAxisMin = textfield("1234567890",60, true, widthBy23*12.5, heightBy30*6.5, "textFieldDarkGreen");
        Button btnRefresh1 = button("Refresh Calculation", widthBy23*12.5, heightBy30*3.25, "functionalButton2", true, "to refresh calculations");
        Label lblYincrementYScale = label("y increment y scale", 40, 15, widthBy23*14, heightBy30*4, "label", "Y increment y scale");
        lblYincrementYScale.setWrapText(true);
        lblYincrementYScale.setMaxWidth(70);
        TextField txtMaxYincrement= textfield("1234567890",60, true, widthBy23*14, heightBy30*5.5, "textFieldDarkGreen");
        TextField txtMinYYScale = textfield("1234567890",60, true, widthBy23*14, heightBy30*6.5, "textFieldDarkBlue");
        Label lblSampling = label("sampling(Hz)", 40, 15, widthBy23*15.5, heightBy30*4, "label", "Sampling (Hz)");
        TextField txtMaxSampling= textfield("1234567890",60, true, widthBy23*15.5, heightBy30*5.5, "textFieldDarkGreen");
        Label lblStability = label("Stability +/-", 40, 15, widthBy23*17, heightBy30*4, "label", "Stability +/-");
        TextField txtMaxStability= textfield("1234567890",60, true, widthBy23*17, heightBy30*5.5, "textFieldDarkGreen");
        Label lblPulsation = label("Pulsation +/-", 40, 15, widthBy23*17, heightBy30*3.25, "label", "Sampling (Hz)");
        TextField txtMaxTimeout= textfield("1234567890",60, true, widthBy23*18.5, heightBy30*5.5, "textFieldDarkGreen");
        Label lblTimeout = label("timeout (sec.)", 40, 15, widthBy23*18.5, heightBy30*4, "label", "timeout (seconds)");
        Label lblColorRGB = label("Color R-G-B#", 40, 15, widthBy23*20, heightBy30*4, "functionalButton", "Enter Hexidecimal Color");
        TextField txtRGBColor= textfield("1234567890",60, true, widthBy23*20, heightBy30*5.5, "textFieldDarkGreen");
        Label lblTS1 = label("TS1 limit", 40, 15, widthBy23*21.5, heightBy30*4, "label", "TS1 Limit");
        TextField txtMaxTS1= textfield("1234567890",60, true, widthBy23*21.5, heightBy30*5.5, "textField");
        TextField txtMinTS1 = textfield("1234567890",60, true, widthBy23*21.5, heightBy30*6.5, "textField");
        Label lblTestSystemCapability = label("test system capability", 40, 15, widthBy23*21.5, heightBy30*3.25, "label", "Test System Capability");
        lblTestSystemCapability.setMaxWidth(65);
        canvas.getChildren().addAll(lblMax,lblMin,lblParameter,lblUnit,lblIntentRange,lblFaultRange,lblLimitRange,lblPChange,lblRampReduction,
        		lblYAxisMaxYAxisMin,lblYincrementYScale,lblSampling,lblStability,lblTimeout,lblColorRGB,btnRefresh,
        		lblDLTCriteria,btnRefresh1,lblPulsation,lblTS1,lblTestSystemCapability,txtparameter,txtUnit,txtMaxIntent
        		,txtMinIntent,txtMaxFault,txtMinFault,txtMaxLimit,txtMinLimit,txtMaxChange,txtMinChange,txtRampReduction,
        		txtMaxYAxisMax,txtMinYAxisMin,txtMaxYincrement,txtMinYYScale,txtMaxSampling,txtMaxStability,txtMaxTimeout
        		,txtRGBColor,txtMaxTS1,txtMinTS1,btnClearAll1);

        
        
        // 4 x 1.5
        // textField 4x1
        Button btnClearAll2 = button("clear all", widthBy23, heightBy30*9, "functionalButton", true, "to copy");
        
        Label lblDurationSec = label("duration sec.", 30, 15, widthBy23, heightBy30*10.75, "label", "Level 1");
        lblDurationSec.setWrapText(true);
        lblDurationSec.setMaxWidth(54);
        Label lblRampUnit = label("ramp unit/sec.", 30, 15, widthBy23, heightBy30*11.75, "label", "Level 1");
        lblRampUnit.setWrapText(true);
        lblRampUnit.setMaxWidth(55);
       
        Label lblLevel1 = label("level 1", 40, 15, widthBy23 *2, heightBy30*9, "label", "Level 1");
        TextField txtLevel11= textfield("1234567890",50, true, widthBy23*2, heightBy30*10, "textField1");
       
        TextField txtLevel12= textfield("1234567890",50, true, widthBy23*2, heightBy30*11, "textField1");
        TextField txtLevel13= textfield("1234567890",50, true, widthBy23*2, heightBy30*12, "textField1");
        Label lblLevel2 = label("level 2", 45, 15, widthBy23 *3, heightBy30*9, "label", "Level 2");
        TextField txtLevel21= textfield("1234567890",50, true, widthBy23*3, heightBy30*10, "textField1");
        TextField txtLevel22= textfield("1234567890",50, true, widthBy23*3, heightBy30*11, "textField1");
        TextField txtLevel23= textfield("1234567890",50, true, widthBy23*3, heightBy30*12, "textField1");
        Label lblLevel3 = label("level 3", 45, 15, widthBy23 *4, heightBy30*9, "label", "Level 3");
        TextField txtLevel31= textfield("1234567890",50, true, widthBy23*4, heightBy30*10, "textField1");
        TextField txtLevel32= textfield("1234567890",50, true, widthBy23*4, heightBy30*11, "textField1");
        TextField txtLevel33= textfield("1234567890",50, true, widthBy23*4, heightBy30*12, "textField1");
        Label lblIntentReference = label("Intent Reference", 40, 15, widthBy23 *4, heightBy30*7.5, "label", "Level 1");
        lblIntentReference.setWrapText(true);
        lblIntentReference.setMaxWidth(65);   

        
        //TextArea txtAreaIntentReference = textarea("Enter text here.", 100, widthBy23 *5, heightBy30*9, "TextField1");
        Label lblLevel4 = label("level 4", 45, 15, widthBy23 *5, heightBy30*9, "label", "Level 4");
        TextField txtLevel41= textfield("1234567890",50, true, widthBy23*5, heightBy30*10, "textField1");
        TextField txtLevel42= textfield("1234567890",50, true, widthBy23*5, heightBy30*11, "textField1");
        TextField txtLevel43= textfield("1234567890",50, true, widthBy23*5, heightBy30*12, "textField1");
        Label lblLevel5 = label("level 5", 45, 15, widthBy23 *6, heightBy30*9, "label", "Level 5");
        TextField txtLevel51= textfield("1234567890",50, true, widthBy23*6, heightBy30*10, "textField1");
        TextField txtLevel52= textfield("1234567890",50, true, widthBy23*6, heightBy30*11, "textField1");
        TextField txtLevel53= textfield("1234567890",50, true, widthBy23*6, heightBy30*12, "textField1");
        Label lblLevel6 = label("level 6", 45, 15, widthBy23 *7, heightBy30*9, "label", "Level 6");
        TextField txtLevel61= textfield("1234567890",50, true, widthBy23*7, heightBy30*10, "textField1");
        TextField txtLevel62= textfield("1234567890",50, true, widthBy23*7, heightBy30*11, "textField1");
        TextField txtLevel63= textfield("1234567890",50, true, widthBy23*7, heightBy30*12, "textField1");
        Label lblLevel7 = label("level 7", 45, 15, widthBy23 *8, heightBy30*9, "label", "Level 7");
        TextField txtLevel71= textfield("1234567890",50, true, widthBy23*8, heightBy30*10, "textField1");
        TextField txtLevel72= textfield("1234567890",50, true, widthBy23*8, heightBy30*11, "textField1");
        TextField txtLevel73= textfield("1234567890",50, true, widthBy23*8, heightBy30*12, "textField1");
        Label lblLevel8 = label("level 8", 45, 15, widthBy23 *9, heightBy30*9, "label", "Level 8");
        TextField txtLevel81= textfield("1234567890",50, true, widthBy23*9, heightBy30*10, "textField1");
        TextField txtLevel82= textfield("1234567890",50, true, widthBy23*9, heightBy30*11, "textField1");
        TextField txtLevel83= textfield("1234567890",50, true, widthBy23*9, heightBy30*12, "textField1");
        Label lblLevel9 = label("level 9", 45, 15, widthBy23 *10, heightBy30*9, "label", "Level 9");
        TextField txtLevel91= textfield("1234567890",50, true, widthBy23*10, heightBy30*10, "textField1");
        TextField txtLevel92= textfield("1234567890",50, true, widthBy23*10, heightBy30*11, "textField1");
        TextField txtLevel93= textfield("1234567890",50, true, widthBy23*10, heightBy30*12, "textField1");
        Label lblLevel10 = label("level 10", 45, 15, widthBy23 *11, heightBy30*9, "label", "Level 10");
        TextField txtLevel101= textfield("1234567890",50, true, widthBy23*11, heightBy30*10, "textField1");
        TextField txtLevel102= textfield("1234567890",50, true, widthBy23*11, heightBy30*11, "textField1");
        TextField txtLevel103= textfield("1234567890",50, true, widthBy23*11, heightBy30*12, "textField1");
        
        Button btnIntentProfile = button("Intent profile", widthBy23 *2, heightBy30*8, "functionalButton", true, "to copy");
        btnIntentProfile.setOnAction(e ->{
        	//Aarons graph

        });
        
        Label lblLevel11= label("level 11", 45, 15, widthBy23 *12, heightBy30*9, "label", "Level 11");
        TextField txtLevel111= textfield("1234567890",50, true, widthBy23*12, heightBy30*10, "textField1");
        TextField txtLevel112= textfield("1234567890",50, true, widthBy23*12, heightBy30*11, "textField1");
        TextField txtLevel113= textfield("1234567890",50, true, widthBy23*12, heightBy30*12, "textField1");
        Label lblLevel12 = label("level 12", 45, 15, widthBy23 *13, heightBy30*9, "label", "Level 12");
        TextField txtLevel121= textfield("1234567890",50, true, widthBy23*13, heightBy30*10, "textField1");
        TextField txtLevel122= textfield("1234567890",50, true, widthBy23*13, heightBy30*11, "textField1");
        TextField txtLevel123= textfield("1234567890",50, true, widthBy23*13, heightBy30*12, "textField1");
        Label lblLevel13 = label("level 13", 45, 15, widthBy23 *14, heightBy30*9, "label", "Level 13");
        TextField txtLevel131= textfield("1234567890",50, true, widthBy23*14, heightBy30*10, "textField1");
        TextField txtLevel132= textfield("1234567890",50, true, widthBy23*14, heightBy30*11, "textField1");
        TextField txtLevel133= textfield("1234567890",50, true, widthBy23*14, heightBy30*12, "textField1");
        Label lblLevel14 = label("level 14", 45, 15, widthBy23 *15, heightBy30*9, "label", "Level 14");
        TextField txtLevel141= textfield("1234567890",50, true, widthBy23*15, heightBy30*10, "textField1");
        TextField txtLevel142= textfield("1234567890",50, true, widthBy23*15, heightBy30*11, "textField1");
        TextField txtLevel143= textfield("1234567890",50, true, widthBy23*15, heightBy30*12, "textField1");
        Label lblLevel15 = label("level 15", 45, 15, widthBy23 *16, heightBy30*9, "label", "Level 15");
        TextField txtLevel151= textfield("1234567890",50, true, widthBy23*16, heightBy30*10, "textField1");
        TextField txtLevel152= textfield("1234567890",50, true, widthBy23*16, heightBy30*11, "textField1");
        TextField txtLevel153= textfield("1234567890",50, true, widthBy23*16, heightBy30*12, "textField1");
        Label lblLevel16 = label("level 16", 45, 15, widthBy23 *17, heightBy30*9, "label", "Level 16");
        TextField txtLevel161= textfield("1234567890",50, true, widthBy23*17, heightBy30*10, "textField1");
        TextField txtLevel162= textfield("1234567890",50, true, widthBy23*17, heightBy30*11, "textField1");
        TextField txtLevel163= textfield("1234567890",50, true, widthBy23*17, heightBy30*12, "textField1");
        Label lblLevel17 = label("level 17", 45, 15, widthBy23 *18, heightBy30*9, "label", "Level 17");
        TextField txtLevel171= textfield("1234567890",50, true, widthBy23*18, heightBy30*10, "textField1");
        TextField txtLevel172= textfield("1234567890",50, true, widthBy23*18, heightBy30*11, "textField1");
        TextField txtLevel173= textfield("1234567890",50, true, widthBy23*18, heightBy30*12, "textField1");
        Label lblLevel18 = label("level 18", 45, 15, widthBy23 *19, heightBy30*9, "label", "Level 18");
        TextField txtLevel181= textfield("1234567890",50, true, widthBy23*19, heightBy30*10, "textField1");
        TextField txtLevel182= textfield("1234567890",50, true, widthBy23*19, heightBy30*11, "textField1");
        TextField txtLevel183= textfield("1234567890",50, true, widthBy23*19, heightBy30*12, "textField1");
        Label lblLevel19 = label("level 19", 45, 15, widthBy23 *20, heightBy30*9, "label", "Level 19");
        TextField txtLevel191= textfield("1234567890",50, true, widthBy23*20, heightBy30*10, "textField1");
        TextField txtLevel192= textfield("1234567890",50, true, widthBy23*20, heightBy30*11, "textField1");
        TextField txtLevel193= textfield("1234567890",50, true, widthBy23*20, heightBy30*12, "textField1");
        Label lblLevel20 = label("level 20", 45, 15, widthBy23 *21, heightBy30*9, "label", "Level 20");
        TextField txtLevel201= textfield("1234567890",50, true, widthBy23*21, heightBy30*10, "textField1");
        TextField txtLevel202= textfield("1234567890",50, true, widthBy23*21, heightBy30*11, "textField1");
        TextField txtLevel203= textfield("1234567890",50, true, widthBy23*21, heightBy30*12, "textField1");
        canvas.getChildren().addAll(btnClearAll2,lblLevel1,lblLevel2,lblLevel3,lblLevel4,lblLevel5,lblLevel6,
        		lblLevel7,lblLevel8,lblLevel9,lblLevel10,lblLevel11,lblLevel12,lblLevel13,lblLevel14,
        		lblLevel15,lblLevel16,lblLevel17,lblLevel18,lblLevel19,lblLevel20,btnIntentProfile,lblIntentReference,
        		lblDurationSec,lblRampUnit,txtLevel11,txtLevel12,txtLevel13,txtLevel21,txtLevel22,txtLevel23,txtLevel31,
        		txtLevel32,txtLevel33,txtLevel41,txtLevel42,txtLevel43,txtLevel51,txtLevel52,txtLevel53,txtLevel61,
        		txtLevel62,txtLevel63,txtLevel71,txtLevel72,txtLevel73,txtLevel81,txtLevel82,txtLevel83,txtLevel91,
        		txtLevel92,txtLevel93,txtLevel101,txtLevel102,txtLevel103,txtLevel111,txtLevel112,txtLevel113,
        		txtLevel121,txtLevel122,txtLevel123,txtLevel131,txtLevel132,txtLevel133,txtLevel141,txtLevel142,txtLevel143,
        		txtLevel151,txtLevel152,txtLevel153,txtLevel161,txtLevel162,txtLevel163,txtLevel171,txtLevel172,txtLevel173,
        		txtLevel181,txtLevel182,txtLevel183,txtLevel191,txtLevel192,txtLevel193,txtLevel201,txtLevel202,txtLevel203);
       
       // 
        //
        //
        //
        //
        //
        //
        
        Label lblLevel1Two = label("level 1", 40, 15, widthBy23 *2, heightBy30*13, "label", "Level 1");
        TextField txtLevel211= textfield("1234567890",50, true, widthBy23*2, heightBy30*14, "textField1");
        TextField txtLevel212= textfield("1234567890",50, true, widthBy23*2, heightBy30*15, "textField1");
        TextField txtLevel213= textfield("1234567890",50, true, widthBy23*2, heightBy30*16, "textField1");
        Label lblLevel2Two = label("level 2", 45, 15, widthBy23 *3, heightBy30*13, "label", "Level 2");
        TextField txtLevel221= textfield("1234567890",50, true, widthBy23*3, heightBy30*14, "textField1");
        TextField txtLevel222= textfield("1234567890",50, true, widthBy23*3, heightBy30*15, "textField1");
        TextField txtLevel223= textfield("1234567890",50, true, widthBy23*3, heightBy30*16, "textField1");
        Label lblLevel3Two = label("level 3", 45, 15, widthBy23 *4, heightBy30*13, "label", "Level 3");
        TextField txtLevel231= textfield("1234567890",50, true, widthBy23*4, heightBy30*14, "textField1");
        TextField txtLevel232= textfield("1234567890",50, true, widthBy23*4, heightBy30*15, "textField1");
        TextField txtLevel233= textfield("1234567890",50, true, widthBy23*4, heightBy30*16, "textField1");
        Label lblLevel4Two = label("level 4", 45, 15, widthBy23 *5, heightBy30*13, "label", "Level 4");
        TextField txtLevel241= textfield("1234567890",50, true, widthBy23*5, heightBy30*14, "textField1");
        TextField txtLevel242= textfield("1234567890",50, true, widthBy23*5, heightBy30*15, "textField1");
        TextField txtLevel243= textfield("1234567890",50, true, widthBy23*5, heightBy30*16, "textField1");
        Label lblLevel5Two = label("level 5", 45, 15, widthBy23 *6, heightBy30*13, "label", "Level 5");
        TextField txtLevel251= textfield("1234567890",50, true, widthBy23*6, heightBy30*14, "textField1");
        TextField txtLevel252= textfield("1234567890",50, true, widthBy23*6, heightBy30*15, "textField1");
        TextField txtLevel253= textfield("1234567890",50, true, widthBy23*6, heightBy30*16, "textField1");
        Label lblLevel6Two = label("level 6", 45, 15, widthBy23 *7, heightBy30*13, "label", "Level 6");
        TextField txtLevel261= textfield("1234567890",50, true, widthBy23*7, heightBy30*14, "textField1");
        TextField txtLevel262= textfield("1234567890",50, true, widthBy23*7, heightBy30*15, "textField1");
        TextField txtLevel263= textfield("1234567890",50, true, widthBy23*7, heightBy30*16, "textField1");
        Label lblLevel7Two = label("level 7", 45, 15, widthBy23 *8, heightBy30*13, "label", "Level 7");
        TextField txtLevel271= textfield("1234567890",50, true, widthBy23*8, heightBy30*14, "textField1");
        TextField txtLevel272= textfield("1234567890",50, true, widthBy23*8, heightBy30*15, "textField1");
        TextField txtLevel273= textfield("1234567890",50, true, widthBy23*8, heightBy30*16, "textField1");
        Label lblLevel8Two = label("level 8", 45, 15, widthBy23 *9, heightBy30*13, "label", "Level 8");
        TextField txtLevel281= textfield("1234567890",50, true, widthBy23*9, heightBy30*14, "textField1");
        TextField txtLevel282= textfield("1234567890",50, true, widthBy23*9, heightBy30*15, "textField1");
        TextField txtLevel283= textfield("1234567890",50, true, widthBy23*9, heightBy30*16, "textField1");
        Label lblLevel9Two = label("level 9", 45, 15, widthBy23 *10, heightBy30*13, "label", "Level 9");
        TextField txtLevel291= textfield("1234567890",50, true, widthBy23*10, heightBy30*14, "textField1");
        TextField txtLevel292= textfield("1234567890",50, true, widthBy23*10, heightBy30*15, "textField1");
        TextField txtLevel293= textfield("1234567890",50, true, widthBy23*10, heightBy30*16, "textField1");
        canvas.getChildren().addAll(txtLevel211,txtLevel212,txtLevel213,txtLevel221,txtLevel222,txtLevel223,txtLevel231,
        		txtLevel232,txtLevel233,txtLevel241,txtLevel242,txtLevel243,txtLevel251,txtLevel252,txtLevel253,txtLevel261,
        		txtLevel262,txtLevel263,txtLevel271,txtLevel272,txtLevel273,txtLevel281,txtLevel282,txtLevel283,txtLevel291,
        		txtLevel292,txtLevel293);
        
        Label lblLevel10Two = label("level 10", 45, 15, widthBy23 *11, heightBy30*13, "label", "Level 10");
        TextField txtLevel2101= textfield("1234567890",50, true, widthBy23*11, heightBy30*14, "textField1");
        TextField txtLevel2102= textfield("1234567890",50, true, widthBy23*11, heightBy30*15, "textField1");
        TextField txtLevel2103= textfield("1234567890",50, true, widthBy23*11, heightBy30*16, "textField1");
        Label lblLevel11Two= label("level 11", 45, 15, widthBy23 *12, heightBy30*13, "label", "Level 11");
        TextField txtLevel2111= textfield("1234567890",50, true, widthBy23*12, heightBy30*14, "textField1");
        TextField txtLevel2112= textfield("1234567890",50, true, widthBy23*12, heightBy30*15, "textField1");
        TextField txtLevel2113= textfield("1234567890",50, true, widthBy23*12, heightBy30*16, "textField1");
        Label lblLevel12Two = label("level 12", 45, 15, widthBy23 *13, heightBy30*13, "label", "Level 12");
        TextField txtLevel2121= textfield("1234567890",50, true, widthBy23*13, heightBy30*14, "textField1");
        TextField txtLevel2122= textfield("1234567890",50, true, widthBy23*13, heightBy30*15, "textField1");
        TextField txtLevel2123= textfield("1234567890",50, true, widthBy23*13, heightBy30*16, "textField1");
        Label lblLevel13Two = label("level 13", 45, 15, widthBy23 *14, heightBy30*13, "label", "Level 13");
        TextField txtLevel2131= textfield("1234567890",50, true, widthBy23*14, heightBy30*14, "textField1");
        TextField txtLevel2132= textfield("1234567890",50, true, widthBy23*14, heightBy30*15, "textField1");
        TextField txtLevel2133= textfield("1234567890",50, true, widthBy23*14, heightBy30*16, "textField1");
        Label lblLevel14Two = label("level 14", 45, 15, widthBy23 *15, heightBy30*13, "label", "Level 14");
        TextField txtLevel2141= textfield("1234567890",50, true, widthBy23*15, heightBy30*14, "textField1");
        TextField txtLevel2142= textfield("1234567890",50, true, widthBy23*15, heightBy30*15, "textField1");
        TextField txtLevel2143= textfield("1234567890",50, true, widthBy23*15, heightBy30*16, "textField1");
        Label lblLevel15Two = label("level 15", 45, 15, widthBy23 *16, heightBy30*13, "label", "Level 15");
        TextField txtLevel2151= textfield("1234567890",50, true, widthBy23*16, heightBy30*14, "textField1");
        TextField txtLevel2152= textfield("1234567890",50, true, widthBy23*16, heightBy30*15, "textField1");
        TextField txtLevel2153= textfield("1234567890",50, true, widthBy23*16, heightBy30*16, "textField1");        
        Label lblLevel16Two = label("level 16", 45, 15, widthBy23 *17, heightBy30*13, "label", "Level 16");
        TextField txtLevel2161= textfield("1234567890",50, true, widthBy23*17, heightBy30*14, "textField1");
        TextField txtLevel2162= textfield("1234567890",50, true, widthBy23*17, heightBy30*15, "textField1");
        TextField txtLevel2163= textfield("1234567890",50, true, widthBy23*17, heightBy30*16, "textField1");
        Label lblLevel17Two = label("level 17", 45, 15, widthBy23 *18, heightBy30*13, "label", "Level 17");
        TextField txtLevel2171= textfield("1234567890",50, true, widthBy23*18, heightBy30*14, "textField1");
        TextField txtLevel2172= textfield("1234567890",50, true, widthBy23*18, heightBy30*15, "textField1");
        TextField txtLevel2173= textfield("1234567890",50, true, widthBy23*18, heightBy30*16, "textField1");
        Label lblLevel18Two = label("level 18", 45, 15, widthBy23 *19, heightBy30*13, "label", "Level 18");
        TextField txtLevel2181= textfield("1234567890",50, true, widthBy23*19, heightBy30*14, "textField1");
        TextField txtLevel2182= textfield("1234567890",50, true, widthBy23*19, heightBy30*15, "textField1");
        TextField txtLevel2183= textfield("1234567890",50, true, widthBy23*19, heightBy30*16, "textField1");
        Label lblLevel19Two = label("level 19", 45, 15, widthBy23 *20, heightBy30*13, "label", "Level 19");
        TextField txtLevel2191= textfield("1234567890",50, true, widthBy23*20, heightBy30*14, "textField1");
        TextField txtLevel2192= textfield("1234567890",50, true, widthBy23*20, heightBy30*15, "textField1");
        TextField txtLevel2193= textfield("1234567890",50, true, widthBy23*20, heightBy30*16, "textField1");
        Label lblLevel20Two = label("level 20", 45, 15, widthBy23 *21, heightBy30*13, "label", "Level 20"); 
        TextField txtLevel2201= textfield("1234567890",50, true, widthBy23*21, heightBy30*14, "textField1");
        TextField txtLevel2202= textfield("1234567890",50, true, widthBy23*21, heightBy30*15, "textField1");
        TextField txtLevel2203= textfield("1234567890",50, true, widthBy23*21, heightBy30*16, "textField1");
        canvas.getChildren().addAll(txtLevel2101,txtLevel2102,txtLevel2103,txtLevel2111,txtLevel2112,txtLevel2113,
        		txtLevel2121,txtLevel2122,txtLevel2123,txtLevel2131,txtLevel2132,txtLevel2133,txtLevel2141,
        		txtLevel2142,txtLevel2143,txtLevel2151,txtLevel2152,txtLevel2153,txtLevel2161,txtLevel2162,txtLevel2163,
        		txtLevel2171,txtLevel2172,txtLevel2173,txtLevel2181,txtLevel2182,txtLevel2183,txtLevel2191,txtLevel2192,
        		txtLevel2193,txtLevel2201,txtLevel2202,txtLevel2203);
        Button btnClearAll2Two = button("clear all", widthBy23, heightBy30*13, "functionalButton", true, "to copy");
        Label lblDurationSecTwo = label("duration sec.", 30, 15, widthBy23, heightBy30*14.75, "label", "Level 1");
        lblDurationSecTwo.setWrapText(true);
        lblDurationSecTwo.setMaxWidth(54);
        Label lblRampUnitTwo = label("ramp unit/sec.", 30, 15, widthBy23, heightBy30*15.75, "label", "Level 1");
        lblRampUnitTwo.setWrapText(true);
        lblRampUnitTwo.setMaxWidth(55);
        
        canvas.getChildren().addAll(btnClearAll2Two,lblDurationSecTwo,lblRampUnitTwo,lblLevel1Two,lblLevel2Two,lblLevel3Two,
        		lblLevel4Two,lblLevel5Two,lblLevel6Two,lblLevel7Two,lblLevel8Two,lblLevel9Two,lblLevel10Two,lblLevel11Two,
        		lblLevel12Two,lblLevel13Two,lblLevel14Two,lblLevel15Two,lblLevel16Two,lblLevel17Two,lblLevel18Two,
        		lblLevel19Two,lblLevel20Two);

       
        
        // 4 x 1.5
        // textField 4x1

        Button btnClearAll12 = button("clear all", widthBy23, heightBy30*19, "functionalButton", true, "to copy");
        Label lblDurationSec1 = label("duration sec.", 30, 15, widthBy23, heightBy30*20.75, "label", "Level 1");
        lblDurationSec1.setWrapText(true);
        lblDurationSec1.setMaxWidth(54);
        Label lblRampUnit1 = label("ramp unit/sec.", 30, 15, widthBy23, heightBy30*21.75, "label", "Level 1");
        lblRampUnit1.setWrapText(true);
        lblRampUnit1.setMaxWidth(55);
        Button btnFaultProfile = button("Fault profile", widthBy23 *2, heightBy30*18, "functionalButton", true, "to copy");
        Label lbl1 = label("level 1", 40, 15, widthBy23 *2, heightBy30*19, "label", "Level 1");
        TextField txt11= textfield("1234567890",50, true, widthBy23*2, heightBy30*20, "textField1");
        TextField txt12= textfield("1234567890",50, true, widthBy23*2, heightBy30*21, "textField1");
        TextField txt13= textfield("1234567890",50, true, widthBy23*2, heightBy30*22, "textField1");
        Label lbl2 = label("level 2", 45, 15, widthBy23 *3, heightBy30*19, "label", "Level 2");
        TextField txt21= textfield("1234567890",50, true, widthBy23*3, heightBy30*20, "textField1");
        TextField txt22= textfield("1234567890",50, true, widthBy23*3, heightBy30*21, "textField1");
        TextField txt23= textfield("1234567890",50, true, widthBy23*3, heightBy30*22, "textField1");

        
        Label lbl3 = label("level 3", 45, 15, widthBy23 *4, heightBy30*19, "label", "Level 3");
        TextField txt31= textfield("1234567890",50, true, widthBy23*4, heightBy30*20, "textField1");
        TextField txt32= textfield("1234567890",50, true, widthBy23*4, heightBy30*21, "textField1");
        TextField txt33= textfield("1234567890",50, true, widthBy23*4, heightBy30*22, "textField1");
        Label lblFaultReference = label("Fault Reference", 40, 15, widthBy23 *4, heightBy30*17.5, "label", "Level 1");
        lblFaultReference.setWrapText(true);
        lblFaultReference.setMaxWidth(65);
        Label lbl4 = label("level 4", 45, 15, widthBy23 *5, heightBy30*19, "label", "Level 4");
        TextField txt41= textfield("1234567890",50, true, widthBy23*5, heightBy30*20, "textField1");
        TextField txt42= textfield("1234567890",50, true, widthBy23*5, heightBy30*21, "textField1");
        TextField txt43= textfield("1234567890",50, true, widthBy23*5, heightBy30*22, "textField1");
        Label lbl5 = label("level 5", 45, 15, widthBy23 *6, heightBy30*19, "label", "Level 5");
        TextField txt51= textfield("1234567890",50, true, widthBy23*6, heightBy30*20, "textField1");
        TextField txt52= textfield("1234567890",50, true, widthBy23*6, heightBy30*21, "textField1");
        TextField txt53= textfield("1234567890",50, true, widthBy23*6, heightBy30*22, "textField1");
        Label lbl6 = label("level 6", 45, 15, widthBy23 *7, heightBy30*19, "label", "Level 6");
        TextField txt61= textfield("1234567890",50, true, widthBy23*7, heightBy30*20, "textField1");
        TextField txt62= textfield("1234567890",50, true, widthBy23*7, heightBy30*21, "textField1");
        TextField txt63= textfield("1234567890",50, true, widthBy23*7, heightBy30*22, "textField1");
        Label lbl7 = label("level 7", 45, 15, widthBy23 *8, heightBy30*19, "label", "Level 7");
        TextField txt71= textfield("1234567890",50, true, widthBy23*8, heightBy30*20, "textField1");
        TextField txt72= textfield("1234567890",50, true, widthBy23*8, heightBy30*21, "textField1");
        TextField txt73= textfield("1234567890",50, true, widthBy23*8, heightBy30*22, "textField1");
        Label lbl8 = label("level 8", 45, 15, widthBy23 *9, heightBy30*19, "label", "Level 8");
        TextField txt81= textfield("1234567890",50, true, widthBy23*9, heightBy30*20, "textField1");
        TextField txt82= textfield("1234567890",50, true, widthBy23*9, heightBy30*21, "textField1");
        TextField txt83= textfield("1234567890",50, true, widthBy23*9, heightBy30*22, "textField1");
        Label lbl9 = label("level 9", 45, 15, widthBy23 *10, heightBy30*19, "label", "Level 9");
        TextField txt91= textfield("1234567890",50, true, widthBy23*10, heightBy30*20, "textField1");
        TextField txt92= textfield("1234567890",50, true, widthBy23*10, heightBy30*21, "textField1");
        TextField txt93= textfield("1234567890",50, true, widthBy23*10, heightBy30*22, "textField1");
        Label lbl10 = label("level 10", 45, 15, widthBy23 *11, heightBy30*19, "label", "Level 10");
        TextField txt101= textfield("1234567890",50, true, widthBy23*11, heightBy30*20, "textField1");
        TextField txt102= textfield("1234567890",50, true, widthBy23*11, heightBy30*21, "textField1");
        TextField txt103= textfield("1234567890",50, true, widthBy23*11, heightBy30*22, "textField1");
        Label lbl11= label("level 11", 45, 15, widthBy23 *12, heightBy30*19, "label", "Level 11");
        TextField txt111= textfield("1234567890",50, true, widthBy23*12, heightBy30*20, "textField1");
        TextField txt112= textfield("1234567890",50, true, widthBy23*12, heightBy30*21, "textField1");
        TextField txt113= textfield("1234567890",50, true, widthBy23*12, heightBy30*22, "textField1");
        Label lbl12 = label("level 12", 45, 15, widthBy23 *13, heightBy30*19, "label", "Level 12");
        TextField txt121= textfield("1234567890",50, true, widthBy23*13, heightBy30*20, "textField1");
        TextField txt122= textfield("1234567890",50, true, widthBy23*13, heightBy30*21, "textField1");
        TextField txt123= textfield("1234567890",50, true, widthBy23*13, heightBy30*22, "textField1");
        Label lbl13 = label("level 13", 45, 15, widthBy23 *14, heightBy30*19, "label", "Level 13");
        TextField txt131= textfield("1234567890",50, true, widthBy23*14, heightBy30*20, "textField1");
        TextField txt132= textfield("1234567890",50, true, widthBy23*14, heightBy30*21, "textField1");
        TextField txt133= textfield("1234567890",50, true, widthBy23*14, heightBy30*22, "textField1");
        Label lbl14 = label("level 14", 45, 15, widthBy23 *15, heightBy30*19, "label", "Level 14");
        TextField txt141= textfield("1234567890",50, true, widthBy23*15, heightBy30*20, "textField1");
        TextField txt142= textfield("1234567890",50, true, widthBy23*15, heightBy30*21, "textField1");
        TextField txt143= textfield("1234567890",50, true, widthBy23*15, heightBy30*22, "textField1");
        Label lbl15 = label("level 15", 45, 15, widthBy23 *16, heightBy30*19, "label", "Level 15");
        TextField txt151= textfield("1234567890",50, true, widthBy23*16, heightBy30*20, "textField1");
        TextField txt152= textfield("1234567890",50, true, widthBy23*16, heightBy30*21, "textField1");
        TextField txt153= textfield("1234567890",50, true, widthBy23*16, heightBy30*22, "textField1");
        Label lbl16 = label("level 16", 45, 15, widthBy23 *17, heightBy30*19, "label", "Level 16");
        TextField txt161= textfield("1234567890",50, true, widthBy23*17, heightBy30*20, "textField1");
        TextField txt162= textfield("1234567890",50, true, widthBy23*17, heightBy30*21, "textField1");
        TextField txt163= textfield("1234567890",50, true, widthBy23*17, heightBy30*22, "textField1");
        Label lbl17 = label("level 17", 45, 15, widthBy23 *18, heightBy30*19, "label", "Level 17");
        TextField txt171= textfield("1234567890",50, true, widthBy23*18, heightBy30*20, "textField1");
        TextField txt172= textfield("1234567890",50, true, widthBy23*18, heightBy30*21, "textField1");
        TextField txt173= textfield("1234567890",50, true, widthBy23*18, heightBy30*22, "textField1");
        Label lbl18 = label("level 18", 45, 15, widthBy23 *19, heightBy30*19, "label", "Level 18");
        TextField txt181= textfield("1234567890",50, true, widthBy23*19, heightBy30*20, "textField1");
        TextField txt182= textfield("1234567890",50, true, widthBy23*19, heightBy30*21, "textField1");
        TextField txt183= textfield("1234567890",50, true, widthBy23*19, heightBy30*22, "textField1");
        Label lbl19 = label("level 19", 45, 15, widthBy23 *20, heightBy30*19, "label", "Level 19");
        TextField txt191= textfield("1234567890",50, true, widthBy23*20, heightBy30*20, "textField1");
        TextField txt192= textfield("1234567890",50, true, widthBy23*20, heightBy30*21, "textField1");
        TextField txt193= textfield("1234567890",50, true, widthBy23*20, heightBy30*22, "textField1");
        Label lbl20 = label("level 20", 45, 15, widthBy23 *21, heightBy30*19, "label", "Level 20");
        TextField txt201= textfield("1234567890",50, true, widthBy23*21, heightBy30*20, "textField1");
        TextField txt202= textfield("1234567890",50, true, widthBy23*21, heightBy30*21, "textField1");
        TextField txt203= textfield("1234567890",50, true, widthBy23*21, heightBy30*22, "textField1");


        
        Label lbl1Two = label("level 1", 40, 15, widthBy23 *2, heightBy30*23, "label", "Level 1");
        TextField txt211= textfield("1234567890",50, true, widthBy23*2, heightBy30*24, "textField1");
        TextField txt212= textfield("1234567890",50, true, widthBy23*2, heightBy30*25, "textField1");
        TextField txt213= textfield("1234567890",50, true, widthBy23*2, heightBy30*26, "textField1");

        Label lbl2Two = label("level 2", 45, 15, widthBy23 *3, heightBy30*23, "label", "Level 2");
        TextField txt221= textfield("1234567890",50, true, widthBy23*3, heightBy30*24, "textField1");
        TextField txt222= textfield("1234567890",50, true, widthBy23*3, heightBy30*25, "textField1");
        TextField txt223= textfield("1234567890",50, true, widthBy23*3, heightBy30*26, "textField1");
        Label lbl3Two = label("level 3", 45, 15, widthBy23 *4, heightBy30*23, "label", "Level 3");
        TextField txt231= textfield("1234567890",50, true, widthBy23*4, heightBy30*24, "textField1");
        TextField txt232= textfield("1234567890",50, true, widthBy23*4, heightBy30*25, "textField1");
        TextField txt233= textfield("1234567890",50, true, widthBy23*4, heightBy30*26, "textField1");
        Label lbl4Two = label("level 4", 45, 15, widthBy23 *5, heightBy30*23, "label", "Level 4");
        TextField txt241= textfield("1234567890",50, true, widthBy23*5, heightBy30*24, "textField1");
        TextField txt242= textfield("1234567890",50, true, widthBy23*5, heightBy30*25, "textField1");
        TextField txt243= textfield("1234567890",50, true, widthBy23*5, heightBy30*26, "textField1");
        Label lbl5Two = label("level 5", 45, 15, widthBy23 *6, heightBy30*23, "label", "Level 5");
        TextField txt251= textfield("1234567890",50, true, widthBy23*6, heightBy30*24, "textField1");
        TextField txt252= textfield("1234567890",50, true, widthBy23*6, heightBy30*25, "textField1");
        TextField txt253= textfield("1234567890",50, true, widthBy23*6, heightBy30*26, "textField1");
        Label lbl6Two = label("level 6", 45, 15, widthBy23 *7, heightBy30*23, "label", "Level 6");
        TextField txt261= textfield("1234567890",50, true, widthBy23*7, heightBy30*24, "textField1");
        TextField txt262= textfield("1234567890",50, true, widthBy23*7, heightBy30*25, "textField1");
        TextField txt263= textfield("1234567890",50, true, widthBy23*7, heightBy30*26, "textField1");
        Label lbl7Two = label("level 7", 45, 15, widthBy23 *8, heightBy30*23, "label", "Level 7");
        TextField txt271= textfield("1234567890",50, true, widthBy23*8, heightBy30*24, "textField1");
        TextField txt272= textfield("1234567890",50, true, widthBy23*8, heightBy30*25, "textField1");
        TextField txt273= textfield("1234567890",50, true, widthBy23*8, heightBy30*26, "textField1");
        Label lbl8Two = label("level 8", 45, 15, widthBy23 *9, heightBy30*23, "label", "Level 8");
        TextField txt281= textfield("1234567890",50, true, widthBy23*9, heightBy30*24, "textField1");
        TextField txt282= textfield("1234567890",50, true, widthBy23*9, heightBy30*25, "textField1");
        TextField txt283= textfield("1234567890",50, true, widthBy23*9, heightBy30*26, "textField1");
        Label lbl9Two = label("level 9", 45, 15, widthBy23 *10, heightBy30*23, "label", "Level 9");
        TextField txt291= textfield("1234567890",50, true, widthBy23*10, heightBy30*24, "textField1");
        TextField txt292= textfield("1234567890",50, true, widthBy23*10, heightBy30*25, "textField1");
        TextField txt293= textfield("1234567890",50, true, widthBy23*10, heightBy30*26, "textField1");
        canvas.getChildren().addAll(lblFaultReference,btnClearAll12,lblDurationSec1,lblRampUnit1,btnFaultProfile,lbl1,txt11,txt12,txt13,
        		lbl2,txt21,txt22,txt23,lbl3,txt31,txt32,txt33,lbl4,txt41,txt42,txt43,lbl5,txt51,txt52,txt53,lbl6,txt61,txt62,
        		txt63,lbl7,txt71,txt72,txt73,lbl8,txt81,txt82,txt83,lbl9,txt91,txt92,txt93,lbl10,txt101,txt102,txt103,lbl11,txt111,
        		txt112,txt113,lbl12,txt121,txt122,txt123,lbl13,txt131,txt132,txt133,lbl14,txt141,txt142,txt143,lbl15,txt151,txt152,txt153,
        		lbl16,txt161,txt162,txt163,lbl17,txt171,txt172,txt173,lbl18,txt181,txt182,txt183,lbl19,txt191,txt192,txt193,
        		lbl20,txt201,txt202,txt203,lbl1Two,txt211,txt212,txt213,lbl2Two,txt221,txt222,txt223,lbl3Two,txt231,txt232,txt233,lbl4Two,txt241,
        		txt242,txt243,lbl5Two,txt251,txt252,txt253,lbl6Two,txt261,txt262,txt263,lbl7Two,txt271,txt272,txt273,lbl8Two,txt281,
        		txt282,txt283,lbl9Two,txt291,txt292,txt293);  
        Label lbl10Two = label("level 10", 45, 15, widthBy23 *11, heightBy30*23, "label", "Level 10");
        TextField txt2101= textfield("1234567890",50, true, widthBy23*11, heightBy30*24, "textField1");
        TextField txt2102= textfield("1234567890",50, true, widthBy23*11, heightBy30*25, "textField1");
        TextField txt2103= textfield("1234567890",50, true, widthBy23*11, heightBy30*26, "textField1");
        Label lbl11Two= label("level 11", 45, 15, widthBy23 *12, heightBy30*23, "label", "Level 11");
        TextField txt2111= textfield("1234567890",50, true, widthBy23*12, heightBy30*24, "textField1");
        TextField txt2112= textfield("1234567890",50, true, widthBy23*12, heightBy30*25, "textField1");
        TextField txt2113= textfield("1234567890",50, true, widthBy23*12, heightBy30*26, "textField1");
        Label lbl12Two = label("level 12", 45, 15, widthBy23 *13, heightBy30*23, "label", "Level 12");
        TextField txt2121= textfield("1234567890",50, true, widthBy23*13, heightBy30*24, "textField1");
        TextField txt2122= textfield("1234567890",50, true, widthBy23*13, heightBy30*25, "textField1");
        TextField txt2123= textfield("1234567890",50, true, widthBy23*13, heightBy30*26, "textField1");
        Label lbl13Two = label("level 13", 45, 15, widthBy23 *14, heightBy30*23, "label", "Level 13");
        TextField txt2131= textfield("1234567890",50, true, widthBy23*14, heightBy30*24, "textField1");
        TextField txt2132= textfield("1234567890",50, true, widthBy23*14, heightBy30*25, "textField1");
        TextField txt2133= textfield("1234567890",50, true, widthBy23*14, heightBy30*26, "textField1");
        Label lbl14Two = label("level 14", 45, 15, widthBy23 *15, heightBy30*23, "label", "Level 14");
        TextField txt2141= textfield("1234567890",50, true, widthBy23*15, heightBy30*24, "textField1");
        TextField txt2142= textfield("1234567890",50, true, widthBy23*15, heightBy30*25, "textField1");
        TextField txt2143= textfield("1234567890",50, true, widthBy23*15, heightBy30*26, "textField1");
        Label lbl15Two = label("level 15", 45, 15, widthBy23 *16, heightBy30*23, "label", "Level 15");
        TextField txt2151= textfield("1234567890",50, true, widthBy23*16, heightBy30*24, "textField1");
        TextField txt2152= textfield("1234567890",50, true, widthBy23*16, heightBy30*25, "textField1");
        TextField txt2153= textfield("1234567890",50, true, widthBy23*16, heightBy30*26, "textField1");        
        Label lbl16Two = label("level 16", 45, 15, widthBy23 *17, heightBy30*23, "label", "Level 16");
        TextField txt2161= textfield("1234567890",50, true, widthBy23*17, heightBy30*24, "textField1");
        TextField txt2162= textfield("1234567890",50, true, widthBy23*17, heightBy30*25, "textField1");
        TextField txt2163= textfield("1234567890",50, true, widthBy23*17, heightBy30*26, "textField1");
        Label lbl17Two = label("level 17", 45, 15, widthBy23 *18, heightBy30*23, "label", "Level 17");
        TextField txt2171= textfield("1234567890",50, true, widthBy23*18, heightBy30*24, "textField1");
        TextField txt2172= textfield("1234567890",50, true, widthBy23*18, heightBy30*25, "textField1");
        TextField txt2173= textfield("1234567890",50, true, widthBy23*18, heightBy30*26, "textField1");
        Label lbl18Two = label("level 18", 45, 15, widthBy23 *19, heightBy30*23, "label", "Level 18");
        TextField txt2181= textfield("1234567890",50, true, widthBy23*19, heightBy30*24, "textField1");
        TextField txt2182= textfield("1234567890",50, true, widthBy23*19, heightBy30*25, "textField1");
        TextField txt2183= textfield("1234567890",50, true, widthBy23*19, heightBy30*26, "textField1");
        Label lbl19Two = label("level 19", 45, 15, widthBy23 *20, heightBy30*23, "label", "Level 19");
        TextField txt2191= textfield("1234567890",50, true, widthBy23*20, heightBy30*24, "textField1");
        TextField txt2192= textfield("1234567890",50, true, widthBy23*20, heightBy30*25, "textField1");
        TextField txt2193= textfield("1234567890",50, true, widthBy23*20, heightBy30*26, "textField1");
        

        
        Label lbl20Two = label("level 20", 45, 15, widthBy23 *21, heightBy30*23, "label", "Level 20"); 
        TextField txt2201= textfield("1234567890",50, true, widthBy23*21, heightBy30*24, "textField1");
        TextField txt2202= textfield("1234567890",50, true, widthBy23*21, heightBy30*25, "textField1");
        TextField txt2203= textfield("1234567890",50, true, widthBy23*21, heightBy30*26, "textField1");
        
        Button btnClearAll2Two4 = button("clear all", widthBy23, heightBy30*23, "functionalButton", true, "to copy");
        Label lblDurationSecTwo4 = label("duration sec.", 30, 15, widthBy23, heightBy30*24.75, "label", "Duration seconds.");
        lblDurationSecTwo4.setWrapText(true);
        lblDurationSecTwo4.setMaxWidth(54);
        Label lblRampUnitTwo4 = label("ramp unit/sec.", 30, 15, widthBy23, heightBy30*25.75, "label", "ramp unit/sec");
        lblRampUnitTwo4.setWrapText(true);
        lblRampUnitTwo4.setMaxWidth(55);
        Label lblKPFTriggerinDIP = label("KPF Trigger in DIP", 30, 15, widthBy23, heightBy30*27.25, "labelYellow", "KPF Trigger in DIP");
        lblKPFTriggerinDIP.setWrapText(true);
        lblKPFTriggerinDIP.setMaxWidth(70);
        
        
        Label KPF1 = label("KPF 1", 45, 15, widthBy23 *2.5, heightBy30*26.75, "label", "KPF 1");
        TextField txtKPF1= textfield("1234567890",50, true, widthBy23*2.5, heightBy30*27.5, "textField1");
        Label KPF2 = label("KPF 2", 45, 15, widthBy23 *3.5, heightBy30*26.75, "label", "KPF2");
        TextField txtKPF2= textfield("1234567890",50, true, widthBy23*3.5, heightBy30*27.5, "textField1");
        Label KPF3 = label("KPF 3", 45, 15, widthBy23 *4.5, heightBy30*26.75, "label", "KPF 3");
        TextField txtKPF3= textfield("1234567890",50, true, widthBy23*4.5, heightBy30*27.5, "textField1");
        Label KPF4 = label("KPF 4", 45, 15, widthBy23 *5.5, heightBy30*26.75, "label", "KPF 4");
        TextField txtKPF4= textfield("1234567890",50, true, widthBy23*5.5, heightBy30*27.5, "textField1");
        Label KPF5 = label("KPF 5", 45, 15, widthBy23 *6.5, heightBy30*26.75, "label", "KPF 5");
        TextField txtKPF5= textfield("1234567890",50, true, widthBy23*6.5, heightBy30*27.5, "textField1");
       
        Button KPFSubmit = button("Submit", widthBy23 *7.5, heightBy30*27, "functionalButton", true, "to copy");
        KPFSubmit.setOnAction(e ->{
        	//Aarons graph
        	List<Double> list = new ArrayList<Double>();
        	list.add(Double.valueOf(txtLevel11.getText()));
        	list.add(Double.valueOf(txtLevel12.getText()));
        	list.add(Double.valueOf(txtLevel13.getText()));
        	list.add(Double.valueOf(txtLevel21.getText()));
        	list.add(Double.valueOf(txtLevel22.getText()));
        	list.add(Double.valueOf(txtLevel23.getText()));
        	
        	list.add(Double.valueOf(txtLevel31.getText()));
        	list.add(Double.valueOf(txtLevel32.getText()));
        	list.add(Double.valueOf(txtLevel33.getText()));
        	list.add(Double.valueOf(txtLevel41.getText()));
        	list.add(Double.valueOf(txtLevel42.getText()));
        	list.add(Double.valueOf(txtLevel43.getText()));
        	
        	list.add(Double.valueOf(txtLevel51.getText()));
        	list.add(Double.valueOf(txtLevel52.getText()));
        	list.add(Double.valueOf(txtLevel53.getText()));
//        	list.add(Double.valueOf(txtLevel61.getText()));
//        	list.add(Double.valueOf(txtLevel62.getText()));
//        	list.add(Double.valueOf(txtLevel63.getText()));
//        	
//        	list.add(Double.valueOf(txtLevel71.getText()));
//        	list.add(Double.valueOf(txtLevel72.getText()));
//        	list.add(Double.valueOf(txtLevel73.getText()));
//        	list.add(Double.valueOf(txtLevel81.getText()));
//        	list.add(Double.valueOf(txtLevel82.getText()));
//        	list.add(Double.valueOf(txtLevel83.getText()));
//        	
//        	list.add(Double.valueOf(txtLevel91.getText()));
//        	list.add(Double.valueOf(txtLevel92.getText()));
//        	list.add(Double.valueOf(txtLevel93.getText()));
//        	list.add(Double.valueOf(txtLevel101.getText()));
//        	list.add(Double.valueOf(txtLevel102.getText()));
//        	list.add(Double.valueOf(txtLevel103.getText()));
//        	
        	List<Double> list1 = new ArrayList<Double>();
        	list1.add(Double.valueOf(txtKPF1.getText()));
        	list1.add(Double.valueOf(txtKPF2.getText()));
        	
        	System.out.println(list.size());
        	System.out.println(list1.size());
        	
        	//Chart2.chartItUp(list, list1);
        	
        });
        
        canvas.getChildren().addAll(KPFSubmit,lbl10Two,txt2101,txt2102,txt2103,lbl11Two,txt2111,txt2112,txt2113,lbl12Two,txt2121,txt2122,
        		txt2123,lbl13Two,txt2131,txt2132,txt2133,lbl14Two,txt2141,txt2142,txt2143,lbl15Two,txt2151,txt2152,txt2153,lbl16Two,
        		txt2161,txt2162,txt2163,lbl17Two,txt2171,txt2172,txt2173,lbl18Two,txt2181,txt2182,txt2183,lbl19Two,txt2191,txt2192,
        		txt2193,lbl20Two,txt2201,txt2202,txt2203,btnClearAll2Two4,lblRampUnitTwo4,lblDurationSecTwo4,lblKPFTriggerinDIP
        		,KPF1,txtKPF1,KPF2,txtKPF2,KPF3,txtKPF3,KPF4,txtKPF4,KPF5,txtKPF5);
        
        group.getChildren().add(canvas);
        System.out.println(screenWidth);
        System.out.println(screenHeight);
        // create scene which can be dragged and zoomed
        Scene scene = new Scene(group, 1440,1100);
        SceneGestures sceneGestures = new SceneGestures(canvas);
        scene.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        scene.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        scene.addEventFilter( ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());

        scene.getStylesheets().add(ZoomRevisionLog.class.getResource("application.css").toExternalForm());
        //scene.getStylesheets().add("application.css");
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
 
        stage.setScene(scene);
        stage.show();        
    }
   


    
    /**
     * 
     * @param text The text on the button
     * @param positionX The position of the button on the x-axis
     * @param positionY The position of the button on the y-axis
     * @param style The CSS style for the button
     * @param shadow Determines if there is a shadow when the mouse is over the button
     * @param wrap The max width of the button (used for wrapping the text)
     * @return This returns a button
     */
    public static Button button(String text, double positionX, double positionY, String style, Boolean shadow, double wrap) {

        Button button1 = new Button();
        button1.setText(text);
        button1.setLayoutX(positionX);
        button1.setLayoutY(positionY);
        button1.setId(style);
        button1.setAlignment(Pos.BOTTOM_CENTER);

        if(shadow == true) {
            DropShadow shadowDrop = new DropShadow();
            // Adding the shadow when the mouse cursor is on
            button1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                button1.setEffect(shadowDrop);
            });
            //Removing the shadow when the mouse cursor is off
            button1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                button1.setEffect(null);
            });
        }
        button1.setMaxWidth(wrap);
        button1.setWrapText(true);
        
     
        return button1;
    }
    
    public static Button button(String text, double positionX, double positionY, String style, Boolean shadow, double wrap, String tip) {

        Button button1 = new Button();
        button1.setText(text);
        button1.setLayoutX(positionX);
        button1.setLayoutY(positionY);
        button1.setId(style);
        button1.setAlignment(Pos.BOTTOM_CENTER);

        if(shadow == true) {
            DropShadow shadowDrop = new DropShadow();
            // Adding the shadow when the mouse cursor is on
            button1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                button1.setEffect(shadowDrop);
            });
            //Removing the shadow when the mouse cursor is off
            button1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                button1.setEffect(null);
            });
        }
        button1.setMaxWidth(wrap);
        button1.setWrapText(true);
        button1.setTooltip(new Tooltip(tip));
        
     
        return button1;
    }
        
        /**
         * 
         * @param text The text on the button
         * @param positionX The position of the button on the x-axis
         * @param positionY The position of the button on the y-axis
         * @param style The CSS style for the button
         * @param shadow Determines if there is a shadow when the mouse is over the button
         * @return This returns a button
         */
        public static Button button(String text, double positionX, double positionY, String style, Boolean shadow) {

        Button button1 = new Button();
        button1.setText(text);
        button1.setLayoutX(positionX);
        button1.setLayoutY(positionY);
        
        button1.setId(style);
        button1.setAlignment(Pos.CENTER);

        if(shadow == true) {
            DropShadow shadowDrop = new DropShadow();
            // Adding the shadow when the mouse cursor is on
            button1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                button1.setEffect(shadowDrop);
            });
            //Removing the shadow when the mouse cursor is off
            button1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                button1.setEffect(null);
            });
        }
        return button1;
        }
        
        public static Button button(String text, double positionX, double positionY, String style, Boolean shadow, String tipText ){

        Button button1 = new Button();
        button1.setText(text);
        button1.setLayoutX(positionX);
        button1.setLayoutY(positionY);
        button1.setId(style);
        button1.setAlignment(Pos.CENTER);
        Tooltip tip = new Tooltip();
        tip.setText(tipText);
        tip.setId("tooltip");
        button1.setTooltip(tip);

        if(shadow == true) {
            DropShadow shadowDrop = new DropShadow();
            // Adding the shadow when the mouse cursor is on
            button1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                button1.setEffect(shadowDrop);
            });
            //Removing the shadow when the mouse cursor is off
            button1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                button1.setEffect(null);
            });
        }
        return button1;
        }
        /**
         * 
         * @param text The text on the label.
         * @param boxWidth The width of the label
         * @param boxHeight The height of the label
         * @param x The x position of the label
         * @param y The y position of the label
         * @param style The style used for this certain label
         * @return Returns a label
         */
        public static Label label(String text, int boxWidth, int boxHeight, double x, double y, String style) {
           
            Label label1 = new Label();
            label1.setText(text);
            label1.setAlignment(Pos.CENTER);
            label1.setMinWidth(boxWidth);
            label1.setMinHeight(boxHeight);
            label1.setLayoutX(x);
            label1.setLayoutY(y);
            label1.setId(style);
                    
            return label1;
        }
      
        
        /**
         * 
         * @param text The text on the label.
         * @param boxWidth The width of the label
         * @param boxHeight The height of the label
         * @param x The x position of the label
         * @param y The y position of the label
         * @param style The style used for this certain label
         * @return Returns a label
         */
        public static Label label(String text, int boxWidth, int boxHeight, int x, double y, String style) {
           
            Label label1 = new Label();
            label1.setText(text);
            label1.setAlignment(Pos.CENTER);
            label1.setMinWidth(boxWidth);
            label1.setMinHeight(boxHeight);
            label1.setLayoutX(x);
            label1.setLayoutY(y);
            label1.setId(style);
                    
            return label1;
        }
        
        public static Label label(String text, int boxWidth, int boxHeight, int x, double y, String style, String tip) {
           
            Label label1 = new Label();
            label1.setText(text);
            label1.setAlignment(Pos.CENTER);
            label1.setMinWidth(boxWidth);
            label1.setMinHeight(boxHeight);
            label1.setLayoutX(x);
            label1.setLayoutY(y);
            label1.setId(style);
            label1.setTooltip(new Tooltip(tip));
                    
            return label1;
        }
        public static Label label(String text, int boxWidth, int boxHeight, double x, double y, String style, String tip) {
            
            Label label1 = new Label();
            label1.setText(text);
            label1.setAlignment(Pos.CENTER);
            label1.setMinWidth(boxWidth);
            label1.setMinHeight(boxHeight);
            label1.setLayoutX(x);
            label1.setLayoutY(y);
            label1.setId(style);
            label1.setTooltip(new Tooltip(tip));
                    
            return label1;
        }
            /**
             * 
             * @param text The text on the label.
             * @param boxWidth The width of the label
             * @param boxHeight The height of the label
             * @param style The style used for this certain label
             * @param def The definition of the label
             * @return Returns a label
         */
        public static Label label(String text, int boxWidth, int boxHeight, String style, Label def) {
            DropShadow shadowDrop = new DropShadow();
            Label label1 = new Label();
            label1.setText(text);
            label1.setAlignment(Pos.CENTER);
            label1.setMinWidth(boxWidth);
            label1.setMinHeight(boxHeight);
            label1.setId(style);
            /**
             * On and off mouse definition functionality
             */
            label1.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> { // Displaying a shadow effect and the definition when the mouse cursor is on
                def.setVisible(true);
                label1.setEffect(shadowDrop);
            });
            label1.addEventHandler(MouseEvent.MOUSE_EXITED, e -> { // Removing the shadow effect and hiding the definition when the mouse cursor is off
                def.setVisible(false);
                label1.setEffect(null);
            });        
            return label1;
        }
        /**
         * 
         * @param text The text on the label.
         * @param boxWidth The width of the label
         * @param boxHeight The height of the label
         * @param style The style used for this certain label
         * @return Returns a label
         */
        public static Label label(String text, int boxWidth, int boxHeight, String style) {

            Label label1 = new Label();
            label1.setText(text);
            label1.setAlignment(Pos.CENTER);
            label1.setMinWidth(boxWidth);
            label1.setMinHeight(boxHeight);
            label1.setId(style);
           
            return label1;
        }
            /**
         * 
         * @param text The text on the label.
         * @param style The style used for this certain label
         * @return Returns a label
         */
        public static Label label(String text, String style) {
           
            Label label1 = new Label();
            label1.setText(text);
            label1.setAlignment(Pos.CENTER);
            label1.setId(style);
                    
            return label1;
        }
    
        
    @Override
    public void start(Stage stage) {
        trying(stage);

    }
    /**
     * 
     * @param text The prompt text set in the text field
     * @param textWidth The width of the text field
     * @param booll Determines if the text field is editable
     * @param positionX The position of the text field on the x-axis
     * @param positionY The position of the text field on the y-axis
     * @param style The CSS style associated with the text field
     * @return This returns a text field
     */
    public static TextField textfield(String text, int textWidth, Boolean booll, double positionX, double positionY, String style) {
	TextField textfield1 = new TextField();
        textfield1.setPromptText(text);
        textfield1.setAlignment(Pos.CENTER);
        textfield1.setPrefWidth(textWidth);
        textfield1.setPrefHeight(20.0);
        textfield1.setEditable(booll);
       // eventually might need to add prefHeight
        textfield1.setLayoutX(positionX);
        textfield1.setLayoutY(positionY);
        textfield1.setId(style);
	    
        return textfield1;
    }
    
    /**
     * 
     * @param text The prompt text set in the text area
     * @param textWidth The width of the text area
     * @param positionX The position of the text area on the x-axis
     * @param positionY The position of the text area on the y-axis
     * @param style The CSS style associated with the text area
     * @return This returns a text area
     */
    public static TextArea textarea(String text, int textWidth, int positionX, double positionY, String style){
        
        TextArea textarea1 = new TextArea();
        
        textarea1.setPromptText(text);
        textarea1.setPrefWidth(textWidth);
        textarea1.setLayoutX(positionX);
        textarea1.setLayoutY(positionY);
        textarea1.setId(style);
        
        
        return textarea1;
    }
    /**
     * takes in the x,y,x1,y1 coordinates in pixels of a specific line 
    * and returns the line
     * @param x1 The starting point on the x-axis
     * @param y1 The starting point on the y-axis
     * @param x2 The ending point on the x-axis
     * @param y2 The ending point on the y-axis
     * @return This returns a black line with the given coordinates from the parameters
     */
    public static Line LineBlackNoFill(double x1, int y1, double x2, int y2) {

        Line line = new Line(x1, y1, x2, y2); 
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1.0); 
        line.getStrokeDashArray().addAll(10.0,0.0,10.0,0.0);

        return line;
    }
 
}
class PannableCanvas extends Pane {

	DoubleProperty myScale = new SimpleDoubleProperty(1.0);

    public PannableCanvas() {
        // add scale transform
        scaleXProperty().bind(myScale);
        scaleYProperty().bind(myScale);
    }

    /**
     * Add a grid to the canvas, send it to back
     */
    public void addGrid() {

    	double w = getBoundsInLocal().getWidth();
        double h = getBoundsInLocal().getHeight();
        // add grid
        Canvas grid = new Canvas(w, h);

        // don't catch mouse events
        grid.setMouseTransparent(true);

        GraphicsContext gc = grid.getGraphicsContext2D();

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);

        // draw grid lines
        double offset = 50;
        for( double i=offset; i < w; i+=offset) {
            gc.strokeLine( i, 0, i, h);
            gc.strokeLine( 0, i, w, i);
        }

        getChildren().add( grid);

        grid.toBack();
    }

    public double getScale() {
        return myScale.get();
    }

    public void setScale( double scale) {
        myScale.set(scale);
    }

    public void setPivot( double x, double y) {
        setTranslateX(getTranslateX()-x);
        setTranslateY(getTranslateY()-y);
    }
}


/**
 * Mouse drag context used for scene and nodes.
 */
class DragContext {

    double mouseAnchorX;
    double mouseAnchorY;

    double translateAnchorX;
    double translateAnchorY;

}

/**
 * Listeners for making the nodes draggable via left mouse button. Considers if parent is zoomed.
 */
class NodeGestures {

    private DragContext nodeDragContext = new DragContext();

    PannableCanvas canvas;

    public NodeGestures( PannableCanvas canvas) {
        this.canvas = canvas;

    }

    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {

            // left mouse button => dragging
            if( !event.isPrimaryButtonDown())
                return;

            nodeDragContext.mouseAnchorX = event.getSceneX();
            nodeDragContext.mouseAnchorY = event.getSceneY();

            Node node = (Node) event.getSource();

            nodeDragContext.translateAnchorX = node.getTranslateX();
            nodeDragContext.translateAnchorY = node.getTranslateY();

        }

    };

    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {

            // left mouse button => dragging
            if( !event.isPrimaryButtonDown())
                return;

            double scale = canvas.getScale();

            Node node = (Node) event.getSource();

            node.setTranslateX(nodeDragContext.translateAnchorX + (( event.getSceneX() - nodeDragContext.mouseAnchorX) / scale));
            node.setTranslateY(nodeDragContext.translateAnchorY + (( event.getSceneY() - nodeDragContext.mouseAnchorY) / scale));

            event.consume();

        }
    };
}

/**
 * Listeners for making the scene's canvas draggable and zoomable
 */
class SceneGestures {

    private static final double MAX_SCALE = 5.0d;
    private static final double MIN_SCALE = 0.66d;

    private DragContext sceneDragContext = new DragContext();

    PannableCanvas canvas;

    public SceneGestures( PannableCanvas canvas) {
        this.canvas = canvas;
    }

    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    public EventHandler<ScrollEvent> getOnScrollEventHandler() {
        return onScrollEventHandler;
    }

    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {

            // right mouse button => panning
            if( !event.isSecondaryButtonDown())
                return;

            sceneDragContext.mouseAnchorX = event.getSceneX();
            sceneDragContext.mouseAnchorY = event.getSceneY();

            sceneDragContext.translateAnchorX = canvas.getTranslateX();
            sceneDragContext.translateAnchorY = canvas.getTranslateY();

        }

    };

    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {

            // right mouse button => panning
            if( !event.isSecondaryButtonDown())
                return;

            canvas.setTranslateX(sceneDragContext.translateAnchorX + event.getSceneX() - sceneDragContext.mouseAnchorX);
            canvas.setTranslateY(sceneDragContext.translateAnchorY + event.getSceneY() - sceneDragContext.mouseAnchorY);

            event.consume();
        }
    };

    /**
     * Mouse wheel handler: zoom to pivot point
     */
    private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {

        @Override
        public void handle(ScrollEvent event) {

            double delta = 1.2;

            double scale = canvas.getScale(); // currently we only use Y, same value is used for X
            double oldScale = scale;

            if (event.getDeltaY() < 0)
                scale /= delta;
            else
                scale *= delta;

            scale = clamp( scale, MIN_SCALE, MAX_SCALE);

            double f = (scale / oldScale)-1;

            double dx = (event.getSceneX() - (canvas.getBoundsInParent().getWidth()/2 + canvas.getBoundsInParent().getMinX()));
            double dy = (event.getSceneY() - (canvas.getBoundsInParent().getHeight()/2 + canvas.getBoundsInParent().getMinY()));

            canvas.setScale( scale);

            // note: pivot value must be untransformed, i. e. without scaling
            canvas.setPivot(f*dx, f*dy);

            event.consume();

        }

    };
    public static double clamp( double value, double min, double max) {

        if( Double.compare(value, min) < 0)
            return min;

        if( Double.compare(value, max) > 0)
            return max;

        return value;
    }

    
}


