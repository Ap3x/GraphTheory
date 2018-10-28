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
		BorderPane root = new BorderPane();
		primaryStage.setScene(new Scene(root, SceneSizeX, SceneSizeY, Color.BLACK));
		int radiusThreshold = 200;
		int numOfCircles = 100;
		int x1, y1, x2, y2, x3, y3;
		ArrayList<Integer> Xcord1 = new ArrayList<>();
		ArrayList<Integer> Ycord1 = new ArrayList<>();

		// This adds the circles to the scene
		Random rand = new Random();
		for (int i = 0; i < numOfCircles; i++) {
			int rand1X = rand.nextInt(SceneSizeX); // Sets the Boundaries where the circles can appear
			int rand1Y = rand.nextInt(SceneSizeY);
			Xcord1.add(rand1X);
			Ycord1.add(rand1Y);
		}

		// This is the decision making for the connection process
		for (int x = 0; x < numOfCircles - 1; x++) {
			x1 = Xcord1.get(x);
			y1 = Ycord1.get(x);
			for (int y = 1; y < numOfCircles; y++) {
				x2 = Xcord1.get(y);
				y2 = Ycord1.get(y);

				// If its within the radius then make the connection
				if ((int) Math
						.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2)) < radiusThreshold) {

					Line Edge = new Line(x1, y1, x2, y2);
					Edge.setStroke(Color.DARKGRAY);
					// Edge.setStrokeWidth(1/12);
					root.getChildren().addAll(Edge);
					primaryStage.show();
				}
				for (int z = x + 1; z < numOfCircles; z++) {
					x2 = Xcord1.get(z);
					y2 = Ycord1.get(z);
					if ((int) Math
							.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2)) < radiusThreshold) {
						Line Edge = new Line(x1, y1, x2, y2);
						Edge.setStroke(Color.DARKGRAY);
						root.getChildren().addAll(Edge);
						primaryStage.show();
					}
				}

			}
		}
		Circle start = new Circle(Xcord1.get(0), Ycord1.get(0), 10, Color.BLUE); // First dot
		Circle end = new Circle(Xcord1.get(numOfCircles - 1), Ycord1.get(numOfCircles - 1), 10, Color.RED); // Last Dot
		root.getChildren().addAll(start, end);
		primaryStage.show();

		for (int z = 1; z < numOfCircles - 1; z++) {
			Circle Node1 = new Circle(Xcord1.get(z), Ycord1.get(z), 10, Color.YELLOW);

			root.getChildren().addAll(Node1);
			primaryStage.show();
		}

		double degreesFromStartToFinish = Math.toDegrees(
				Math.atan2(Xcord1.get(0) - Xcord1.get(numOfCircles - 1), Ycord1.get(0) - Ycord1.get(numOfCircles - 1)));
		int currentNodeX = Xcord1.get(0);
		int currentNodeY = Ycord1.get(0);
		int nextNodeX, nextNodeY;
		ArrayList<Integer> pointsToCheckX = new ArrayList<>();
		ArrayList<Integer> pointsToCheckY = new ArrayList<>();
		ArrayList<Double> dist = new ArrayList<>();
		boolean breakLoop = true;
		int count = 0;
		while (breakLoop) {
			for (int n = 1; n < numOfCircles; n++) {
				x3 = Xcord1.get(n);
				y3 = Ycord1.get(n);
				if ((int) Math.sqrt(Math.pow(Math.abs(currentNodeX - x3), 2)
						+ Math.pow(Math.abs(currentNodeY - y3), 2)) < radiusThreshold) {
					if (Math.toDegrees(Math.atan2(currentNodeX - x3, currentNodeY - y3)) > degreesFromStartToFinish - 90
							&& Math.toDegrees(
									Math.atan2(currentNodeX - x3, currentNodeY - y3)) < degreesFromStartToFinish + 90) {
						pointsToCheckX.add(x3);
						pointsToCheckY.add(y3);

						// Line Edge = new Line(currentNodeX, currentNodeY, x3, y3);
						// Edge.setStroke(Color.YELLOW);
						// Edge.setStrokeWidth(2);
						// root.getChildren().addAll(Edge);
						// primaryStage.show();
					}

				}
			}
			for (int b = 0; b < pointsToCheckX.size(); b++) {
				double degreesClosestToLine = Math.toDegrees(
						Math.atan2(currentNodeX - pointsToCheckX.get(b), currentNodeY - pointsToCheckY.get(b)));
				dist.add(degreesClosestToLine);
			}

			// Finds the closest angle

			double myNumber = degreesFromStartToFinish;

			double degrees = Math.abs(dist.get(0) - myNumber);
			int idx = 0;
			for (int c = 1; c <= dist.size()-1; c++) {
				double cdistance = Math.abs(dist.get(c) - myNumber);
				if (cdistance < degrees) {
					idx = c;
					degrees = cdistance;
				}
			}
			// int theNumber = dist.get(idx);
			// int minIndex = dist.indexOf(Collections.min(dist));
			nextNodeX = pointsToCheckX.get(idx);
			nextNodeY = pointsToCheckY.get(idx);
			degreesFromStartToFinish = Math.toDegrees(Math.atan2(currentNodeX - Xcord1.get(numOfCircles - 1),
					currentNodeY - Ycord1.get(numOfCircles - 1)));
			Line Edge = new Line(currentNodeX, currentNodeY, nextNodeX, nextNodeY);
			Edge.setStroke(Color.PURPLE);
			Edge.setStrokeWidth(5);
			root.getChildren().addAll(Edge);
			primaryStage.show();
			currentNodeX = nextNodeX;
			currentNodeY = nextNodeY;
			dist.clear();
			count++;
			if (Xcord1.get(numOfCircles - 1) == currentNodeX && Ycord1.get(numOfCircles -1) == currentNodeY) {
			breakLoop = false;
			}

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}