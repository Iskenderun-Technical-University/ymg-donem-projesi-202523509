package com.isteMySql.Util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class veriTabaniUtil {
	static Connection conn=null;
	public static Connection Baglan() {
		try {
			//"jdcb:mysql://server›PAdresi//db_ismi","kullanici","sifre"
			conn=DriverManager.getConnection("jdbc:mysql://localhost/projemdb","root","mysql");
			return conn;
		}catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
   public static String MD5Sifrele(String icerik) {
	   try {
		MessageDigest md=MessageDigest.getInstance("MD5");
		//byte olarak okunur
		byte[] sifrelenmis=md.digest(icerik.getBytes());
		BigInteger no= new BigInteger(1,sifrelenmis);
		//hex hesapla
		String hashIcerik=no.toString(16);
		while (hashIcerik.length()<32) {
			hashIcerik="0"+hashIcerik;
			
		}
		return hashIcerik;
	} catch (NoSuchAlgorithmException e) {
	 throw new RuntimeException(e);
	}
	   
   }
}
