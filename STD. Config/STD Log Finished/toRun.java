package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class toRun extends Application {
    
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
//    @Override
//    public void start(Stage stage) throws Exception {
//        trying(stage);
//
//    }
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        group = root;
        // create canvas
        PannableCanvas canvas = new PannableCanvas();



        // create sample nodes which can be dragged
        NodeGestures nodeGestures = new NodeGestures( canvas);

        int screenProportionHeightby25 = screenHeight/25;   
        Line line1 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenProportionHeightby25,screenHeight-80);
        Line line2 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenProportionHeightby25);
        Line line3 = LineBlackNoFill(screenWidth-screenProportionHeightby25,screenProportionHeightby25,screenWidth-screenProportionHeightby25,screenHeight-80);
        Line line4 = LineBlackNoFill(screenWidth-screenProportionHeightby25, screenHeight-80, screenProportionHeightby25, screenHeight-80);
        Line line5 = LineBlackNoFill(screenProportionHeightby25,screenProportionHeightby25+50,screenWidth-screenProportionHeightby25,screenProportionHeightby25+50);
        canvas.getChildren().addAll(line1,line2,line3,line4,line5);
        

            
        Label lblRevisionControl = label("Main Page", 175, 15, 60, 50, "revisionControl", "The Current Menu");
        Label lblProjectID = label("123456789012345678901234567890", 250, 15, proportionalWidth*9, 55, "", "Project ID");
        Label lblRevisionNum = label("R1", 75, 15, proportionalWidth*12, 55, "", "Revision Number");
        Label lblDateBorder = label("YY-MM-DD", 75, 15, proportionalWidth*13, 55, "", "Current Date");
        Label lblPageNum = label("Page 1 of 1", 75, 15, proportionalWidth*14, 55, "", "Page Number");
        canvas.getChildren().addAll(lblRevisionControl, lblProjectID,lblRevisionNum,lblDateBorder,lblPageNum);
        
        Button KPFChart = button("Intent Stress-Drivers Profile", 350,300, "functionalButtonMain", true, "");      
        KPFChart.setMaxWidth(70);
        KPFChart.setWrapText(true);
        KPFChart.setOnAction(e -> {       	
        	
        	try {
				Desktop.getDesktop().open(new File("C:\\Users\\Minghua\\Desktop\\test.jar"));
				System.out.println("DONE");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        Button VSDSC = button("Variable Stress-Drivers' Signal Configurator", 650,300, "functionalButtonMain", true, "");      
        VSDSC.setMaxWidth(70);
        VSDSC.setWrapText(true);
        VSDSC.setOnAction(e -> {       	
        	
        	try {
				Desktop.getDesktop().open(new File("C:\\Users\\Minghua\\Desktop\\zoomRevisionLog.jar"));
				System.out.println("DONE");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        canvas.getChildren().addAll(KPFChart,VSDSC);
        scene = new Scene(canvas);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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


class LogStreamReader implements Runnable {
	
	private BufferedReader reader;
	
	public LogStreamReader(InputStream is) {
		this.reader = new BufferedReader(new InputStreamReader(is));
	}
	
	public void run() {
		try {
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e){
			e.printStackTrace();
		}
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


