import java.util.HashMap;


public class Test {

  HashMap<String, Node> nodes = new HashMap<>();

  public class Node {

    int nodeIndex;
    Node nextNode;

    public int getNodeIndex() {
      return this.nodeIndex;
    }

    public void setNodeIndex(int nodeIndex) {
      this.nodeIndex = nodeIndex;
    }

    public Node getNextNode() {
      return this.nextNode;
    }

    public void setNextNode(Node nextNode) {
      this.nextNode = nextNode;
    }
  }

  public Integer findNetworkEndPoint(int startNodeId, Integer[] fromIds, Integer[] toIds) {

    for (Integer fromId : fromIds) {
      if (nodes.get(String.valueOf(fromId)) == null && fromId != null) {
        Node node = new Node();
        node.setNodeIndex(fromId);
        nodes.put(String.valueOf(fromId), node);
      }
    }

    for (Integer toId : toIds) {
      if (nodes.get(String.valueOf(toId)) == null && toId != null) {
        Node node = new Node();
        node.setNodeIndex(toId);
        nodes.put(String.valueOf(toId), node);
      }
    }

    int i = 0;
    for (Integer fromId : fromIds) {
      if (toIds[i] != null) {
        nodes.get(String.valueOf(fromId)).setNextNode(nodes.get(String.valueOf(toIds[i])));
      }
      i++;
    }

    Node finalNode = nodes.get(String.valueOf(startNodeId));
    while (finalNode.getNextNode() != null) {
      if (finalNode.getNextNode() != null
          && finalNode.getNextNode().getNodeIndex() == startNodeId) {
        break;
      }
      finalNode = finalNode.getNextNode();
    }

    return finalNode.getNodeIndex();
  }

  @org.junit.Test
  public void myTest() {
    Integer[] fromIds = {1, 7, 3, 4, 2, 6, 9, 5};
    Integer[] toIds = {3, 3, 4, 6, 6, 9, 5, null};
    int startNodeId = 7;
    Integer endpointNode = findNetworkEndPoint(startNodeId, fromIds, toIds);
    System.out.println(startNodeId + " -> " + endpointNode);
  }

}
