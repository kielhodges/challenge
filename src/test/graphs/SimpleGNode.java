package graphs;

public class SimpleGNode implements GNode {

  private final String name;
  private final GNode[] children;

  public SimpleGNode(String name, GNode[] children) {
    this.name = name;
    this.children = children;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public GNode[] getChildren() {
    return children;
  }

  @Override
  public String toString() {
    return "SimpleGNode<" + name + '>';
  }
}
