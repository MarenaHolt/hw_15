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
            Configuration.baseUrl = System.getProperty("baseUrl", "https://okko.tv/");
            Configuration.pageLoadStrategy = "eager";
            Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.remote = System.getProperty("selenoid");
            Configuration.browserVersion = System.getProperty("browserVersion","100.0");

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
