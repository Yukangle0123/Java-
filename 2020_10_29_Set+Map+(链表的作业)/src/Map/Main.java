package Map;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<String, String> m = new TreeMap<>();
        m.put("林冲", "豹子头");
        m.put("鲁智深", "花和尚");
        m.put("武松", "行者");
        m.put("宋江", "及时雨");
        String str = m.put("李逵", "黑旋风");
        System.out.println(str);
        System.out.println(m.size());
        System.out.println(m);
        str = m.put("无名", null);
        System.out.println(m.size());
        Set<String> strings = m.keySet();
        for(String s:strings){
            System.out.println(s);
            System.out.println(m.get(s));
        }
        System.out.println("------------------------------");
        Set<Map.Entry<String, String>> set = m.entrySet();
        for(Map.Entry<String,String> e:set){
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }



    }
}
