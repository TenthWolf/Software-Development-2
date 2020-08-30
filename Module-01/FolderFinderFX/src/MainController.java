import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Controller class for Main.fmxl GUI.
 *
 * @author Andre Fernandez
 *
 */
public class MainController {

    // Path to the directory that the user selects
    private static Path selectedPath = null;

    // Path for the project folder used in the default TreeView
    Path currentPath = Paths.get(System.getProperty("user.dir"));

    //FXML Elements
    @FXML
    TreeView <Node> treeView;
    @FXML
    private TextField newDirectory;
    @FXML
    private Button submit;
    @FXML
    private Text stats;

    /**
     * Initializes the GUI, and either loads the default tree, or the user selected tree.
     * @throws Exception
     */
    public void initialize() throws Exception {
        Node temp = null;

        if(selectedPath != null) {
            temp = new Node(selectedPath);
            System.out.println(getNodeData(temp));
            TreeItem<Node> root = new TreeItem<>(temp);
            createTree(selectedPath, 0, root);
            root.setExpanded(true);
            treeView.setRoot(root);
            treeView.setRoot(root.getChildren().get(0));
        } else {
            temp = new Node(currentPath);
            System.out.println(currentPath.toString());
            System.out.println(getNodeData(temp));
            TreeItem<Node> root = new TreeItem<>(temp);
            createTree(currentPath, 0, root);
            root.setExpanded(true);
            treeView.setRoot(root.getChildren().get(0));
        }

    } // end initialize()

    /**
     * Grabs the path inputted by the user when the Submit button is triggered.
     * @throws Exception
     */
    public void submitButtonAction() throws Exception {
        Path path = Paths.get(newDirectory.getText());
        selectedPath = path;
        System.out.println("Successfully loaded " + path.toString() + " as path");
        initialize();
    } // end submitButtonAction()

    /**
     * Builds a tree from the root node passed in.
     * @param path
     * @param depth
     * @param parent
     * @throws Exception
     */
    public static void createTree(Path path, int depth, TreeItem<Node> parent) throws Exception {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        // If this is a directory, list all directories inside
        if(attr.isDirectory()) {

            // Start Directory Stream to view contents
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);

            System.out.println(spacesForDepth(depth) + " >" + path.getFileName());

            // Create new branch
            TreeItem<Node> item = new TreeItem<>(new Node(path));
            item.setExpanded(true);

            // Add branch to parent
            parent.getChildren().add(item);

            // Recursion to create all branches from root path
            for(Path p : paths)
                createTree(p, depth + 1, item);
        } // end if

    } // end createTree()

    /**
     * Used to count the spaces needed for a new line in the tree printing out in the console.
     * @param depth
     * @return
     */
    public static String spacesForDepth(int depth) {
        StringBuilder builder = new StringBuilder();

        for(int i=0; i < depth; i++ )
            builder.append("  ");

        return builder.toString();
    } // end spacesForDepth()

    /**
     * Used to read the properties of a Node object, and return them as a String.
     * @param node
     * @return
     */
    public static String getNodeData(Node node){
        String result = "";
        result += "Folder Name: " + node.folderName + "\n";
        result += "Files Contained: " + node.filesInside + "\n";
        result += "Size of Contents: " + node.sizeOfFiles + " bytes\n";
        result += "Child Folders: " + node.branches.toString();
        return result;
    } // end getNodeData()

    /**
     * Presents the properties of the Node selected in the TreeView to the user.
     * @param mouseEvent
     */
    public void selectNode(MouseEvent mouseEvent) {
        TreeItem<Node> item = treeView.getSelectionModel().getSelectedItem();
        String results = getNodeData(item.getValue());
        stats.setText(results);
    } // end selectNode()

} // end MainController