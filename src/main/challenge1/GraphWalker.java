package challenge1;

import graphs.GNode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class GraphWalker {

  public ArrayList walkGraph(GNode node) {
    ArrayList allNodes = new ArrayList();
    List<GNode> nodesToConsider = new ArrayList<>();
    nodesToConsider.add(node);
    walkGraphCollectingInto(allNodes, nodesToConsider);
    return allNodes;
  }

  private void walkGraphCollectingInto(List nodes, List<GNode> nodesToConsider) {
    while (!nodesToConsider.isEmpty()) {
      GNode node = nodesToConsider.remove(0);
      if (!nodes.contains(node)) {
        nodes.add(node);
        nodesToConsider.addAll(0, asList(node.getChildren()));
      }
    }
  }

}
