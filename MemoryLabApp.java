import java.util.ArrayList;
import java.util.List;

/**
 * Lab: JVM Memory Analysis
 * 
 * TODO: Complete this application to demonstrate memory behavior
 * 
 * Run with: java -Xms128m -Xmx256m -Xlog:gc*:file=gc.log MemoryLabApp
 */
public class MemoryLabApp {
    private static final int MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== JVM Memory Lab ===");
        printMemoryStatus("Initial");

        List<byte[]> memoryBlocks = new ArrayList<>();

        // TODO: Implement the following
        // 1. Allocate memory in a loop (e.g., 1MB chunks)
        // 2. Print memory status after each allocation
        // 3. Handle OutOfMemoryError gracefully
        // 4. Add a small delay between allocations for observation

        // Your code here:
        try {
            int count = 0;
            while (true) {
                // Allocate 1 MB
                byte[] block = new byte[MB];
                memoryBlocks.add(block);
                count++;

                printMemoryStatus("After allocating " + count + " MB");

                // Small delay to allow GC to occur
                Thread.sleep(200);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("⚠️ OutOfMemoryError caught!");
            printMemoryStatus("After OOM");
        }

        printMemoryStatus("Final");
    }

    /**
     * Helper method to print current memory status
     */
    private static void printMemoryStatus(String label) {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        System.out.printf("[%s] Max: %d MB, Total: %d MB, Used: %d MB, Free: %d MB%n",
                label, maxMemory, totalMemory, usedMemory, freeMemory);
    }
}
