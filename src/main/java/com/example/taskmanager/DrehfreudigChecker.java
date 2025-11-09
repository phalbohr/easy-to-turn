import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Main class for checking if a tree is drehfreudig.
 * Reads input files, processes trees, and displays results.
 */
public class DrehfreudigChecker {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java DrehfreudigChecker <filename1> [filename2] [filename3] ...");
            System.out.println("Or run without args to process all files in docs/aufgaben/");
            processAllFiles();
            return;
        }

        // Process each file argument
        for (String filename : args) {
            System.out.println("\n=== Processing " + filename + " ===");
            processFile(filename);
        }
    }

    /**
     * Processes all files in the aufgaben directory.
     */
    private static void processAllFiles() {
        try {
            Path aufgabenDir = Paths.get("docs", "aufgaben");
            if (!Files.exists(aufgabenDir)) {
                System.out.println("Directory docs/aufgaben not found!");
                return;
            }

            try (Stream<Path> paths = Files.list(aufgabenDir)) {
                paths.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".txt"))
                        .sorted()
                        .forEach(path -> {
                            System.out.println("\n=== Processing " + path.getFileName() + " ===");
                            processFile(path.toString());
                        });
            }
        } catch (IOException e) {
            System.out.println("Error reading directory: " + e.getMessage());
        }
    }

    /**
     * Processes a single file.
     * 
     * @param filename the file to process
     */
    private static void processFile(String filename) {
        try {
            String content = readFile(filename);
            if (content == null) {
                System.out.println("Could not read file: " + filename);
                return;
            }

            // Parse the tree
            TreeParser parser = new TreeParser();
            Node root = parser.parse(content.trim());

            if (root == null) {
                System.out.println("Invalid tree structure in file: " + filename);
                return;
            }

            // Calculate widths and depths
            WidthCalculator calculator = new WidthCalculator();
            int totalWidth = calculator.calculateTotalWidth(root);
            calculator.assignWidthsAndDepths(root, totalWidth);

            // Get leaf widths and depths, then check palindrome
            List<Integer> leafWidths = calculator.getLeafWidths(root);
            List<Integer> leafDepths = calculator.getLeafDepths(root);
            boolean isDrehfreudig = calculator.isPalindrome(leafWidths);

            // Output result
            System.out.println("Tree: " + content.trim());
            System.out.println("Leaf widths: " + leafWidths);
            System.out.println("Leaf depths: " + leafDepths);
            System.out.println("Total width: " + totalWidth);
            System.out.println("Result: " + (isDrehfreudig ? "Drehfreudig" : "Nicht drehfreudig"));

            // Display tree if drehfreudig
            if (isDrehfreudig) {
                System.out.println("\nTree visualization:");
                TreeDisplay display = new TreeDisplay();
                display.display(root);
            }

        } catch (Exception e) {
            System.out.println("Error processing file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Reads the content of a file.
     * 
     * @param filename the file to read
     * @return the file content or null if error
     */
    private static String readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}