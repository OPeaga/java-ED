import DataStructures.*;

import java.io.*;
import java.io.IOException;


public class IndiceRemissivo {
    private ArvoreBinariaBusca arvoreBinariaBusca = new ArvoreBinariaBusca();
    private HashTable hashTable = new HashTable(500);
    String[] palavras_chave;

    public IndiceRemissivo() throws IOException {
//        Metodo para pegar os nomes presentes no indice e insere em uma arvore de busca binaria
        this.getNomesIndice();
        this.getIndicesPorNome();
        this.getIndiceRemissivoOrdenado();
    }

    public String[] getNomesIndice() throws IOException {
        //Aquei eu pego os nome presentes no arquivo de indice e os armazeno em uma ABB - ok
        String nomeArquivo = "palavras-chave.txt";

        File arquivoIndices =
                new File("C:\\Users\\Lorde\\Documents\\Estudos\\Faculdade\\EST.DADOS\\projetoFinal\\"+ nomeArquivo);


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
                    if (arvoreBinariaBusca.busca(todasPalavrasLinha[i].toLowerCase())){
                        hashTable.adiciona(todasPalavrasLinha[i],contadorLinhas);
                    }
                }

                linha = br.readLine();
            }

        } catch (IOException e){
            System.out.println("Erro do tipo: " + e.getMessage());
        }
    }

//    Criar metodo para imprimir indice remissivo em ordem, utilizando da arvore de busca e em seguida o hash e
//    gravar em arquvio - ok porraa
    public void getIndiceRemissivoOrdenado() throws IOException {

        String nomeArquivoGravacao = "arquivoIndiceRemissivo.txt";
        File arquivoGravacao = new File("C:\\Users\\Lorde\\Documents\\Estudos\\Faculdade\\EST" +
                ".DADOS\\projetoFinal\\"+nomeArquivoGravacao);

        boolean state = arquivoGravacao.createNewFile();

        if (state){
            System.out.println("Arquivo foi criado: " + arquivoGravacao.getName());
        } else {
            System.out.println("Arquivo já presente: " + arquivoGravacao.getName());
//            Se o arquivo já estiver presente, apagar ele para criar outro
            try {
                System.out.println("Arquivo anterior deletado " + arquivoGravacao.delete());
                System.out.println("Arquivo novo criado " + arquivoGravacao.createNewFile());

            } catch (SecurityException e){
                System.out.println("Erro de segurança");
            }
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoGravacao))){
//            Parte mais dificil, pegar os valores ordenados para posteriormente localizalos na has e colocar no arquivo

            bw.write("Indice Remissivo");
            bw.newLine();

            for (int i = 0; i < arvoreBinariaBusca.tamanho(); i++) {
                String chave = arvoreBinariaBusca.getChavesOrdenadas().elemento(i);
                System.out.print(chave + ": ");
                bw.write(chave +": ");

                for (int j = 0; j < hashTable.valoresChave(chave).length; j++) {
                    System.out.print(hashTable.valoresChave(chave)[j] + " ");
                    bw.write(hashTable.valoresChave(chave)[j] +" ");
                }
                bw.newLine();
            }


        }
        catch ( IOException e){
            System.out.println("Erro do tipo: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        IndiceRemissivo newIndice = new IndiceRemissivo();
        System.out.println();
        System.out.println("Arvore de Busca armazenou:");
        newIndice.arvoreBinariaBusca.imprimeEmOrdem();
        System.out.println("Hash armazenou:");
        newIndice.hashTable.imprime();



    }
}
