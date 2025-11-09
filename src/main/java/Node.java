import java.util.ArrayList;
import java.util.List;

/**
 * Stellt einen Knoten in der Baumstruktur dar.
 * Jeder Knoten kann Kinder haben und eine berechnete Breite und Tiefe.
 */
public class Node {
    private List<Node> children;
    private int width;
    private int depth;

    public Node() {
        this.children = new ArrayList<>();
        this.width = 0;
        this.depth = 0;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}