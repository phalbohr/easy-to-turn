import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hauptklasse zur Überprüfung, ob ein Baum drehfreudig ist.
 * Liest Eingabedateien, verarbeitet Bäume und zeigt Ergebnisse an.
 */
public class DrehfreudigChecker {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Verwendung: java DrehfreudigChecker <dateiname1> [dateiname2] [dateiname3] ...");
            System.out.println("Oder ohne Argumente alle Dateien in docs/aufgaben/ verarbeiten");
            processAllFiles();
            return;
        }

        // Jede Datei-Argument verarbeiten
        for (String filename : args) {
            System.out.println("\n=== Verarbeite " + filename + " ===");
            processFile(filename);
        }
    }

    /**
     * Verarbeitet alle Dateien im Aufgaben-Verzeichnis.
     */
    private static void processAllFiles() {
        try {
            Path aufgabenDir = Paths.get("docs", "aufgaben");
            if (!Files.exists(aufgabenDir)) {
                System.out.println("Verzeichnis docs/aufgaben nicht gefunden!");
                return;
            }

            try (Stream<Path> paths = Files.list(aufgabenDir)) {
                paths.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".txt"))
                        .sorted()
                        .forEach(path -> {
                            System.out.println("\n=== Verarbeite " + path.getFileName() + " ===");
                            processFile(path.toString());
                        });
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen des Verzeichnisses: " + e.getMessage());
        }
    }

    /**
     * Verarbeitet eine einzelne Datei.
     *
     * @param filename die zu verarbeitende Datei
     */
    private static void processFile(String filename) {
        try {
            String content = readFile(filename);
            if (content == null) {
                System.out.println("Datei konnte nicht gelesen werden: " + filename);
                return;
            }

            // Parse den Baum
            TreeParser parser = new TreeParser();
            Node root = parser.parse(content.trim());

            if (root == null) {
                System.out.println("Ungültige Baumstruktur in Datei: " + filename);
                return;
            }

            // Berechne Breiten und Tiefen
            WidthCalculator calculator = new WidthCalculator();
            int totalWidth = calculator.calculateTotalWidth(root);
            calculator.assignWidthsAndDepths(root, totalWidth);

            // Hole Blattbreiten und -tiefen, dann prüfe Drehfreudig-Bedingungen
            List<Integer> leafWidths = calculator.getLeafWidths(root);
            List<Integer> leafDepths = calculator.getLeafDepths(root);
            boolean isWidthPalindrome = calculator.isWidthPalindrome(leafWidths);
            boolean isConstantDepthSum = calculator.isConstantDepthSum(leafDepths);
            boolean isDrehfreudig = isWidthPalindrome && isConstantDepthSum;

            // Ausgabe Ergebnis
            System.out.println("Baum: " + content.trim());
            System.out.println("Blattbreiten: " + leafWidths);
            System.out.println("Breitenprüfung: " + (isWidthPalindrome ? "bestanden" : "nicht bestanden"));

            if (isWidthPalindrome) {
                System.out.println("Blatttiefen: " + leafDepths);
                System.out.println("Tiefenprüfung: " + (isConstantDepthSum ? "bestanden" : "nicht bestanden"));
            }

            System.out.println("Ergebnis: " + (isDrehfreudig ? "DREHFREUDIG" : "NICHT DREHFREUDIG"));

            // Zeige Baum an, wenn drehfreudig
            if (isDrehfreudig) {
                System.out.println("\nBaumvisualisierung:");
                TreeDisplay display = new TreeDisplay();
                display.display(root);
            }

        } catch (Exception e) {
            System.out.println("Fehler bei der Verarbeitung der Datei " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Liest den Inhalt einer Datei.
     *
     * @param filename die zu lesende Datei
     * @return der Dateiinhalt oder null bei Fehler
     */
    private static String readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}