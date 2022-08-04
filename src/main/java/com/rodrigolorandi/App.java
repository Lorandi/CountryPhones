package com.rodrigolorandi;

import com.rodrigolorandi.entity.Country;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App {


    private static List<Country> list = new ArrayList<>();
    private static List<String> allNumbers = new ArrayList<>();

    public static void main(String[] args) {
        readInputsFromFile();
        readCountryCodesFromFile();
        countNumbersFromEachCountry(allNumbers);
        showOrderedByApperance(list);
        System.out.println("acabou");
    }


    public static List<Country> readInputsFromFile() {
        String path = "src\\main\\resources\\coutryCodes.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String temp[];
            Long id = 0L;


            while ((line = br.readLine()) != null) {
                id++;
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
        } finally {
            Collections.sort(list);
            return list;
        }
    }

    public static void showList() {
        readInputsFromFile();
        for (Country country : list) {
            System.out.println(country.getCountry() + " " + country.getDdi());
        }
    }

    public static void showOrderedByApperance(List<Country> list) {
        Collections.sort(list);
        for (Country country : list) {
            if (country.getCount() > 0) {
                System.out.println(country.getCountry() + ":" + country.getCount());
            }
        }
    }


    public static void readCountryCodesFromFile() {
        String path = "src\\main\\resources\\input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String temp[];
            Long id = 0L;

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

    public static String removeFirstCharIfNeeds(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str.startsWith("+")) {
            stringBuilder = stringBuilder.deleteCharAt(0);
        }
        if (str.startsWith("00")) {
            stringBuilder = stringBuilder.deleteCharAt(0).deleteCharAt(0);
        }
        return new String(stringBuilder);
    }

    public static boolean isLargeNumber(String str) {
        str = removeFirstCharIfNeeds(str);

        str = StringUtils.trimAllWhitespace(str);

        return str.matches("\\d{9,14}");
    }

    public static boolean isSmallNumbers(String st) {
        var starsWithZero = st.startsWith("0");
        var hasSmallNumberSize = st.matches("\\d{4,6}");

        return !starsWithZero && hasSmallNumberSize;
    }

    public static Country findCountryByName(String string) {
        for (Country country : list) {
            if (country.getCountry().equals(string)) {
                return country;
            }
        }
        return null;
    }

    public static void addCountToCountry(String string, String st) {
        var country = findCountryByName(string);
        if (country != null) {
            country.setCount(country.getCount() + 1);
            var addNumberToCountryList = country.getAdded();
            addNumberToCountryList.add(st);
        }
    }

    public static void countNumbersFromEachCountry(List<String> allNumbers) {
        for (String number : allNumbers) {
            var match = false;
            if (isSmallNumbers(number)) {
                addCountToCountry("Portugal", number);
            } else if (isLargeNumber(number)) {
                number = removeFirstCharIfNeeds(number);
                for (Country country : list) {
                    var i = 0;
                    var j = i;
                    for (i = 0; i < country.getDdi().split("").length; i++) {
                        if(i !=0 && number.charAt(j) == ' '){
                            j++;
                        }
                        if (country.getDdi().charAt(i) == number.charAt(j)) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        addCountToCountry(country.getCountry(), number);
                        break;
                    }
                }
                if (!match) {
                    addCountToCountry("Invalid Numbers", number);
                }
            } else {
                addCountToCountry("Invalid Numbers", number);
            }
        }
    }
}












