package edu.caltech.cs2.project08.datastructures;

import edu.caltech.cs2.project08.interfaces.IDeque;
import edu.caltech.cs2.project08.interfaces.IQueue;
import edu.caltech.cs2.project08.interfaces.IStack;

import java.util.Iterator;

public class LinkedDeque<E> implements IDeque<E>, IQueue<E>, IStack<E> {
    public class Node {
        public E data;
        public Node next;
        public Node prev;

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;


    public LinkedDeque(){
        this.size = 0;
    }

    @Override
    public void addFront(E e) {
        if (this.size == 0) {
            this.head = new Node(e);
            this.head.prev = null;
            this.head.next = null;
            this.tail = this.head;
            this.size++;
        }
        else if (this.size == 1){
            Node temp = new Node(e);
            temp.next = this.head;
            this.tail = temp.next;
            this.head = temp;
            this.tail.prev = this.head;
            this.tail.next = null;
            this.head.prev = null;
            this.size++;
        }
        else {
            Node temp = new Node(e);
            temp.next = this.head;
            this.head = temp;
            temp.next.prev = this.head;
            this.head.prev = null;
            this.size++;
        }
    }

    @Override
    public void addBack(E e) {
        if (this.size == 0){
            this.head = new Node(e);
            this.head.next = null;
            this.head.prev = null;
            this.tail = this.head;
            this.size++;
        }
        else if (this.size == 1){
            this.tail = new Node(e);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.tail.next = null;
            this.size++;
        }
        else {
            this.tail.next = new Node(e, null);
            Node temp = this.tail;
            this.tail = this.tail.next;
            this.tail.prev = temp;
            this.tail.next = null;
            this.size++;
        }
    }

    @Override
    public E removeFront() {
        if(this.size == 0){
            return null;
        }
        else if (this.size == 1){
            E removedValue = this.head.data;
            this.head = null;
            this.tail = null;
            this.size--;
            return removedValue;
        }
        else if(this.size == 2){
            E removedValue = this.head.data;
            this.head = this.tail;
            this.size--;
            return removedValue;
        }
        else {
            E removedValue = this.head.data;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return removedValue;
        }
    }

    @Override
    public E removeBack() {
        if(this.size == 0){
            return null;
        }
        else if(this.size == 1){
            return removeFront();
        }
        else if (this.size == 2){
            E removedValue = this.tail.data;
            this.tail = this.head;
            this.head.next = null;
            this.tail.prev = null;
            this.size--;
            return removedValue;
        }
        else {
            E removedValue = this.tail.data;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            return removedValue;
        }
    }

    @Override
    public boolean enqueue(E e) {
        addFront(e);
        return true;
    }

    @Override
    public E dequeue() {
        return removeBack();
    }

    @Override
    public boolean push(E e) {
        addBack(e);
        return true;
    }

    @Override
    public E pop() {
        return removeBack();
    }

    @Override
    public E peek() {
        return peekBack();
    }

    @Override
    public E peekFront() {
        if (this.head == null){
            return null;
        }
        else {
            return this.head.data;
        }
    }

    @Override
    public E peekBack() {
        if(this.head == null && this.tail == null){
            return null;
        }
        else if (this.head != null && this.tail == null){
            return peekFront();
        }
        else {
            return this.tail.data;
        }
    }

    private class SASIterator implements Iterator<E> {

        private Node current = LinkedDeque.this.head;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < LinkedDeque.this.size();
        }

        @Override
        public E next() {
            E result = this.current.data;
            current = current.next;
            this.currentIndex++;
            return result;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SASIterator();
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "[]";
        }

        Node curr = this.head;
        String result = "";
        while (curr != null && curr.next != null) {
            result += curr.data + ", ";
            curr = curr.next;
        }

        return "[" + result + curr.data + "]";
    }
}