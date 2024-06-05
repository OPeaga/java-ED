import DataStructures.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class IndiceRemissivo {
    private ArvoreBinariaBusca arvoreBinariaBusca = new ArvoreBinariaBusca();
    private HashTable hashTable = new HashTable(500);
    String[] palavras_chave;

    public IndiceRemissivo() throws IOException {
//        Metodo para pegar os nomes presentes no indice e insere em uma arvore de busca binaria
        this.getNomesIndice();
        this.getIndicesPorNome();
        hashTable.imprime();
    }

    public String[] getNomesIndice() throws IOException {
        //Aquei eu pego os nome presentes no arquivo de indice e os armazeno em uma ABB - ok
        String nomeArquivo = "palavras-chave.txt";

        File arquivoIndices =
                new File("C:\\Users\\Lorde\\Documents\\Estudos\\Faculdade\\EST.DADOS\\projetoFinal\\"+ nomeArquivo);

        boolean state = arquivoIndices.createNewFile();

        if (state){
            System.out.println("Arquivo foi criado: " + arquivoIndices.getName());
        } else {
            System.out.println("Arquivo já presente: " + arquivoIndices.getName());
        }


        try(BufferedReader br = new BufferedReader(new FileReader(arquivoIndices))){
            String linha = br.readLine();

            while (linha != null){

                palavras_chave = linha.split(", ");

                for (int i = 0; i < palavras_chave.length; i++) {
                    arvoreBinariaBusca.insere(palavras_chave[i]);
                }
                linha = br.readLine();
            }

        } catch (IOException e){
            System.out.println("Erro do tipo: " + e.getMessage());
        }
        return palavras_chave;
    }

    public void getIndicesPorNome() throws IOException {
        //Após ter pegue as palavras eu crio uma HashTable para armazenar a linha e a palavra chave do texto em Hash
        // - ok
        String nomeArquivo = "texto.txt";

        File arquivoTexto =
                new File("C:\\Users\\Lorde\\Documents\\Estudos\\Faculdade\\EST.DADOS\\projetoFinal\\"+ nomeArquivo);

        boolean state = arquivoTexto.createNewFile();

        if (state){
            System.out.println("Arquivo foi criado: " + arquivoTexto.getName());
        } else {
            System.out.println("Arquivo já presente: " + arquivoTexto.getName());
        }


        try(BufferedReader br = new BufferedReader(new FileReader(arquivoTexto))){
            String linha = br.readLine();
            String[] todasPalavrasLinha;
            int contadorLinhas = 0;

            while (linha != null){

                contadorLinhas++;

                String regex = "(?<!\\w)-|\\p{Punct}(?!-)";

                String linhaSemPontuacoes = linha.replaceAll(regex,"");

                todasPalavrasLinha = linhaSemPontuacoes.split(" ");

//                Agora basta fazer um método para ler as palavras e verificar se estão
//                na arvore de busca - ok

                for (int i = 0; i < todasPalavrasLinha.length; i++) {
                    if (arvoreBinariaBusca.busca(todasPalavrasLinha[i])){
                        hashTable.adiciona(todasPalavrasLinha[i],contadorLinhas);
                    }
                }

                linha = br.readLine();
            }

        } catch (IOException e){
            System.out.println("Erro do tipo: " + e.getMessage());
        }
    }

//    Criar metodo para imprimir indice remissivo em ordem, utilizando da arvore de busca e em seguida o hash

//    Criar metodo para gravar em arquivo o indice remissivo em ordem alfabetica


    public static void main(String[] args) throws IOException {
        IndiceRemissivo newIndice = new IndiceRemissivo();
        System.out.println();
        System.out.println("Resultados da Arvore:");
        newIndice.arvoreBinariaBusca.imprimeEmOrdem();



    }
}
