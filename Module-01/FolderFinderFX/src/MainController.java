import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;


public class MainController {

    @FXML
    TreeItem <String> currentParent;

    Path currentPath = Paths.get(System.getProperty("user.dir"));

    @FXML
    TreeView <String> treeView;


    public void initialize() throws Exception {
        TreeItem<String> root = new TreeItem<>(currentPath.getFileName().toString());
        currentParent = root;
        listPath(currentPath, 0, root);
        root.setExpanded(true);
        treeView.setRoot(root);

    }


    

    public static void listPath(Path path, int depth, TreeItem<String> parent) throws Exception {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        // If this is a directory, list all directories inside
        if(attr.isDirectory()) {

            // Start Directory Stream to view contents
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);

            System.out.println(spacesForDepth(depth) + " >" + path.getFileName());

            // Create new branch
            TreeItem<String> item = new TreeItem<>(path.getFileName().toString());
            item.setExpanded(true);

            // Add branch to parent
            parent.getChildren().add(item);


            // Recursion to create all branches from root path
            for(Path p : paths)
                listPath(p, depth + 1, item);
        } // end if

    } // end listPath

    public static String spacesForDepth(int depth) {
        StringBuilder builder = new StringBuilder();

        for(int i=0; i < depth; i++ )
            builder.append("  ");

        return builder.toString();
    } // end spacesForDepth


    public static TreeItem<String> makeBranch(String folderName, TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(folderName);
        item.setExpanded(true);
        parent.getChildren().add(item);
        System.out.println("[makeBranch] Added " + item.toString());
        return item;
    } // end makeBranch

} // end MainController

