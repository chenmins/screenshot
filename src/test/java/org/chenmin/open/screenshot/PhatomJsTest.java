package org.chenmin.open.screenshot;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Test with PhantomJS.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class PhatomJsTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.phantomjs().useMirror().setup();
    }

    @Before
    public void setupTest() {
        driver = new PhantomJSDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws IOException {
        // Your test code here. For example:
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //file://D:/1/birt/output.html
        driver.get("file:///D:/1/birt/output.html");
//        driver.get("https://en.wikipedia.org/wiki/Main_Page");
//
//        By searchInput = By.id("searchInput");
//        wait.until(presenceOfElementLocated(searchInput));
//        driver.findElement(searchInput).sendKeys("Software");
//        By searchButton = By.id("searchButton");
//        wait.until(elementToBeClickable(searchButton));
//        driver.findElement(searchButton).click();
//
//        wait.until(textToBePresentInElementLocated(By.tagName("body"),
//                "Computer software"));

        /**
         * 截屏操作
         * 图片已当前时间命名
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  //转换时间格式
        String time = dateFormat.format(Calendar.getInstance().getTime());  //获取当前时间
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  //执行屏幕截取
        FileUtils.copyFile(srcFile, new File("screen", time + ".png")); //利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件;"屏幕截图"即时保存截图的文件夹

    }

}