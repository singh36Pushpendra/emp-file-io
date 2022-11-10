package emp.file.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchDirectory {
    public static void main(String[] args) {
        boolean poll = true;

        // Converting location to file path.
        Path path = Paths.get("C:/Users/micro/Desktop/Suit_camp/TerminalCommands/linux-content/thakur");

        // Activating watch service using try with-resource.
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {

            // Registering watch service on above path and passing events to be watched.
            path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

            ArrayList<String> logs = new ArrayList<>();

            while (poll) {
                // Reference/Pointer of current file or folder watching.
                WatchKey key = watchService.take();

                // Printing the events (create, modify, delete).
                key.pollEvents()
                        .stream()
                        .forEach(watchEvent -> {
                                    String eventMessage = "Event kind: " + watchEvent.kind() + " - File: " + watchEvent.context();
                                    System.out.println(eventMessage);
                                    logs.add(eventMessage);
                                });

                // Resetting the reference so that it can be used later.
                poll = key.reset();
            }
            // Assuming logs.txt is already present in Downloads folder.
            Path logFilePath = Paths.get("C:/Users/micro/Downloads/logs.txt");

            Files.write(logFilePath, logs, StandardOpenOption.APPEND);

            long count = Files.lines(logFilePath).count();
            System.out.println("Total number of lines in file: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}