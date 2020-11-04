import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
//        MyPriorityQueue myPriorityQueue=new MyPriorityQueue();
//        myPriorityQueue.add(3);
//        myPriorityQueue.add(5);
//        myPriorityQueue.add(2);
//        myPriorityQueue.add(7);
//
//
//        System.out.println(myPriorityQueue.remove());
//        System.out.println(myPriorityQueue.remove());
//        System.out.println(myPriorityQueue.remove());
//        System.out.println(myPriorityQueue.remove());
//        PriorityQueue<Person>queue=new PriorityQueue<>();
        PriorityQueue <Person>queue=new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age-o2.age;

            }
        });
        queue.add(new Person("曹操",40));
        queue.add(new Person("刘备",27));
        queue.add(new Person("诸葛亮",30));
        queue.add(new Person("张飞",19));
        queue.add(new Person("吕布",20));
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
