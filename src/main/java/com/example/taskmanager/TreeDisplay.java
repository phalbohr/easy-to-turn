import java.util.ArrayList;
import java.util.List;

/**
 * Handles the display of the tree structure using ASCII art.
 * Uses the calculated widths to create proportional representation.
 */
public class TreeDisplay {

    /**
     * Displays the tree in a visual format.
     * 
     * @param root the root node of the tree
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
     * Recursively builds the display lines for the tree.
     * 
     * @param node   current node
     * @param level  current depth level
     * @param offset horizontal offset
     * @param lines  list to collect display lines
     * @return the width used by this subtree
     */
    private int buildDisplay(Node node, int level, int offset, List<String> lines) {
        // Ensure we have enough lines
        while (lines.size() <= level) {
            lines.add("");
        }

        int nodeWidth = node.getWidth();
        String nodeStr;

        // Special display for all nodes based on width (same logic for leaves and internal nodes)
        if (nodeWidth == 1) {
            nodeStr = "1";
        } else if (nodeWidth == 2) {
            nodeStr = "[]";
        } else {
            // For width >= 3: [....number....] with total width characters
            String numStr = String.valueOf(nodeWidth);
            int dotsNeeded = nodeWidth - numStr.length() - 2; // -2 for brackets
            int leftDots = dotsNeeded / 2;
            int rightDots = dotsNeeded - leftDots;
            nodeStr = "[" + ".".repeat(leftDots) + numStr + ".".repeat(rightDots) + "]";
        }

        // Center the node in its allocated width
        int padding = (nodeWidth - nodeStr.length()) / 2;
        String paddedNode = " ".repeat(Math.max(0, padding)) + nodeStr +
                " ".repeat(Math.max(0, nodeWidth - nodeStr.length() - padding));

        // Add to current level line
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

        // Handle children
        int childOffset = offset;
        for (Node child : node.getChildren()) {
            int childWidth = buildDisplay(child, level + 1, childOffset, lines);
            childOffset += childWidth;
        }

        return nodeWidth;
    }
}