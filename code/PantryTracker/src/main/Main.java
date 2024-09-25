package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for the Pantry Tracking program.
 * 
 * @author jhand1
 * @version 1.0
 */
public class Main extends Application {

	/**
	 * JavaFX entry point.​ ​
	 * 
	 * @precondition none​
	 * @postcondition none​ ​
	 * @throws IOException unable to load fxml for MainWindow​
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
		AnchorPane root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pantry Tracker");
		primaryStage.show();
	}

	/**
	 * Primary Java entry point.​ ​
	 * 
	 * @precondition none​
	 * @postcondition none​ ​
	 * @param args command line arguments​
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}
}