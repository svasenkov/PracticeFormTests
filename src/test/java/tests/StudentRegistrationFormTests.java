package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Feature("Registration form tests")
public class StudentRegistrationFormTests extends TestBase {

    @Test
    @Story("E2E test")
    @Owner("denor1999")
    void endToEndPositiveTest(){

        step("Open registration form", () -> {
            registrationPage.openPage();
        });

        step("Filling registration form", () -> {
            registrationPage.typeUserFirstName(userTest.firstName)
                    .typeUserLastName(userTest.lastName)
                    .typeUserEmail(userTest.correctUserEmail)
                    .setGenderContainer(userTest.gender)
                    .typeUserNumber(userTest.userNumber)
                    .setDateOfBirth(userTest.day, userTest.month, userTest.year)
                    .setSubjects(userTest.selectedSubject)
                    .setHobbies(userTest.selectedHobby)
                    .setPicture(userTest.picture)
                    .typeUserAddress(userTest.currentAddress)
                    .setState(userTest.setRandomState())
                    .setCity(userTest.setRandomCity());
        });

        step("Submit form", () -> {
            registrationPage.submitForm();
        });

        step("Check registration form results", () -> {
            registrationPage.checkResults(userTest.firstName + " " + userTest.lastName)
                    .checkResults(userTest.correctUserEmail)
                    .checkResults(userTest.gender)
                    .checkResults(userTest.userNumber)
                    .checkResults(userTest.day + " " + userTest.month + "," + userTest.year)
                    .checkResults(userTest.selectedSubject)
                    .checkResults(userTest.selectedHobby);
            if (!"firefox".equalsIgnoreCase(Configuration.browser)) {
                registrationPage.checkResults(userTest.picture);
            }
            registrationPage.checkResults(userTest.currentAddress)
                    .checkResults(userTest.state + " " + userTest.city);
        });
    }

}
