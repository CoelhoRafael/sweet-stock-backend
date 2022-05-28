package com.stock.sweet.sweetstockapi.utils;

public class StackObj<T> {

    private T[] pilha;
    private int topo;

    public StackObj(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    public Boolean isEmpty() {
        return this.topo == -1;
    }

    public Boolean isFull() {
        return this.topo == pilha.length - 1;
    }

    public void push(T info) {
        if (!isFull()) {
            pilha[++topo] = info;
        } else {
            throw new IllegalStateException();
        }
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Lista Vazia!");
            return null;
        } else {
            return pilha[topo--];
        }
    }

    public T peek() {
        if (isEmpty()) return null;
        return pilha[topo];
    }
}
