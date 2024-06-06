package DataStructures;

import java.util.NoSuchElementException;

public class ListaEncadeada<T> {
    public static class Nodo<T> {
        private T elemento;
        // TAD, objetos ou dados primários
        private Nodo<T> proxNodo;

        // Ponteiro
        public Nodo(T elemento) {
            this.elemento = elemento;
            this.proxNodo = null;
        }

        public T getElemento() {
            return elemento;
        }

        public void setElemento(T elemento) {
            this.elemento = elemento;
        }

        public Nodo<T> getProxNodo() {
            return proxNodo;
        }

        public void setProxNodo(Nodo<T> proxNodo) {
            this.proxNodo = proxNodo;
        }
    }

    private Nodo<T> inicio;

    private Nodo<T> ultimo;
    //Podemos imaginar como inicio a seta logo após o inicio conceitual : Inicio --> inicio(null)
    private int quantidadeElementos;

    public ListaEncadeada() {
        this.inicio = null;
        this.ultimo = null;
        this.quantidadeElementos = 0;
    }

    public int getQuantidadeElementos() {
        return quantidadeElementos;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public void insereInicio(T valor) {
        Nodo novoNodo = new Nodo(valor);
        if (inicio == null) {
            inicio = novoNodo;
            ultimo = novoNodo;
        } else {
            novoNodo.proxNodo = inicio;
            inicio = novoNodo;
        }
        quantidadeElementos++;
    }

    public void insereFinal(T elemento) {
        Nodo<T> novoNodo = new Nodo<>(elemento);

        if (inicio == null) {
            inicio = novoNodo;
            ultimo = novoNodo;

        } else {
            ultimo.proxNodo = novoNodo;
            ultimo = novoNodo;
        }
        quantidadeElementos++;
    }

    public void imprimirLista() {
        Nodo atual = inicio;
        while (atual != null) {
            System.out.print(atual.elemento + " ");
            atual = atual.proxNodo;
        }
        System.out.println();
    }

    public T elemento(int pos){
        Nodo<T> atual = inicio;
        int posNodoAtual = 0;
        while (posNodoAtual != pos){
            atual = atual.proxNodo;
            posNodoAtual++;
        }
        return atual.elemento;
    }

//    public void inserePos(T elemento, int pos) {
//        if (pos == 0) {
//            insereInicio(elemento);
//        } else if (pos < 0 || pos > quantidadeElementos) {
//            throw new IndexOutOfBoundsException("POSICAO INEXISTENTE");
//        } else {
//            Nodo<T> novoNodo = new Nodo<>(elemento);
//            Nodo<T> nodoAtual = inicio;
//            for (int index = 0; index < pos - 1; index++) {
//                nodoAtual = nodoAtual.getProxNodo();
//            }
//            novoNodo.setProxNodo(nodoAtual.getProxNodo());
//            nodoAtual.setProxNodo(novoNodo);
//            quantidadeElementos++;
//        }
//    }
//
//    public void removePos(int pos) {
//        if (pos < 0 || pos >= quantidadeElementos) {
//            throw new IndexOutOfBoundsException("Posição inválida");
//        }
//        if (inicio == null) {
//            System.out.println("Lista Vazia");
//        } else if (pos == quantidadeElementos - 1) {
//            removeFinal();
//        } else if (pos == 0) {
//            removeInicio();
//        } else {
//            Nodo<T> nodoAtual;
//            nodoAtual = inicio;
//            for (int i = 0; i < pos - 1; i++) {
//                nodoAtual = nodoAtual.getProxNodo();
//            }
//            nodoAtual.setProxNodo(nodoAtual.getProxNodo().getProxNodo());
//            quantidadeElementos--;
//        }
//    }
//
//    public void removeFinal() {
//        if (inicio == null) {
//            System.out.println("Lista vazia");
//        } else {
//            Nodo<T> atual;
//            atual = inicio;
//            for (int i = 0; i < quantidadeElementos - 2; i++){
//                atual = atual.getProxNodo();
//            }
//            atual.setProxNodo(null);
//            quantidadeElementos--;
//        }
//    }
//
//    public void removeInicio(){
//        if (inicio == null){
//            throw new NoSuchElementException("Lista Vazia");
//        } else {
//            inicio = inicio.getProxNodo();
//        }
//    }
}
