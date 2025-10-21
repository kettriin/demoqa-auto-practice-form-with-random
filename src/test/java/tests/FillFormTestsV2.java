package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.LkForm;
import pages.components.Buttons;
import pages.components.Modals;
import utils.RandomUtils;

public class FillFormTestsV2 {

    LkForm lkForm = new LkForm();
    Buttons submitButton = new Buttons();
    Modals dataModal = new Modals();
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String userTelephoneNumber = RandomUtils.getRandomPhoneNumber();
    String userGender = RandomUtils.getRandomGender();
    String userHobbie = RandomUtils.getRandomHobby();
    String streetAddress = faker.address().streetAddress();
    String userImage = "png.png";
    String userDayOfBirth = RandomUtils.getRandomDay();
    String userMonthOfBirth = RandomUtils.getRandomMonth();
    String userYearOfBirth = RandomUtils.getRandomYear();
    String userSubject = RandomUtils.getRandomSubject();
    String userAddressState = RandomUtils.getRandomState();
    String userAddressCity = RandomUtils.getCityByState(userAddressState);


    @BeforeAll
    static void beforeFormFillingTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void positiveFormFilling() {
        lkForm.goToPage()
                .removeAddBannersFromFooter()
                .nameSurnameFilling(firstName, lastName)
                .emailFilling(userEmail)
                .genderSetting(userGender)
                .phoneNumberFilling(userTelephoneNumber)
                .birthDateSetting(userDayOfBirth, userMonthOfBirth, userYearOfBirth)
                .subjectInput(userSubject)
                .hobbiesInpit(userHobbie)
                .uploadPicture(userImage)
                .locationDropdowns(userAddressState, userAddressCity)
                .currentAddressInput(streetAddress);

        submitButton.buttonClick();

        // проверяем появилась ли модалка с введенными данными
        dataModal.checkModalVisible("Thanks for submitting the form");
        // через проверку содержимого нужных нам ячеек в таблице
        dataModal.checkSavedData("Student Name", firstName + " " + lastName)
                .checkSavedData("Student Email", userEmail)
                .checkSavedData("Gender", userGender)
                .checkSavedData("Mobile", userTelephoneNumber)
                .checkSavedData("Date of Birth", userDayOfBirth + " " + userMonthOfBirth + "," + userYearOfBirth)
                .checkSavedData("Subjects", userSubject)
                .checkSavedData("Hobbies", userHobbie)
                .checkSavedData("Picture", userImage)
                .checkSavedData("Address", streetAddress)
                .checkSavedData("State and City", userAddressState + " " + userAddressCity);
    }

    @Test
    void minimalDataFormFilling() {
        lkForm.goToPage()
                .removeAddBannersFromFooter()
                .nameSurnameFilling(firstName, lastName)
                .genderSetting(userGender)
                .phoneNumberFilling(userTelephoneNumber);

        submitButton.buttonClick();

        dataModal.checkModalVisible("Thanks for submitting the form");

        dataModal.checkSavedData("Student Name", firstName + " " + lastName)
                .checkSavedData("Gender", userGender)
                .checkSavedData("Mobile", userTelephoneNumber);
    }

    @Test
    void invalidEmailAddress() {
        lkForm.goToPage()
                .removeAddBannersFromFooter()
                .nameSurnameFilling(firstName, lastName)
                .emailFilling(firstName + " " + lastName) // невалидный формат ввода почты
                .genderSetting(userGender)
                .phoneNumberFilling(userTelephoneNumber);

        submitButton.buttonClick();

        // проверяем что модалка с сохраненными данными не появилась
        dataModal.modalNotVisible();
        // проверяем что поле покраснело из-за ошибки введенных данных
        lkForm.invalidEmailAddress();
    }

}
