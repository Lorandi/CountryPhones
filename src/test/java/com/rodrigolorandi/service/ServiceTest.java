package com.rodrigolorandi.service;

import com.rodrigolorandi.entity.Country;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {
    @Test
    void isSmallNumber_returns_true() {
        assertEquals(true, Service.isSmallNumber("123456"));
    }

    @Test
    void isSmallNumber_hasNoWhiteSpaces_true() {
        assertEquals(false, Service.isSmallNumber("123 456"));
    }

    @Test
    void isSmallNumber_startsWithZero_false() {
        assertEquals(false, Service.isSmallNumber("012345"));
    }

    @Test
    void isSmallNumber_smallerThan4_false() {
        assertEquals(false, Service.isSmallNumber("123"));
    }

    @Test
    void isSmallNumber_biggerThan6_false() {
        assertEquals(false, Service.isSmallNumber("1234567"));
    }

    @Test
    void isLargeNumber_returns_true() {
        assertEquals(true, Service.isLargeNumber("00123 45678 901 234"));
    }

    @Test
    void isLargelNumber_hasNoWhiteSpaceBetweenStartsAndDDI_false() {
        assertEquals(false, Service.isLargeNumber("00 123 45678 901 234"));
    }

    @Test
    void isLargeNumber_smallerThan9_false() {
        assertEquals(false, Service.isLargeNumber("12345678"));
    }

    @Test
    void isLargeNumber_biggerThan14_false() {
        assertEquals(false, Service.isLargeNumber("123456789012345"));
    }

    @Test
    void hasNoWhiteSpaceBetweenStartsAndDDI_true() {
        assertEquals(true, Service.hasNoWhiteSpaceBetweenStartsAndDDI("+123456789"));

    }

    @Test
    void hasNoWhiteSpaceBetweenStartsAndDDI_false() {
        assertEquals(false, Service.hasNoWhiteSpaceBetweenStartsAndDDI("+ 123456789"));

    }

    @Test
    void removeFirstCharIfNeeds_successful() {
        assertEquals("123456789", Service.removeFirstCharIfNeeds("+123456789"));
    }

    @Test
    void removeFirstCharIfNeeds_00_successful() {
        assertEquals("123456789", Service.removeFirstCharIfNeeds("00123456789"));
    }

    @Test
    void findCountryByName_whenFind() {
        List<Country> list = new ArrayList<>();
        var country = new Country("EUA", "0000", 0);
        list.add(country);
        assertEquals(country, Service.findCountryByName("EUA", list));
    }

    @Test
    void findCountryByName_when_Null() {
        List<Country> list = new ArrayList<>();
        var country = new Country("EUA", "0000", 0);
        list.add(country);
        assertEquals(null, Service.findCountryByName("Portugal", list));
    }

    @Test
    void addCountToCountry() {
        List<Country> list = new ArrayList<>();
        var country = new Country("EUA", "0000", 0);
        list.add(country);
        Service.addCountToCountry("EUA", "1234567890", list);
        assertEquals(country.getCount(), 1);
        assertEquals(country.getAdded().toString(), "[1234567890]");
    }

    @Test
    void addCountToCountry_notIncrement() {
        List<Country> list = new ArrayList<>();
        var country = new Country("EUA", "0000", 0);
        list.add(country);
        Service.addCountToCountry("CARIBE", "1234567890", list);
        assertEquals(country.getCount(), 0);
    }
}
