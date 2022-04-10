package com.stock.sweet.sweetstockapi.utils;

public class ListObject<T> {

    private T[] vector;
    private int numberElement;

    public ListObject(int size) {
        vector = (T[]) new Object[size];
        numberElement = 0;
    }
    
    public void add(T element) {
        if (numberElement >= vector.length) {
            System.out.println("Lista está cheia");
        }
        else {
            vector[numberElement++] = element;
        }
    }

    public void showList() {
        if (numberElement == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < numberElement; i++) {
                System.out.println(vector[i]);
            }
        }
    }
    
    public int search(T elementSearch) {
        for (int i = 0; i < numberElement; i++) {
            if (vector[i].equals(elementSearch)) {   
                return i;                     
            }
        }
        return -1;                   
    }
    
    public boolean removeForIndex(int index) {
        if (index < 0 || index >= numberElement) {
            System.out.println("\nÍndice inválido!");
            return false;
        }
   
        for (int i = index; i < numberElement-1; i++) {
            vector[i] = vector[i+1];
        }

        numberElement--;         
        return true;
    }

    public boolean removeElemento(T elementoToRemove) {
        return removeForIndex(search(elementoToRemove));
    }

    public int getTamanho() {
        return numberElement;
    }

    public T getElemento(int index) {
        if (index < 0 || index >= numberElement) {
            return null;                      
        }
        else {
            return vector[index];
        }
    }
    
    public void erase() {
        numberElement = 0;
    }
}
