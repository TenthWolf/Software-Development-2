import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

/**
 * @author Andre Fernandez
 *
 * Controller class for the Main.fxml GUI
 */
public class MainController {

    // Working data
    ArrayList<TaskItem> tasks = new ArrayList<TaskItem>();

    // List that will populate the table view
    ObservableList<TaskItem> tableItems = FXCollections.observableArrayList();

    // FXML elements
    @FXML
    private TextField enterTask;
    @FXML
    private DatePicker enterDate;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView taskView;

    /**
     * Initializes the GUI
     */
    public void initialize() { updateTable(); } // end initialize()

    /**
     * Refreshes the TableView with the most updated tasks
     */
    public void updateTable(){
        // Clearing old view
        tableItems.clear();
        taskView.getItems().clear();
        taskView.getColumns().clear();

        for(TaskItem t : tasks)
            tableItems.add(t);

        TableColumn<TaskItem, String> taskColumn = new TableColumn<>("Task");
        taskColumn.setMinWidth(400);
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));

        TableColumn<TaskItem, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(198);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


        taskView.setItems(tableItems);
        taskView.getColumns().addAll(taskColumn, dateColumn);
    } // end updateTable()

    /**
     * Takes the data entered from the user, creates a new TaskItem,
     * and adds it to the working task list
     */
    public void addTask() {
        String task = enterTask.getText();
        String date = enterDate.getValue().toString();
        TaskItem newItem = new TaskItem(task, date);
        tasks.add(newItem);

        enterTask.clear();
        enterDate.setValue(null);

        initialize();
    } // end addTask()

    /**
     * Deletes the selected TaskItem from the working data
     */
    public void deleteTask() {
        tasks.remove(taskView.getSelectionModel().getSelectedItem());
        initialize();
    } // end deleteTask()
    
} // end MainController
