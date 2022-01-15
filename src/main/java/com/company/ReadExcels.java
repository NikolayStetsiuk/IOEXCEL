package com.company;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ReadExcels {

    private  final String path = "E:/excel test/users.xlsx";

   ArrayList<Employee> employeeList = new ArrayList<Employee>();
    public  Map<String,String> listUser = new HashMap<String, String>();
    public int countWord(String input) {

        int count = 0;
        String str = new String(input);
        input = str.replaceAll("[\\p{Zs}\\s]+", " ");
        String[] str0 = input.split(" ");
        count=str0.length;
        return count;
    }

    public String[] splitString(String input) {

        String str = new String(input);
        input = str.replaceAll("[\\p{Zs}\\s]+", " ");

        String[] str0 = input.split(" ");
         int  count=str0.length;
        return str0;
    }

    public List<Employee> readFile() {


        String name = "";
        String lastName = "";

        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            DataFormatter formatter = new DataFormatter();
            XSSFSheet sheet = workbook.getSheetAt(0);
            int countofRow = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < countofRow; i++) {

                String record = sheet.getRow(i).getCell(1).getStringCellValue();

                String phone  = formatter.formatCellValue(sheet.getRow(i).getCell(2));

                String email = sheet.getRow(i).getCell(3).getStringCellValue();

                Employee employee = new Employee(lastName,name,phone,email);

                if (countWord(record) == 1) {
                    employeeList.add(employee);
                    lastName = record;
                    name = splitString(sheet.getRow(i+1).getCell(1).getStringCellValue())[0];
                    employee.setLastname(lastName);
                    employee.setName(name);

                } else if (countWord(record) >= 3){

                    employeeList.add(employee);
                    String[]str = splitString(record);
                    employee.setLastname(str[0]);
                    employee.setName(str[1]);
                }
            }
            file.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return employeeList;
    }




    // НА щось ше згодиться, може, колись, я хз.
    
          /*  Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
                        case NUMERIC:
                            System.out.printf("%.0f", cell.getNumericCellValue());
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                    }
                    System.out.println();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

}



