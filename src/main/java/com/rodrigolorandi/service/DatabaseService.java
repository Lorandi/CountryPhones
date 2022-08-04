//package com.rodrigolorandi.service;
//
//import com.rodrigolorandi.entity.CountryNumber;
//import com.rodrigolorandi.entity.Database;
//import com.rodrigolorandi.repository.DatabaseRepository;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.HashMap;
//
//@RequiredArgsConstructor
//@Service
//@Slf4j
//public class DatabaseService {
//    private final DatabaseRepository repository;
//
//    private static HashMap<Long, CountryNumber> list = new HashMap<Long, CountryNumber>();
//
//    public void insertCode(CountryNumber countryNumber) {
//        list.put(countryNumber.getId(), countryNumber);
//    }
//
//    public HashMap<Long, CountryNumber> showAllCodes(CountryNumber countryNumber) {
//        return list;
//    }
//
//    public HashMap<Long, CountryNumber> readFromFile() {
//        String path = "src/main/resources/countryCodes.csv";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String line = br.readLine();
//            String temp[];
//
//            while ((line = br.readLine()) != null) {
//                temp = line.split("-");
//
//                CountryNumber countryNumber = new CountryNumber(temp[0], temp[1]);
//
//                list.put(countryNumber.getId(), countryNumber);
//            }
//        } catch (
//                FileNotFoundException e) {
//            System.out.println("arquivo não encontrado");
//            e.printStackTrace();
//        } catch (
//                IOException e) {
//            System.out.println("problema na leitura do arquivo");
//            e.printStackTrace();
//        } finally {
//            return list;
//        }
//    }
//
//    public void showStockList() {
//        System.out.println("\n Lista de produtos");
//        for (CountryNumber countryNumber : list.values()) {
//            System.out.println(countryNumber.toString());
//        }
//    }
//}
