import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvExporter {

    private final String filePath = "system_logs.csv";
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CsvExporter() {
        File file = new File(filePath);
        // Create file with headers if it doesn't exist
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                writer.println("Timestamp,CPU (%),RAM (%),Free Disk (GB)");
            } catch (IOException e) {
                System.out.println("Error initializing CSV file: " + e.getMessage());
            }
        }
    }

    public void saveRecord(double cpu, double ram, double disk) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String currentTime = LocalDateTime.now().format(timeFormat);
            writer.println(currentTime + "," + cpu + "," + ram + "," + disk);
        } catch (IOException e) {
            System.out.println("Failed to write metrics to log file.");
        }
    }
}