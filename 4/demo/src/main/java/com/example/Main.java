package com.example;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Division;
import com.example.models.Employee;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Main {
    public static void main(String[] args) {
        String csvPath = "foreign_names.csv";
        char delimiter = ';';

        List<Employee> employees = parseEmployeesFromCSV(csvPath, delimiter);

        employees.forEach(System.out::println);
    }

    public static List<Employee> parseEmployeesFromCSV(String fileName, char delimiter) {
        List<Employee> result = new ArrayList<>();
        Map<String, Division> divisions = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try (
                InputStream input = Main.class.getClassLoader().getResourceAsStream(fileName);
                CSVReader csvReader = input == null ? null :
                        new CSVReaderBuilder(new InputStreamReader(input, StandardCharsets.UTF_8))
                                .withCSVParser(new CSVParserBuilder().withSeparator(delimiter).build())
                                .build()
        ) {
            if (csvReader == null) throw new RuntimeException("Не удалось найти файл: " + fileName);

            String[] row;
            boolean skipHeader = true;

            while ((row = csvReader.readNext()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                int id = Integer.parseInt(row[0]);
                String fullName = row[1];
                Gender sex = Gender.getGender(row[2]);
                Date birth = dateFormat.parse(row[3]);
                String divisionTitle = row[4];
                double income = Double.parseDouble(row[5]);

                Division division = divisions.computeIfAbsent(divisionTitle, Division::new);

                Employee employee = new Employee(id, fullName, sex, division, income, birth);
                result.add(employee);
            }

        } catch (Exception e) {
            System.err.println("Ошибка чтения CSV: " + e.getMessage());
        }

        return result;
    }
}
