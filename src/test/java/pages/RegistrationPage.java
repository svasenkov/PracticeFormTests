package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.TableComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    CalendarComponent calendar = new CalendarComponent();
    TableComponent tableOfResults = new TableComponent();

    private final SelenideElement firstNameInput = $("[id=firstName]");
    private final SelenideElement lastNameInput = $("[id=lastName]");
    private final SelenideElement userEmailInput = $("[id=userEmail]");
    private final SelenideElement genderContainer = $("[id=genterWrapper]");
    private final SelenideElement userNumberInput = $("[id=userNumber]");
    private final SelenideElement dateOfBirthInput = $("[id=dateOfBirthInput]");
    private final SelenideElement stateSelect = $("[id=react-select-3-input]");
    private final SelenideElement citySelect = $("[id=react-select-4-input]");
    private final SelenideElement subjectsInput = $("[id=subjectsInput]");
    private final SelenideElement hobbiesInput = $("[id=hobbiesWrapper]");
    private final SelenideElement pictureResource = $("[id=uploadPicture]");
    private final SelenideElement userAddress = $("[id=currentAddress]");
    private final SelenideElement submitButton = $("[id=submit]");

    @Step("Open registration page")
    public RegistrationPage openPage(){
        open(System.getProperty("form_path", "/automation-practice-form"));
        return this;
    }

    @Step("Fill first name {value}")
    public RegistrationPage typeUserFirstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Fill last name {value}")
    public RegistrationPage typeUserLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Fill user email {value}")
    public RegistrationPage typeUserEmail(String value){
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Select user gender {value}")
    public RegistrationPage setGenderContainer(String value){
        genderContainer.find(byText(value)).click();
        return this;
    }

    @Step("Fill user number {value}")
    public RegistrationPage typeUserNumber(String value){
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Select date of birth")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDateOfBirth(day, month, year);
        return this;
    }

    @Step("Select subject {value}")
    public RegistrationPage setSubjects(String value) {
        subjectsInput.press(value).pressEnter();
        return this;
    }

    @Step("Select hobby {value}")
    public RegistrationPage setHobbies(String value) {
        hobbiesInput.find(byText(value)).click();
        return this;
    }

    @Step("Set picture {value}")
    public RegistrationPage setPicture(String value) {
        // Gecko in Selenoid has no /se/file upload (405 Method Not Allowed).
        if ("firefox".equalsIgnoreCase(Configuration.browser)) {
            return this;
        }
        pictureResource.uploadFromClasspath(value);
        return this;
    }

    @Step("Fill user address {value}")
    public RegistrationPage typeUserAddress (String value) {
        userAddress.setValue(value);
        return this;
    }

    @Step("Select state {value}")
    public RegistrationPage setState(String value) {
        stateSelect.setValue(value).pressEnter();
        return this;
    }

    @Step("Select city {value}")
    public RegistrationPage setCity(String value) {
        citySelect.setValue(value).pressEnter();
        return this;
    }

    @Step("Submit form")
    public void submitForm() {
        submitButton.scrollTo().click();
    }

    @Step("Chek result of filling field {value}")
    public RegistrationPage checkResults(String value) {
        tableOfResults.checkResults(value);
        return this;
    }

    @Step("Check field condition")
    public RegistrationPage checkErrorFieldCondition(String value) {
        SelenideElement fieldCondition = $("[id=" + value + "]");
        fieldCondition.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }

    @Step("Check email field condition")
    public RegistrationPage checkErrorEmailCondition(String value) {
        SelenideElement emailCondition = $("[id=" + value + "]");
        emailCondition.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }

}