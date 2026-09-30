import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SystemMetricsCollector collector = new SystemMetricsCollector();
        AlertManager alertManager = new AlertManager();
        CsvExporter exporter = new CsvExporter();

        // Launch GUI Window
        AppWindow gui = new AppWindow();
        SwingUtilities.invokeLater(() -> gui.setVisible(true));

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(() -> {
            try {
                double cpu = collector.fetchCpuLoad();
                double ram = collector.fetchRamUsage();
                double disk = collector.fetchAvailableDiskSpace();

                // Update Swing GUI Live
                gui.updateUIValues(cpu, ram, disk);

                // Alert & Save Logic
                alertManager.evaluateMetrics(cpu, ram);
                exporter.saveRecord(cpu, ram, disk);

            } catch (Exception e) {
                System.out.println("Error reading system metrics: " + e.getMessage());
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}