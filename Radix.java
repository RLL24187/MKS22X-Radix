public class Radix{
  public static void radixsort(int[]data){
    @SuppressWarnings("unchecked")
    //creating the buckets
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    //each bucket will have another linked list inside, makes this faster
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
    MyLinkedList<Integer> m = new MyLinkedList<Integer>(); //MLL to stores values into
    int modder = 10; //keep track of what number to mod by for the correct digit
    while (max > 0){
      modder *= 10;
      //fill the buckets in the MLL
      m.clear(); //make sure the list is clear first
      for (MyLinkedList<Integer> bucket : buckets){
        //K said to connect all the linked lists together
        m.extend(bucket);
      }
      for (int i : m){
        //now for every integer in m (formerly the buckets) fill buckets based on negativity
        if (i >= 0){
          buckets[i%modder/(modder/10) + 10].add(i);
        }
        else buckets[i%modder/(modder/10)].add(i);
      }
      max--; //reduce number of times you still have to sort by buckets
    }
    //final adjustments
    m.clear();
    for (MyLinkedList<Integer> bucket : buckets){
      m.extend(bucket);
    }
    for (int i : m){
      //note: max must already be 0, this will use less memory
      data[max] = i;
      max++;
    }
  }
}
