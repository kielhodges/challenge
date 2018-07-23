package challenge1;

import graphs.GNode;
import graphs.SimpleGNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GraphWalkerTest {

  private static final GNode[] NO_CHILDREN = {};

  private GraphWalker walker = new GraphWalker();

  @Test
  public void nodeWithNoChildren() {
    GNode node = new SimpleGNode("node", NO_CHILDREN);

    ArrayList allNodes = walker.walkGraph(node);

    assertEquals(asList(node), allNodes);
  }

  @Test
  public void nodeWithChildrenButNoGrandchildren() {
    GNode nodeA = new SimpleGNode("nodeA", NO_CHILDREN);
    GNode nodeB = new SimpleGNode("nodeB", NO_CHILDREN);
    GNode nodeC = new SimpleGNode("nodeC", NO_CHILDREN);
    GNode[] children= {nodeA, nodeB, nodeC};
    GNode node = new SimpleGNode("node", children);

    ArrayList allNodes = walker.walkGraph(node);

    List<GNode> expectedNodes = asList(node, nodeA, nodeB, nodeC);
    assertEquals(expectedNodes, allNodes);
  }

  @Test
  public void nodeWithGrandchildrenAndGreatGrandchildren() {
    GNode nodeA1alpha = new SimpleGNode("nodeA1alpha", NO_CHILDREN);
    GNode nodeA1beta  = new SimpleGNode("nodeA1beta", NO_CHILDREN);
    GNode[] nodeA1Children = {nodeA1alpha, nodeA1beta};
    GNode nodeA1 = new SimpleGNode("nodeA1", nodeA1Children);
    GNode nodeA2 = new SimpleGNode("nodeA2", NO_CHILDREN);
    GNode nodeA3 = new SimpleGNode("nodeA3", NO_CHILDREN);
    GNode nodeC1 = new SimpleGNode("nodeC1", NO_CHILDREN);
    GNode nodeC2 = new SimpleGNode("nodeC2", NO_CHILDREN);
    GNode[] nodeAChildren = {nodeA1, nodeA2, nodeA3};
    GNode[] nodeBChildren = {};
    GNode[] nodeCChildren = {nodeC1, nodeC2};
    GNode nodeA  = new SimpleGNode("nodeA", nodeAChildren);
    GNode nodeB  = new SimpleGNode("nodeB", nodeBChildren);
    GNode nodeC  = new SimpleGNode("nodeC", nodeCChildren);
    GNode[] children = {nodeA, nodeB, nodeC};
    GNode node = new SimpleGNode("node", children);

    ArrayList allNodes = walker.walkGraph(node);

    List<GNode> expectedNodes =
        asList(node, nodeA, nodeA1, nodeA1alpha, nodeA1beta, nodeA2, nodeA3, nodeB, nodeC, nodeC1, nodeC2);
    assertEquals(expectedNodes, allNodes);
  }

  @Test
  public void nodeWithDescendantReachableViaMoreThanOnePath() {
    GNode sharedNodeChild1 = new SimpleGNode("sharedNodeChild1", NO_CHILDREN);
    GNode sharedNodeChild2  = new SimpleGNode("sharedNodeChild2", NO_CHILDREN);
    GNode[] sharedNodeChildren = {sharedNodeChild1, sharedNodeChild2};
    GNode sharedNode = new SimpleGNode("sharedNode", sharedNodeChildren);
    GNode nodeA2 = new SimpleGNode("nodeA2", NO_CHILDREN);
    GNode nodeA3 = new SimpleGNode("nodeA3", NO_CHILDREN);
    GNode nodeC1 = new SimpleGNode("nodeC1", NO_CHILDREN);
    GNode nodeC2 = new SimpleGNode("nodeC2", NO_CHILDREN);
    GNode[] nodeAChildren = {sharedNode, nodeA2, nodeA3};
    GNode[] nodeBChildren = {};
    GNode[] nodeCChildren = {nodeC1, sharedNode, nodeC2};
    GNode nodeA  = new SimpleGNode("nodeA", nodeAChildren);
    GNode nodeB  = new SimpleGNode("nodeB", nodeBChildren);
    GNode nodeC  = new SimpleGNode("nodeC", nodeCChildren);
    GNode[] children = {nodeA, nodeB, nodeC};
    GNode node = new SimpleGNode("node", children);

    ArrayList allNodes = walker.walkGraph(node);

    List<GNode> expectedNodes =
        asList(node, nodeA, sharedNode, sharedNodeChild1, sharedNodeChild2, nodeA2, nodeA3, nodeB, nodeC, nodeC1, nodeC2);
    assertEquals(expectedNodes, allNodes);
  }
}
