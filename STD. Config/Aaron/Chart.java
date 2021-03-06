package application;
	
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

public class Chart extends Application {
	
	static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	
    static int screenWidth = (int) primaryScreenBounds.getWidth();
    static int screenHeight = (int) primaryScreenBounds.getHeight();
    static int fieldWidthAlignment = screenWidth/45;
    static int proportionalHeight = screenHeight/10;
    static double proportionalWidth = screenWidth/16;
    
    //private Line valueMarker = new Line();
    //private static LineChart<Number, Number> chart;
    private static XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private static NumberAxis xAxis;
    private static NumberAxis yAxis;
    private static double xShift;
    private static double yShift;
    private static Node chartArea;
    
    private static int numOfKPFs = 0;
    
    static Group root;

	@Override
	public void start(Stage stage) throws Exception {
		
		root = new Group();

		int screenProportionHeightby25 = screenHeight/25;   

		Line line11 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenProportionHeightby25,screenHeight-80);
        Line line2 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenProportionHeightby25);     
        Line line3 = LineBlackNoFill(screenWidth-screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenHeight-80);      
        Line line = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenHeight-80, screenProportionHeightby25, screenHeight-80);
        Line line1 = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenProportionHeightby25+30, screenProportionHeightby25, screenProportionHeightby25+30);
        
        Line smallVert1 = LineBlackNoFill(proportionalWidth*5.5, screenProportionHeightby25, proportionalWidth*5.5, screenProportionHeightby25+30);
        Line smallVert2 = LineBlackNoFill(proportionalWidth*9.5, screenProportionHeightby25, proportionalWidth*9.5, screenProportionHeightby25+30);
        Line smallVert3 = LineBlackNoFill(proportionalWidth*13.5, screenProportionHeightby25, proportionalWidth*13.5, screenProportionHeightby25+30);
        
        Label lblSTDConfig = label("Intent Stress-Drivers Profile", 175, 15, 60, 45, "", "The Current Menu");
        Label lblProjectID = label("123456789012345678901234567890", 250, 15, proportionalWidth*6, 45, "", "Project ID");
        Label lblRevisionNum = label("R1", 75, 15, proportionalWidth*10, 45, "", "Revision Number");
        Label lblDateBorder = label("YY-MM-DD", 75, 15, proportionalWidth*12, 45, "", "Current Date");
        Label lblPageNum = label("Page 1 of 1", 75, 15, proportionalWidth*14, 45, "", "Page Number");
        Label lblSignal = label("Signal", 75, 15, proportionalWidth*14+30, screenProportionHeightby25*4, "", "View/Hide Signals");
        lblSignal.setRotate(270);
        Label lblLabels = label("Labels", 75, 15, proportionalWidth*14+60, screenProportionHeightby25*4, "", "View/Hide Labels");
        lblLabels.setRotate(270);
        Label lblTemp = label("Temp.", 75, 15, proportionalWidth*14, screenProportionHeightby25*5, "", "Temperature");
        
        Button btnComponentState = button("Component State (KPF)",22,15, screenProportionHeightby25 + 1, screenProportionHeightby25+30+1, "SmallLabel" ,false, "Print the Current Page");
        Button btnSystemState = button("System State (KPF)",22,15, screenProportionHeightby25 + 1,screenHeight-80-15-10, "SmallLabel" ,false, "Print the Current Page");
        Button btnPrint = button("Print", 120, (screenHeight-75), "functionalButtonBiggerText" ,true, "Print the Current Page");
        Button btnDashboard = button("Dashboard", 220, (screenHeight-75), "functionalButtonBiggerText", true, "Redirects to the Dashboard Page");
        Button btnZoomIO = button("Zoom-In/Out", screenWidth/2, (screenHeight-75), "functionalButtonBiggerText", true, "Zoom in and out on current page");
        Button btnHelp = button("Help", screenWidth-180, (screenHeight-75), "functionalButtonBiggerText", true, "Redirects to the Hellp Page");
        
        root.getChildren().addAll(line1, line11, line2, line3, line,
        		smallVert1, smallVert2, smallVert3, lblSTDConfig, lblProjectID,
        		lblRevisionNum, lblDateBorder, lblPageNum, lblSignal, lblLabels, lblTemp,
        		btnComponentState, btnSystemState, btnPrint, btnDashboard, btnZoomIO, btnHelp);
        
        
        LineChart<Number, Number> chart = new LineChart<>(xAxis = new NumberAxis(0, 1000, 50), yAxis = new NumberAxis(0, 100, 10));
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.setCreateSymbols(true);
        chart.setAlternativeRowFillVisible(false);
        chart.setAlternativeColumnFillVisible(false);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);
        chart.getXAxis().setVisible(false);
        chart.getYAxis().setVisible(false);
        
        chart.setMinWidth(screenWidth-250);
        chart.setMinHeight(screenHeight-230);
        
        xAxis.setLabel("Time (seconds)");
        yAxis.setTickLabelsVisible(false);
        yAxis.setOpacity(0);
        //xAxis.setTickLabelsVisible(false);
        //xAxis.setOpacity(0);
        
        
        chart.getData().add(series);
        
 
        CheckBox cbLabels1 = new CheckBox();
        cbLabels1.setSelected(true);
        cbLabels1.setLayoutX(lblLabels.getLayoutX() + 30);
        cbLabels1.setLayoutY(lblLabels.getLayoutY() + screenProportionHeightby25);
        cbLabels1.setOnAction(e -> {
        	if (cbLabels1.isSelected()) {
        		for (XYChart.Data<Number, Number> data : series.getData())
        			data.getNode().setVisible(true);
        		for (Node i : root.getChildren()) {
        			if (i.getId() == "dataPoint")
        				i.setVisible(true);
        		}
        	}
        	else {
        		for (XYChart.Data<Number, Number> data : series.getData())
        			data.getNode().setVisible(false);
        		for (Node i : root.getChildren()) {
        			if (i.getId() == "dataPoint")
        				i.setVisible(false);
        		}
        	}
        });
        
        CheckBox cbSignal1 = new CheckBox();
        cbSignal1.setLayoutX(lblSignal.getLayoutX() + 30);
        cbSignal1.setLayoutY(lblSignal.getLayoutY() + screenProportionHeightby25);
        cbSignal1.setSelected(true);
        cbSignal1.setOnAction(e -> {
        	if (cbSignal1.isSelected()) {
        		series.getNode().setVisible(true);
        		for (XYChart.Data<Number, Number> data : series.getData())
        			data.getNode().setVisible(true);
        		for (Node i : root.getChildren()) {
        			if (i.getId() == "dataPoint")
        				i.setVisible(true);
        		}
        		if (!cbLabels1.isSelected()) {
        			cbLabels1.setSelected(true);
        			cbLabels1.setDisable(false);
        		}
        	}
        	else {
        		series.getNode().setVisible(false);
        		for (XYChart.Data<Number, Number> data : series.getData())
        			data.getNode().setVisible(false);
        		for (Node i : root.getChildren()) {
        			if (i.getId() == "dataPoint")
        				i.setVisible(false);
        		}
        		if (cbLabels1.isSelected()) 
        			cbLabels1.setSelected(false);
        		cbLabels1.setDisable(true);
        	}
        });
        
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(chart);
        stack.setLayoutX(screenProportionHeightby25*2);
        stack.setLayoutY(screenProportionHeightby25+90);
        
        //root.getChildren().addAll(stack, valueMarker, cbSignal1, cbLabels1);
        root.getChildren().addAll(stack, cbSignal1, cbLabels1);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setX(primaryScreenBounds.getMinX());
    	stage.setY(primaryScreenBounds.getMinY());
    	stage.setWidth(primaryScreenBounds.getWidth());
    	stage.setHeight(primaryScreenBounds.getHeight());
        stage.show();
        
        chartArea = chart.lookup(".chart-plot-background");
//        Bounds chartAreaBounds = chartArea.localToScene(chartArea.getBoundsInLocal());
//        
//        xShift = chartAreaBounds.getMinX();
//        yShift = chartAreaBounds.getMinY();
//        valueMarker.setStartY(90);
//        valueMarker.setEndY(screenHeight-105);
//        valueMarker.setStroke(Color.BLUE);
//        valueMarker.getStrokeDashArray().addAll(10.0, 10.0);
//        
//        double max = 210;
//        
//        double displayPosition = xAxis.getDisplayPosition(max);
//        valueMarker.setStartX(xShift + displayPosition);
//        valueMarker.setEndX(xShift + displayPosition);
//        
      
        try {
        	
        	Connection connection = ConnectionToDatabase.connectToDatabase();
        	Statement stm = connection.createStatement();
        	String sql = "SELECT * FROM mydb.DBTABLE2";
        	ResultSet rst;
        	rst = stm.executeQuery(sql);

        	while(rst.next()){
        		addDataPoint(rst.getDouble("X"), rst.getDouble("Y"));
        	}	
        	
        } catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
        }catch (SQLException e1) {
        	e1.printStackTrace();
        }
        System.out.println("ADDED ALL VALUES");
        

        try {
        	
        	Connection connection = ConnectionToDatabase.connectToDatabase();
        	Statement stm = connection.createStatement();
        	String sql = "SELECT * FROM mydb.kpf";
        	ResultSet rst;
        	rst = stm.executeQuery(sql);

        	while(rst.next()){
        		addKPF(rst.getDouble("TIME"));
        	}	
        	
        } catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
        }catch (SQLException e1) {
        	e1.printStackTrace();
        }
        System.out.println("ADDED KPF");

        

        
//        Label lblKPF1 = label("KPF1", 40, 15, valueMarker.getStartX()-20, valueMarker.getStartY()-10, "functionalButton", "The Current Menu");
//        Label lblS1 = label("S1", 40, 15, valueMarker.getEndX()-20, valueMarker.getEndY()-10, "labelGrey", "The Current Menu");
        
        //root.getChildren().addAll(lblKPF1, lblS1);
        
	}
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void addDataPoint(double x, double y) {
    	series.getData().add(new XYChart.Data(x, y));
    	double[] xy = getDisplayPosition(x, y);
    	
    	Label point = new Label();
    	point.setId("dataPoint");
    	point.setText(x + ", " + y);
    	point.setLayoutX(xy[0]);
    	point.setLayoutY(xy[1] + 15);
    	System.out.println(point);
    	
    	root.getChildren().add(point);
    }
    
    public static double[] getDisplayPosition(double x, double y) {
    	double displayPositionX = (xShift + xAxis.getDisplayPosition(x));
    	double displayPositionY = (yShift + yAxis.getDisplayPosition(y));
    	double[] xy = {displayPositionX, displayPositionY};
    	System.out.println(xy[0] + ", " + xy[1]);
    	return xy;
    }
    
    public static double getDisplayPositionX(double x) {
    	return xAxis.getDisplayPosition(x);
    }
    
    public static void addKPF(double x) {
    	Line valueMarker = new Line();
        Bounds chartAreaBounds = chartArea.localToScene(chartArea.getBoundsInLocal());
        xShift = chartAreaBounds.getMinX();
        
        valueMarker.setStartY(90);
        valueMarker.setEndY(screenHeight-105);
        valueMarker.setStroke(Color.GREEN);
        valueMarker.getStrokeDashArray().addAll(10.0, 10.0);
        
        
        double displayPosition = getDisplayPositionX(x);
        valueMarker.setStartX(xShift + displayPosition);
        valueMarker.setEndX(xShift + displayPosition);
        numOfKPFs += 1;
        
        String KPFName = "KPF" + numOfKPFs;
        String SName = "S" + numOfKPFs;
        Label lblKPF = label(KPFName, 40, 15, valueMarker.getStartX()-20, valueMarker.getStartY()-10, "functionalButton", "The Current Menu");
        Label lblS = label(SName, 40, 15, valueMarker.getEndX()-20, valueMarker.getEndY()-10, "labelGrey", "The Current Menu");
        
        root.getChildren().addAll(valueMarker, lblKPF, lblS);
    }
	
    public static Line LineBlackNoFill(double x1, int y1, double x2, int y2) {

        Line line = new Line(x1, y1, x2, y2); 
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1.0); 
        line.getStrokeDashArray().addAll(10.0,0.0,10.0,0.0);

        return line;
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
        public static Button button(String text, int minWidth, int minHeight,double positionX, double positionY, String style, Boolean shadow, String tipText ){

        Button button1 = new Button();
        button1.setText(text);
        button1.setMaxWidth(55);
        button1.setWrapText(true);
        button1.setMinWidth(minWidth);
        button1.setMinHeight(minHeight);
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
	
}
