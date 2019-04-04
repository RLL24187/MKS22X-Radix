public class Radix{
  public static void radixsort(int[]data){
    @SuppressWarnings("unchecked")
    //creating the buckets
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    //each bucket will have another linked list inside
    for (int i = 0; i < 20; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    //find the largest length integer
    int max = 0;
    for (int i : data){
      if (i > max){
        max = i;
      }
    }
    String s = "" + max;
    max = s.length();
    //adding to the buckets for the first time
    for (int i : data){
      //split data into nonegatives and negatives
      if (i >= 0){
        buckets[i%10 + 10].add(i); //second half of the buckets
      }
      else{
        buckets[i%10 + 9].add(i); //first half of the buckets
      }
    }
    MyLinkedList<Integer> m = new MyLinkedList<Integer>();
  }
}
