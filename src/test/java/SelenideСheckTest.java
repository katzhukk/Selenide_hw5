import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideСheckTest {

    @BeforeAll
    static void beforeAll() {/* Всегда пишется со static. Вызывается один перед всеми тестами в этом тестовом классе*/
        Configuration.browserSize = "1920x1080";        /* Задаем разрешение браузера */
        Configuration.pageLoadStrategy = "eager";       /* Не ждем, когда загрузится полностью страница, чтобы долго не ждать*/
        //Configuration.holdBrowserOpen = true;         /* Не дает закрыть тесту браузер. Нужно только для отладки */
    }

    @AfterAll
    static void afterAll() {/* Всегда пишется со static. Вызывается один после всеми тестов в этом тестовом классе*/
        WebDriverRunner.closeWebDriver();
    }

    @Test
    void fillFormTest() {
        //На главной странице GitHub выберите: Меню -> Solutions -> Enterprise (с помощью команды hover для Solutions).
        open("https://github.com/");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover(); // подвигает курсор к Solutions
        $("[href='https://github.com/enterprise']").click();

        //Убедиться, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.").
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered\ndeveloper platform."));
    }
}