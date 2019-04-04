import java.util.*;
public class MyLinkedList<E> implements Iterable<E>{
  private int length;
  private Node<E> start, end, cur;

  //Constructor for MyLinkedlist
  public MyLinkedList(){
    length = 0;
    start = null;
    end = null;
    cur = null;
  }

  public void clear(){
    length = 0;
    start = null;
    end = null;
    cur = null;
  }

  public Iterator<E> iterator(){
    return new MLLIterator(start);
  }

  private class MLLIterator implements Iterator<E>{
    Node cur;

    public MLLIterator(Node start){
      cur = start;
    }
    public E next(){
      Node<E> temp = cur;
      cur = cur.next();
      return temp.getData();
    }

    public boolean hasNext(){
      return cur != null;
    }
  }
  /** Returns length of the list
    @return the current size of the list (number of nodes)
    */
  public int size(){
    return length;
  }//returns the size of the list (number of nodes)

  /** Adds the specified value to the end of the LinkedList
    @param value value to be added
    @return boolean for whether or not addition was successful
    */
  public boolean add(E value){
    Node newVal; //Node to be added
    if (length == 0){
      newVal = new Node<E>(value, null, null);
      //If at the start of the list, there is no prev nor next for the new node yet
      start = newVal;
      end = newVal;
      //Both start and end refer to the same node
    }
    else{
      newVal = new Node<E>(value, null, end);
      //If not, there is still no next, but there is a prev for the new node
      end.setNext(newVal);//End node must change its next to refer to newVal
      end = newVal; //Sets end reference to the last node
    }
    length++; //new element
    return true;
  }
  //Appends the specified element to the end of this list.

  /** Each value separated by a comma inside square brackets
    @return String of the list
    */
  public String toString(){
    String output = "[";
    Node nowVal = start; //Keep track of what node you're up to
    if (nowVal == null){
      return "[]";
    }
    while (nowVal.hasNext()){
      output += nowVal.getData() + ", ";
      nowVal = nowVal.next(); //Loop through the nodes
    }
    output += nowVal.getData();
    return output + "]";
  }

  /** Removes the first element in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
    @param index is the location of the element
    @return an first element of list of the value removed
    */
    //in O(1) time, connect the other list to the end of this list.
   //The other list is then reset to size 0 (do not wipe out the nodes, just disconnect them.)
   //This is how you will merge lists together for your radix sort.
  public E removeFront(){
    Node<E> val=start;
    //index == 0 //if at beginning, next node's before is null
    Node newStart = start.next;
    newStart.setPrev(null);
    start = newStart;
    length--; //1 less element
    return val.data;
  }
    //remove the 1st element of the list, and return that value.
//OPTIONALLY:
    //A working iterator would be faster than remove front for traversing the list.

  /**Concatenates two MyLinkedLists and deletes one of them
  @param other is the list to be concatenated to
  @return void
  */
  public void extend(MyLinkedList<E> other){
    //in O(1) runtime, move the elements from other onto the end of this
    //The size of other is reduced to 0
    //The size of this is now the combined sizes of both original list
    if (other.length > 0){ // if the other size was 0, nothing would change)
      //start stays the same
      end.setNext(other.start); //set next of original end node to start of other
      end = other.end; //move current end to end of other
      length+= other.length; //total size increase by other length
      other.clear();
    }
  }

//------------------------------------------------------------------------------------------------------------------------------------//

  private class Node<E>{
   private E data;
   private Node<E> next,prev;

   //Constructor for Node
   public Node(E val, Node Next, Node Prev){
     data = val;
     next = Next;
     prev = Prev;
   }

   //Getters and Setters

   public Node<E> next(){
     return next;
   }

   public Node<E> prev(){
     return prev;
   }

   public E getData(){
     return data;
   }

   public E setData(E i){ //Might not be needed
     //Replaces the element at the specified position in this list with the specified element.
     //Returns the old value
     E oldData = data;
     data = i;
     return oldData;
   }

   public void setNext(Node<E> newNext){
     next = newNext;
   }

   public void setPrev(Node<E> newPrev){
     prev = newPrev;
   }

   public boolean hasNext(){
      return (next!=null);
   }

   public boolean hasPrev(){
      return (prev!=null);
   }
   public String toString(){
     return ""+data;
   }
  }
}
