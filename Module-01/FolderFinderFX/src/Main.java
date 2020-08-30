import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;



public class Main extends Application {

    private static Stage primaryStage;
    private static AnchorPane mainLayout;

    /**
     * Builds the main view of the border pane.
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
        Main.primaryStage.setTitle("Word Occurrence Counter");
        mainView();
    } // end start()


    /**
     * Launches the application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    } // end main()

} //end Main
