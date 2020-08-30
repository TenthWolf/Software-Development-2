import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Path.*;
import static java.nio.file.Paths.get;



public class Main {

    public static void main(String[] args) throws Exception {

        Path currentPath = Paths.get(System.getProperty("user.dir"));
        System.out.println("Current path: " + currentPath.toString());
        listPath(currentPath,0);
    } // end main()

    public static void listPath(Path path, int depth) throws Exception {


        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

        // If this is a directory, list all directories inside
        if(attr.isDirectory()) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            System.out.println(spacesForDepth(depth) + " >" + path.getFileName());

            for(Path p : paths)
                listPath(p, depth + 1);

        }

    } // end listDir

    public static String spacesForDepth(int depth) {
        StringBuilder builder = new StringBuilder();

        for(int i=0; i < depth; i++ )
            builder.append("  ");

        return builder.toString();
    }


} // end Main
