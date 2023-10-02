package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void cardDelivery() {
        open("http://localhost:9999");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id='name'] input").setValue("Мария Иванова");
        $("[data-test-id='phone'] input").setValue("+79573452122");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15))
                .shouldBe(visible);

    }
}
