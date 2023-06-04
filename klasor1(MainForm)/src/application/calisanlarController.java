package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.isteMySql.Util.veriTabaniUtil;

import application.urunlerController.urunler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.sql.*;

public class calisanlarController {
	public calisanlarController() {
		baglanti=veriTabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_bilgi;

    @FXML
    private AnchorPane anchor_calisan;

    @FXML
    private AnchorPane anchor_table;

    @FXML
    private Button ekle;

    @FXML
    private Button goster;

    @FXML
    private Button guncelle;

    @FXML
    private TableColumn<calisanlar, Integer> id;

    @FXML
    private TableColumn<calisanlar, String> kul_ad;

    @FXML
    private TableColumn<calisanlar, Integer> kul_sifre;

    @FXML
    private Label lbl;

    @FXML
    private Label lbl_ad;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_sifre;

    @FXML
    private Label lbl_telefon;

    @FXML
    private Button sil;

    @FXML
    private SplitPane split;

    @FXML
    private TableView<calisanlar> table;

    @FXML
    private TableColumn<calisanlar, Integer> telefon;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TextField txt_telefon;
    
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen= null;
    String sql;
    
    ObservableList<calisanlar> veriler;

    @FXML
    void ekle_click(ActionEvent event) {
    	 sql="insert into  login (kul_id, kulAd, sifre,yetkili, telefon) values(?,?,?,?,?)";
    	 try {
    			sorguIfadesi= baglanti.prepareStatement(sql);
    			sorguIfadesi.setString(1, txt_id.getText().trim());
    			sorguIfadesi.setString(2, txt_ad.getText().trim());
    			sorguIfadesi.setString(3,  txt_sifre.getText().trim());//veriTabaniUtil.MD5Sifrele(txt_sifre.getText().trim())
    			sorguIfadesi.setString(4, "1");
    			sorguIfadesi.setString(5, txt_telefon.getText().trim());

    			 sorguIfadesi.executeUpdate();
    			 System.out.println("ekleme iþlemi gerçekleþti");
    			 txt_id.clear();
    			 txt_ad.clear();
    			 txt_sifre.clear();
    			 txt_telefon.clear();
    		
    			 degerleriGetir(table,sql );
    			 txt_id.clear();
    			 txt_ad.clear();
    			 txt_sifre.clear();
    			 txt_telefon.clear();
    			 
    		}catch (Exception e) {
    			System.out.println(e.getMessage().toString());
    		}
    		 
    			
    	/*veriler=FXCollections.observableArrayList();
    	veriler.add(new calisanlar(Integer.parseInt(txt_id.getText()), txt_ad.getText(),Integer.parseInt(txt_sifre.getText()),Integer.parseInt(txt_telefon.getText())));
    	table.getItems().addAll(veriler);*/

    }

    @FXML
    void goster_click(ActionEvent event) {
    	
    	calisanlar kayit=new calisanlar();
    	if(table.getSelectionModel().getSelectedIndex()!=-1) {
    		kayit=(calisanlar) table.getItems().get(table.getSelectionModel().getSelectedIndex());
    		
    	System.out.println("kullanýcý_ID:"+kayit.getId()+"\n"+ " Kullanýcý Adý:"+kayit.getKul_ad()+"\n"+ " Þifre="+kayit.getKul_sifre()+"\n"+"telefon:"+kayit.getTelefon());
    	}
    	else {
    		System.out.println("Herhangi bir kayýt seçilmedi....");
    	}
    	txt_id.clear();
		 txt_ad.clear();
		 txt_sifre.clear();
		 txt_telefon.clear();
	

    }
    
    @FXML
    void mouse_click(MouseEvent event) {
    	calisanlar kayit=new calisanlar();
    	kayit=(calisanlar) table.getItems().get(table.getSelectionModel().getSelectedIndex());
    	txt_id.setText(Integer.toString(kayit.getId()));
    	txt_ad.setText(kayit.getKul_ad());
    	txt_sifre.setText(Integer.toString(kayit.getKul_sifre()));
    	txt_telefon.setText(Integer.toString(kayit.getTelefon()));
    /*	lbl_id.setText(String.valueOf(kayit.getId()));
    	lbl_ad.setText(String.valueOf(kayit.getKul_ad()));
    	lbl_sifre.setText(String.valueOf(kayit.getKul_sifre()));
    	lbl_telefon.setText(String.valueOf(kayit.getTelefon()));*/
    	
    	
    	

    }

    @FXML
    void guncelle_click(ActionEvent event) {
    sql="update login set sifre=? ,kulAd=?,  yetkili=? ,telefon=?  where  kul_id=?";//MD5 veya SHA256
    	try {
    		sorguIfadesi= baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(5, txt_id.getText().trim());
			sorguIfadesi.setString(2, txt_ad.getText().trim());
			sorguIfadesi.setString(1, txt_sifre.getText().trim());//veriTabaniUtil.MD5Sifrele( txt_sifre.getText().trim())
			sorguIfadesi.setString(3, "1");
			sorguIfadesi.setString(4, txt_telefon.getText().trim());

    		 sorguIfadesi.executeUpdate();
    		 System.out.println(" güncelleme gerçekleþti..");
    		 txt_id.clear();
			 txt_ad.clear();
			 txt_sifre.clear();
			 txt_telefon.clear();
			 
			 degerleriGetir(table,sql);
			 
    	}catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    	
    	
    	
    	
    	
    	/*//Secili Kaydi Aldirmak icin
    	calisanlar kayit=new calisanlar();
    	if(table.getSelectionModel().getSelectedIndex()!=-1) {
    		kayit=(calisanlar) table.getItems().get(table.getSelectionModel().getSelectedIndex());
    		int idm=kayit.getId();
    		//txt_kul.setText(kayit.getKul_ad());
    		//txt_sifre.setText(kayit.getSifre());
    		//lbl_deger.setText("Deger= ID:"+kayit.getId()+ " Kullanýcý Adý:"+kayit.getKul_ad()+ " Þifre="+kayit.getKul_sifre());
    		
    		//Yeni kayitlar olusturarak indise gore deger atama
    		calisanlar kk=new calisanlar(idm, txt_ad.getText(), Integer.parseInt( txt_sifre.getText()),Integer.parseInt(txt_telefon.getText()));
    		int sira=table.getSelectionModel().getSelectedIndex();
    		table.getItems().set(sira, kk);
    		
    	}
    	else {
    		System.out.println("Herhangi bir kayýt seçili deðil...");
    	}	*/


    }

    @FXML
    void sil_click(ActionEvent event) {
    	
    	sql="delete from login where kul_id=? and kulAd=? and sifre=? and yetkili=? and telefon=?";
    	 try {
 			sorguIfadesi= baglanti.prepareStatement(sql);
 			sorguIfadesi.setString(1, txt_id.getText().trim());
 			sorguIfadesi.setString(2, txt_ad.getText().trim());
 			sorguIfadesi.setString(3, txt_sifre.getText().trim());//veriTabaniUtil.MD5Sifrele(txt_sifre.getText().trim())
 			sorguIfadesi.setString(4, "1");
 			sorguIfadesi.setString(5, txt_telefon.getText().trim());
 			
 			
 			
 			Alert alert=new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Çalýþanlar Listesi");
	    	alert.setHeaderText("");
	    	alert.setContentText("Silmek istediðinizden emin misiniz");
	    	
	    	ButtonType btn1=new ButtonType("Evet");
	    	ButtonType btn2=new ButtonType("Hayýr");
	    	ButtonType btn3=new ButtonType("iptal", ButtonData.CANCEL_CLOSE);
	    	alert.getButtonTypes().setAll(btn1, btn2, btn3);
	    	Optional<ButtonType> sonuc= alert.showAndWait();
	    	
	    	if(sonuc.get()==btn1) {
	    		
	    		 sorguIfadesi.executeUpdate();
	    		 System.out.println("Silme iþlemi gerçekleþti");

	    		
	    	}
	    	else if(sonuc.get()==btn2) {
	    		System.out.println(" hayýr butonuna týklandý..");
	    	}
	    	else if(sonuc.get()==btn3) {
	    		System.out.println("iptal butonuna týklandý..");
	    	}
	    	else {
	    		System.out.println("Ýptal tuþu");
	    	}
	    	
	    	
 			 txt_id.clear();
 			 txt_ad.clear();
 			 txt_sifre.clear();
 			 txt_telefon.clear();
 		
 			 degerleriGetir(table,sql );
 			 txt_id.clear();
 			 txt_ad.clear();
 			 txt_sifre.clear();
 			 txt_telefon.clear();
 			 
 		}catch (Exception e) {
 			System.out.println(e.getMessage().toString());
 		}
    	
    	
    	
    	
    /*	ObservableList<calisanlar> secilenKayit, tumKayitlar;
    	tumKayitlar=table.getItems();
    	secilenKayit=table.getSelectionModel().getSelectedItems();
    	
    	secilenKayit.forEach(tumKayitlar::remove);*/

    }
    public void degerleriGetir(TableView<calisanlar> tablo,String sql) {
    	sql="select * from login";
    	ObservableList<calisanlar> veriler=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				veriler.add(new calisanlar(getirilen.getInt("kul_id"),getirilen.getString("kulAd"),getirilen.getInt("sifre"),getirilen.getInt("telefon")));//databaseten
			}
			//classtan
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
			kul_ad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
	     	kul_sifre.setCellValueFactory(new PropertyValueFactory<>("kul_sifre"));
	     	telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
	     	
	    	table.setItems(veriler);
	     	} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
		}
    	
    }

    @FXML
    void initialize() {
    	
    	/*veriler=FXCollections.observableArrayList();
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
     	kul_ad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
     	kul_sifre.setCellValueFactory(new PropertyValueFactory<>("kul_sifre"));
     	telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
     	
     	table.setItems(veriler);*/
    	degerleriGetir(table,sql);
      
    }
    public static class calisanlar{
    	private int id;
    	private String kul_ad;
    	private int kul_sifre;
    	private int telefon;
    	
    	
    	//construct
        calisanlar() {
        	this.id=0;
        	this.kul_ad=" ";
        	this.kul_sifre=0;
        	this.telefon=0;
        	
    		
    	}
        calisanlar(int id,String kul_ad,int kul_sifre,int telefon){
    	this.id=id;
    	this.kul_ad=kul_ad;
    	this.kul_sifre=kul_sifre;
    	this.telefon=telefon;
    	
        }
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getKul_ad() {
			return kul_ad;
		}
		public void setKul_ad(String kul_ad) {
			this.kul_ad = kul_ad;
		}
		public int getKul_sifre() {
			return kul_sifre;
		}
		public void setKul_sifre(int kul_sifre) {
			this.kul_sifre = kul_sifre;
		}
		public int getTelefon() {
			return telefon;
		}
		public void setTelefon(int telefon) {
			this.telefon = telefon;
		}
        

}}
