import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;

public class SystemMetricsCollector {

    private final OperatingSystemMXBean sysBean;

    public SystemMetricsCollector() {
        this.sysBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    // Returns CPU usage percentage
    public double fetchCpuLoad() {
        double load = sysBean.getCpuLoad() * 100;
        if (load < 0) return 0.0;
        return Math.round(load * 10.0) / 10.0; // Clean 1-decimal precision
    }

    // Returns RAM usage percentage
    public double fetchRamUsage() {
        long totalMem = sysBean.getTotalMemorySize();
        long freeMem = sysBean.getFreeMemorySize();
        long usedMem = totalMem - freeMem;
        
        double usedPercentage = ((double) usedMem / totalMem) * 100;
        return Math.round(usedPercentage * 10.0) / 10.0;
    }

    // Returns Available Disk space in GB
    public double fetchAvailableDiskSpace() {
        File rootDrive = new File("C:"); // Windows default root
        if (!rootDrive.exists()) {
            rootDrive = new File("/"); // Fallback for Linux/Mac
        }
        double freeGb = (double) rootDrive.getFreeSpace() / (1024 * 1024 * 1024);
        return Math.round(freeGb * 100.0) / 100.0;
    }
}