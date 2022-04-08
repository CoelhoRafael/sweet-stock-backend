package com.stock.sweet.sweetstockapi.utils;

import com.stock.sweet.sweetstockapi.model.Ingredient;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ArqCSV {

    public static void gravaArquivoCsv(ListaObj<Ingredient> listOfIngredients, String nomeArq){
        FileWriter arq = null; //obj representa o arquivo que sera gravado
        Formatter saida = null; //obj que usaremos para escrever no arquivo
        Boolean erro = false;
        nomeArq +=".csv";
        //bloco try catch para abri arq

        try{
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }catch (IOException e){
            System.out.println("Erro ao abrir arquivo");
            System.exit(1);

        }
        saida.format("Id|" +
                "Nome|" +
                "Quantidade|" +
                "Validade|" +
                "Temperatura de armazenamento|" +
                "Refrigeração|" +
                "Valor de compra|" +
                "Quantidade utilizada|" +
                "Data registro|" +
                "Número do lote|\n");
        try{
            //Percorrer lista de cachorros
            for (int i = 0; i<listOfIngredients.getTamanho(); i++){
                var ingredient=listOfIngredients.getElemento(i);
                saida.format("%s;%s;%.2f;%s;%.2f;%s;%.2f;%.2f;%s;%d\n",
                        ingredient.getUuid(),
                        ingredient.getName(),
                        ingredient.getQuantity(),
                        ingredient.getExpirationDate(),
                        ingredient.getStorageTemperature(),
                        ingredient.getIsRefigerated() ? "Sim" : "Não",
                        ingredient.getBuyValue(),
                        ingredient.getQuantityUsed(),
                        ingredient.getNumberLot()
                        );
            }
        }catch (FormatterClosedException e){
            System.out.println("Erro ao gravar o arquivo");
            erro = true;
        }
        finally {
            saida.close();
            try{
                arq.close();
            }catch (IOException e){
                System.out.println("Erro ao fechar arquivo");
                erro = true;
            }
            if (erro){
                System.exit(1);
            }
        }
    }
    public static void leExibeAqrCsv(String nomeAqr){
        FileReader arq = null;
        Scanner in = null;
        Boolean deuRuim = false;
        nomeAqr += ".csv";

        try{
            arq = new FileReader(nomeAqr);
            in = new Scanner(arq).useDelimiter(";|\n");
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }
        try{
            System.out.printf("%4s %-15s %-9s %04s \n","ID","NOME","PORTE","PESO");
            while(in.hasNext()){
                Integer id = in.nextInt();
                String nome = in.next();
                String porte = in.next();
                Double peso = in.nextDouble();
                System.out.printf("%4d %-15s %-9s %4.2f", id,nome,porte,peso);
            }
        }catch (NoSuchElementException e){
            System.out.println("Arquivo com problema");
            deuRuim = true;

        }
        catch (  IllegalStateException e){
            System.out.println("erro leito do arq");
            deuRuim =true;
        } finally {
            in.close();
            try{
                arq.close();
            }catch (IOException e){
                System.out.println("Erro ao fechar arquivo");
                deuRuim = true;
            }
            if (deuRuim){
                System.exit(1);
            }
        }
    }

}
