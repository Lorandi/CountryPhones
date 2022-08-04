package com.rodrigolorandi.service;

import com.rodrigolorandi.entity.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service {
    private static final List<Country> list = new ArrayList<>();

    private static final List<String> allNumbers = new ArrayList<>();

    public static void readInputsFromFile() {
        String path = "src\\main\\resources\\coutryCodes.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String[] temp;

            while ((line = br.readLine()) != null) {
                temp = line.split("-");

                Country country = new Country(temp[0], temp[1], 0);

                list.add(country);
            }
            list.add(new Country("Invalid Numbers", "0000", 0));
        } catch (
                FileNotFoundException e) {
            System.out.println("arquivo não encontrado");
            e.printStackTrace();
        } catch (
                IOException e) {
            System.out.println("problema na leitura do arquivo");
            e.printStackTrace();
        }
    }

    public static void showList() {
        readInputsFromFile();
        for (Country country : list) {
            System.out.println(country.getCountry() + " " + country.getDdi());
        }
    }

    public  static void readCountryCodesFromFile() {
        String path = "src\\main\\resources\\input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                allNumbers.add(line);
            }

        } catch (
                FileNotFoundException e) {
            System.out.println("arquivo não encontrado");
            e.printStackTrace();
        } catch (
                IOException e) {
            System.out.println("problema na leitura do arquivo");
            e.printStackTrace();
        }
    }

    public  static void countNumbersFromEachCountry() {
        for (String number : allNumbers) {
            var match = false;
            if (isSmallNumber(number)) {
                addCountToCountry("Portugal", number, list);
            } else if (isLargeNumber(number)) {
                number = removeFirstCharIfNeeds(number);
                number = StringUtils.trimAllWhitespace(number);
                for (Country country : list) {
                    for (int i = 0; i < country.getDdi().split("").length; i++) {
                        if (country.getDdi().charAt(i) == number.charAt(i)) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        addCountToCountry(country.getCountry(), number, list);
                        break;
                    }
                }
                if (!match) {
                    addCountToCountry("Invalid Numbers", number, list);
                }
            } else {
                addCountToCountry("Invalid Numbers", number, list);
            }
        }
    }

    public static boolean isSmallNumber(String number) {
        var starsWithZero = number.startsWith("0");
        var hasSmallNumberSize = number.matches("\\d{4,6}");

        return !starsWithZero && hasSmallNumberSize;
    }

    public static void addCountToCountry(String country, String number, List<Country> list ) {
        var find = findCountryByName(country, list);
        if (find != null) {
            find.setCount(find.getCount() + 1);
            var addNumberToCountryList = find.getAdded();
            addNumberToCountryList.add(number);
        }
    }

    public static Country findCountryByName(String name, List<Country> list ) {
        for (Country country : list) {
            if (country.getCountry().equals(name)) {
                return country;
            }
        }
        return null;
    }

    public static  boolean isLargeNumber(String number) {
        var check = hasNoWhiteSpaceBetweenStartsAndDDI(number);
        number = removeFirstCharIfNeeds(number);
        number = StringUtils.trimAllWhitespace(number);

        return check && number.matches("\\d{9,14}");
    }

    public static boolean hasNoWhiteSpaceBetweenStartsAndDDI(String number) {
        return (!number.startsWith("+") || number.charAt(1) != ' ') &&
                (!number.startsWith("00") || number.charAt(2) != ' ');
    }

    public static String removeFirstCharIfNeeds(String number) {
        StringBuilder stringBuilder = new StringBuilder(number);
        if (number.startsWith("+")) {
            stringBuilder.deleteCharAt(0);
        }
        if (number.startsWith("00")) {
            stringBuilder.deleteCharAt(0).deleteCharAt(0);
        }
        return new String(stringBuilder);
    }

    public  static void showOrderedByApperance() {
        Collections.sort(list);
        for (Country country : list) {
            if (country.getCount() > 0) {
                System.out.println(country.getCountry() + ":" + country.getCount());
            }
        }
    }

    public static void run(){
        readInputsFromFile();
        readCountryCodesFromFile();
        countNumbersFromEachCountry();
        showOrderedByApperance();
    }
}
