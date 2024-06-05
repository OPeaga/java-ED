package DataStructures;

import java.util.NoSuchElementException;

class ListaDuplamenteEncadeada {
    private class Node {

        private int linha;
        private String chave;
        public Node proximo;
        public Node anterior;

        public Node(String chave, int linha) {
            this.chave = chave;
            this.linha = linha;
            this.proximo = null;
            this.anterior = null;
        }

        public int getLinha() {
            return linha;
        }

        public void setLinha(int linha) {
            this.linha = linha;
        }

        public String getChave() {
            return chave;
        }

        public void setChave(String chave) {
            this.chave = chave;
        }
    }

    private Node primeiro;
    private Node ultimo;
    private int nElementos;

    public ListaDuplamenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.nElementos = 0;
    }

    public void insereInicio(String chave, int valor) {
        Node novoNodo = new Node(chave, valor);
        if (primeiro == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            novoNodo.proximo = primeiro;
            primeiro.anterior = novoNodo;
            primeiro = novoNodo;
        }
        nElementos++;
    }

    public void removeInicio() {
        if (primeiro == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            primeiro = primeiro.proximo;
            if (primeiro != null)
                primeiro.anterior = null;
            nElementos--;
            if (nElementos == 0) {
                ultimo = null;
            }
        }
    }

    public void insereFinal(String chave, int valor) {
        Node novoNodo = new Node(chave,valor);
        if (ultimo == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            ultimo.proximo = novoNodo;
            novoNodo.anterior = ultimo;
            ultimo = novoNodo;
        }
        nElementos++;
    }

    public void removeFinal() {
        if (ultimo == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            if (primeiro == ultimo) {
                primeiro = null;
                ultimo = null;
            } else {
                ultimo = ultimo.anterior;
                ultimo.proximo = null;
            }
            nElementos--;
        }
    }

    public void inserePosicao(String chave, int valor, int posicao) {
        if (posicao < 0 || posicao > nElementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            insereInicio(chave,valor);
        } else if (posicao == nElementos) {
            insereFinal(chave, valor);
        } else {
            Node novoNodo = new Node(chave, valor);
            Node atual = primeiro;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.proximo;
            }
            novoNodo.proximo = atual.proximo;
            novoNodo.anterior = atual;
            atual.proximo.anterior = novoNodo;
            atual.proximo = novoNodo;
            nElementos++;
        }
    }

    public void removePosicao(int posicao) {
        if (posicao < 0 || posicao >= nElementos) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            removeInicio();
        } else if (posicao == nElementos - 1) {
            removeFinal();
        } else {
            Node atual = primeiro;
            for (int i = 0; i < posicao; i++) {
                atual = atual.proximo;
            }
            atual.anterior.proximo = atual.proximo;
            atual.proximo.anterior = atual.anterior;
            nElementos--;
        }
    }

    public void imprimirLista() {
        Node atual = primeiro;
        while (atual != null) {
            if (atual == primeiro){
                System.out.print(atual.getChave());
            }
            System.out.print(" " + atual.getLinha());
            atual = atual.proximo;
        }
        System.out.println();
    }

    public boolean temChave(){
        Node no = primeiro;
        if (primeiro == null){
            return false;
        }
        return true;
    }

}
