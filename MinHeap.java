package _0_DataStructures.Heap;
import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<Generic>{

    public ArrayList<Node> keys = new ArrayList<>();
    public int nodeCount = 0;
    public double height = 0;

    public MinHeap(){
        this.addNode(null);
    }

    public void updateHeight(){
        this.height = Math.ceil( (Math.log(this.nodeCount+1)/Math.log(2)) - 1 );
    }

    public void addNode(Generic value){
        Node newNode = new Node(value);
        this.keys.add(newNode);

        if (value != null) {
            this.nodeCount++;
            this.updateHeight();

            newNode.updateNodeAttr(this.nodeCount,false);
            newNode.swim();
        }
    }

    public Node getNode(int nodeIndex){
        return this.keys.get(nodeIndex);
    }

    public void printHeap(){
        for (Node node:this.keys){
            System.out.print(node.value + ", ");
        }
        System.out.println();
    }

    public void printRelationships(){
        for (Node node:this.keys){
            System.out.println(node.value + ": ");
            System.out.println("leaf:" + node.isLeaf);
            System.out.println("root:" + node.isRoot);
            if (node.leftChild != null) {
                System.out.println("\t Left Child: " + node.leftChild.value);
            } else {
                System.out.println("\t Left Child: " + null);
            }
            if (node.rightChild != null) {
                System.out.println("\t Right Child: " + node.rightChild.value);
            } else {
                System.out.println("\t Right Child: " + null);
            }
        }
        System.out.println();
    }

    public Generic getSmallestValue(){
        return keys.get(1).value;
    }

    public void removeSmallest(){
        if (nodeCount != 0){
            int lastIndex = this.nodeCount;
            Node lastNode = this.keys.get(lastIndex);

            this.keys.set(1, lastNode);
            this.keys.remove(lastIndex);

            nodeCount --;
            this.updateHeight();

            lastNode.updateNodeAttr(1,false);
            lastNode.sink();
        }
    }

    public void swapNodes(Node nodeA, int destIndexA, Node nodeB, int destIndexB){
        keys.set(destIndexA, nodeA);
        keys.set(destIndexB, nodeB);

        nodeA.updateNodeAttr(destIndexA, false);
        nodeB.updateNodeAttr(destIndexB, false);
    }

    public class Node implements Comparator<Node> {
        Generic value;
        int index;
        Node leftChild; Node rightChild; Node parent;
        boolean isLeaf; boolean isRoot;

        private Node(Generic value) {
            this.value = value;
        }

        public void updateNodeAttr(int index, boolean recursiveCall) {
            this.index = index;

            if (this.value == null) {
                this.parent = null;
            } else {
                this.parent = this.getParent();
            }

            this.leftChild = this.getChild(0);
            this.rightChild = this.getChild(1);

            this.isLeaf = (this.leftChild == null && this.rightChild == null);

            this.isRoot = (this.index == 1);

            if (!recursiveCall) {
                if (!this.isRoot) {
                    this.parent.updateNodeAttr(this.parent.index, true);
                }
                if (this.leftChild != null) {
                    this.leftChild.updateNodeAttr(this.leftChild.index, true);
                }
                if (this.rightChild != null) {
                    this.rightChild.updateNodeAttr(this.rightChild.index, true);
                }
            }
        }

        public void sink(){
            if (!this.isLeaf){
                Node smallerChildNode = this.getSmallerChild();

                int nodeIndex = this.index;
                int largerChildNodeIndex = smallerChildNode.index;

                int diff = compare(this, smallerChildNode);

                if (diff > 0){
                    swapNodes(this, largerChildNodeIndex, smallerChildNode, nodeIndex);
                    this.sink();
                }
            }
        }

        public Node getSmallerChild(){
            if (this.rightChild == null || compare(this.leftChild, this.rightChild) <= 0){
                return this.leftChild;
            } else {// if this.leftChild == null || compare(this.leftChild, this.rightChild) <= 0
                return this.rightChild;
            }
        }

        public void swim(){
            if (!this.isRoot){
                Node nodeParent = this.parent;

                int nodeIndex = this.index;
                int parentNodeIndex = nodeParent.index;

                int diff = compare(this, nodeParent);

                if (diff < 0){
                    swapNodes(this, parentNodeIndex, nodeParent, nodeIndex);
                    this.swim();
                }
            }
        }

        @Override
        public int compare(Node nodeA, Node nodeB) {
            if (nodeA.value instanceof Integer) {
                return (Integer) nodeA.value - (Integer) nodeB.value;
            } else if (nodeA.value instanceof String) {
                String valueNodeA = (String) nodeA.value;
                String valueNodeB = (String) nodeB.value;
                return valueNodeA.compareTo(valueNodeB);
            } else {
                return 0;
            }
        }

        public Node getChild(int which) {
            int leftChildIndex = this.index * 2;
            int rightChildIndex = this.index * 2 + 1;

            if (which == 0 && leftChildIndex <= nodeCount) {
                return getNode(leftChildIndex);
            } else if (which == 1 && rightChildIndex <= nodeCount) {
                return getNode(rightChildIndex);
            } else {
                return null;
            }
        }

        private Node getParent(){
            return getNode(this.index/2);
        }
    }
}

