package challenge2;

import graphs.GNode;

import java.util.ArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class PathFinder {

  public ArrayList paths(GNode node) {
    ArrayList<GNode> partialPath = new ArrayList<>();
    partialPath.add(node);
    return extendPartialPath(partialPath);
  }

  private ArrayList<ArrayList<GNode>> extendPartialPath(ArrayList<GNode> path) {
    ArrayList<ArrayList<GNode>> partialPaths  = new ArrayList<>();
    partialPaths.add(path);
    return extendPartialPaths(partialPaths);
  }

  private ArrayList<ArrayList<GNode>> extendPartialPaths(ArrayList<ArrayList<GNode>> partialPaths) {
    ArrayList<ArrayList<GNode>> completePaths = new ArrayList<>();
    while (!partialPaths.isEmpty()) {
      ArrayList<GNode> path = partialPaths.remove(0);
      GNode[] children = lastNodeOf(path).getChildren();
      if (children.length == 0) {
        completePaths.add(path);
      } else {
        partialPaths.addAll(stream(children).map(child -> append(path, child)).collect(toList()));
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
