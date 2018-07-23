package challenge2;

import graphs.GNode;
import graphs.SimpleGNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class PathFinderTest {

  private static final GNode[] NO_CHILDREN = {};

  private PathFinder pathFinder = new PathFinder();

  @Test
  public void nodeWithNoChildren() {
    GNode node = new SimpleGNode("node", NO_CHILDREN);

    ArrayList paths = pathFinder.paths(node);

    assertEquals(asList(asList(node)), paths);
  }

  @Test
  public void nodeWithChildrenButNoGrandchildren() {
    GNode nodeA = new SimpleGNode("nodeA", NO_CHILDREN);
    GNode nodeB = new SimpleGNode("nodeB", NO_CHILDREN);
    GNode nodeC = new SimpleGNode("nodeC", NO_CHILDREN);
    GNode[] children= {nodeA, nodeB, nodeC};
    GNode node = new SimpleGNode("node", children);

    ArrayList paths = pathFinder.paths(node);

    List<GNode> pathToA = asList(node, nodeA);
    List<GNode> pathToB = asList(node, nodeB);
    List<GNode> pathToC = asList(node, nodeC);
    List<List<GNode>> expectedPaths = asList(pathToA, pathToB, pathToC);
    assertEquals(expectedPaths, paths);
  }

  @Test
  public void nodeWithGrandchildrenAndGreatGrandchildren() {
    GNode j = new SimpleGNode("J", NO_CHILDREN);
    GNode i = new SimpleGNode("I", NO_CHILDREN);
    GNode h = new SimpleGNode("H", NO_CHILDREN);
    GNode g = new SimpleGNode("G", NO_CHILDREN);
    GNode f = new SimpleGNode("F", NO_CHILDREN);
    GNode e = new SimpleGNode("E", NO_CHILDREN);
    GNode[] dChildren = {j};
    GNode[] cChildren = {g, h, i};
    GNode[] bChildren = {e, f};
    GNode d = new SimpleGNode("D", dChildren);
    GNode c = new SimpleGNode("C", cChildren);
    GNode b = new SimpleGNode("B", bChildren);
    GNode[] aChildren = {b, c, d};
    GNode a = new SimpleGNode("A", aChildren);

    ArrayList paths = pathFinder.paths(a);

    List<List<GNode>> expectedPaths = asList(
        asList(a, b, e),
        asList(a, b, f),
        asList(a, c, g),
        asList(a, c, h),
        asList(a, c, i),
        asList(a, d, j)
    );
    assertEquals(expectedPaths, paths);
  }

}
