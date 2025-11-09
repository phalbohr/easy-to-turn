import java.util.ArrayList;
import java.util.List;

/**
 * Behandelt Breitenberechnungen für den Baum.
 * Implementiert den Algorithmus zur Berechnung der Gesamtbreite mit kgV und
 * Zuweisung von
 * ganzzahligen Breiten.
 */
public class WidthCalculator {

    /**
     * Berechnet die Gesamtbreite durch Finden der kgV aller Pfadnenner.
     * @param root der Wurzelknoten des Baumes
     * @return die Gesamtbreite (kgV)
     */
    public int calculateTotalWidth(Node root) {
        List<Integer> denominators = new ArrayList<>();
        collectDenominators(root, 1, denominators);
        return lcm(denominators);
    }

    /**
     * Sammelt rekursiv die Nenner für jeden Blattpfad.
     * 
     * @param node               aktueller Knoten
     * @param currentDenominator aktueller Pfadnenner
     * @param denominators       Liste zum Sammeln der Nenner
     */
    private void collectDenominators(Node node, int currentDenominator, List<Integer> denominators) {
        if (node.isLeaf()) {
            denominators.add(currentDenominator);
            return;
        }

        int childCount = node.getChildren().size();
        int newDenominator = currentDenominator * childCount;

        for (Node child : node.getChildren()) {
            collectDenominators(child, newDenominator, denominators);
        }
    }

    /**
     * Berechnet das kleinste gemeinsame Vielfache (kgV) einer Liste von ganzen Zahlen.
     * 
     * @param numbers Liste von Zahlen
     * @return kgV aller Zahlen
     */
    private int lcm(List<Integer> numbers) {
        if (numbers.isEmpty())
            return 1;
        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }
        return result;
    }

    /**
     * Berechnet das kgV von zwei Zahlen mit Hilfe des ggT.
     * 
     * @param a erste Zahl
     * @param b zweite Zahl
     * @return kgV von a und b
     */
    private int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    /**
     * Berechnet den größten gemeinsamen Teiler (ggT) mit dem Euklidischen Algorithmus.
     * 
     * @param a erste Zahl
     * @param b zweite Zahl
     * @return ggT von a und b
     */
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Weist allen Knoten ganzzahlige Breiten und Tiefen zu, ausgehend von der Gesamtbreite.
     *
     * @param root       der Wurzelknoten
     * @param totalWidth die zu verteilende Gesamtbreite
     */
    public void assignWidthsAndDepths(Node root, int totalWidth) {
        assignWidthsAndDepthsRecursive(root, totalWidth, 1);
    }

    /**
     * Weist Knoten rekursiv Breiten und Tiefen zu.
     *
     * @param node  aktueller Knoten
     * @param width diesem Knoten zuzuweisende Breite
     * @param depth diesem Knoten zuzuweisende Tiefe
     */
    private void assignWidthsAndDepthsRecursive(Node node, int width, int depth) {
        node.setWidth(width);
        node.setDepth(depth);
        if (node.isLeaf())
            return;

        int childCount = node.getChildren().size();
        int childWidth = width / childCount;

        for (Node child : node.getChildren()) {
            assignWidthsAndDepthsRecursive(child, childWidth, depth + 1);
        }
    }

    /**
     * Sammelt die Breiten aller Blattknoten in Links-nach-Rechts-Reihenfolge.
     * 
     * @param root der Wurzelknoten
     * @return Liste der Blattbreiten
     */
    public List<Integer> getLeafWidths(Node root) {
        List<Integer> widths = new ArrayList<>();
        collectLeafWidths(root, widths);
        return widths;
    }

    /**
     * Sammelt rekursiv die Blattbreiten.
     * 
     * @param node   aktueller Knoten
     * @param widths Liste zum Sammeln der Breiten
     */
    private void collectLeafWidths(Node node, List<Integer> widths) {
        if (node.isLeaf()) {
            widths.add(node.getWidth());
            return;
        }

        for (Node child : node.getChildren()) {
            collectLeafWidths(child, widths);
        }
    }

    /**
     * Sammelt die Tiefen aller Blattknoten in Links-nach-Rechts-Reihenfolge.
     *
     * @param root der Wurzelknoten
     * @return Liste der Blatttiefen
     */
    public List<Integer> getLeafDepths(Node root) {
        List<Integer> depths = new ArrayList<>();
        collectLeafDepths(root, depths);
        return depths;
    }

    /**
     * Sammelt rekursiv die Blatttiefen.
     *
     * @param node   aktueller Knoten
     * @param depths Liste zum Sammeln der Tiefen
     */
    private void collectLeafDepths(Node node, List<Integer> depths) {
        if (node.isLeaf()) {
            depths.add(node.getDepth());
            return;
        }

        for (Node child : node.getChildren()) {
            collectLeafDepths(child, depths);
        }
    }

    /**
     * Prüft, ob die Liste der Breiten ein Palindrom bildet.
     *
     * @param widths Liste von ganzen Zahlen
     * @return true, wenn Palindrom, sonst false
     */
    public boolean isWidthPalindrome(List<Integer> widths) {
        int n = widths.size();
        for (int i = 0; i < n / 2; i++) {
            if (!widths.get(i).equals(widths.get(n - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft, ob die Summe der Tiefen von beiden Enden konstant ist.
     * Für jede Position i sollte depths[i] + depths[n-1-i] gleich sein.
     *
     * @param depths Liste der Tiefen
     * @return true, wenn die Tiefensumme konstant ist, sonst false
     */
    public boolean isConstantDepthSum(List<Integer> depths) {
        int n = depths.size();
        if (n == 0)
            return true;

        int expectedSum = depths.get(0) + depths.get(n - 1);
        for (int i = 1; i < n / 2; i++) {
            if (depths.get(i) + depths.get(n - 1 - i) != expectedSum) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft, ob der Baum drehfreudig ist (sowohl Breitenpalindrom als auch konstante Tiefensumme).
     *
     * @param widths Liste der Blattbreiten
     * @param depths Liste der Blatttiefen
     * @return true, wenn drehfreudig, sonst false
     */
    public boolean isDrehfreudig(List<Integer> widths, List<Integer> depths) {
        return isWidthPalindrome(widths) && isConstantDepthSum(depths);
    }
}