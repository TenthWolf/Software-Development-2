import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

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
    private Button saveButton;
    @FXML
    private TableView taskView;

    /**
     * Initializes the GUI
     */
    public void initialize() {
        getDatabaseItems();
        updateTable();
    } // end initialize()

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

        updateTable();
    } // end addTask()

    /**
     * Deletes the selected TaskItem from the working data
     */
    public void deleteTask() {
        tasks.remove(taskView.getSelectionModel().getSelectedItem());
        updateTable();
    } // end deleteTask()

    /**
     * Saves information from the database using hibernation
     */
    public void saveButtonAction() {
        flushDatabase();

        Session session = Main.factory.getCurrentSession();

        try {
            // Start transaction
            session.beginTransaction();

            // Save TaskItems
            System.out.println("[MainController.saveButtonAction()] Saving all TaskItems to database...");
            for(TaskItem t : tasks) {
                session.save(t);
                System.out.println("    > " + t);
            }

            // Commit transaction
            System.out.println("[MainController.saveButtonAction()] Committing changes to database...");
            session.getTransaction().commit();

            System.out.println("[MainController.saveButtonAction()] Successfully saved TaskItems to database!");

            Main.primaryStage.close();
        } finally {
            System.out.println("[MainController.saveButtonAction()] Closing the save session!");
            session.close();
        }
    } // end saveButtonAction

    /**
     * Retrieves saved information from the database using hibernation
     */
    public void getDatabaseItems() {
        Session session = Main.factory.getCurrentSession();

        try {
            // Start transaction
            System.out.println("[MainController.getDatabaseItems()] Retrieving TaskItems from database...");
            session.beginTransaction();

            List<TaskItem> temp = session.createQuery("from TaskItem").list();

            if (temp.size() > 0) {
                for (TaskItem t : temp) {
                    tasks.add(t);
                    System.out.println("    > " + t);
                }
            } else System.out.println("[MainController.getDatabaseItems()] The database was empty.");
            session.getTransaction().commit();

        } finally {
            session.close();
            System.out.println("[MainController.getDatabaseItems()] Closing the retrieve session!");
        }

    } // end getDatabaseItems()

    /**
     * Deletes the old data from the database using hibernation, so that no overwrite errors occur.
     */
    public void flushDatabase(){
        Session session = Main.factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from TaskItem").executeUpdate();
            session.getTransaction().commit();
            System.out.println("[MainController.flushDatabase()] Successfully flushed old data from database!");
        } finally {
            session.close();
        }

    } // end flushDataBase


} // end MainController
