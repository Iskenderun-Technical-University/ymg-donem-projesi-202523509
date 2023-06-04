package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.isteMySql.Util.veriTabaniUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*;

public class GirisSayfasiController {
	public GirisSayfasiController() {
		baglanti = veriTabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane anchor_giris;

    @FXML
    private HBox box;

    @FXML
    private Button cikis_btn;

    @FXML
    private Button giris_btn;

    @FXML
    private Label kul_lbl;

    @FXML
    private Label pass_lbl;
    
    @FXML
    private Label lbl_sonuc;

    @FXML
    private TextField txt_kulAd;

    @FXML
    private TextField txt_sifre;
    
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen= null;
    String sql;

    @FXML
    void cikis_click(ActionEvent event) {
    	sql="select*from login where kulAd=? and sifre =?";
    	try {
    		sorguIfadesi= baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_kulAd.getText().trim());
    		sorguIfadesi.setString(2, txt_sifre.getText().trim());
    		
    		ResultSet getirilen = sorguIfadesi.executeQuery();
    		if(!getirilen.next()) {
    			lbl_sonuc.setText("Kullanici adı veya şifre hatalı..");
    			
    		}
    		else {
    			
    			getirilen.getString(1);
    			System.out.println("kul_id:"+getirilen.getString("kul_id"));//getirilen.getİNT
    			System.out.println("kulAd:"+getirilen.getString("kulAd"));
    			System.out.println("sifre:"+getirilen.getString("sifre"));
    			System.out.println("yetkili:"+getirilen.getString("yetkili"));
    			System.out.println("telefon:"+getirilen.getString("telefon"));
    			lbl_sonuc.setText("...");
    			Alert alert=new Alert(AlertType.CONFIRMATION);
    	    	alert.setTitle("Kırtasiye Otomasyonu");
    	    	alert.setHeaderText("");
    	    	alert.setContentText("Çıkmak istediğinizden emin misiniz");
    	    	
    	    	ButtonType btn1=new ButtonType("Evet");
    	    	ButtonType btn2=new ButtonType("Hayır");
    	    	ButtonType btn3=new ButtonType("iptal", ButtonData.CANCEL_CLOSE);
    	    	alert.getButtonTypes().setAll(btn1, btn2, btn3);
    	    	Optional<ButtonType> sonuc= alert.showAndWait();
    	    	
    	    	if(sonuc.get()==btn1) {
    	    		System.out.println("Otomasyondan çıktınız");
                    Platform.exit();

    	    		
    	    	}
    	    	else if(sonuc.get()==btn2) {
    	    		System.out.println(" hayır butonuna tıklandı..");
    	    	}
    	    	else if(sonuc.get()==btn3) {
    	    		System.out.println("iptal butonuna tıklandı..");
    	    	}
    	    	else {
    	    		System.out.println("İptal tuşu");
    	    	}
    	    

    			
    
    		}
    		
    	}catch (Exception e) {
    		lbl_sonuc.setText(e.getMessage().toString());
    	}
    	
    	
    
    }

    @FXML
    void giris_click(ActionEvent event) {
    	sql="select*from login where kulAd=? and sifre =?";
    	try {
    		sorguIfadesi= baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_kulAd.getText().trim());
    		sorguIfadesi.setString(2,  txt_sifre.getText().trim());
    		
    		ResultSet getirilen = sorguIfadesi.executeQuery();
    		if(!getirilen.next()) {
    			lbl_sonuc.setText("Kullanici adı veya şifre hatalı..");
    		
    			
    		}
    		else {
    			
    			getirilen.getString(1);
    			System.out.println("kul_id:"+getirilen.getString("kul_id"));//getirilen.getİNT
    			System.out.println("kulAd:"+getirilen.getString("kulAd"));
    			System.out.println("sifre:"+getirilen.getString("sifre"));
    			System.out.println("yetkili:"+getirilen.getString("yetkili"));
    			System.out.println("telefon:"+getirilen.getString("telefon"));

    			lbl_sonuc.setText("Welcome");
    			try {
    	    		Stage stage=new Stage();
    	        	AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("AnaSayfa.fxml"));
    	        	Scene scene1=new Scene(pane1);
    	        	stage.setScene(scene1);
    	        	stage.setTitle("Ana Sayfa");
    	        	
    	            stage.show();
    				
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    			
    
    		}
    		
    	}catch (Exception e) {
    		lbl_sonuc.setText(e.getMessage().toString());
    	}

    }

    @FXML
    void initialize() {
      
    }

}
