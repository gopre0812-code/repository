package com.lamdatest;
import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.*;


public class LamdaTestAssignment {
	 Playwright playwright; 

	    Browser browser; 

	    Page page; 

	    

	    @BeforeClass 

	    public void setup() { 

	        playwright = Playwright.create(); 

	        // Launch a Chromium browser in non-headless mode (visible window) 

	        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true)); 

	        page = browser.newPage(); 

	    } 

	    

	    @Test(priority=1) 

	    public void testScenario1_Simpleform() { 

	        page.navigate("https://www.lambdatest.com/selenium-playground"); 

	        page.click("text=simple-form-demo"); 

	        assertThat(page).hasURL(java.util.regex.Pattern.compile(".*simple-form-demo.*")); 

	        String message="Welcome to LamdatTest"; 

	        page.fill("input#user-message", message); 

	        page.click("button#showinput"); 

	        assertThat(page.locator("id=message")).hasText(message); 

	    } 

	    

	    @Test(priority=2) 

	    public void testScenario2_DragDropslide() { 

	        page.navigate("https://www.lambdatest.com/selenium-playground"); 

	        page.click("text=Drag & Drop Sliders"); 

	        Locator slider=page.locator("input[value='15']"); 

	        // The fill method on a slider/input element generally expects a string value for the "value" attribute

	        slider.fill("95"); 

	        assertThat(page.locator("#rangeSucess")).hasText("95"); 

	    } 

	    

	    @Test(priority=3) 

	    public void testScenario3_InputFormSubmit() { 

	        page.navigate("https://www.lambdatest.com/selenium-playground"); 

	        page.click("text=Input Form Submit"); 

	        // A direct submit without filling required fields might show validation errors first.

	        // It's better to fill the required fields before submitting to test the success message.

	        page.fill("input[name='name']","John Mart"); // Corrected name for consistency

	        page.fill("input#inputEmail4","john@gmail.com"); // Corrected email for consistency

	        page.fill("input[name='phone']", "1234567890");

	        page.fill("input[name='address']", "123 Main St");

	        page.fill("input[name='city']", "Anytown");

	        page.selectOption("select[name='state']", "California"); // Select a valid state

	        page.fill("input[name='zip']", "12345");

	        page.click("button:has-text('Submit')"); 

	        

	        // Assert the success message

	        assertThat(page.locator(".success-msg")).hasText("Thanks for contacting us, we will get back to you shortly."); 

	    } 

	    

	    @AfterClass 

	    public void tearDown() { 

	        browser.close(); 

	        playwright.close(); 

	    } 
}
