package com.severstal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.partialLinkText;

public class ParameterizedTestForGithub {

    static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of("JUnit5", "#2813"),
                Arguments.of("Allure", "#1558")
        );
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1280";
        Configuration.headless = true;
    }

    @BeforeEach
    void beforeEach() {
        open("https://github.com/");
    }

    @AfterEach
    void AfterEach() {
       closeWebDriver();
    }

    @ValueSource(strings = {"JUnit5", "Allure"})
    @ParameterizedTest(name = "Поиск в Github по ключевому слову {0}")
    public void commonSearchTest(String textSearch) {
        $("[data-test-selector='nav-search-input']").setValue(textSearch).pressEnter();
        $(".repo-list").shouldHave(Condition.text(textSearch));
    }

    @CsvSource({
            "JUnit5, #2813",
            "Allure, #1558"
    })
    @ParameterizedTest(name = "Поиск в Github по ключевому слову {0} и по искомому issue {1}")
    public void commonSearchTest(String textSearch, String issues) {
        $("[data-test-selector='nav-search-input']").setValue(textSearch).pressEnter();
        $(".repo-list").shouldHave(Condition.text(textSearch));
        $("ul.repo-list li").$("a").click();
        $(partialLinkText("Issues")).click();
        $(withText(issues)).should(Condition.visible);
    }

    @MethodSource("methodSource")
    @ParameterizedTest(name = "Поиск в Github по ключевым параметрам {0} - {1}")
    public void commonSearchTest2(String textSearch, String issues) {
        $("[data-test-selector='nav-search-input']").setValue(textSearch).pressEnter();
        $(".repo-list").shouldHave(Condition.text(textSearch));
        $("ul.repo-list li").$("a").click();
        $(partialLinkText("Issues")).click();
        $(withText(issues)).should(Condition.visible);
    }
}