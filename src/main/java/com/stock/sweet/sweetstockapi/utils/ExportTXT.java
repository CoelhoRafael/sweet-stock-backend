package com.stock.sweet.sweetstockapi.utils;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
import com.stock.sweet.sweetstockapi.model.Ingredient;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExportTXT {

    public static void gravaRegistro(String register, String nomeArq) {
        BufferedWriter output = null;

        // try-catch para abrir o arquivo
        try {
            output = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        // try-catch para gravar o register e fechar o arquivo
        try {
            output.append(register + "\n");
            output.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo: " + erro);
        }
    }

    public static String gravaArquivoTxt(List<Ingredient> listOfIngredients, String nomeArq) {
        int contaRegCorpo = 0;

        // Monta o register de header
        String header = "00INGREDIENTS20221";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";
        // Grava o register de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registers de corpo

        String corpo = "";
        for (Ingredient a : listOfIngredients) {
            corpo += "02";
            corpo += String.format("%40d", a.getId());
            corpo += String.format("%-50.50s", a.getName());
            corpo += String.format("%-40.40s", a.getUnitMeasurement());
            corpo += String.format("%06.2f", a.getQuantityPerUnit());
            corpo += String.format("%-8.08s", a.getExpirationDate());
            corpo += String.format("%04.2f", a.getStorageTemperature());
            corpo += String.format("%-6.6s", a.getIsRefigerated());
            corpo += String.format("%06.2f", a.getBuyValue());
            corpo += String.format("%05d", a.getQuantityUsed());
            corpo += String.format("%-19.19s", a.getDateInsert());
            corpo += String.format("%06d", a.getNumberLot());
            contaRegCorpo++;
            gravaRegistro(corpo, nomeArq);
        }

        // Monta e grava o register de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegCorpo);
        gravaRegistro(trailer, nomeArq);
        return header;
    }

    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
         String name;
        UnitMeasurement unitMeasurement;
         String register;
        String tipoRegistro;
        Integer quantityUsed, id;
         LocalDate expirationDate, dateInsert;
         Double storageTemperature, quantityPerUnit;
         Boolean isRefigerated;
         BigDecimal buyValue;
         Integer provideCode ,numberLot;

        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<Ingredient> listIngredients = new ArrayList<>();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        // try-catch para ler e fechar o arquivo
        try {
            // Leitura do primeiro register do arquivo
            register = entrada.readLine();

            while (register != null) { // enquanto não chegou ao final do arquivo
                // obtém os 2 primeiros caracteres do register
                // 01234567
                // 00NOTA20221
                tipoRegistro = register.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("É um register de header");
                    System.out.println("Tipo de arquivo: " + register.substring(2,6));
                    System.out.println("Ingredientes: " + register.substring(6,24));
                    System.out.println("Data e hora da gravação: " + register.substring(24,43));
                    System.out.println("Versão do documento: " + register.substring(43,45));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("É um register de trailer");
                    qtdRegCorpoGravado = Integer.parseInt(register.substring(2,12));
                    if (contaRegCorpoLido == qtdRegCorpoGravado) {
                        System.out.println("Quantidade de registers lidos é compatível " +
                                "com a quantidade de registers gravados");
                    }
                    else {
                        System.out.println("Quantidade de registers lidos não é compatível " +
                                "com a quantidade de registers gravados");
                    }
                 }

                else if (tipoRegistro.equals("02")) {
                    System.out.println("É um register de corpo");
                    id = Integer.valueOf(register.substring(3,43));
                    name = register.substring(44,94).trim();
                    unitMeasurement = UnitMeasurement.valueOf(register.substring(95,135).trim());
                    quantityPerUnit = Double.valueOf(register.substring(136,142).replace(',','.'));
                    expirationDate = LocalDate.parse(register.substring(143,151).trim());
                    storageTemperature = Double.valueOf(register.substring(152,156).replace(',','.'));
                    isRefigerated = Boolean.valueOf(register.substring(157,162).trim());
                    buyValue = BigDecimal.valueOf(Double.parseDouble(register.substring(163,169).replace(',','.')));
                    quantityUsed = Integer.valueOf(register.substring(170,175).trim());
                    dateInsert = LocalDate.parse(register.substring(176,196).trim());
                    numberLot = Integer.valueOf(register.substring(197,203));
                    contaRegCorpoLido++;

                     Ingredient a = new Ingredient(id,name,quantityPerUnit,unitMeasurement,expirationDate,
                            storageTemperature,isRefigerated,buyValue,quantityUsed,
                            buyValue,dateInsert,numberLot);

                    // No projeto de PI, poderia fazer:
                    // repository.save(a);

                    // No nosso caso, vamos adicionar o objeto a na listIngredients:
                    listIngredients.add(a);
                }
                else {
                    System.out.println("Tipo de register inválido!");
                }

                // Lê o próximo register
                register = entrada.readLine();
            }

            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro);
        }

        // No Projeto de PI, pode-se fazer:
        // repository.saveAll(listIngredients);

        // Vamos exibir a listIngredients
        System.out.println("\nConteúdo da lista lida:");
        for (Ingredient a : listIngredients) {
            System.out.println(a);
        }
    }

}


