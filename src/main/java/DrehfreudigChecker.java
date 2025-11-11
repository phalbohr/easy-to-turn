import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hauptklasse zur Überprüfung, ob ein in Klammernotation gegebener Baum "drehfreudig" ist.
 * <p>
 * Ein Baum ist drehfreudig, wenn zwei Bedingungen erfüllt sind:
 * 1. Die Sequenz der Breiten seiner Blätter ist ein Palindrom.
 * 2. Die Summe der Tiefen für jedes Paar symmetrischer Blätter ist konstant.
 * <p>
 * Das Programm kann eine oder mehrere Dateien als Kommandozeilenargumente verarbeiten oder, wenn keine Argumente angegeben sind, alle .txt-Dateien im Verzeichnis 'aufgaben/'.
 * @author Pavel Polukhin
 * @version 1.0
 */
public class DrehfreudigChecker {

    /**
     * Privater Konstruktor, um die Instanziierung dieser Utility-Klasse zu verhindern.
     */
    private DrehfreudigChecker() {
        // Diese Klasse sollte nicht instanziiert werden.
    }

    /**
     * Hauptmethode zum Ausführen des Programms.
     * @param args Kommandozeilenargumente, die die zu verarbeitenden Dateinamen enthalten
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Verwendung: java DrehfreudigChecker <dateiname1> [dateiname2] [dateiname3] ...");
            System.out.println("Oder ohne Argumente alle Dateien in aufgaben/ verarbeiten");
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
            Path aufgabenDir = Paths.get("aufgaben");
            if (!Files.exists(aufgabenDir)) {
                System.out.println("Verzeichnis aufgaben nicht gefunden!");
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
     * @param filename der Name der zu verarbeitenden Datei
     * @throws IllegalArgumentException wenn die Baumstruktur in der Datei ungültig ist.
     */
    private static void processFile(String filename) {
        try {
            String content = readFile(filename);
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("Die Datei ist leer oder enthält keine Baumstruktur.");
            }

            // Parse den Baum
            TreeParser parser = new TreeParser();
            String trimmedContent = content.trim();
            Node root = parser.parse(trimmedContent);

            if (root == null) {
                throw new IllegalArgumentException("Ungültige Baumstruktur in Datei: " + filename);
            }

            // Berechne Breiten und Tiefen
            WidthAndDepthCalculator calculator = new WidthAndDepthCalculator();
            int totalWidth = calculator.calculateTotalWidth(root);
            calculator.assignWidthsAndDepths(root, totalWidth);

            // Hole Blattbreiten und -tiefen, dann prüfe Drehfreudig-Bedingungen
            List<Integer> leafWidths = calculator.getLeafWidths(root);
            List<Integer> leafDepths = calculator.getLeafDepths(root);
            boolean isWidthPalindrome = calculator.isWidthPalindrome(leafWidths);
            boolean isConstantDepthSum = calculator.isConstantDepthSum(leafDepths);

            // Ausgabe Ergebnis
            System.out.println("Baum: " + trimmedContent);
            System.out.println("Blattbreiten: " + leafWidths);
            System.out.println("Breitenprüfung: " + (isWidthPalindrome ? "bestanden" : "nicht bestanden"));

            if (isWidthPalindrome) {
                System.out.println("Blatttiefen: " + leafDepths);
                System.out.println("Tiefenprüfung: " + (isConstantDepthSum ? "bestanden" : "nicht bestanden"));
            }

            boolean isDrehfreudig = isWidthPalindrome && isConstantDepthSum;
            System.out.println("Ergebnis: " + (isDrehfreudig ? "DREHFREUDIG" : "NICHT DREHFREUDIG"));

            // Zeige Baum an, wenn drehfreudig
            if (isDrehfreudig) {
                System.out.println("\nBaumvisualisierung:");
                TreeDisplay display = new TreeDisplay();
                display.display(root);
            }

        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei '" + filename + "': " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Fehler in der Eingabe für Datei '" + filename + "': " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ein unerwarteter Fehler ist bei der Verarbeitung von '" + filename + "' aufgetreten: " + e.getMessage());
        }
    }

    /**
     * Liest den Inhalt einer Datei.
     * @param filename der Name der zu lesenden Datei
     * @return der Inhalt der Datei als String oder null bei einem Fehler
     * @throws IOException wenn ein Fehler beim Lesen der Datei auftritt.
     */
    private static String readFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        }
    }
}