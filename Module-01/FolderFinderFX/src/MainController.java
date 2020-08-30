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
    TreeItem<String> currentParent;

    Path currentPath = Paths.get(System.getProperty("user.dir"));

    @FXML
    private TreeView<String> tree;
    @FXML
    TreeItem<String> root = new TreeItem<String>(currentPath.toString());


    

    public static void listPath(Path path, int depth) throws Exception {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        // If this is a directory, list all directories inside
        if(attr.isDirectory()) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            System.out.println(spacesForDepth(depth) + " >" + path.getFileName());
            for(Path p : paths)
                listPath(p, depth + 1);
        } // end if

    } // end listPath

    public static String spacesForDepth(int depth) {
        StringBuilder builder = new StringBuilder();

        for(int i=0; i < depth; i++ )
            builder.append("  ");

        return builder.toString();
    } // end spacesForDepth


    public TreeItem<String> makeBranch(String folderName, TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(folderName);
        item.setExpanded(true);
        parent.getChildren().add(item);
        System.out.println("[makeBranch] Added " + item.toString());
        return item;
    } // end makeBranch

} // end MainController

