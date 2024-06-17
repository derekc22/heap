package _0_DataStructures.Heap;

public class HeapSession {
    public static void main(String[] args) {

        MaxHeap<Integer> myHeap = new MaxHeap<>();

        int i = 1;
        while(i < 10){
            myHeap.addNode((int) (Math.round(10*i*Math.random())));
            i++;
        }


        /**
         myHeap.addNode(62);
         myHeap.addNode(269);
         myHeap.addNode(849);
         myHeap.addNode(899);
         myHeap.addNode(840);
         myHeap.addNode(154);
         myHeap.addNode(250);
         */

        myHeap.printRelationships();
        myHeap.printHeap();


        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

        myHeap.removeLargest();
        //myHeap.printRelationships();
        myHeap.printHeap();

    }
}
