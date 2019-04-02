/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import javafx.application.Application;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 *
 * @author RJulin
 */
public class loginViewTest extends ApplicationTest {
    private Stage stage;
    
    public loginViewTest() {
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        loginView instance = new loginView();
        Application app = Application.class.cast(instance);
        app.start(stage);
        
        this.stage = stage;
    }



    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of start method, of class loginView.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Stage primaryStage = null;
        loginView instance = new loginView();
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.

    }


    @Test
    public void login() {
        clickOn("#user").write("Admin");
        //click("user").type("Admin");   GUITEST
        clickOn("#password").write("salis");
        //click("password").type("salis");   GUITEST
        clickOn("#login");

        Stage Viewclass = null;
        View instance = new View();
        instance.start(Viewclass);
        // new View().start(Viewclass);               
    }

    @Test
    public void fail() {

        clickOn("#login");

        verifyThat("#fail", hasText("Salasana väärin."));

    }

}
