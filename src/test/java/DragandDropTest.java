import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class DragandDropTest {

    @BeforeAll
    static void beforeAll() {/* Всегда пишется со static. Вызывается один перед всеми тестами в этом тестовом классе*/
        Configuration.browserSize = "1920x1080";        /* Задаем разрешение браузера */
        Configuration.pageLoadStrategy = "eager";       /* Не ждем, когда загрузится полностью страница, чтобы долго не ждать*/
        Configuration.baseUrl = "https://the-internet.herokuapp.com/";
        //Configuration.holdBrowserOpen = true;         /* Не дает закрыть тесту браузер. Нужно только для отладки */
    }

    @AfterAll
    static void afterAll() {/* Всегда пишется со static. Вызывается один после всеми тестов в этом тестовом классе*/
        WebDriverRunner.closeWebDriver();
    }

    @Test
    void moveToElementTest(){
        //Откроем https://the-internet.herokuapp.com/drag_and_drop
        open("/drag_and_drop");

        //Проверим, что прямоугольник А и прямоугольник B стоят на своих местах
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        //Перенесем прямоугольник А на место В:
        actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(200, 0).release().perform(); //перенос прямоугольника А на 200 px по оси X

        //Проверим, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));

        WebDriverRunner.closeWindow();
    }

    @Test
    void DropTest(){
        open("/drag_and_drop");

        //Проверим, что прямоугольник А и прямоугольник B стоят на своих местах
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        //В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()
        $("#column-b").dragAndDrop(to("#column-a"));

        //Проверим, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));

        WebDriverRunner.closeWindow();
    }
}