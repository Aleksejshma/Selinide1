package ru.netology;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Application2Test {

    private String gereteData(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void shouldBeSuccessufully() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String planData = gereteData(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planData);
        $("[data-test-id='name'] input").setValue("Петр Петрович");
        $("[data-test-id='phone'] input").setValue("+89209756316");
        $("[data-test-id='agreement'] ").click();
        $("button.button").click();
        SelenideElement selenideElement = $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planData));

    }

}

