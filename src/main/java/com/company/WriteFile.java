package com.company;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class WriteFile {

    private final String path = "E:/excel test/listUser.xlsx";

    public void writeExcel (List<Employee> listUser) throws FileNotFoundException {

        RandomStrGenerator strGenerator = new RandomStrGenerator();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("User");

            for (int i = 0; i < listUser.size(); i++){


                String emails = Transliteration.generateLat(listUser.get(i).getLastname().toLowerCase().trim())
                        +  Transliteration.generateLat(listUser.get(i).getName().substring(0,1)) + "@khmnu.edu.ua";
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(listUser.get(i).getName());
                row.createCell(1).setCellValue(listUser.get(i).getLastname());
                row.createCell(2).setCellValue(emails);
                row.createCell(3).setCellValue(strGenerator.RandomGenerator());


                //Службова інформація
                row.createCell(4).setCellValue(listUser.get(i).getPhone());
                row.createCell(5).setCellValue(listUser.get(i).getEmail());
            }
        try {
            FileOutputStream file = new FileOutputStream(path);
            workbook.write(file);
            file.close();
            System.out.println("файл создан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
