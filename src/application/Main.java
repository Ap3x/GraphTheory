package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main extends Application {

	public void start(Stage primaryStage) {
		int SceneSizeX = 1000;
		int SceneSizeY = 1000;
		int textSize = 13;
		Color text = Color.RED;
		BorderPane root = new BorderPane();
		primaryStage.setScene(new Scene(root, SceneSizeX, SceneSizeY, Color.BLACK));
		int threshold = 500;
		int numOfCircles = 10;
		int x1, y1, x2, y2;
		ArrayList<Integer> Xcord1 = new ArrayList<>();
		ArrayList<Integer> Ycord1 = new ArrayList<>();

		// Edge Connections
		ArrayList<Integer> distance = new ArrayList<>();
		ArrayList<Integer> indexXYcord1 = new ArrayList<>();
		ArrayList<Integer> indexXYcord2 = new ArrayList<>();
		ArrayList<Integer> XcordEdge1 = new ArrayList<>();
		ArrayList<Integer> YcordEdge1 = new ArrayList<>();
		ArrayList<Integer> XcordEdge2 = new ArrayList<>();
		ArrayList<Integer> YcordEdge2 = new ArrayList<>();

		// This adds the circles to the scene
		Random rand = new Random();
		for (int i = 0; i < numOfCircles; i++) {
			int rand1X = rand.nextInt(SceneSizeX); // Sets the Boundaries where the circles can appear
			int rand1Y = rand.nextInt(SceneSizeY);
			Xcord1.add(rand1X);
			Ycord1.add(rand1Y);
		}

		// This is the decision making for the connection process
		// int[] temp;
		for (int x = 0; x < numOfCircles - 1; x++) {
			x1 = Xcord1.get(x);
			y1 = Ycord1.get(x);
			for (int y = 1; y < numOfCircles; y++) {
				x2 = Xcord1.get(y);
				y2 = Ycord1.get(y);

				// If its within the radius then make the connection
				if ((int) Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2)) < threshold) {
					// if (Math.abs(x1 - x2) < threshold && Math.abs(y1 - y2) < threshold) {
					Line Edge = new Line(x1, y1, x2, y2);

					int dist = (int) Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));

					Text t = new Text((Math.abs(x1 + x2) / 2) + 5, (Math.abs(y1 + y2) / 2) - 5, Integer.toString(dist));

					distance.add(dist);
					// indexXYcord1.add(x);
					indexXYcord2.add(y);
					t.setFont(new Font(textSize));
					Edge.setStroke(Color.WHITE);
					t.setFill(text);
					t.setStrokeWidth(4);
					// Edge.setStrokeWidth(2);
					root.getChildren().addAll(t, Edge);
					primaryStage.show();
				}
				for (int z = x + 1; z < numOfCircles; z++) {
					x2 = Xcord1.get(z);
					y2 = Ycord1.get(z);
					if ((int) Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2)) < threshold) {
						// if (Math.abs(x1 - x2) < threshold && Math.abs(y1 - y2) < threshold) {

						Line Edge = new Line(x1, y1, x2, y2);
						int dist = (int) Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));

						Text t = new Text((Math.abs(x1 + x2) / 2) + 5, (Math.abs(y1 + y2) / 2) - 5,
								Integer.toString(dist));
						t.setFont(new Font(textSize));
						Edge.setStroke(Color.WHITE);
						t.setFill(text);
						t.setStrokeWidth(4);
						// Edge.setStrokeWidth(2);
						root.getChildren().addAll(t, Edge);
						primaryStage.show();
					}
				}

				// int newint = distance.indexOf(Collections.min(distance));

			}
		}
		Circle start = new Circle(Xcord1.get(0), Ycord1.get(0), 5, Color.BLUE); // First dot
		Circle end = new Circle(Xcord1.get(numOfCircles - 1), Ycord1.get(numOfCircles - 1), 5, Color.RED); // Last Dot
		root.getChildren().addAll(start, end);
		primaryStage.show();

		for (int z = 1; z < numOfCircles - 1; z++) {
			Circle Node1 = new Circle(Xcord1.get(z), Ycord1.get(z), 5, Color.YELLOW);

			root.getChildren().addAll(Node1);
			primaryStage.show();
		}

		// Start of shortest path
		// for(int newindex = 0; newindex < distance.size(); newindex++)
		// {
		// if(Xcord1.get(0)==Xcord1.get(indexXYcord1.get(newindex)) &&
		// Ycord1.get(0)==Ycord1.get(indexXYcord1.get(newindex)))
		// {
		// Line Short = new Line(Xcord1.get(indexXYcord1.get(newindex)),
		// Ycord1.get(indexXYcord1.get(newindex)),
		// Xcord1.get(indexXYcord1.get(newindex)),
		// Ycord1.get(indexXYcord1.get(newindex)));
		// Short.setStroke(Color.BLUE);
		// root.getChildren().addAll(Short);
		// primaryStage.show();
		// }
		// else {
		// Line EdgeShort = new Line(0,0,1000+(newindex*300),200+(newindex*300));
		// EdgeShort.setStroke(Color.YELLOW);
		// root.getChildren().add(EdgeShort);
		// primaryStage.show();
		//
		// }
		// }

		// ArrayList<Integer> Temp = new ArrayList<>();
		// ArrayList<Integer> XYxyEdge = new ArrayList<>();
		//
		// for(int i = 0; i< 1000; i++) {
		// if(XcordEdge1.get(i)==Xcord1.get(0) && YcordEdge1.get(i)==Ycord1.get(0))
		// {
		// int xdiff = (int)
		// Math.pow(Math.abs((XcordEdge1.get(i)-XcordEdge2.get(i))),2);
		// int ydiff = (int)
		// Math.pow(Math.abs((YcordEdge1.get(i)-YcordEdge2.get(i))),2);
		// int distance = (int) Math.sqrt(xdiff+ydiff);
		// Temp.add(distance);
		// XYxyEdge.add(i);
		// Line EdgeShort = new
		// Line(XcordEdge1.get(i),YcordEdge1.get(i),XcordEdge2.get(i),YcordEdge2.get(i));
		// EdgeShort.setStroke(Color.YELLOW);
		// root.getChildren().add(EdgeShort);
		// primaryStage.show();
		// }
		// else if(XcordEdge2.get(i)==Xcord1.get(0) && YcordEdge2.get(i)==Ycord1.get(0))
		// {
		// int xdiff = (int)
		// Math.pow(Math.abs((XcordEdge1.get(i)-XcordEdge2.get(i))),2);
		// int ydiff = (int)
		// Math.pow(Math.abs((YcordEdge1.get(i)-YcordEdge2.get(i))),2);
		// int distance = (int) Math.sqrt(xdiff+ydiff);
		// Temp.add(distance);
		// XYxyEdge.add(i);
		// Line EdgeShort = new
		// Line(XcordEdge1.get(i),YcordEdge1.get(i),XcordEdge2.get(i),YcordEdge2.get(i));
		// EdgeShort.setStroke(Color.YELLOW);
		// root.getChildren().add(EdgeShort);
		// primaryStage.show();
		// }
		// else {
		// Line EdgeShort = new Line(0,0,1000+(i*300),200+(i*300));
		// EdgeShort.setStroke(Color.YELLOW);
		// root.getChildren().add(EdgeShort);
		// primaryStage.show();
		//
		// }
		// }
		// int newint = Temp.indexOf(Collections.min(Temp));
		// Line EdgeShort = new Line(XcordEdge1.get(XYxyEdge.get(newint)),
		// YcordEdge1.get(XYxyEdge.get(newint)),
		// XcordEdge2.get(XYxyEdge.get(newint)),
		// YcordEdge1.get(XYxyEdge.get(newint)));
		// EdgeShort.setStroke(Color.YELLOW);
		// root.getChildren().add(EdgeShort);
		// primaryStage.show();
		//

	}// End of Start Method

	public static void main(String[] args) {
		launch(args);
	}
}