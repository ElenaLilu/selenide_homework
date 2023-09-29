package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[name='city']").setValue("Москва");
        $("[name='date']").setValue("04.10.2023");
        $("[name='name']").setValue("Мария Иванова");
        $("[name='phone']").setValue("+79573452122");
        $("[name='agreement']").click();
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldBe(visible, Duration.ofSeconds(10));

    }
}
