package pr2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyPlainArrList<E> implements List<E> {
    public Object[] data;

    private int index = 0;

    public MyPlainArrList() {
        data = new Object[10];
    }

    public MyPlainArrList(int capacity) {
        data = new Object[capacity];
    }


    @Override
    public boolean add(E e) {
        if (index + 1 > data.length){
            growUpArray();
        }
        data[index] = e;
        index++;
        return true;
    }

    private void growUpArray(){
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        this.data = newData;
    }


    @Override
    public E get(int index) {
        Object datum = data[index];
        return (E) datum;
    }

    @Override
    public E remove(int removedIndex) {
        E removed = get(removedIndex);
        System.arraycopy(data, removedIndex+1, data, removedIndex, index - removedIndex);
        index--;
        return removed;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean remove(Object o) {
        for (int i =0; i < index; i++){
            if (data[i].equals(o)){
                remove(i);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean isEmpty( ) {
        return index == 0;
    }

    @Override
    public boolean contains (Object o) {
        for (int i =0; i < index; i++){
            if (data[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        data = new Object[data.length];
        index = 0;
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    /* implment methods above addFirst(), addLast()*/

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }




    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
