import java.util.Stack;

/**
 * Parst eine Klammerzeichenfolge in eine Baumstruktur.
 * Verwendet einen stack-basierten Ansatz, um den Baum rekursiv aufzubauen.
 */
public class TreeParser {

    /**
     * Parst die Eingabezeichenfolge in einen Node-Baum.
     *
     * @param input die Klammerzeichenfolge, die den Baum darstellt
     * @return der Wurzelknoten des geparsten Baumes
     */
    public Node parse(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        Stack<Node> stack = new Stack<>();
        Node root = null;
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);

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
            // Überspringe andere Zeichen falls vorhanden

            i++;
        }

        return root;
    }
}