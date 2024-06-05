package DataStructures;

public class ArvoreBinariaBusca {

    private class Nodo {

        public String chave;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(String chave) {
            this.chave = chave;
            this.esquerdo = null;
            this.direito = null;
        }

    }


    public Nodo raiz;
    public int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

//    public void imprimeEmLargura() {
//
//        FilaDinamica<Nodo> fila = new FilaDinamica<Nodo>();
//
//        fila.enfileira(this.raiz);
//        while (!fila.estaVazia()) {
//
//            Nodo cursor = fila.desenfileira();
//
//            System.out.print(cursor.elemento + " ");
//
//            if (cursor.esquerdo != null) {
//                fila.enfileira(cursor.esquerdo);
//            }
//
//            if (cursor.direito != null) {
//                fila.enfileira(cursor.direito);
//            }
//        }
//
//        System.out.println();
//
//    }

    public void imprimePreOrdem() {
        this.preOrdem(this.raiz);
        System.out.println();
    }

    public void imprimePosOrdem() {
        this.posOrdem(this.raiz);
        System.out.println();
    }

    public void imprimeEmOrdem() {
        this.emOrdem(this.raiz);
        System.out.println();
    }

    public void preOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        System.out.print(nodo.chave + " ");
        this.preOrdem(nodo.esquerdo);
        this.preOrdem(nodo.direito);
    }

    public void posOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.posOrdem(nodo.esquerdo);
        this.posOrdem(nodo.direito);
        System.out.print(nodo.chave + " ");
    }

    public void emOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.emOrdem(nodo.esquerdo);
        System.out.print(nodo.chave + "\n");
        this.emOrdem(nodo.direito);
    }

    public void insere(String chave) {
        this.insere(chave.toLowerCase(), this.raiz);
    }

    private void insere(String chave, Nodo nodo) {

        Nodo novo = new Nodo(chave);

        if (nodo == null) {
            this.raiz = novo;
            this.nElementos++;
            return;
        }

        if (chave.compareTo(nodo.chave) < 0) {
            if (nodo.esquerdo == null) {
                nodo.esquerdo = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(chave, nodo.esquerdo);
            }
        }

        if (chave.compareTo(nodo.chave) > 0) {
            if (nodo.direito == null) {
                nodo.direito = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(chave, nodo.direito);
            }
        }
    }

    private Nodo maiorElemento(Nodo nodo) {
        while (nodo.direito != null) {
            nodo = nodo.direito;
        }
        return nodo;
    }

    private Nodo menorElemento(Nodo nodo) {
        while (nodo.esquerdo != null) {
            nodo = nodo.esquerdo;
        }
        return nodo;
    }


    public boolean busca(String chave) {
        return this.busca(chave, this.raiz);
    }

    private boolean busca(String chave, Nodo nodo) {

        if (nodo == null) {
            return false;
        }

        if (chave.compareTo(nodo.chave) < 0) {
            return this.busca(chave, nodo.esquerdo);
        } else if (chave.compareTo(nodo.chave) > 0) {
            return this.busca(chave, nodo.direito);
        } else {
            return true;
        }
    }

    private int altura(Nodo nodo) {

        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
        int alturaDireita = this.altura(nodo.direito) + 1;

        int altura = Math.max(alturaEsquerda, alturaDireita);

        return altura;

    }

    public int altura() {
        return this.altura(this.raiz);
    }

}
