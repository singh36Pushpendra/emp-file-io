package emp.file.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileOperationsDemo {

    public static void main(String[] args) {
        final String DOWNLOADS_PATH = "C:\\Users\\micro\\Downloads";

        Path path = Paths.get(DOWNLOADS_PATH + "\\emp-info.txt");

        // Checking file existence.
        boolean exists = Files.exists(path);
        System.out.println("File exists? : " + exists);

        if (exists) {
            try {
                Files.delete(path);
                System.out.println("File Deleted!");
                System.out.println("Check File Exist & Removed[true/false]? : " + Files.deleteIfExists(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Path dirPath = Paths.get(DOWNLOADS_PATH + "\\mydir");
        Path filePath = Paths.get(DOWNLOADS_PATH + "\\myfile.txt");
        try {
            System.out.println("Created Directory: " + Files.createDirectory(dirPath));
            System.out.println("Created File: " + Files.createFile(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Path dirDownloads = Paths.get(DOWNLOADS_PATH);

        try (Stream<Path> list = Files.list(dirDownloads)) {
            System.out.println("Listing files and directories from: " + dirDownloads);
            for (Path location :
                    list.toList()) {
                System.out.println(location);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}