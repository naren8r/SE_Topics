import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;

public class file_dowload {
    static WebDriver driver;

    public static void main(String args[]){
        String location=System.getProperty("user.dir")+"\\Downloads\\";
        HashMap preferences=new HashMap();
        preferences.put("download.default_directory", location);
        ChromeOptions options=new ChromeOptions();
        options.setExperimentalOption("pref", preferences);

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver(options);

        FirefoxProfile profile=new FirefoxProfile();
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword");
        profile.setPreference("browser.dowload.folderList", 2);
        profile.setPreference("browser.dowload.dir", location);

        FirefoxOptions foptions= new FirefoxOptions();
        foptions.setProfile(profile);

        WebDriverManager.firefoxdriver().setup();
        driver=new FirefoxDriver(foptions)

        driver.get("https://file-examples.com/index.php/sample-documents-download/sample-doc-download/");
        driver.findElement(By.xpath("(//a[contains(@href, 'file-sample_100kB.doc')])[1]")).click();
    }



}
