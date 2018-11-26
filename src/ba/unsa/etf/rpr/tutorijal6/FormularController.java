package ba.unsa.etf.rpr.tutorijal6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    private boolean imeValidno;
    private boolean emailValidan;
    private boolean prezimeValidno;
    private boolean jmbgValidan;
    private boolean mjestoValidno;
    private boolean telefonValidan;
    private boolean odsjekValidan;
    private boolean godinaValidna;
    private boolean ciklusValidan;
    private boolean statusValidan;
    private boolean datumValidan;
    private boolean indeksValidan;

    private boolean validnoIme(String n) {
        if(n.length() < 1 || n.length() > 20)
            return false;
        for(int i = 0; i < n.length(); i++)
            if(n.toUpperCase().charAt(i) < 65 || n.toUpperCase().charAt(i) > 90)
                return false;
        return true;
    }

    private boolean validanEmail(String n){
        if(!n.contains("@"))
            return false;
        if(!n.contains("."))
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

    public boolean isImeValidno() {
        return imeValidno;
    }
    public boolean isPrezimeValidno() {
        return prezimeValidno;
    }
    public boolean isJmbgValidan(){return jmbgValidan;}
    public boolean isTelefonValidan(){return telefonValidan;}
    public boolean isMjestoValidno(){return mjestoValidno;}
    public boolean isOdsjekValidan(){return odsjekValidan;}
    public boolean isGodinaValidna(){return godinaValidna;}
    public boolean isCiklusValidan(){return ciklusValidan;}
    public boolean isStatusValidan(){return statusValidan;}
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

        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (validanEmail(newValue)) {
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

        mjestoField.converterProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (validnoIme(newValue)) {
                    mjestoField.getStyleClass().removeAll("poljeNijeIspravno");
                    mjestoField.getStyleClass().add("poljeIspravno");
                    mjestoValidno = true;
                } else {
                    mjestoField.getStyleClass().removeAll("poljeIspravno");
                    mjestoField.getStyleClass().add("poljeNijeIspravno");
                    mjestoValidno = false;
                }
            }
        });

        odsjekField.converterProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                odsjekValidan = true;
            }
        });

        godinaField.converterProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                godinaValidna = true;
            }
        });

        statusField.converterProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                statusValidan = true;
            }
        });

        ciklusField.converterProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                ciklusValidan = true;
            }
        });
    }
}
