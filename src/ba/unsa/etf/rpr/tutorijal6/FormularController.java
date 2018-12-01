package ba.unsa.etf.rpr.tutorijal6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.apache.commons.validator.routines.EmailValidator;

import java.awt.*;
import java.time.LocalDate;
import java.util.function.Predicate;

public class FormularController {

    public TextField imeField;
    public TextField emailField;
    public TextField prezimeField;
    public TextField jmbgField;
    public TextField indeksField;
    public ComboBox mjestoField;
    public TextField telefonField;
    public ChoiceBox odsjekField;
    public ChoiceBox godinaField;
    public ChoiceBox ciklusField;
    public ChoiceBox statusField;
    public DatePicker datumField;
    public CheckBox borackaField;
    public TextField adresaField;

    private boolean imeValidno;
    private boolean emailValidan;
    private boolean prezimeValidno;
    private boolean jmbgValidan;
    private boolean telefonValidan;
    private boolean datumValidan;
    private boolean indeksValidan;

    String jmbgValue;

    private boolean validnoIme(String n) {
        if(n.length() < 1 || n.length() > 20)
            return false;
        for(int i = 0; i < n.length(); i++)
            if(n.toUpperCase().charAt(i) < 65 || n.toUpperCase().charAt(i) > 90)
                return false;
        return true;
    }

    private boolean validanIndeks(String n){
        if(n.length() != 5)
            return false;
        for(int i = 0; i < n.length(); i++)
            if(n.charAt(i) < 48 || n.charAt(i) > 57)
                return false;
        return true;
    }

    private boolean validanTelefon(String n){
        if(n.length() < 6)
            return false;
        for(int i = 0; i < n.length(); i++)
            if(n.charAt(i) < 48 || n.charAt(i) > 57)
                return false;
        return true;
    }

    private boolean validanDatum(LocalDate l){
        if(l.isAfter(LocalDate.now()))
            return false;
        if(isJmbgValidan() && l.getDayOfMonth() != (charToInt(jmbgValue.charAt(0)) *10 + charToInt(jmbgValue.charAt(1))))
            return false;
        if(isJmbgValidan() && l.getMonthValue() != (charToInt(jmbgValue.charAt(2)) *10 + charToInt(jmbgValue.charAt(3))))
            return false;
        if(isJmbgValidan() && l.getYear()%1000 != (charToInt(jmbgValue.charAt(4)) *100 + charToInt(jmbgValue.charAt(5)) * 10 + charToInt(jmbgValue.charAt(6))))
            return false;
        return true;
    }

    private boolean validanJmbg(String n){
        //0209997170055
        if(n.length() != 13)
            return false;
        for(int i = 0; i < 13; i++)
            if(n.charAt(i) < 48 || n.charAt(i) > 57)
                System.out.println(n.charAt(i));
        if(charToInt(n.charAt(0)) > 3 || (charToInt(n.charAt(0)) == 3 && charToInt(n.charAt(1)) > 1) || (charToInt(n.charAt(0)) == 0 && charToInt(n.charAt(1)) == 0))
            return false;
        if(charToInt(n.charAt(2)) > 1 || (charToInt(n.charAt(2)) == 1 && charToInt(n.charAt(3)) > 2) || (charToInt(n.charAt(2)) == 0 && charToInt(n.charAt(3)) == 0))
            return false;
        if(charToInt(n.charAt(7)) == 9 && charToInt(n.charAt(8)) > 6)
            return false;
        if(charToInt(n.charAt(12)) != izracunajKontrolnu(n))
            return false;
        return true;
    }

    int izracunajKontrolnu(String n){
        return 11 - ((7*(charToInt(n.charAt(0)) + charToInt(n.charAt(6))) + 6*(charToInt(n.charAt(1)) + charToInt(n.charAt(7))) + 5*(charToInt(n.charAt(2)) + charToInt(n.charAt(8)))
        + 4*(charToInt(n.charAt(3)) + charToInt(n.charAt(9))) + 3*(charToInt(n.charAt(4)) + charToInt(n.charAt(10))) + 2*(charToInt(n.charAt(5)) + charToInt(n.charAt(11)))) % 11);
    }

    int charToInt(char c){
        return c-48;
    }

    public boolean isImeValidno() {
        return imeValidno;
    }
    public boolean isPrezimeValidno() {
        return prezimeValidno;
    }
    public boolean isJmbgValidan(){return jmbgValidan;}
    public boolean isTelefonValidan(){return telefonValidan;}
    public boolean isDatumValidan(){return datumValidan;}
    public boolean isIndeksValidan(){return indeksValidan;}

    @FXML
    public void initialize() {
        imeValidno = false;
        imeField.getStyleClass().add("poljeNijeIspravno");

        imeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoIme(n)) {
                    imeField.getStyleClass().removeAll("poljeNijeIspravno");
                    imeField.getStyleClass().add("poljeIspravno");
                    imeValidno = true;
                } else {
                    imeField.getStyleClass().removeAll("poljeIspravno");
                    imeField.getStyleClass().add("poljeNijeIspravno");
                    imeValidno = false;
                }
            }
        });

        prezimeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoIme(n)) {
                    prezimeField.getStyleClass().removeAll("poljeNijeIspravno");
                    prezimeField.getStyleClass().add("poljeIspravno");
                    prezimeValidno = true;
                } else {
                    prezimeField.getStyleClass().removeAll("poljeIspravno");
                    prezimeField.getStyleClass().add("poljeNijeIspravno");
                    prezimeValidno = false;
                }
            }
        });

        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanJmbg(n)) {
                    jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
                    jmbgField.getStyleClass().add("poljeIspravno");
                    jmbgValidan = true;
                    jmbgValue = n;
                } else {
                    jmbgField.getStyleClass().removeAll("poljeIspravno");
                    jmbgField.getStyleClass().add("poljeNijeIspravno");
                    jmbgValidan = false;
                }
            }
        });

        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                EmailValidator validator = EmailValidator.getInstance();
                if (validator.isValid(newValue)) {
                    emailField.getStyleClass().removeAll("poljeNijeIspravno");
                    emailField.getStyleClass().add("poljeIspravno");
                    emailValidan = true;
                } else {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                    emailValidan = false;
                }
            }
        });

        indeksField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanIndeks(n)) {
                    indeksField.getStyleClass().removeAll("poljeNijeIspravno");
                    indeksField.getStyleClass().add("poljeIspravno");
                    indeksValidan = true;
                } else {
                    indeksField.getStyleClass().removeAll("poljeIspravno");
                    indeksField.getStyleClass().add("poljeNijeIspravno");
                    indeksValidan = false;
                }
            }
        });

        telefonField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanTelefon(n)) {
                    telefonField.getStyleClass().removeAll("poljeNijeIspravno");
                    telefonField.getStyleClass().add("poljeIspravno");
                    telefonValidan = true;
                } else {
                    telefonField.getStyleClass().removeAll("poljeIspravno");
                    telefonField.getStyleClass().add("poljeNijeIspravno");
                    telefonValidan = false;
                }
            }
        });

        datumField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if(validanDatum(newValue)){
                    datumField.getStyleClass().removeAll("poljeNijeIspravno");
                    datumField.getStyleClass().add("poljeIspravno");
                    datumValidan = true;
                }else {
                    datumField.getStyleClass().removeAll("poljeIspravno");
                    datumField.getStyleClass().add("poljeNijeIspravno");
                    datumValidan = false;
                }
            }
        });

    }

    public void potvrdiBtn(ActionEvent actionEvent) {
        if(isImeValidno() && isPrezimeValidno() && isJmbgValidan() && isDatumValidan() && isIndeksValidan() && isTelefonValidan()
        && !odsjekField.getSelectionModel().isEmpty() && !godinaField.getSelectionModel().isEmpty()
        && !ciklusField.getSelectionModel().isEmpty() && !statusField.getSelectionModel().isEmpty()
        && !mjestoField.getSelectionModel().isEmpty()){
            System.out.println(imeField.textProperty().get());
            System.out.println(prezimeField.textProperty().get());
            System.out.println(indeksField.textProperty().get());
            System.out.println(jmbgField.textProperty().get());
            System.out.println(datumField.getValue().toString());
            System.out.println(mjestoField.getValue().toString());
            System.out.println(adresaField.textProperty().get());
            System.out.println(telefonField.textProperty().get());
            System.out.println(emailField.textProperty().get());
            System.out.println(odsjekField.getValue().toString());
            System.out.println(godinaField.getValue().toString());
            System.out.println(ciklusField.getValue().toString());
            System.out.println(statusField.getValue().toString());
            System.out.println(borackaField.isSelected());
        }
    }
}
