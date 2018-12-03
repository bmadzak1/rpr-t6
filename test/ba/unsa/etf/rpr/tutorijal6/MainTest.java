package ba.unsa.etf.rpr.tutorijal6;

import javafx.scene.control.Label;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class MainTest {

    @Start
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("formular.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void tacanUnosTest (FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#imeField");
        robot.write("Berin");
        robot.clickOn("#prezimeField");
        robot.write("Madzak");
        robot.clickOn("#indeksField");
        robot.write("17897");
        robot.clickOn("#jmbgField");
        robot.write("0209997170055");
        robot.clickOn("#mjestoField");
        robot.write("Sarajevo");
        robot.clickOn("#emailField");
        robot.write("bmadzak1@etf.unsa.ba");
        robot.clickOn("#odsjekField");
        robot.clickOn("RI");
        robot.clickOn("#godinaField");
        robot.clickOn("Druga");
        robot.clickOn("#ciklusField");
        robot.clickOn("Bachelor");
        robot.clickOn("#statusField");
        robot.clickOn("Redovan samofinansirajuci");
        assertTrue(formularController.isImeValidno());
        assertTrue(formularController.isPrezimeValidno());
        assertTrue(formularController.isIndeksValidan());
        assertTrue(formularController.isJmbgValidan());
        assertTrue(formularController.isEmailValidan());
        assertTrue(!formularController.mjestoField.getSelectionModel().isEmpty());
        assertTrue(!formularController.odsjekField.getSelectionModel().isEmpty());
        assertTrue(!formularController.godinaField.getSelectionModel().isEmpty());
        assertTrue(!formularController.ciklusField.getSelectionModel().isEmpty());
        assertTrue(!formularController.statusField.getSelectionModel().isEmpty());
    }

    @Test
    public void pogresnoImeTest (FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#imeField");
        robot.write("Berin3");
        assertFalse(formularController.isImeValidno());
    }

    @Test
    public void pogresnoPrezimeTest (FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#prezimeField");
        robot.write("192");
        assertFalse(formularController.isPrezimeValidno());
    }

    @Test
    public void pogresanIndexTest (FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#indeksField");
        robot.write("indeks");
        assertFalse(formularController.isIndeksValidan());
    }

    @Test
    public void pogresanJmbgTest (FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#jmbgField");
        robot.write("1234567890");
        assertFalse(formularController.isJmbgValidan());
    }

    @Test
    public void godinaNijeIzabranaTest(FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#godinaField");
        assertTrue(formularController.godinaField.getSelectionModel().isEmpty());
    }

    @Test
    public void ciklusNijeIzabranTest(FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#ciklusField");
        assertTrue(formularController.ciklusField.getSelectionModel().isEmpty());
    }

    @Test
    public void odsjekNijeIzabranTest(FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#odsjekField");
        assertTrue(formularController.odsjekField.getSelectionModel().isEmpty());
    }

    @Test
    public void statusNijeIzabranaTest(FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#statusField");
        assertTrue(formularController.statusField.getSelectionModel().isEmpty());
    }

    @Test
    public void mjestoNijeIzabranoTest(FxRobot robot) {
        FormularController formularController = FormularController.instance;
        robot.clickOn("#mjestoField");
        assertTrue(formularController.mjestoField.getSelectionModel().isEmpty());
    }


}