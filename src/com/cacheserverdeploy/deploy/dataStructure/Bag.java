package com.cacheserverdeploy.deploy.dataStructure;

import java.util.Iterator;

/**
 * Created by tuzhenyu on 17-3-8.
 * @author tuzhenyu
 */
public class Bag<Item> implements Iterable<Item>{
    private int count;
    private Node first;

    private class Node{
        Item item;
        Node next;
    }

    public void add(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        count++;
    }

    public int length(){
        return count;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
