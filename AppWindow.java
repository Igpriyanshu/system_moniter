import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    private JProgressBar cpuBar;
    private JProgressBar ramBar;
    private JLabel diskLabel;
    private JLabel statusLabel;

    public AppWindow() {
        // Window Configuration
        setTitle("System Resource Monitor v1.0");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        // CPU Section
        JPanel cpuPanel = new JPanel(new BorderBagLayout());
        JLabel cpuTitle = new JLabel("CPU Load: ");
        cpuBar = new JProgressBar(0, 100);
        cpuBar.setStringPainted(true);
        cpuBar.setForeground(new Color(46, 134, 193));

        // RAM Section
        JLabel ramTitle = new JLabel("RAM Usage: ");
        ramBar = new JProgressBar(0, 100);
        ramBar.setStringPainted(true);
        ramBar.setForeground(new Color(155, 89, 182));

        // Disk Section
        diskLabel = new JLabel("Free Disk Space: Checking...", SwingConstants.CENTER);
        diskLabel.setFont(new Font("SansSerif", Font.BOLD, 13));

        // Status Alert Label
        statusLabel = new JLabel("Status: System Normal", SwingConstants.CENTER);
        statusLabel.setForeground(new Color(39, 174, 96));

        // Add components to Window
        add(createCardPanel("CPU Monitor", cpuBar));
        add(createCardPanel("RAM Monitor", ramBar));
        add(diskLabel);
        add(statusLabel);
    }

    private JPanel createCardPanel(String title, JProgressBar bar) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.add(bar, BorderLayout.CENTER);
        return panel;
    }

    // Live Metrics Update Method
    public void updateUIValues(double cpu, double ram, double disk) {
        SwingUtilities.invokeLater(() -> {
            cpuBar.setValue((int) cpu);
            cpuBar.setString(cpu + "%");

            ramBar.setValue((int) ram);
            ramBar.setString(ram + "%");

            diskLabel.setText("Free Disk Space (C:): " + disk + " GB");

            if (cpu > 85.0 || ram > 80.0) {
                statusLabel.setText("⚠️ Warning: High Resource Usage!");
                statusLabel.setForeground(Color.RED);
            } else {
                statusLabel.setText("Status: System Running Smoothly");
                statusLabel.setForeground(new Color(39, 174, 96));
            }
        });
    }
}
