import java.util.Stack;

/**
 * Parses a parenthesis string into a tree structure.
 * Uses a stack-based approach to build the tree recursively.
 */
public class TreeParser {

    /**
     * Parses the input string into a Node tree.
     * 
     * @param input the parenthesis string representing the tree
     * @return the root node of the parsed tree
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
            // Skip other characters if any

            i++;
        }

        return root;
    }
}