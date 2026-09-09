package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import pages.TextBoxPage;
import testdata.FormTestData;
import testdata.TextBoxTestData;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    FormTestData userTest = new FormTestData();
    TextBoxPage textBoxPage = new TextBoxPage();
    TextBoxTestData textBoxUserTest = new TextBoxTestData();

    @BeforeAll
    static void setup() {
        Configuration.browserSize = System.getProperty("size", "1920x1080");
        Configuration.baseUrl = System.getProperty("base_url", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (Configuration.browser.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(List.of("--disable-dev-shm-usage", "--no-sandbox"));
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        } else if (Configuration.browser.equals("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("webSocketUrl", false);
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        } else {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(List.of("--disable-dev-shm-usage", "--no-sandbox"));
            capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
        }

        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = System.getProperty("remote_url");
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @AfterEach
    void tearsDown() {
        closeWebDriver();
    }
}
