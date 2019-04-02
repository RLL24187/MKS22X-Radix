public class MyLinkedList{
  private int length;
  private Node start,end;

  //Constructor for MyLinkedlist
  public MyLinkedList(){
    length = 0;
    start = null;
    end = null;
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
  public boolean add(int value){
    Node newVal; //Node to be added
    if (length == 0){
      newVal = new Node(value, null, null);
      //If at the start of the list, there is no prev nor next for the new node yet
      start = newVal;
      end = newVal;
      //Both start and end refer to the same node
    }
    else{
      newVal = new Node(value, null, end);
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

  private Node getNthNode(int n){
    //returns the nth node
    Node nowNode; //Keep track of what node you're up to
    if (n < this.size() / 2){
      nowNode = start;
      for (int i = 0; i <= n - 1; i++){
        nowNode = nowNode.next();
      }
    }
    else{ //Optimization for indices closer to the end
      nowNode = end;
      for (int i = this.size() - 1; i >= n + 1; i--){
        nowNode = nowNode.prev();
      }
    }
    return nowNode;
  }

  /** Retrieves the value at specified index
  @param index is the index of the wanted value
  @return Integer of the value at specified index
  */
  public Integer get(int index) {
    //Throws IndexOutOfBoundsException
    //if the index is out of range (index < 0 || index >= size())
    if (index < 0 || index >= this.size()){
      throw new IndexOutOfBoundsException("Integer get(int index) error handled!");
    }
    //returns the data at the node of index
    return getNthNode(index).getData();
  }

  /** Changes the value at the specified index
  @param index is the index of the new value
  @return Integer of the original value at specified index
  */
  public Integer set(int index,Integer value){
    //Throws IndexOutOfBoundsException
    //if the index is out of range (index < 0 || index >= size())
    if (index < 0 || index >= this.size()){
      throw new IndexOutOfBoundsException("Integer set(int index, Integer value) error handled!");
    }
    //returns the old value and changes the value at the index specified
    return getNthNode(index).setData(value);
  }

  /** Finds whether or not given value is in the list
    @param value is the wanted value
    @return boolean depending on if the value is in the list
    */
  public boolean contains(Integer value){
    //Returns true if this list contains the specified element
    for (int i = 0; i < length; i++){
      if (this.get(i) == value){
        return true;
      }
    }
    return false;
  }

  /** Retrieves the index of first instance of the specified value
   @param value is the specified value
   @return an int of the index of the first time the specified value appears, or -1 if it doesn't exist
   */
  public int indexOf(Integer value){
    //returns the index of first occurrence of the specified element in this list
    //or -1 if this list does not contain the element
    if (this.contains(value)){
      for (int i = 0; i < length; i++){
        if (this.get(i)==value){
          return i;
        }
      }
    }
    return -1; //returns -1 if it doesn't contain the value
  }

  /** Adds the given value to the given index
  @param index the index of the new value
  @param value the value that will be added
  @return void for whether or not addition was successful
  */
  public void add(int index,Integer value) {
    //Throws IndexOutOfBoundsException
    //if the index is out of range (index < 0 || index > size())
    Node before, after;
    if (index < 0 || index > this.size()){
      throw new IndexOutOfBoundsException("void add(int index, Integer value) error handled!");
    }
    if (index == 0){ //If adding to beginning, there is no before
      after = getNthNode(0);
      Node newNode = new Node(value, after, null);
      after.setPrev(newNode);
      start = newNode;
      length++;
    }
    else if (index == this.size()){ //If adding to the end, there is no after
      add(value);
    }
    else{
      after = getNthNode(index);
      before = after.prev();
      Node newNode = new Node (value, after, before);
      after.setPrev(newNode);
      before.setNext(newNode);
      length++;
    }

    /*Alternatively
    //System.out.println(this.get(this.size() - 1));
    this.add(get(this.size()-1));
    for (int i = this.size() - 1; i > index; i--){ //Loop thru to shift over
      this.set(i, get(i - 1)); //Set the current one's data to the data of the one previous
    }
    this.set(index, value);*/
  }

  /** Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
    @param index is the location of the element
    @return an Integer of the value removed
    */
  public Integer remove(int index) {
    //Throws IndexOutOfBoundsException
    //if the index is out of range (index < 0 || index >= size())
    if (index < 0 || index >= this.size()){
      throw new IndexOutOfBoundsException("void add(int index, Integer value) error handled!");
    }
    Integer val= this.get(index);
    if (index == 0){ //if at beginning, next node's before is null
      Node newStart = getNthNode(1);
      newStart.setPrev(null);
      start = newStart;
    }
    else if(index == this.size()-1){ //if at end, second to last node's after is null
      Node newStart = getNthNode(this.size()-2);
      newStart.setNext(null);
      end = newStart;
    }
    else{
      Node after = getNthNode(index+1); //save after node
      Node before = after.prev().prev(); //save before node
      after.setPrev(before); //set before of after node to before
      before.setNext(after); //set after of before node to after
    }
    length--; //1 less element
    return val;
  }

  /** Removes the first occurrence of the specified element from this list, if it is present. If this list does not contain the element, it is unchanged. More formally, removes the element with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
  @param value is the specified value to be removed
  @return a boolean depending on whether or not the value was successfully removed
  */
  public boolean remove(Integer value) {
    int index = indexOf(value); // get the index
    if (index == -1) return false; //return false if it isn't in the list (list unaltered)
    this.remove(index);
    return true;
  }

  /**Concatenates two MyLinkedLists and deletes one of them
  @param other is the list to be concatenated to
  @return void
  */
  public void extend(MyLinkedList other){
    //in O(1) runtime, move the elements from other onto the end of this
    //The size of other is reduced to 0
    //The size of this is now the combined sizes of both original list
    if (other.size() != 0){ // if the other size was 0, nothing would change)
      Node endNode = this.end; //Keep track of this to set its prev and next later
      //start stays the same
      end = other.end; //move current end to end of other
      length+= other.length; //total size increase by other length
      endNode.setNext(other.start); //set next of original end node to start of other
      other.start.setPrev(endNode); //set prev of original other start node to original end node
      other.start = null;
      other.end = null;
      other.length = 0; //set length 0
    }
  }

  public boolean add(E value){
    E newVal; //element to be added
    if (length == 0){
      newVal = new Node(value, null, null);
      //If at the start of the list, there is no prev nor next for the new node yet
      start = newVal;
      end = newVal;
      //Both start and end refer to the same node
    }
    else{
      newVal = new Node(value, null, end);
      //If not, there is still no next, but there is a prev for the new node
      end.setNext(newVal);//End node must change its next to refer to newVal
      end = newVal; //Sets end reference to the last node
    }
    length++; //new element
    return true;
  }
    //add an element to the end of the list (the boolean would be true all the time if you want to conform to list standards)
  public void extend(MyLinkedList<E> other){

  }
     //in O(1) time, connect the other list to the end of this list.
    //The other list is then reset to size 0 (do not wipe out the nodes, just disconnect them.)
    //This is how you will merge lists together for your radix sort.
  public E removeFront(){

  }
    //remove the 1st element of the list, and return that value.
//OPTIONALLY:
    //A working iterator would be faster than remove front for traversing the list.

//------------------------------------------------------------------------------------------------------------------------------------//

  private class Node{
   private Integer data;
   private Node next,prev;

   //Constructor for Node
   public Node(int val, Node Next, Node Prev){
     data = val;
     next = Next;
     prev = Prev;
   }

   public Node(E val, Node Next, Node Prev){
     E data = val;
     next = Next;
     prev = Prev;
   }

   //Getters and Setters

   public Node next(){
     return next;
   }

   public Node prev(){
     return prev;
   }

   public Integer getData(){
     return data;
   }

   public Integer setData(Integer i){ //Might not be needed
     //Replaces the element at the specified position in this list with the specified element.
     //Returns the old value
     Integer oldData = data;
     data = i;
     return oldData;
   }

   public void setNext(Node newNext){
     next = newNext;
   }

   public void setPrev(Node newPrev){
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
