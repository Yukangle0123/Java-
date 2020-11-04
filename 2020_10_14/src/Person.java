public class Person  {
    public String name;
    public  int age;
    Person(String name,int age){
        this.name=name;
        this.age=age;
    }

//    @Override
//    public int compareTo(Object o) {
//        Person p=(Person)o;
//        return this.age-p.age;
//    }
    public String toString(){
        return "{"+this.age+this.name+" }";
    }
}
