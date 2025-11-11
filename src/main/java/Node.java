import java.util.ArrayList;
import java.util.List;

/**
 * Stellt einen Knoten in der Baumstruktur dar.
 * Jeder Knoten kann Kinder haben und eine berechnete Breite und Tiefe.
 */
public class Node {
    /** Die Liste der Kinderknoten dieses Knotens. */
    private List<Node> children;
    /** Die berechnete Breite des Knotens, die den ihm zugewiesenen horizontalen Raum darstellt. */
    private int width;
    /** Die Tiefe des Knotens im Baum, wobei der Wurzelknoten die Tiefe 1 hat. */
    private int depth;

    /**
     * Konstruktor für einen neuen Knoten.
     */
    public Node() {
        this.children = new ArrayList<>();
        this.width = 0;
        this.depth = 0;
    }

    /**
     * Gibt die Liste der Kinderknoten zurück.
     * @return die Liste der Kinderknoten
     */
    public List<Node> getChildren() {
        return children;
    }

    /**
     * Fügt einen Kinderknoten hinzu.
     * @param child der hinzuzufügende Kinderknoten
     */
    public void addChild(Node child) {
        this.children.add(child);
    }

    /**
     * Gibt die Breite des Knotens zurück.
     * @return die Breite des Knotens
     */
    public int getWidth() {
        return width;
    }

    /**
     * Legt die Breite des Knotens fest.
     * @param width die dem Knoten zuzuweisende Breite
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gibt die Tiefe des Knotens zurück.
     * @return die Tiefe des Knotens
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Legt die Tiefe des Knotens fest.
     * @param depth die dem Knoten zuzuweisende Tiefe
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Prüft, ob der Knoten ein Blatt ist.
     * @return true, wenn der Knoten ein Blatt ist, sonst false
     */
    public boolean isLeaf() {
        return children.isEmpty();
    }
}