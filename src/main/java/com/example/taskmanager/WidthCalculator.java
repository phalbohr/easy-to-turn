import java.util.ArrayList;
import java.util.List;

/**
 * Handles width calculations for the tree.
 * Implements the algorithm to compute total width using LCM and assign integer
 * widths.
 */
public class WidthCalculator {

    /**
     * Calculates the total width by finding LCM of all path denominators.
     * 
     * @param root the root node of the tree
     * @return the total width (LCM)
     */
    public int calculateTotalWidth(Node root) {
        List<Integer> denominators = new ArrayList<>();
        collectDenominators(root, 1, denominators);
        return lcm(denominators);
    }

    /**
     * Recursively collects denominators for each leaf path.
     * 
     * @param node               current node
     * @param currentDenominator current path denominator
     * @param denominators       list to collect denominators
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
     * Computes the Least Common Multiple (LCM) of a list of integers.
     * 
     * @param numbers list of numbers
     * @return LCM of all numbers
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
     * Computes LCM of two numbers using GCD.
     * 
     * @param a first number
     * @param b second number
     * @return LCM of a and b
     */
    private int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    /**
     * Computes Greatest Common Divisor using Euclidean algorithm.
     * 
     * @param a first number
     * @param b second number
     * @return GCD of a and b
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
     * Assigns integer widths and depths to all nodes starting from total width.
     *
     * @param root       the root node
     * @param totalWidth the total width to distribute
     */
    public void assignWidthsAndDepths(Node root, int totalWidth) {
        assignWidthsAndDepthsRecursive(root, totalWidth, 1);
    }

    /**
     * Recursively assigns widths and depths to nodes.
     *
     * @param node  current node
     * @param width width to assign to this node
     * @param depth depth to assign to this node
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
     * Collects widths of all leaf nodes in left-to-right order.
     * 
     * @param root the root node
     * @return list of leaf widths
     */
    public List<Integer> getLeafWidths(Node root) {
        List<Integer> widths = new ArrayList<>();
        collectLeafWidths(root, widths);
        return widths;
    }

    /**
     * Recursively collects leaf widths.
     * 
     * @param node   current node
     * @param widths list to collect widths
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
     * Collects depths of all leaf nodes in left-to-right order.
     *
     * @param root the root node
     * @return list of leaf depths
     */
    public List<Integer> getLeafDepths(Node root) {
        List<Integer> depths = new ArrayList<>();
        collectLeafDepths(root, depths);
        return depths;
    }

    /**
     * Recursively collects leaf depths.
     *
     * @param node   current node
     * @param depths list to collect depths
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
     * Checks if the list of widths forms a palindrome.
     *
     * @param widths list of integers
     * @return true if palindrome, false otherwise
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
     * Checks if the sum of depths from both ends is constant.
     * For each position i, depths[i] + depths[n-1-i] should be the same.
     *
     * @param depths list of depths
     * @return true if constant depth sum, false otherwise
     */
    public boolean isConstantDepthSum(List<Integer> depths) {
        int n = depths.size();
        if (n == 0) return true;

        int expectedSum = depths.get(0) + depths.get(n - 1);
        for (int i = 1; i < n / 2; i++) {
            if (depths.get(i) + depths.get(n - 1 - i) != expectedSum) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the tree is drehfreudig (both width palindrome and constant depth sum).
     *
     * @param widths list of leaf widths
     * @param depths list of leaf depths
     * @return true if drehfreudig, false otherwise
     */
    public boolean isDrehfreudig(List<Integer> widths, List<Integer> depths) {
        return isWidthPalindrome(widths) && isConstantDepthSum(depths);
    }
}
