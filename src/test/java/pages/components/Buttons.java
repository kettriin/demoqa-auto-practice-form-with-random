package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Buttons {
    private final SelenideElement
        submit = $("#submit");

    public void buttonClick() {
        $(submit).click();
    }

}
