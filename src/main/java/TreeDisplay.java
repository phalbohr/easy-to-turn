import java.util.ArrayList;
import java.util.List;

/**
 * Behandelt die Anzeige der Baumstruktur mit ASCII-Kunst.
 * Verwendet die berechneten Breiten, um eine proportionale Darstellung zu erstellen.
 */
public class TreeDisplay {

    /**
     * Erstellt eine neue Instanz des TreeDisplay.
     */
    public TreeDisplay() {}

    /**
     * Zeigt den Baum in einem visuellen Format an.
     * 
     * @param root der Wurzelknoten des Baumes
     */
    public void display(Node root) {
        if (root == null)
            return;

        List<String> lines = new ArrayList<>();
        buildDisplay(root, 0, 0, lines);

        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Baut rekursiv die Anzeigezeilen für den Baum auf.
     * 
     * @param node   aktueller Knoten
     * @param level  aktuelle Tiefenebene
     * @param offset horizontaler Versatz
     * @param lines  Liste zum Sammeln der Anzeigezeilen
     * @return die von diesem Teilbaum verwendete Breite
     */
    private int buildDisplay(Node node, int level, int offset, List<String> lines) {
        // Sicherstellen, dass wir genügend Zeilen haben
        while (lines.size() <= level) {
            lines.add("");
        }

        int nodeWidth = node.getWidth();
        String nodeStr;

        // Spezielle Anzeige für alle Knoten basierend auf der Breite (gleiche Logik für Blätter und interne Knoten)
        if (nodeWidth == 1) {
            nodeStr = "1";
        } else if (nodeWidth == 2) {
            nodeStr = "[]";
        } else {
            // Für Breite >= 3: [....Zahl....] mit Gesamtbreite an Zeichen
            String numStr = String.valueOf(nodeWidth);
            int dotsNeeded = nodeWidth - numStr.length() - 2; // -2 für Klammern
            int leftDots = dotsNeeded / 2;
            int rightDots = dotsNeeded - leftDots;
            nodeStr = "[" + ".".repeat(leftDots) + numStr + ".".repeat(rightDots) + "]";
        }

        // Zentriert den Knoten in seiner zugewiesenen Breite
        int padding = (nodeWidth - nodeStr.length()) / 2;
        String paddedNode = " ".repeat(Math.max(0, padding)) + nodeStr +
                " ".repeat(Math.max(0, nodeWidth - nodeStr.length() - padding));

        // Zur aktuellen Zeile hinzufügen
        if (lines.get(level).length() < offset) {
            lines.set(level, lines.get(level) + " ".repeat(offset - lines.get(level).length()));
        }
        lines.set(level, lines.get(level).substring(0, offset) + paddedNode +
                (lines.get(level).length() > offset + paddedNode.length()
                        ? lines.get(level).substring(offset + paddedNode.length())
                        : ""));

        if (node.isLeaf()) {
            return nodeWidth;
        }

        // Kinder behandeln
        int childOffset = offset;
        for (Node child : node.getChildren()) {
            int childWidth = buildDisplay(child, level + 1, childOffset, lines);
            childOffset += childWidth;
        }

        return nodeWidth;
    }
}