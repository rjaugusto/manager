package br.com.smartfinances.manager.tools;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVFileToTransaction {

    public static void main(String[] args) throws IOException {

        String path = "c:\\investimentos.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            var linhas = br.lines().collect(Collectors.toList());

            for (String linha:linhas) {
                String[] split = linha.split(",");
                System.out.println(split[0]);
                System.out.println(split[1]);
                System.out.println(split[2]);
                System.out.println((split[3]+","+split[4]).replace(" ","").replace("\"",""));
                System.out.println(split[5]);
                System.out.println(split[6]);
            }

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
