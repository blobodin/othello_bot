package edu.caltech.cs2.project08.datastructures;

import edu.caltech.cs2.project08.interfaces.IDeque;
import edu.caltech.cs2.project08.interfaces.IQueue;
import edu.caltech.cs2.project08.interfaces.IStack;

import java.util.Iterator;

public class ArrayDeque<E> implements IDeque<E>, IQueue<E>, IStack<E> {
    private E[] data;
    private static final int INT_CAPACITY = 10;
    private static final int growFactor = 2;
    private int size;


    public ArrayDeque(int initialCapacity) {
        this.size = 0;
        this.data = (E[])new Object[initialCapacity];
    }

    public ArrayDeque() {
        this(INT_CAPACITY);
    }

    private void changeCapacity() {
        E[] newData = (E[])new Object[(this.data.length*growFactor)];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }

    @Override
    public void addFront(E e) {
        if (this.size + 1 > this.data.length) {
            changeCapacity();
        }
        this.size++;
        for(int i = this.size - 1; i >= 0; i--){
            if (i == 0){
                this.data[i] = e;
            }
            else{
                this.data[i] = this.data[i - 1];
            }
        }

    }

    @Override
    public void addBack(E e) {
        if (this.size >= this.data.length){
            changeCapacity();}
        this.data[this.size] = e;
        this.size++;
    }

    @Override
    public E removeFront() {
        if (this.size == 0){
            return null;
        }
        else {
            E removedValue = this.data[0];
            for (int i = 0; i < this.size - 1; i++) {
                this.data[i] = this.data[i + 1];
            }
            this.size--;
            return removedValue;
        }
    }

    @Override
    public E removeBack() {
        if(this.size == 0){
            return null;
        }
        else {
            E removedValue = this.data[this.size -1];
            this.size--;
            return removedValue;
        }
    }

    @Override
    public boolean enqueue(E e) {
        if (this.size >= this.data.length) {
            changeCapacity();
        }
        addFront(e);
        return true;
    }

    @Override
    public E dequeue() {
        if(this.size == 0){return null;}
        else {
            E removedValue = removeBack();
            return removedValue;

        }
    }

    @Override
    public boolean push(E e) {
//        if (this.size >= this.data.length){
//            changeCapacity();
//        }
//        for(int i = this.size; i > 0; i--){
//            this.data[i] = this.data[i - 1];
//        }
//        this.data[0] = e;
//        this.size++;
//        return true;
        if (this.size >= this.data.length){
            changeCapacity();
        }
        this.data[this.size] = e;
        this.size++;
        return true;
    }

    @Override
    public E pop() {
        if(this.size == 0){
            return null;
        }
        else {
            E removedValue = this.data[this.size - 1];
            this.size--;
            return removedValue;
        }
    }

    @Override
    public E peek() {
        if(this.size == 0){
            return null;
        }
        else{
            return this.data[this.size - 1];
        }
    }

    @Override
    public E peekFront() {
        if(this.size == 0){
            return null;
        }
        else{
            return this.data[0];
        }
    }

    @Override
    public E peekBack() {
        if(this.size == 0){
            return null;
        }
        else{
            return this.data[this.size - 1];
        }
    }

    private class SASIterator implements Iterator<E> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < ArrayDeque.this.size();
        }

        @Override
        public E next() {
            E result = ArrayDeque.this.data[this.currentIndex];
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
    public String toString(){
        if (this.size == 0) {
            return "[]";
        }

        String result = "[";
        for (int i = 0; i < this.size - 1; i++) {
            result += this.data[i] + ", ";
        }
        return result + this.data[this.size - 1] + "]";
    }
}

