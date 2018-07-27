package application;

import java.util.Random;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
  
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;



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
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    
//    private Line valueMarker = new Line();
//    private XYChart.Series<Number, Number> series = new XYChart.Series<>();
//    //private NumberAxis yAxis;
//    private double yShift;
	
	@Override
	public void start(Stage stage) {
	
		Group root = new Group();
		
        int screenProportionHeightby25 = screenHeight/25;   

        Line line11 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenProportionHeightby25,screenHeight-80);
        Line line2 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenProportionHeightby25);     
        Line line3 = LineBlackNoFill(screenWidth-screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenHeight-80);      
        Line line = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenHeight-80, screenProportionHeightby25, screenHeight-80);
        Line line1 = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenProportionHeightby25+30, screenProportionHeightby25, screenProportionHeightby25+30);
        
        Line smallVert1 = LineBlackNoFill(proportionalWidth*5.5, screenProportionHeightby25, proportionalWidth*5.5, screenProportionHeightby25+30);
        Line smallVert2 = LineBlackNoFill(proportionalWidth*9.5, screenProportionHeightby25, proportionalWidth*9.5, screenProportionHeightby25+30);
        Line smallVert3 = LineBlackNoFill(proportionalWidth*13.5, screenProportionHeightby25, proportionalWidth*13.5, screenProportionHeightby25+30);
        
        root.getChildren().addAll(line1,line11,line2,line3,line,smallVert1,smallVert2,smallVert3);	
        
        Label lblSTDConfig = label("Intent Stress-Drivers Profile", 175, 15, 60, 37, "", "The Current Menu");
        Label lblProjectID = label("123456789012345678901234567890", 250, 15, proportionalWidth*6, 37, "", "Project ID");
        Label lblRevisionNum = label("R1", 75, 15, proportionalWidth*10, 37, "", "Revision Number");
        Label lblDateBorder = label("YY-MM-DD", 75, 15, proportionalWidth*12, 37, "", "Current Date");
        Label lblPageNum = label("Page 1 of 1", 75, 15, proportionalWidth*14, 37, "", "Page Number");
        
        Button btnComponentState = button("Component State (KPF)",22,15, screenProportionHeightby25 + 1, screenProportionHeightby25+30+1, "functionalButton1" ,false, "Print the Current Page");
        Button btnSystemState = button("System State (KPF)",22,15, screenProportionHeightby25 + 1,screenHeight-80-15-10, "functionalButton1" ,false, "Print the Current Page");
        
        root.getChildren().addAll(lblSTDConfig, lblPageNum,btnSystemState,btnComponentState,lblProjectID, lblRevisionNum, lblDateBorder);
        Button btnPrint = button("Print", 120, (screenHeight-75), "functionalButtonBiggerText" ,true, "Print the Current Page");
//        btnPrint.setOnAction((ActionEvent e) -> {
//            try {
//                PrintTest.printPDF();
//            } catch (IOException ex) {
//                Logger.getLogger(ButtonClass.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (BadElementException ex) {
//                Logger.getLogger(ButtonClass.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (PrintException ex) {
//                Logger.getLogger(ButtonClass.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
        Button btnDashboard = button("Dashboard", 220, (screenHeight-75), "functionalButtonBiggerText", true, "Redirects to the Dashboard Page");
        Button btnZoomIO = button("Zoom-In/Out", screenWidth/2, (screenHeight-75), "functionalButtonBiggerText", true, "Zoom in and out on current page");
        Button btnHelp = button("Help", screenWidth-180, (screenHeight-75), "functionalButtonBiggerText", true, "Redirects to the Hellp Page");
        root.getChildren().addAll(btnPrint,btnDashboard,btnZoomIO,btnHelp);
     
        LineChart<Number, Number> lineChart = createChart();
        final NumberAxis axis = (NumberAxis) lineChart.getXAxis();
        final double lowerX = axis.getLowerBound();
        final double upperX = axis.getUpperBound();
        
        lineChart.setOnScroll(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                final double minX = axis.getLowerBound();
                final double maxX = axis.getUpperBound();
                double threshold = minX + (maxX - minX) / 2d;
                double x = event.getX();
                double value = axis.getValueForDisplay(x).doubleValue();
                double direction = event.getDeltaY();
                if (direction > 0) {
                    if (maxX - minX <= 1) {
                        return;
                    }
                    if (value < threshold) { // if(value > threshold)
                        axis.setLowerBound(minX + 1);
                    } else {
                        axis.setUpperBound(maxX - 1);
                    }
                } else {
                    if (value < threshold) {
                        double nextBound = Math.max(lowerX, minX - 1);
                        axis.setLowerBound(nextBound);
                    } else {
                        double nextBound = Math.min(upperX, maxX + 1);
                        axis.setUpperBound(nextBound);
                    }
                }

            }
        });
        
        
        lineChart.setMinWidth(screenWidth-300);
        lineChart.setMinHeight(screenHeight-180);
        //lineChart.setMinHeight(screenHeight*2/3);
        //lineChart.setLayoutX(screenProportionHeightby25*2);
        //lineChart.setLayoutY(screenProportionHeightby25+60);
        lineChart.setCursor(Cursor.CROSSHAIR);
  
//        Node chartPlotArea = lineChart.lookup(".chart-plot-background");
//        double chartZeroX = chartPlotArea.getLayoutX();
//        double chartZeroY = chartPlotArea.getLayoutY();
//        System.out.println("X = " + chartZeroX);
//        System.out.println("Y = " + chartZeroY);
        
//        final CheckBox box1 = new CheckBox(lineChart.getTitle());
//        box1.setSelected(true);
//        if (box1.isSelected())
//        	lineChart.getData().add(e)
//        else
//        	lineChart.getData().remove()
        
        LineChart<Number, Number> lineChart2 = createChart2();
        final NumberAxis axis1 = (NumberAxis) lineChart2.getXAxis();
        final double lowerX1 = axis1.getLowerBound();
        final double upperX1 = axis1.getUpperBound();
        
        lineChart2.setOnScroll(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                final double minX1 = axis1.getLowerBound();
                final double maxX1 = axis1.getUpperBound();
                double threshold = minX1 + (maxX1 - minX1) / 2d;
                double x1 = event.getX();
                double value1 = axis1.getValueForDisplay(x1).doubleValue();
                double direction1 = event.getDeltaY();
                if (direction1 > 0) {
                    if (maxX1 - minX1 <= 1) {
                        return;
                    }
                    if (value1 < threshold) { // if(value > threshold)
                        axis.setLowerBound(minX1 + 1);
                    } else {
                        axis.setUpperBound(maxX1 - 1);
                    }
                } else {
                    if (value1 < threshold) {
                        double nextBound = Math.max(lowerX, minX1 - 1);
                        axis.setLowerBound(nextBound);
                    } else {
                        double nextBound = Math.min(upperX, maxX1 + 1);
                        axis.setUpperBound(nextBound);
                    }
                }

            }
        });

        
        
        lineChart2.setMinWidth(screenWidth-300);
        lineChart2.setMinHeight(screenHeight-180);
        lineChart2.getStylesheets().addAll(getClass().getResource("application.css").toExternalForm());
        lineChart2.setCursor(Cursor.CROSSHAIR);
        
        StackPane stack = new StackPane();
        stack.setLayoutX(screenProportionHeightby25*2);
        stack.setLayoutY(screenProportionHeightby25+60);
        stack.getChildren().addAll(lineChart, lineChart2);
        
        root.getChildren().addAll(stack);
        
        
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
		yAxis.setTickLabelsVisible(false);
		yAxis.setOpacity(0);

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setHorizontalGridLinesVisible(false);
		lineChart.setVerticalGridLinesVisible(false);
		
		lineChart.getYAxis().setTickLabelsVisible(false);
		lineChart.getYAxis().setOpacity(0);
		//lineChart.setTitle("Line Chart");
		
		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
		series2.setName("0 to -5000");
		series2.getData().add(new XYChart.Data<Number, Number>(20, 0));
		series2.getData().add(new XYChart.Data(200, -1400));
		series2.getData().add(new XYChart.Data(350, -1500));
		series2.getData().add(new XYChart.Data(490, -2400));
		series2.getData().add(new XYChart.Data(1000, -3450));
		series2.getData().add(new XYChart.Data(2500, -3690));
		series2.getData().add(new XYChart.Data(2700, -2240));
		
//		double max = 0;
//		for (Data<Number, Number> value : series2.getData()) {
//			double y = value.getYValue().doubleValue();
//			System.out.println("Current Y-value = " + y);
//			if(y < max) {
//				max = y;
//				System.out.println("Current max = " + max);
//			}
//		}
//		double displayPosition = yAxis.getDisplayPosition(max);
//		Node chartArea = lineChart.lookup(".chart-plot-background");
//		Bounds chartAreaBounds = chartArea.localToScene(chartArea.getBoundsInLocal());
//		double yShift = chartAreaBounds.getMinY();
//		System.out.println("Y-shift = " + yShift);
//		
//		System.out.println("Max: " + displayPosition);
		
//		XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
//		series3.setName("-40 to 150");
//		series3.getData().add(new XYChart.Data<Number, Number>(20, 0));
//		series3.getData().add(new XYChart.Data(200, -40));
//		series3.getData().add(new XYChart.Data(350, -10));
//		series3.getData().add(new XYChart.Data(490, 20));
//		series3.getData().add(new XYChart.Data(1000, 150));
//		series3.getData().add(new XYChart.Data(2500, 36));
//		series3.getData().add(new XYChart.Data(2700, 100));
		
//		XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
//		series4.setName("-1 to 1");
//		series4.getData().add(new XYChart.Data<Number, Number>(20, 0));
//		series4.getData().add(new XYChart.Data(200, -0.5));
//		series4.getData().add(new XYChart.Data(350, -1));
//		series4.getData().add(new XYChart.Data(490, 0.2));
//		series4.getData().add(new XYChart.Data(1000, 1));
//		series4.getData().add(new XYChart.Data(2500, 0.7));
//		series4.getData().add(new XYChart.Data(2700, 0.5));

		lineChart.getData().add(series2);
		
//		final NumberAxis xAxis2 = new NumberAxis(0, 5400, 600);
//		xAxis.setLabel("Time (in seconds)");
//		xAxis.setMinorTickVisible(false);
//		
//		final NumberAxis yAxis2 = new NumberAxis(-40, 150, 10);
//		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
//		final LineChart<Number, Number> lineChart2 = new LineChart<Number, Number>(xAxis, yAxis2);
//		lineChart2.getData().add(series3);
//	    lineChart2.setLegendVisible(false);
//        lineChart2.setAnimated(false);
//        lineChart2.setCreateSymbols(true);
//        lineChart2.setAlternativeRowFillVisible(false);
//        lineChart2.setAlternativeColumnFillVisible(false);
//        lineChart2.setHorizontalGridLinesVisible(false);
//        lineChart2.setVerticalGridLinesVisible(false);
//        lineChart2.getXAxis().setVisible(false);
//        lineChart2.getYAxis().setVisible(false);
        
		return lineChart;
	}
	
	public static LineChart<Number, Number> createChart2() {
		final NumberAxis xAxis = new NumberAxis(0, 5400, 600);
		xAxis.setLabel("Time (in seconds)");
		xAxis.setMinorTickVisible(false);
		
		final NumberAxis yAxis = new NumberAxis(-40, 150, 10);
		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
		yAxis.setTickLabelsVisible(false);
		yAxis.setOpacity(0);

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
		//lineChart.setTitle("Line Chart");
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
        
        lineChart.getYAxis().setTickLabelsVisible(false);
        lineChart.getYAxis().setOpacity(0);
		
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


//public class Charting extends Application {
//	
//	static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//	
//    static int screenWidth = (int) primaryScreenBounds.getWidth();
//    static int screenHeight = (int) primaryScreenBounds.getHeight();
//    static int fieldWidthAlignment = screenWidth/45;
//    static int proportionalHeight = screenHeight/10;
//    static double proportionalWidth = screenWidth/16;
//	
//	@Override
//	public void start(Stage stage) {
//		
//		Group root = new Group();
//		
//        int screenProportionHeightby25 = screenHeight/25;   
//        Line line1 = LineBlackNoFill(500,0,500,1200);
//        Line x = LineBlackNoFill(screenWidth-120,screenHeight-180,screenWidth,screenHeight);
//        Line x1 = LineBlackNoFill(0,screenWidth-120,screenWidth,screenHeight);
//        Line x2 = LineBlackNoFill(screenHeight-180,0,screenWidth,screenHeight);
//        Line line11 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25+40,screenProportionHeightby25,screenHeight-80);
//
//        Line line2 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25+40,screenWidth-screenProportionHeightby25,screenProportionHeightby25+40);     
//
//        Line line3 = LineBlackNoFill(screenWidth-screenProportionHeightby25,screenProportionHeightby25+40,screenWidth-screenProportionHeightby25,screenHeight-80);
//       
//        Line line = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenHeight-80, screenProportionHeightby25, screenHeight-80);
//
//        root.getChildren().addAll(line11,line2,line3,line,line1,x,x1,x2);
//		
//        Label lblSTDConfig = label("STD Config", 175, 15, 60, 30, "windows7", "The Current Menu");
//
//        Label lblProjectID = label("123456789012345678901234567890", 250, 15, proportionalWidth*6, 30, "lion", "Project ID");
//
//        Label lblRevisionNum = label("R1", 75, 15, proportionalWidth*10, 30, "otherBorder", "Revision Number");
//
//        Label lblDateBorder = label("YY-MM-DD", 75, 15, proportionalWidth*12, 30, "otherBorder", "Current Date");
//
//        Label lblPageNum = label("Page 1 of 1", 75, 15, proportionalWidth*14, 30, "otherBorder", "Page Number");
//		
//        root.getChildren().addAll(lblSTDConfig, lblProjectID, lblRevisionNum, lblDateBorder, lblPageNum);
//        
//        LineChart<Number, Number> lineChart = createChart();
//        lineChart.setMinWidth(screenWidth-120);
//        lineChart.setMinHeight(screenHeight-180);
//        //lineChart.setMinHeight(screenHeight*2/3);
//        //lineChart.setLayoutX(screenProportionHeightby25*2);
//        //lineChart.setLayoutY(screenProportionHeightby25+60);
//        
//        lineChart.setCursor(Cursor.CROSSHAIR);
//        
//        LineChart<Number, Number> lineChart2 = createChart2();
//        lineChart2.setMinWidth(screenWidth-120);
//        lineChart2.setMinHeight(screenHeight-180);
//        lineChart2.getStylesheets().addAll(getClass().getResource("application.css").toExternalForm());
//        lineChart2.setCursor(Cursor.CROSSHAIR);
//        
//        StackPane stack = new StackPane();
//        stack.setLayoutX(screenProportionHeightby25*2);
//        stack.setLayoutY(screenProportionHeightby25+60);
//        stack.getChildren().addAll(lineChart, lineChart2);
//        
//        root.getChildren().add(stack);
//        
//        
//		Scene scene = new Scene(root);
//		
//    	stage.setX(primaryScreenBounds.getMinX());
//    	stage.setY(primaryScreenBounds.getMinY());
//    	stage.setWidth(primaryScreenBounds.getWidth());
//    	stage.setHeight(primaryScreenBounds.getHeight());
//    	
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		stage.setScene(scene);
//		stage.show();
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//	
//	
//	
//	public static LineChart<Number, Number> createChart() {
//		final NumberAxis xAxis = new NumberAxis(0, 5400, 600);
//		xAxis.setLabel("Time (in seconds)");
//		xAxis.setMinorTickVisible(false);
//		
//		final NumberAxis yAxis = new NumberAxis(-5000, 0, 200);
//		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
//		//yAxis.setTickLabelsVisible(false);
//		//yAxis.setOpacity(0);
//
//		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
//		lineChart.setTitle("Line Chart");
//
////		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
////		series1.setName("Ex. Line");
////		series1.getData().add(new XYChart.Data<Number, Number>(1, 23));
////		series1.getData().add(new XYChart.Data(200, 14));
////		series1.getData().add(new XYChart.Data(350, 15));
////		series1.getData().add(new XYChart.Data(490, 24));
////		series1.getData().add(new XYChart.Data(1000, 34));
////		series1.getData().add(new XYChart.Data(2500, 36));
////		series1.getData().add(new XYChart.Data(2700, 22));
////		series1.getData().add(new XYChart.Data(8, 45));
////		series1.getData().add(new XYChart.Data(9, 43));
////		series1.getData().add(new XYChart.Data(10, 17));
////		series1.getData().add(new XYChart.Data(11, 29));
////		series1.getData().add(new XYChart.Data(12, 25));
//		
//		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
//		series2.setName("0 to -5000");
//		series2.getData().add(new XYChart.Data<Number, Number>(20, 0));
//		series2.getData().add(new XYChart.Data(200, -1400));
//		series2.getData().add(new XYChart.Data(350, -1500));
//		series2.getData().add(new XYChart.Data(490, -2400));
//		series2.getData().add(new XYChart.Data(1000, -3450));
//		series2.getData().add(new XYChart.Data(2500, -3690));
//		series2.getData().add(new XYChart.Data(2700, -2240));
//		
//		XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
//		series3.setName("-40 to 150");
//		series3.getData().add(new XYChart.Data<Number, Number>(20, 0));
//		series3.getData().add(new XYChart.Data(200, -40));
//		series3.getData().add(new XYChart.Data(350, -10));
//		series3.getData().add(new XYChart.Data(490, 20));
//		series3.getData().add(new XYChart.Data(1000, 150));
//		series3.getData().add(new XYChart.Data(2500, 36));
//		series3.getData().add(new XYChart.Data(2700, 100));
//		
////		XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
////		series4.setName("-1 to 1");
////		series4.getData().add(new XYChart.Data<Number, Number>(20, 0));
////		series4.getData().add(new XYChart.Data(200, -0.5));
////		series4.getData().add(new XYChart.Data(350, -1));
////		series4.getData().add(new XYChart.Data(490, 0.2));
////		series4.getData().add(new XYChart.Data(1000, 1));
////		series4.getData().add(new XYChart.Data(2500, 0.7));
////		series4.getData().add(new XYChart.Data(2700, 0.5));
//
//		lineChart.getData().addAll(series2);
//		
//		final NumberAxis xAxis2 = new NumberAxis(0, 5400, 600);
//		xAxis.setLabel("Time (in seconds)");
//		xAxis.setMinorTickVisible(false);
//		
//		final NumberAxis yAxis2 = new NumberAxis(-40, 150, 10);
//		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
//		final LineChart<Number, Number> lineChart2 = new LineChart<Number, Number>(xAxis, yAxis2);
//		lineChart2.getData().add(series3);
//	    lineChart2.setLegendVisible(false);
//        lineChart2.setAnimated(false);
//        lineChart2.setCreateSymbols(true);
//        lineChart2.setAlternativeRowFillVisible(false);
//        lineChart2.setAlternativeColumnFillVisible(false);
//        lineChart2.setHorizontalGridLinesVisible(false);
//        lineChart2.setVerticalGridLinesVisible(false);
//        lineChart2.getXAxis().setVisible(false);
//        lineChart2.getYAxis().setVisible(false);
//        
//		return lineChart;
//	}
//	
//	public static LineChart<Number, Number> createChart2() {
//		final NumberAxis xAxis = new NumberAxis(0, 5400, 600);
//		xAxis.setLabel("Time (in seconds)");
//		xAxis.setMinorTickVisible(false);
//		
//		final NumberAxis yAxis = new NumberAxis(-40, 150, 10);
//		//yAxis.setLowerBound(yAxis.getUpperBound()/(-3));
//		//yAxis.setTickLabelsVisible(false);
//		//yAxis.setOpacity(0);
//
//		XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
//		series3.setName("-40 to 150");
//		series3.getData().add(new XYChart.Data<Number, Number>(20, 0));
//		series3.getData().add(new XYChart.Data(200, -40));
//		series3.getData().add(new XYChart.Data(350, -10));
//		series3.getData().add(new XYChart.Data(490, 20));
//		series3.getData().add(new XYChart.Data(1000, 150));
//		series3.getData().add(new XYChart.Data(2500, 36));
//		series3.getData().add(new XYChart.Data(2700, 100));
//		
//		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
//		lineChart.setTitle("Line Chart");
//		lineChart.getData().add(series3);
//	    lineChart.setLegendVisible(false);
//        lineChart.setAnimated(false);
//        lineChart.setCreateSymbols(true);
//        lineChart.setAlternativeRowFillVisible(false);
//        lineChart.setAlternativeColumnFillVisible(false);
//        lineChart.setHorizontalGridLinesVisible(false);
//        lineChart.setVerticalGridLinesVisible(false);
//        lineChart.getXAxis().setVisible(false);
//        lineChart.getYAxis().setVisible(false);
//		
//		return lineChart;
//	}
//	
//    public static Line LineBlackNoFill(double x1, int y1, double x2, int y2) {
//
//        Line line = new Line(x1, y1, x2, y2); 
//        line.setStroke(Color.BLACK);
//        line.setStrokeWidth(1.0); 
//        line.getStrokeDashArray().addAll(10.0,0.0,10.0,0.0);
//
//        return line;
//    }
//    
//    public static Label label(String text, int boxWidth, int boxHeight, double x, double y, String style, String tip) {
//        
//        Label label1 = new Label();
//        label1.setText(text);
//        label1.setAlignment(Pos.CENTER);
//        label1.setMinWidth(boxWidth);
//        label1.setMinHeight(boxHeight);
//        label1.setLayoutX(x);
//        label1.setLayoutY(y);
//        label1.setId(style);
//        label1.setTooltip(new Tooltip(tip));
//                
//        return label1;
//    }
//}

///** Displays a LineChart which displays the value of a plotted Node when you hover over the Node. */
//public class LineChartWithHover extends Application {
//  @SuppressWarnings("unchecked")
//  @Override public void start(Stage stage) {
//    final LineChart lineChart = new LineChart(
//        new NumberAxis(), new NumberAxis(),
//        FXCollections.observableArrayList(
//            new XYChart.Series(
//                
//                FXCollections.observableArrayList(
//                    plot(22,22, 16, 11,11, 28,45,45,44, 43,43,30, 17,17,23, 29,29,27, 25,25,24,23,23,18 ,14,14, 17,19,19, 24,29, 29,32,35, 35)
//                )
//            )
//        )
//    );
//    final LineChart lineChart1 = new LineChart(
//            new NumberAxis(), new NumberAxis(),
//            FXCollections.observableArrayList(
//                new XYChart.Series(FXCollections.observableArrayList(
//                plot(22, 11, 45,44, 43,30, 17,23, 29,27, 25,24,23,17 ,14, 15,19, 24, 29,34, 35,36)))));
//    
//    lineChart.setCursor(Cursor.CROSSHAIR);
//
//    lineChart.setTitle("DIP");
//    
//
//    stage.setScene(new Scene(lineChart,500, 400));
//    stage.show();
//  }
//
//  /** @return plotted y values for monotonically increasing integer x values, starting from x=1 */
//  public ObservableList<XYChart.Data<Integer, Integer>> plot(int... y) {
//
//	 final ObservableList<XYChart.Data<Integer, Integer>> dataset = FXCollections.observableArrayList();
//	 int i = 0;
//	 while (i < y.length) {
//		 final XYChart.Data<Integer, Integer> data = new XYChart.Data<>(i + 1, y[i]);
//		 data.setNode(
//				 new HoveredThresholdNode(
//				(i == 0) ? 0 : y[i-1],
//              y[i]
//          )
//      );
//
//      dataset.add(data);
//      i++;
//    }
//
//    return dataset;
//  }
//
//  /** a node which displays a value on hover, but is otherwise empty */
//  class HoveredThresholdNode extends StackPane {
//    HoveredThresholdNode(int priorValue, int value) {
//      setPrefSize(15, 15);
//
//      
//      final Label label = createDataThresholdLabel(priorValue, value);
//
//      setOnMouseEntered(new EventHandler<MouseEvent>() {
//        @Override public void handle(MouseEvent mouseEvent) {
//          getChildren().setAll(label);
//          setCursor(Cursor.NONE);
//          toFront();
//        }
//      });
//      setOnMouseExited(new EventHandler<MouseEvent>() {
//        @Override public void handle(MouseEvent mouseEvent) {
//          getChildren().clear();
//          setCursor(Cursor.CROSSHAIR);
//        }
//      });
//    }
//
//    private Label createDataThresholdLabel(int priorValue, int value) {
//      int valueX = 0;
//      valueX = valueX + 1;
//      final Label label = new Label("[" + value + "," + valueX + "]");
//      label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
//      label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
//
//      if (priorValue == 0) {
//        label.setTextFill(Color.DARKGRAY);
//      } else if (value > priorValue) {
//        label.setTextFill(Color.FORESTGREEN);
//      } else {
//        label.setTextFill(Color.FIREBRICK);
//      }
//
//      label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
//      return label;
//    }
//  }
//  public void returnValue(int x, int y) {
//	  
//  }
//
//
//  public static void main(String[] args) { launch(args); }
//}
