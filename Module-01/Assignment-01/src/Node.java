import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    // Fields
    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private int depth;
    private Node<T> parent = null;

    // Constructors
    public Node(T data) {
            this.data = data;
        }

    // Getters and Setters
    public Node<T> addChild(Node<T> child) {
    child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public int getDepth() {
     return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }


} // end Node
