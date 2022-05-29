package com.stock.sweet.sweetstockapi.utils;

import java.util.ArrayList;
import java.util.List;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        tamanho = 0;
        fila = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return fila.length == tamanho;
    }

    public void insert(T info) {
        if (!isFull()) {
            fila[tamanho++] = info;
        } else {
            throw new IllegalStateException();
        }
    }

    public T peek() {
        if (!isEmpty()) {
            return fila[0];
        }
        return null;
    }

    public T poll() {
        if (!isEmpty()) {
            T item = fila[0];
            for (int i = 0; i < tamanho - 1; i++) {
                fila[i] = fila[i + 1];
            }
            tamanho--;
            return item;
        }
        return null;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public List<T> retornarTodos() {
        List<T> todos = new ArrayList<>();
        for (int i = 0; i < this.getTamanho(); i++) {
            todos.add(fila[i]);
        }
        return todos;
    }
}
