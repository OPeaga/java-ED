package DataStructures;

public class HashTable {

    private ListaDuplamenteEncadeada[] tabela;
    private int quantidade;


    public HashTable(int tamanho) {

        this.tabela = new ListaDuplamenteEncadeada[tamanho];

        for (int i = 0; i < tabela.length; i++) {
//            Gera para cada endereço uma lista encadeada
            this.tabela[i] = new ListaDuplamenteEncadeada();
        }
    }

    private int calcularHash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = hash + ((i+1) * chave.charAt(i));
//            Multiplica pela posicao para evitar colisoes de palavras com mesmas letras
        }
        return Math.abs(hash) % tabela.length;
    }

    public void adiciona(String palavra, int linha){
        palavra = palavra.toLowerCase();
        int index = calcularHash(palavra);
        tabela[index].insereFinal(palavra, linha);
        quantidade++;
    }

    public void imprime() {
        System.out.println("Chave\tValor");
        for (int i = 0; i < tabela.length; i++) {
//            So imprime se tiver chave
            if (tabela[i].temChave()){
                System.out.print(i + " -->\t");
                tabela[i].imprimirLista();
            }
        }
    }

}