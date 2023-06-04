package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.calisanlarController.calisanlar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import com.isteMySql.Util.veriTabaniUtil;

public class faturalarController { 
    public faturalarController() {
    	baglanti=veriTabaniUtil.Baglan();
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<faturalar, String> aciklama;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button btn_sorgula;

    @FXML
    private ComboBox<String> combo_user;

    @FXML
    private DatePicker date_basla;

    @FXML
    private DatePicker date_bit;

    @FXML
    private TableColumn<faturalar, Integer> fatura_no;

    @FXML
    private TableColumn<faturalar, String> kul_ad;

    @FXML
    private Label lbl;

    @FXML
    private Label lbl_basla;

    @FXML
    private Label lbl_bit;

    @FXML
    private Label lbl_kul;

    @FXML
    private SplitPane split;

    @FXML
    private TableView<faturalar> table;

    @FXML
    private TableColumn<faturalar, LocalDate> tarih;

    @FXML
    private TableColumn<faturalar,Double> tutar;

    @FXML
    private TextField txt_arama;
    
    

    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen= null;
    String sql;
    
    ObservableList<faturalar> veriler;


    @FXML
    void combo_user_action(ActionEvent event) {
			
    
    	degerleriGetir(table,sql);
    


    }

    @FXML
    void sorgula_click(ActionEvent event) {
    	sql="select * from faturalar where Tarih>'"+date_basla.getValue()+"' and Tarih<'"+date_bit.getValue()+"'";
    	degerleriGetir(table,sql);
   	sql="select * from faturalar where Tarih>? and Tarih<?";
        try {
		sorguIfadesi=baglanti.prepareStatement(sql);
        sorguIfadesi.setDate(1, Date.valueOf(date_basla.getValue()));
        degerleriGetir2(table,sorguIfadesi);
        }catch(Exception e) {
			System.out.println(e.getMessage().toString());

        }
    
    }

    @FXML
    void txt_arama_action(ActionEvent event) {
    	
    }

    @FXML
    void txt_arama_keyPressed(KeyEvent event) {
       	if(txt_arama.getText().equals("")) {
        	sql="select * from faturalar";

    	}
    	else {
        	sql="select * from faturalar where Aciklama like '%"+txt_arama.getText()+"%' or kulAd like '%"+txt_arama.getText()+"%'";

    	}
    	degerleriGetir(table,sql);
    }
    
    public void degerleriGetir(TableView<faturalar> tablo,String sql) {
    	//sql="select * from faturalar";
    	ObservableList<faturalar> veriler=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				veriler.add(new faturalar(getirilen.getInt("fatura_no"),getirilen.getString("kulAd"),getirilen.getString("Aciklama"),getirilen.getDouble("Tutar"),getirilen.getDate("Tarih")));//databaseten
			}
			//sol taraf columnlarýn idleri sað taraf classtan
			fatura_no.setCellValueFactory(new PropertyValueFactory<>("fatura_no"));
			kul_ad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
	     	aciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
	     	tutar.setCellValueFactory(new PropertyValueFactory<>("tutar"));
	     	tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));

	    	table.setItems(veriler);
	     	} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
	     	}
		}
    public void degerleriGetir2(TableView<faturalar> tablo,PreparedStatement sorgu) {
    	//sql="select * from faturalar";
    	ObservableList<faturalar> veriler=FXCollections.observableArrayList();
    	try {
			//sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorgu.executeQuery();
			while (getirilen.next()) {
				veriler.add(new faturalar(getirilen.getInt("fatura_no"),getirilen.getString("kulAd"),getirilen.getString("Aciklama"),getirilen.getDouble("Tutar"),getirilen.getDate("Tarih")));//databaseten
			}
			//sol taraf columnlarýn idleri sað taraf classtan
			fatura_no.setCellValueFactory(new PropertyValueFactory<>("fatura_no"));
			kul_ad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
	     	aciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
	     	tutar.setCellValueFactory(new PropertyValueFactory<>("tutar"));
	     	tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));

	    	table.setItems(veriler);
	     	} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
	     	}
		}

    @FXML
    void initialize() {
    	sql="select * from faturalar";
        degerleriGetir(table,sql);
       
        ObservableList<String> dizi2= FXCollections.observableArrayList("admin","shahzanan","zeynep");
        combo_user.getItems().addAll(dizi2);
        date_basla.setValue(LocalDate.now());
        date_bit.setValue(LocalDate.now());

    }
    public static class faturalar{
	  private int fatura_no;
	  private String kul_ad;
	  private String aciklama;
	  private double tutar;
	  private Date tarih;
	  
	  faturalar(){
		  this.fatura_no=0;
	  }
	  faturalar(int fatura_no,String kulAd,String Aciklama,double Tutar,Date Tarih){
		  this.fatura_no=fatura_no;
		  this.kul_ad=kulAd;
		  this.aciklama=Aciklama;
		  this.tutar=Tutar;
		  this.tarih=Tarih;
	  }
	  
	  public int getFatura_no() {
		return fatura_no;
	  }
	  public void setFatura_no(int fatura_no) {
		this.fatura_no = fatura_no;
	  }
	  public String getKul_ad() {
		return kul_ad;
	  }
	  public void setKul_ad(String kul_ad) {
		this.kul_ad = kul_ad;
	  }
	  public String getAciklama() {
	 	return aciklama;
	  }
	  public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	  }
	  public double getTutar() {
		return tutar;
	  }
	  public void setTutar(double tutar) {
		this.tutar = tutar;
	  }
	  public Date getTarih() {
		return tarih;
	  }
	  public void setTarih(Date tarih) {
		this.tarih = tarih;
	  }
	  
     
   }
}
