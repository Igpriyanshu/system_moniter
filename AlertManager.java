public class AlertManager {

    private static final double MAX_CPU_LIMIT = 85.0;
    private static final double MAX_RAM_LIMIT = 80.0;

    public void evaluateMetrics(double cpu, double ram) {
        if (cpu >= MAX_CPU_LIMIT) {
            System.out.println(" -> [WARN] High CPU load detected: " + cpu + "%");
        }
        
        if (ram >= MAX_RAM_LIMIT) {
            System.out.println(" -> [WARN] Memory consumption critical: " + ram + "%");
        }
    }
}