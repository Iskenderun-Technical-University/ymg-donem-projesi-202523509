package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySql.Util.veriTabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

public class ayarlarController {
	public ayarlarController() {
		baglanti = veriTabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> choice;

    @FXML
    private Label lbl_bilgi;

    @FXML
    private Label lbl_eski_sifre;

    @FXML
    private Label lbl_kulAd;

    @FXML
    private Label lbl_sifre_tekrar;

    @FXML
    private Label lbl_ulke;

    @FXML
    private Label lbl_yeni_sifre;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_eski;

    @FXML
    private TextField txt_tekrar;

    @FXML
    private TextField txt_yeni;

    @FXML
    private Button yenile;
    
    
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen= null;
    String sql;

    @FXML
    void yenile_click(ActionEvent event) {

    }

    @FXML
    void initialize() {
      

    }

}
