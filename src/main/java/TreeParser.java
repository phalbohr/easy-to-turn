import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Parst eine Klammerzeichenfolge in eine Baumstruktur.
 * Verwendet einen stack-basierten Ansatz, um den Baum rekursiv aufzubauen.
 */
public class TreeParser {

    /**
     * Erstellt eine neue Instanz des TreeParser.
     */
    public TreeParser() {}

    /**
     * Parst die Eingabezeichenfolge in einen Node-Baum.
     *
     * @param input die Klammerzeichenfolge, die den Baum darstellt.
     * @return der Wurzelknoten des geparsten Baumes.
     * @throws IllegalArgumentException wenn die Eingabe null, leer oder die Klammerstruktur ungültig ist.
     */
    public Node parse(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        Stack<Node> stack = new Stack<>();
        Node root = null;

        try {
            for (char c : input.toCharArray()) {
                if (c == '(') {
                    Node newNode = new Node();
                    if (!stack.isEmpty()) {
                        stack.peek().addChild(newNode);
                    } else {
                        root = newNode;
                    }
                    stack.push(newNode);
                } else if (c == ')') {
                    stack.pop();
                }
            }
        } catch (EmptyStackException e) {
            throw new IllegalArgumentException("Ungültige Baumstruktur: Zu viele schließende Klammern.", e);
        }

        // Nach dem Parsen muss der Stack leer sein, sonst fehlen schließende Klammern.
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Ungültige Baumstruktur: Zu viele öffnende Klammern.");
        }

        return root;
    }
}