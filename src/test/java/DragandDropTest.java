import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class DragandDropTest {

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
    void moveToElementTest() throws InterruptedException {
        //(опциональное) Запрограммируйте Drag&Drop с помощью Selenide.actions()
        //Откройте https://the-internet.herokuapp.com/drag_and_drop
        open("https://the-internet.herokuapp.com/drag_and_drop");

        //Перенесите прямоугольник А на место В:
        // * Поднести курсор к прямоугольнику А   // moveToElement($("#column-a"))
        // * Кликнуть на прямоугольник А и зажать // clickAndHold()
        // * Перенести прямоугольник А на место В // moveByOffset(x, y)
        actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(200, 0).release().perform(); //перенос прямоугольника А на 200 px по оси X

        //Проверьте, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));

        //подождем 2 секунды
        Thread.sleep(2000);

        //В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()
        $("#column-b").dragAndDrop(to("#column-a"));

        //Проверьте, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("A"));
    }
}
