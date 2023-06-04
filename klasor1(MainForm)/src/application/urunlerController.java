package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.isteMySql.Util.veriTabaniUtil;

import application.calisanlarController.calisanlar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

public class urunlerController {
	public urunlerController() {
		baglanti=veriTabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<urunler, String> ad;

    @FXML
    private TableColumn<urunler, Integer> adet;

    @FXML
    private Button btnEkle;

    @FXML
    private Button btnGuncelle;

    @FXML
    private Button btnSil;

    @FXML
    private TableColumn<urunler, Integer> id;

    @FXML
    private TableColumn<urunler, Double> satis_fiyat;

    @FXML
    private TableView<urunler> table;

    @FXML
    private TableColumn<urunler, Double> top_fiyat;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_adet;

    @FXML
    private TextField txt_fiyat1;

    @FXML
    private TextField txt_fiyat2;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_yorum;

    @FXML
    private TableColumn<urunler, String> yorum;
    
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen= null;
    String sql;
    
    
    ObservableList<urunler> veriler;

    @FXML
    void ekle_click(ActionEvent event) {
    	sql="insert into  urunler (urun_id, urun_ad, adet,satis_fiyati,toptan_fiyat,yorum) values(?,?,?,?,?,?)";
   	 try {
   			sorguIfadesi= baglanti.prepareStatement(sql);
   			sorguIfadesi.setString(1, txt_id.getText().trim());
   			sorguIfadesi.setString(2, txt_ad.getText().trim());
   			sorguIfadesi.setString(3, txt_adet.getText().trim());
   			sorguIfadesi.setString(4, txt_fiyat1.getText().trim());
   			sorguIfadesi.setString(5, txt_fiyat2.getText().trim());
   			sorguIfadesi.setString(6, txt_yorum.getText().trim());

   			
   		

   			 sorguIfadesi.executeUpdate();
   			 System.out.println("ekleme iþlemi gerçekleþti");
   			 txt_id.clear();
   			 txt_ad.clear();
   			 txt_adet.clear();
   			 txt_fiyat1.clear();
   			 txt_fiyat2.clear();
   			 txt_yorum.clear();
   			 degerleriGetir(table,sql );
   			 
   			 
   		}catch (Exception e) {
   			Alert alert = new Alert(AlertType.WARNING);
   	    	alert.setTitle("urunler listesi");
   	    	alert.setHeaderText("bilgi mesajý");
   	    	alert.setContentText( (e.getMessage().toString()));
             
   	    	alert.showAndWait();
   			//System.out.println(e.getMessage().toString());
   		}
   		 
    	
    	
    	
    	/*veriler=FXCollections.observableArrayList();
    	veriler.add(new urunler(Integer.parseInt(txt_id.getText()), txt_ad.getText(),Integer.parseInt(txt_adet.getText()),Double.parseDouble(txt_fiyat1.getText()),Double.parseDouble(txt_fiyat2.getText()),txt_yorum.getText()));
    	table.getItems().addAll(veriler);*/


    }

    @FXML
    void guncelle_click(ActionEvent event) {
        sql="update urunler set adet=? ,urun_ad=?,  satis_fiyati=? ,toptan_fiyat=?, yorum=?  where  urun_id=?";//MD5 veya SHA256
        try {
   			sorguIfadesi= baglanti.prepareStatement(sql);
   			sorguIfadesi.setString(6, txt_id.getText().trim());
   			sorguIfadesi.setString(2, txt_ad.getText().trim());
   			sorguIfadesi.setString(1, txt_adet.getText().trim());
   			sorguIfadesi.setString(3, txt_fiyat1.getText().trim());
   			sorguIfadesi.setString(4, txt_fiyat2.getText().trim());
   			sorguIfadesi.setString(5, txt_yorum.getText().trim());

   			
   		

   			 sorguIfadesi.executeUpdate();
   			 System.out.println("güncelleme iþlemi gerçekleþti");
   			 txt_id.clear();
   			 txt_ad.clear();
   			 txt_adet.clear();
   			 txt_fiyat1.clear();
   			 txt_fiyat2.clear();
   			 txt_yorum.clear();
   			 degerleriGetir(table,sql );
   			 
   			 
   		}catch (Exception e) {
   			System.out.println(e.getMessage().toString());
   		}
   		 

    	
    	
    	
    /*	//seçili urunu aldýrmak için
    	urunler urun=new urunler();
    	if(table.getSelectionModel().getSelectedIndex()!=-1) {
    		urun=(urunler) table.getItems().get(table.getSelectionModel().getSelectedIndex());
    		int idm=urun.getId();
    		
    		//Yeni urun
    		urunler urun1=new urunler(idm, txt_ad.getText(), Integer.parseInt(txt_adet.getText()), Double.parseDouble(txt_fiyat1.getText()),Double.parseDouble(txt_fiyat2.getText()),txt_yorum.getText());
    		int sira=table.getSelectionModel().getSelectedIndex();
    		table.getItems().set(sira, urun1);
    	}
    	else {
    		System.out.println("Herhangi bir urun seçili deðil...");
    	}	*/

    }

    @FXML
    void sil_click(ActionEvent event) {
    	sql="delete from urunler where urun_id=? and urun_ad=? and adet=? and satis_fiyati=? and toptan_fiyat=? and yorum=? ";
      	 try {
    			sorguIfadesi= baglanti.prepareStatement(sql);
    			sorguIfadesi.setString(1, txt_id.getText().trim());
    			sorguIfadesi.setString(2, txt_ad.getText().trim());
    			sorguIfadesi.setString(3, txt_adet.getText().trim());
    			sorguIfadesi.setString(4, txt_fiyat1.getText().trim());
    			sorguIfadesi.setString(5, txt_fiyat2.getText().trim());
    			sorguIfadesi.setString(6, txt_yorum.getText().trim());

    			Alert alert=new Alert(AlertType.CONFIRMATION);
    	    	alert.setTitle("Urunler Listesi");
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
   			     txt_adet.clear();
   			     txt_fiyat1.clear();
   			     txt_fiyat2.clear();
   			     txt_yorum.clear();
    			 degerleriGetir(table,sql );
    			 txt_id.clear();
    			 txt_ad.clear();
    			 txt_adet.clear();
    			 txt_fiyat1.clear();
    			 txt_fiyat2.clear();
    			 txt_yorum.clear();
    			 
    			 
    		}catch (Exception e) {
    			System.out.println(e.getMessage().toString());
    		}
    		 
     	
    	
    /*	ObservableList<urunler> secilenUrun, tumUrunler;
    	tumUrunler=table.getItems();
    	secilenUrun=table.getSelectionModel().getSelectedItems();
    	
    	secilenUrun.forEach(tumUrunler::remove);*/

    }
    
    @FXML
    void mouse_click(MouseEvent event) {
    	urunler kayit=new urunler();
    	
    	kayit=(urunler) table.getItems().get(table.getSelectionModel().getSelectedIndex());
    	txt_id.setText(Integer.toString(kayit.getId()));
    	txt_ad.setText(kayit.getAd());
    	txt_adet.setText(Integer.toString(kayit.getAdet()));
    	txt_fiyat1.setText(Double.toString(kayit.getSatis_fiyat()));
    	txt_fiyat2.setText(Double.toString(kayit.getTop_fiyat()));
       // txt_yorum.setText(kayit.getYorum());
       


    }
    
    


	public void degerleriGetir(TableView<urunler> tablo,String sql) {
    	sql="select * from urunler";
    	ObservableList<urunler> veriler=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				veriler.add(new urunler(getirilen.getInt("urun_id"), getirilen.getString("urun_ad"),getirilen.getInt("adet"),getirilen.getDouble("satis_fiyati"),getirilen.getDouble("toptan_fiyat"),getirilen.getString("yorum")));
				}
			//classtan
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
	     	ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
	     	adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
	     	satis_fiyat.setCellValueFactory(new PropertyValueFactory<>("satis_fiyat"));
	     	top_fiyat.setCellValueFactory(new PropertyValueFactory<>("top_fiyat"));
	     	yorum.setCellValueFactory(new PropertyValueFactory<>("yorum"));
	     	
	    	table.setItems(veriler);
	     	} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
			}
    }

    @FXML
    void initialize() {
    	//tooltip
    	btnEkle.setTooltip(new Tooltip("Yeni ürün eklemeyi saðlar..."));
    	btnSil.setTooltip(new Tooltip("Mevcut bir ürünün silinmesini saðlar..."));
    	btnGuncelle.setTooltip(new Tooltip("Mevcut bir ürünün güncellemesini saðlar..."));
    	sql="select * from dolap";
    	degerleriGetir(table,sql);
    	
    	//tableView veri girme
   	 /*   veriler=FXCollections.observableArrayList();

    	 veriler.add(new urunler(1,"HB kalem",60,8,6,"Adana"));
    	 veriler.add(new urunler(2,"A5 telli defter 96",120,9,6,"Antakya"));
    	 veriler.add(new urunler(3,"A4 telli defter 96",84,13,20,"Antakya"));
    	 
    	//Ýlk id tableColumn ismi 
     	//ikinci id Urunler Classindan gelen id
     	id.setCellValueFactory(new PropertyValueFactory<>("id"));
     	ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
     	adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
     	satis_fiyat.setCellValueFactory(new PropertyValueFactory<>("satis_fiyat"));
     	top_fiyat.setCellValueFactory(new PropertyValueFactory<>("top_fiyat"));
     	yorum.setCellValueFactory(new PropertyValueFactory<>("yorum"));
     	
     	table.setItems(veriler);// eklediðimiz verileri göstermek  */

    }
    public static class urunler{
    	private int id;
    	private String ad;
    	private int adet;
    	private double satis_fiyat;
    	private double top_fiyat;
    	private TextField yorum;
    	
    	//construct
        urunler() {
        	this.id=0;
        	this.ad=" ";
        	this.adet=0;
        	this.satis_fiyat=0;
        	this.top_fiyat=0;
        	
    		
    	}
        urunler(int id,String ad,int adet,double satis_fiyat,double top_fiyat,String yorum){
    	this.id=id;
    	this.ad=ad;
    	this.adet=adet;
    	this.satis_fiyat=satis_fiyat;
    	this.top_fiyat=top_fiyat;
    	
    	this.yorum=new TextField();
		this.yorum.setText(yorum);
    	
        }
        //getters and setters
        
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getAd() {
			return ad;
		}
		public void setAd(String ad) {
			this.ad = ad;
		}
		public int getAdet() {
			return adet;
		}
		public void setAdet(int adet) {
			this.adet = adet;
		}
		public double getSatis_fiyat() {
			return satis_fiyat;
		}
		public void setSatis_fiyat(double satis_fiyat) {
			this.satis_fiyat = satis_fiyat;
		}
		public double getTop_fiyat() {
			return top_fiyat;
		}
		public void setTop_fiyat(double top_fiyat) {
			this.top_fiyat = top_fiyat;
		}
		public TextField getYorum() {
			return yorum;
		}
		public void setYorum(TextField yorum) {
			this.yorum = yorum;
		}
        
   }
}
    
