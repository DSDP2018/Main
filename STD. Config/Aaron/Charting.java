package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Charting extends Application {
	
	static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	
    static int screenWidth = (int) primaryScreenBounds.getWidth();
    static int screenHeight = (int) primaryScreenBounds.getHeight();
    static int fieldWidthAlignment = screenWidth/45;
    static int proportionalHeight = screenHeight/10;
    static double proportionalWidth = screenWidth/16;
	
	@Override
	public void start(Stage stage) {
		
		Group root = new Group();
		
        int screenProportionHeightby25 = screenHeight/25;   

        Line line11 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25+40,screenProportionHeightby25,screenHeight-80);

        Line line2 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25+40,screenWidth-screenProportionHeightby25,screenProportionHeightby25+40);     

        Line line3 = LineBlackNoFill(screenWidth-screenProportionHeightby25,screenProportionHeightby25+40,screenWidth-screenProportionHeightby25,screenHeight-80);
       
        Line line = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenHeight-80, screenProportionHeightby25, screenHeight-80);

        root.getChildren().addAll(line11,line2,line3,line);
		
        Label lblSTDConfig = label("STD Config", 175, 15, 60, 30, "windows7", "The Current Menu");

        Label lblProjectID = label("123456789012345678901234567890", 250, 15, proportionalWidth*6, 30, "lion", "Project ID");

        Label lblRevisionNum = label("R1", 75, 15, proportionalWidth*10, 30, "otherBorder", "Revision Number");

        Label lblDateBorder = label("YY-MM-DD", 75, 15, proportionalWidth*12, 30, "otherBorder", "Current Date");

        Label lblPageNum = label("Page 1 of 1", 75, 15, proportionalWidth*14, 30, "otherBorder", "Page Number");
		
        root.getChildren().addAll(lblSTDConfig, lblProjectID, lblRevisionNum, lblDateBorder, lblPageNum);
        
        LineChart<Number, Number> lineChart = createChart();
        lineChart.setMinWidth(screenWidth-120);
        lineChart.setMinHeight(screenHeight-180);
        //lineChart.setMinHeight(screenHeight*2/3);
        //lineChart.setLayoutX(screenProportionHeightby25*2);
        //lineChart.setLayoutY(screenProportionHeightby25+60);
        
        lineChart.setCursor(Cursor.CROSSHAIR);
        
        LineChart<Number, Number> lineChart2 = createChart2();
        lineChart2.setMinWidth(screenWidth-120);
        lineChart2.setMinHeight(screenHeight-180);
        lineChart2.getStylesheets().addAll(getClass().getResource("application.css").toExternalForm());
        lineChart2.setCursor(Cursor.CROSSHAIR);
        
        StackPane stack = new StackPane();
        stack.setLayoutX(screenProportionHeightby25*2);
        stack.setLayoutY(screenProportionHeightby25+60);
        stack.getChildren().addAll(lineChart, lineChart2);
        
        root.getChildren().add(stack);
        
        
		Scene scene = new Scene(root);
		
    	stage.setX(primaryScreenBounds.getMinX());
    	stage.setY(primaryScreenBounds.getMinY());
    	stage.setWidth(primaryScreenBounds.getWidth());
    	stage.setHeight(primaryScreenBounds.getHeight());
    	
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	public static LineChart<Number, Number> createChart() {
		final NumberAxis xAxis = new NumberAxis(0, 5400, 600);
		xAxis.setLabel("Time (in seconds)");
		xAxis.setMinorTickVisible(false);
		
		final NumberAxis yAxis = new NumberAxis(-5000, 0, 200);
		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
		//yAxis.setTickLabelsVisible(false);
		//yAxis.setOpacity(0);

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Line Chart");

//		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
//		series1.setName("Ex. Line");
//		series1.getData().add(new XYChart.Data<Number, Number>(1, 23));
//		series1.getData().add(new XYChart.Data(200, 14));
//		series1.getData().add(new XYChart.Data(350, 15));
//		series1.getData().add(new XYChart.Data(490, 24));
//		series1.getData().add(new XYChart.Data(1000, 34));
//		series1.getData().add(new XYChart.Data(2500, 36));
//		series1.getData().add(new XYChart.Data(2700, 22));
//		series1.getData().add(new XYChart.Data(8, 45));
//		series1.getData().add(new XYChart.Data(9, 43));
//		series1.getData().add(new XYChart.Data(10, 17));
//		series1.getData().add(new XYChart.Data(11, 29));
//		series1.getData().add(new XYChart.Data(12, 25));
		
		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
		series2.setName("0 to -5000");
		series2.getData().add(new XYChart.Data<Number, Number>(20, 0));
		series2.getData().add(new XYChart.Data(200, -1400));
		series2.getData().add(new XYChart.Data(350, -1500));
		series2.getData().add(new XYChart.Data(490, -2400));
		series2.getData().add(new XYChart.Data(1000, -3450));
		series2.getData().add(new XYChart.Data(2500, -3690));
		series2.getData().add(new XYChart.Data(2700, -2240));
		
		XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
		series3.setName("-40 to 150");
		series3.getData().add(new XYChart.Data<Number, Number>(20, 0));
		series3.getData().add(new XYChart.Data(200, -40));
		series3.getData().add(new XYChart.Data(350, -10));
		series3.getData().add(new XYChart.Data(490, 20));
		series3.getData().add(new XYChart.Data(1000, 150));
		series3.getData().add(new XYChart.Data(2500, 36));
		series3.getData().add(new XYChart.Data(2700, 100));
		
//		XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
//		series4.setName("-1 to 1");
//		series4.getData().add(new XYChart.Data<Number, Number>(20, 0));
//		series4.getData().add(new XYChart.Data(200, -0.5));
//		series4.getData().add(new XYChart.Data(350, -1));
//		series4.getData().add(new XYChart.Data(490, 0.2));
//		series4.getData().add(new XYChart.Data(1000, 1));
//		series4.getData().add(new XYChart.Data(2500, 0.7));
//		series4.getData().add(new XYChart.Data(2700, 0.5));

		lineChart.getData().addAll(series2);
		
		final NumberAxis xAxis2 = new NumberAxis(0, 5400, 600);
		xAxis.setLabel("Time (in seconds)");
		xAxis.setMinorTickVisible(false);
		
		final NumberAxis yAxis2 = new NumberAxis(-40, 150, 10);
		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
		final LineChart<Number, Number> lineChart2 = new LineChart<Number, Number>(xAxis, yAxis2);
		lineChart2.getData().add(series3);
	    lineChart2.setLegendVisible(false);
        lineChart2.setAnimated(false);
        lineChart2.setCreateSymbols(true);
        lineChart2.setAlternativeRowFillVisible(false);
        lineChart2.setAlternativeColumnFillVisible(false);
        lineChart2.setHorizontalGridLinesVisible(false);
        lineChart2.setVerticalGridLinesVisible(false);
        lineChart2.getXAxis().setVisible(false);
        lineChart2.getYAxis().setVisible(false);
        
		return lineChart;
	}
	
	public static LineChart<Number, Number> createChart2() {
		final NumberAxis xAxis = new NumberAxis(0, 5400, 600);
		xAxis.setLabel("Time (in seconds)");
		xAxis.setMinorTickVisible(false);
		
		final NumberAxis yAxis = new NumberAxis(-40, 150, 10);
		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
		//yAxis.setTickLabelsVisible(false);
		//yAxis.setOpacity(0);

		XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
		series3.setName("-40 to 150");
		series3.getData().add(new XYChart.Data<Number, Number>(20, 0));
		series3.getData().add(new XYChart.Data(200, -40));
		series3.getData().add(new XYChart.Data(350, -10));
		series3.getData().add(new XYChart.Data(490, 20));
		series3.getData().add(new XYChart.Data(1000, 150));
		series3.getData().add(new XYChart.Data(2500, 36));
		series3.getData().add(new XYChart.Data(2700, 100));
		
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Line Chart");
		lineChart.getData().add(series3);
	    lineChart.setLegendVisible(false);
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(true);
        lineChart.setAlternativeRowFillVisible(false);
        lineChart.setAlternativeColumnFillVisible(false);
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setVerticalGridLinesVisible(false);
        lineChart.getXAxis().setVisible(false);
        lineChart.getYAxis().setVisible(false);
		
		return lineChart;
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
}
