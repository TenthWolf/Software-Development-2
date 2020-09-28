import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;

/**
 * @author Andre Fernandez
 * @version 2.0 - September 27, 2020
 *
 * Module 2 Assignment - ToDoListFX
 *
 * This program is a simple To-Do List application
 * that allows the user to add, delete, and view tasks.
 */

public class Main extends Application {
    public static Stage primaryStage;
    private static AnchorPane mainLayout;
    public static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(TaskItem.class)
            .buildSessionFactory();
    /**
     * Builds the main view of the anchor pane.
     * @throws IOException
     */
    private void mainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Main.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end mainView()

    /**
     * Starts the Main View.
     * @param primaryStage
     * @throws IOException
     */
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("To Do List");
        mainView();
    } // end start()

    /**
     * Launches the application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("[Main] Closing factory session...");
    } // end main()

} // end Main
