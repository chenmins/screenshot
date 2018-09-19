package org.chenmin.open.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
@Controller
public class PngController {
    //生成验证码图片
    @RequestMapping("/valicode") //对应/user/valicode.do请求
    public void valicode(HttpServletResponse response, HttpSession session) throws Exception{
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageUtil.createImage();
        //将验证码存入Session
        session.setAttribute("imageCode",objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);

    }

    WebDriver driver = new PhantomJSDriver();

    @RequestMapping("/png")
    public void png(HttpServletResponse response, HttpSession session, @RequestParam("url") String url) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get(url);
        byte[] df = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        OutputStream os = response.getOutputStream();
        os.write(df);
        os.flush();
        os.close();
    }

}
