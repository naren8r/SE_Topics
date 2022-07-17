package Selenium_001;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.jcajce.provider.symmetric.Serpent;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

/*
  1.Selenium all topics covered in this class.
 */
public class SE_001 {

    public static WebDriver driver;

    @Test
    public static void DesiredCapabilities_BrowserOptions_LaunchingBrowser(){
        // capabilities are used to retrieve browser-name, browser-version and platform-version & type.
        // it will return key-value pairs
        // for distributed computing.
        DesiredCapabilities cap=new DesiredCapabilities();
        cap.getBrowserName();
        cap.getBrowserVersion();
        cap.getPlatformName();
        cap.setAcceptInsecureCerts(true); // for accepting insecure inputs like url etc.,

        // Options are used to get details and set browser settings.
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximize"); // for maximizing browser window.
        options.setImplicitWaitTimeout(Duration.ofSeconds(5)); // for setting implicit-wait.
        options.setHeadless(true); // for headless testing.
        options.merge(cap); // for merging capabilities and browser options.
        options.setPageLoadTimeout(Duration.ofSeconds(300)); // for set page-load timeout.

        // When initial HTML DOM document is ready but other resources still loading.
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        // wait for complete HTML DOM document loading
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // SE webDriver wait until initial DOM document loading
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        // for browser pop-ups handling.
        options.addArguments(new String[]{"disable-notifications", "disable-geolocations", "disable-media-stream"});
        // we can handle browser window pop-ups by using HashMap for chrome
        HashMap<String, Integer> content_settings=new HashMap<>();
        HashMap<String, Object> profile=new HashMap<>();
        HashMap<String, Object> preferences=new HashMap<>();
        content_settings.put("notifications", 1);// 0- ask(default), 1-accept, 2-block.
        profile.put("managed_default_content_settings", content_settings);
        preferences.put("profile", profile);
        options.setExperimentalOption("preferences", preferences);

        // for Launching browser
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver(options);

    }
    public static void Browser_Window_Properties(){

        driver.manage().window().maximize(); // for maximizing window
        driver.manage().window().minimize(); // for minimizing window
        driver.manage().window().getSize().getHeight(); // for getting window height
        driver.manage().window().getSize().getWidth(); // for getting window width
        driver.manage().window().getPosition().getX(); // for getting position on x-axis
        driver.manage().window().getPosition().getY(); // for getting position on y-axis
        driver.manage().window().setSize(new Dimension(1110, 940)); // for setting window size
        driver.manage().window().setPosition(new Point(0,0)); // for setting window position.
    }
    public static void switchTo_Frame_newWindow_newTab_alertbox(){
        driver.switchTo().frame(1); // main page to frame using index
        driver.switchTo().frame("name_ID"); //main page to frame using name or ID.
        driver.switchTo().frame("element"); // main page to frame using web-element
        driver.switchTo().defaultContent(); // for back to the main page
        driver.switchTo().parentFrame(); // from child frame to parent frame
        driver.switchTo().newWindow(WindowType.WINDOW); // from existing window to new window.
        driver.switchTo().newWindow(WindowType.TAB); // from existing tab to new tab.
        driver.switchTo().alert().accept(); // for accepting alert
        driver.switchTo().alert().dismiss(); // for close the alert
        driver.switchTo().alert().getText(); // for getting text inside the alert-box
        driver.switchTo().alert().sendKeys(" text"); // for entering text inside the alert box
    }
    public static void windowHandling(){
        String parentWindow=driver.getWindowHandle(); // retrieve parent window ID.
        Set<String> windowIDs= driver.getWindowHandles(); // retrieve set of window ID's

        // for switch to child window traverse through windowsIDs set
        Iterator it=windowIDs.iterator();
        while(it.hasNext())
        {
            String childWindow= (String) it.next();
            if(!childWindow.equals(parentWindow))
                driver.switchTo().window(childWindow);
        }
        driver.switchTo().window(parentWindow); // after completion of task in child-window return to the main window.

        // for switch to specified child window
        List<String> windowsList=new ArrayList<>(windowIDs); // converting set to list.
        for(String window:windowsList)
        {
            if(!window.equals(parentWindow))
                driver.switchTo().window(windowsList.get(1)); // specify the index of the window.
        }

    public static void dropdowns_handling(){
            // for Select tag dropdowns
        Select dropdown=new Select(element); // create instance of select class  pass WebElement as a parameter.
        dropdown.selectByVisibleText(); // select by visible text method
        dropdown.selectByIndex(); // select by index
        dropdown.selectByValue(); // select by value
        dropdown.deselectAll(); // delete all selected options
        dropdown.deselectByIndex(); // deselect by index
        dropdown.deselectByValue(); // deselect by value
        dropdown.deselectByVisibleText(); // deselect by visible text
        dropdown.getFirstSelectedOption(); // retrieve first selected option

        // list of options get from dropdown
       List<WebElement> allOptions= dropdown.getOptions(); // return list of options
        for(WebElement element:allOptions)
        {
            if(element.getText().equals("value"))
            {
                element.click();
                break;
            }
        }
        //  dropdown options sorting

        ArrayList originalList=new ArrayList();
        ArrayList tempList=new ArrayList();
        for(WebElement element :allOptions)
        {
            originalList.add(element.getText());
            tempList.add(element.getText());
        }
        Collections.sort(tempList); // for sorting list
        if(tempList.equals(originalList))
            System.out.println(" dropdown is sorted");
        else
            System.out.println(" dropdown not sorted");

        // jqery dropdown handling
        // [String...Value]return multiple elements and multiple values.
        static void JQ_dropdown(WebElement element, String...Value)
        {
            List<WebElement> choiceList=driver.findElements(By.xpath("//"));
            if(!Value[0].equalsIgnoreCase(all)) // value not contains all choices
            {
                for(WebElement choice:choiceList) // read each element in the list
                {
                    String text=ele.getText();
                    for(String val:Value) // read each value in the value array.
                    {
                        if(text.equals(val))
                        {
                            choice.click();
                            break;
                        }
                    }
                }
            }else
            {
                for(WebElement choice:choiceList)
                    choice.click();
            }
            }
        // Auto suggest dropdown
        List<WebElement> list=driver.findElements(By.xpath("// list of all choices"));
        for(WebElement element: list)
        {
            if(element.getText().contains("partial value")) // if element contains paritial value will be select
            {
                element.click();
                break;
            }
        }
        // Autocomplete places dropdown
        WebElement ele=driver.findElement(By.xpath("dropdown element"));
        ele.clear(); // clear the dropdown box
        ele.sendKeys(); // enter the text in dropdown box
        String text;
        do
        {
         ele.sendKeys(Keys.ARROW_DOWN);
         text=ele.getAttribute("value");
         if(text.equals("target value"))
         {
             ele.sendKeys(Keys.ENTER);
             break;
         }
        }while(!text.isEmpty());


        }



    }


}
