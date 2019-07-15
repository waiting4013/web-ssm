package gather;

import java.util.*;

public class TestCollection {

    public static void main(String[] args){
        //int[] adq={1,2,3};
        List<Integer> strings=new ArrayList<>();
        strings.add(1);
        strings.add(9);
        strings.add(10);
        strings.add(7);
        strings.add(8);

        Collections.sort(strings);
        System.out.println(strings);
        Set<String> stringc=new HashSet<>();
        stringc.add("zs");
        stringc.add("sad");
        stringc.add("fasf");
        stringc.add("3");
        stringc.add("fa3sf");
        stringc.add("fas22f");
        Iterator it=stringc.iterator();

        while (it.hasNext()){

            if("zs".equals(it.next())){

                it.remove();
            }

        }
        System.out.println(stringc);
    }
}
