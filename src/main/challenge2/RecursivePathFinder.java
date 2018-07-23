package challenge2;

import graphs.GNode;

import java.util.ArrayList;

public class RecursivePathFinder {

  public ArrayList paths(GNode node) {
    ArrayList<GNode> partialPath = new ArrayList<>();
    partialPath.add(node);
    return extendPartialPath(partialPath);
  }

  private ArrayList<ArrayList<GNode>> extendPartialPath(ArrayList<GNode> path) {
    ArrayList<ArrayList<GNode>> completePaths = new ArrayList<>();
    GNode[] children = lastNodeOf(path).getChildren();
    if (children.length == 0) {
      completePaths.add(path);
    } else {
      for (GNode child: children) {
        ArrayList<GNode> childPath = append(path, child);
        completePaths.addAll(extendPartialPath(childPath));
      }
    }
    return completePaths;
  }

  private ArrayList<GNode> append(ArrayList<GNode> path, GNode node) {
    ArrayList<GNode> newPath = new ArrayList<>(path);
    newPath.add(node);
    return newPath;
  }

  private GNode lastNodeOf(ArrayList<GNode> path) {
    return path.get(path.size() - 1);
  }

}
