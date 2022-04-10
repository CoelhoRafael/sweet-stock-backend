package com.stock.sweet.sweetstockapi.utils;

import com.stock.sweet.sweetstockapi.model.Ingredient;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileCSV {

    public static String chaseFileCSV(ListObject<Ingredient> listOfIngredients, String nameFile) {
        FileWriter file = null;
        Formatter output = null;
        Boolean wasError = false;
        String relatory = "";

        nameFile += ".csv";

        String formatColumns = "Id;" +
                "Nome;" +
                "Quantidade;" +
                "Validade;" +
                "Temperatura de armazenamento;" +
                "Refrigeração;" +
                "Valor de compra;" +
                "Cod. Fornecedor;" +
                "Quantidade utilizada;" +
                "Data registro;" +
                "Número do lote\n";

        String stringFormatIngredient = "%s;%.2f;%s;%.2f;%s;%.2f;%d;%.2f;%s;%d\r\n";

        try {
            file = new FileWriter(nameFile);
            output = new Formatter(file);
        } catch (IOException e) {
            System.out.println("Erro ao abrir arquivo");
            System.exit(1);
        }

        output.format(formatColumns);
        relatory += formatColumns;

        try {

            for (int i = 0; i < listOfIngredients.getTamanho(); i++) {
                Ingredient ingredient = listOfIngredients.getElemento(i);

                relatory += String.format(stringFormatIngredient,
                        ingredient.getName(),
                        ingredient.getQuantity(),
                        ingredient.getExpirationDate(),
                        ingredient.getStorageTemperature(),
                        ingredient.getIsRefigerated() ? "Sim" : "Não",
                        ingredient.getBuyValue(),
                        ingredient.getProvideCode(),
                        ingredient.getQuantityUsed(),
                        ingredient.getDateInsert(),
                        ingredient.getNumberLot()
                );

                output.format(stringFormatIngredient,
                        ingredient.getName(),
                        ingredient.getQuantity(),
                        ingredient.getExpirationDate(),
                        ingredient.getStorageTemperature(),
                        ingredient.getIsRefigerated() ? "Sim" : "Não",
                        ingredient.getBuyValue(),
                        ingredient.getProvideCode(),
                        ingredient.getQuantityUsed(),
                        ingredient.getDateInsert(),
                        ingredient.getNumberLot()
                );


            }
        } catch (FormatterClosedException e) {
            System.out.println("Erro ao gravar o arquivo");
            wasError = true;
        } finally {
            output.close();
            try {
                file.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar arquivo");
                wasError = true;
            }
            if (wasError) {
                System.exit(1);
            }
        }
        return relatory;
    }
}
