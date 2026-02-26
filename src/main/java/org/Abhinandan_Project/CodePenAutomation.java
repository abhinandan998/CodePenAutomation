package org.Abhinandan_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CodePenAutomation {
    public static void main(String[] args) {
        String cssCode = """
                 div {
                 background-color: blue;
                 border-radius: 100%;
                 height: 50px;
                 left: calc(50% - 50px);
                 position: absolute;
                 right: calc(50% - 50px);
                 width: 50px;
                 animation: bounce 1s ease-in-out infinite;
                 animation-fill-mode: both;
                 animation-direction: alternate;
                }
                span {
                 border-radius: 100%;
                 bottom: 32.5%;
                 left: calc(50% - 50px);
                 right: calc(50% - 50px);
                 position: absolute;
                 content: '';
                 background-color: black;
                 filter: blur(3px);
                 width: 50px;
                 height: 5px;
                 animation: shadow 1s ease-in-out infinite;
                 animation-fill-mode: both;
                 animation-direction: alternate;
                 z-index: -1;
                }
                @keyframes bounce {
                
                 from {
                 top: 25%;
                 transform: scaleX(79.5%) scaleY(65%);
                 }
                 to {
                 top: 55%;
                 transform: scale(100%);
                 }
                }
                @keyframes shadow {
                 from {
                 opacity: 0;
                 transform: scale(0);
                 }
                 to {
                 opacity: .5;
                 transform: scale(100%);
                 }
                }
                
                """;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://codepen.io/rolandixor/pen/mdwZReq");

        By codeMirrorLocator = By.cssSelector("div.CodeMirror");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

// wait until editors load
        List<WebElement> editors =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(codeMirrorLocator));

// CSS editor is the second editor
        WebElement cssEditor = editors.get(1);

// 👉 REAL editable area
        WebElement codeArea = cssEditor.findElement(By.cssSelector(".CodeMirror-code"));

        Actions actions = new Actions(driver);

// click inside editor like real user
        actions.moveToElement(codeArea).click().perform();

// select all existing text
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

// delete existing text
        actions.sendKeys(Keys.DELETE).perform();

// type new CSS code
        actions.sendKeys(cssCode).perform();



    }
}
