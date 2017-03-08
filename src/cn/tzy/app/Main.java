package cn.tzy.app;

import java.io.ObjectInputStream;

/**
 * Created by tuzhenyu on 17-3-8.
 * @author tuzhenyu
 */
public class Main {
    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);

        for (Object i : bag){
            System.out.println(i);
        }
    }
}
