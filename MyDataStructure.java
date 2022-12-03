import java.util.ArrayList;

public class MyDataStructure {
  private ArrayList<Node> nodes = new ArrayList<>();
  private ArrayList<Edge> edges = new ArrayList<>();

  // Public Methods Start
  public void addNode(String packageName, String className, String methodName, boolean isGreen) {
    nodes.add(new Node(packageName, className, methodName, isGreen));
  }

  public Node getNode(int index) {
    return nodes.get(index);
  }

  public void addEdge(String from, String to) {
    addEdgeToArrayList(searchNode(from), searchNode(to));
  }

  private void addEdgeToArrayList(Node from, Node to) {
    edges.add(new Edge(from, to));
  }

  public void createDotNotation(StringBuilder buf) {
    addNodesToDotNotation(buf);
    addEdgesToDotNotation(buf);
  }

  public boolean contains(String nodeString) {
    for (Node node : nodes) {
      if (node.getNodeString().equals(nodeString)) {
        return true;
      }
    }
    return false;
  }

  public void changeNodeColor(String nodeString, boolean color) {
    for (Node node : nodes) {
      if (node.getNodeString().equals(nodeString)) {
        node.setGreen(color);
      }
    }
  }
  // Public Methods End

  // Private Methods Start
  private void addNodesToDotNotation(StringBuilder buf) {
    // buf.append("charset=\"UTF-8\"\n");
    for (Node node : nodes) {
      buf.append("\"" + node.getNodeString() + "\"");
      if (node.getColor().equals("green")) {
        buf.append(" [fillcolor=\"green1\" style=\"filled\"]");
      }
      buf.append(";\n");
    }
  }

  private void addEdgesToDotNotation(StringBuilder buf) {
    for (Edge edge : edges) {
      buf.append("\"" + edge.getFromNode().getNodeString() + "\"");
      buf.append(" -> ");
      buf.append("\"" + edge.getToNode().getNodeString() + "\"");
      buf.append(";\n");
    }
  }

  private Node searchNode(String nodeString) {
    for (Node node : nodes) {
      if (node.getNodeString().equals(nodeString)) {
        return node;
      }
    }
    return null;
  }
  // Private Methods End

  // Methods used for testing & debugging start
  public void printNodes() {
    for (Node node : nodes) {
      printNode(node);
    }
  }

  private void printNode(Node node) {
    System.out.println(node.getNodeString() + " .  Renk: " + node.getColor());
  }

  private String getFullNode(Node node) {
    return node.getNodeString() + " .  Renk: " + node.getColor();
  }

  public void printEdges() {
    System.out.println("**********EDGES*********");
    for (Edge edge : edges) {
      System.out.println(edge.getFromNode().getNodeString() + "  -  " + edge.getToNode().getNodeString());
    }
    System.out.println("**********EDGES*********");

  }
  // Methods used for testing & debugging end
}