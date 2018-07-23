package challenge1;

import graphs.GNode;

import java.util.ArrayList;
import java.util.List;

public class RecursiveGraphWalker {

  public ArrayList walkGraph(GNode node) {
    ArrayList allNodes = new ArrayList();
    walkGraphCollectingInto(allNodes, node);
    return allNodes;
  }

  private void walkGraphCollectingInto(List nodes, GNode node) {
    if (nodes.contains(node)) {
      return;
    }
    nodes.add(node);
    for (GNode childNode: node.getChildren()) {
      walkGraphCollectingInto(nodes, childNode);
    }
  }

}
