import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Object class for a Node.
 *
 * A Node is a single piece of data the encapsulates the path, name, number of files,
 * size of contents, and children of a directory.
 *
 * @author Andre Fernandez
 *
 */
public class Node extends TreeItem<Node> {

    // Fields_________________________________________________________________________________________

    Path path;
    String folderName;
    int filesInside;
    int sizeOfFiles;
    ArrayList<String> branches;

    // Constructors___________________________________________________________________________________

    /**
     * Constructs a Node object using the data passed in.
     * @param path
     * @param folderName
     * @param filesInside
     * @param sizeOfFiles
     * @param branches
     */
    public Node(Path path, String folderName, int filesInside, int sizeOfFiles, ArrayList<String> branches) {
        this.path = path;
        this.folderName = folderName;
        this.filesInside = filesInside;
        this.sizeOfFiles = sizeOfFiles;
        this.branches = branches;
    } // end Node()

    /**
     * Constructs a Node object by gathering the data from the Path passed in.
     * @param path
     * @throws Exception
     */
    public Node(Path path) throws Exception {
        createNode(path);
    } // end Node()

    // Getters and Setters____________________________________________________________________________

    /**
     * Returns the path of the Node.
     * @return
     */
    public Path getPath() { return path; }

    /**
     * Sets the path of the Node.
     * @param path
     */
    public void setPath(Path path) { this.path = path; }

    /**
     * Returns the directory name of the Node.
     * @return
     */
    public String getFolderName() { return folderName; }

    /**
     * Sets the directory name of the Node.
     * @param folderName
     */
    public void setFolderName(String folderName) { this.folderName = folderName; }

    /**
     * Returns the number of files found inside of the Node.
     * @return
     */
    public int getFilesInside() { return filesInside; }

    /**
     * Sets the number of files found inside of the Node.
     * @param filesInside
     */
    public void setFilesInside(int filesInside) { this.filesInside = filesInside; }

    /**
     * Returns the size of the contents found in the Node.
     * @return
     */
    public int getSizeOfFiles() { return sizeOfFiles; }

    /**
     * Sets the size of the contents found in the Node.
     * @param sizeOfFiles
     */
    public void setSizeOfFiles(int sizeOfFiles) { this.sizeOfFiles = sizeOfFiles; }

    /**
     * Returns the children of the Node.
     * @return
     */
    public ArrayList<String> getBranches() { return branches; }

    /**
     * Sets the children of the Node.
     * @param branches
     */
    public void setBranches(ArrayList<String> branches) { this.branches = branches; }

    // Methods________________________________________________________________________________________

    /**
     * Returns the directory name of a Node.
     * @return
     */
    public String toString() {
        String result = this.folderName;
        return result;
    } // end toString()

    /**
     * Used to set the values of a Node from the path that is passed in.
     * @param path
     * @throws Exception
     */
    public void createNode(Path path) throws Exception {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        DirectoryStream<Path> paths = Files.newDirectoryStream(path);

        // Setting the path
        setPath(path);

        // Setting the folder name
        setFolderName(path.getFileName().toString());

        // Setting the number of files inside
        setFilesInside(getFilesCount(new File(String.valueOf(path))));

        // Setting the size of the contents
        long size = getFilesSize(this.path);
        setSizeOfFiles((int)size);

        // Setting the branches
        ArrayList<String> branches= findBranches(this.path);
        branches.remove(0);
        setBranches(branches);

    } // end createNode()

    /**
     * Returns the size of the contents found in the Node in bytes.
     * @param path
     * @return
     * @throws IOException
     */
    public static long getFilesSize(Path path) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        long count = 0;

        if (attr.isDirectory()) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            for (Path p : paths)
                count+=getFilesSize(p);
        }else {
            count += attr.size();
        }
        return count;
    } // end getFilesSize()

    /**
     * Returns the number of files found in the Node.
     * @param file
     * @return
     */
    public static int getFilesCount(File file) {
        File[] files = file.listFiles();
        int count = 0;
        for (File f : files)
            if (f.isDirectory())
                count += getFilesCount(f);
            else
                count++;

        return count;
    } // end getFilesCount()

    /**
     * Used to find the child branches of the Node, and returns as an ArrayList.
     * @param path
     * @return
     * @throws Exception
     */
    public static ArrayList<String> findBranches(Path path) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        if(attr.isDirectory()) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
                result.add(path.getFileName().toString());
            for(Path p : paths)
                result.addAll(findBranches(p));
        } // end if
        return result;
    } // end findBranches()

} // end Node