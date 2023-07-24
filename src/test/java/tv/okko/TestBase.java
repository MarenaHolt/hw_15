package tv.okko;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import tv.okko.helpers.Attach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

        @BeforeAll
        static void beforeAll() {
            //  Configuration.baseUrl = "https://demoqa.com";
            //  Configuration.browserSize = "1920x1080";
            //Configuration.browser = "chrome";
            // Configuration.holdBrowserOpen = true;
            Configuration.pageLoadStrategy = "eager";
            //  Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

            Configuration.remote = System.getProperty("selenoid", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
            Configuration.baseUrl = System.getProperty("baseUrl","https://okko.tv/");
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
            Configuration.browserSize = System.getProperty("browserSize","1920x1080");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));

            Configuration.browserCapabilities = capabilities;
        }

        @BeforeEach
        void addListener() {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        }

        @AfterEach
        void addAttachments() {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();

            closeWebDriver();
        }
}
