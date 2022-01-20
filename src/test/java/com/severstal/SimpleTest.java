package com.severstal;

import org.junit.jupiter.api.*;


@DisplayName("Класс с тестами проверяющими 3 и 2")
public class SimpleTest {

    @BeforeAll
    static void BeforeAll() {
        System.out.println("- BeforeAll");
    }

    @AfterAll
    static void AfterAll() {
        System.out.println("- AfterAll");
    }

    @BeforeEach
    void BeforeEach() {
        System.out.println("- BeforeEach");
    }

    @AfterEach
    void AfterEach() {
        System.out.println("- AfterEach");
    }

    @Test
    @DisplayName("Тест проверяет, что 3 < 2")
    void test() {
        Assertions.assertTrue(3 < 2);
    }

    @Test
    @DisplayName("Тест проверяет, что 3 > 2")
    void test1() {
        Assertions.assertTrue(3 > 2);
    }
}