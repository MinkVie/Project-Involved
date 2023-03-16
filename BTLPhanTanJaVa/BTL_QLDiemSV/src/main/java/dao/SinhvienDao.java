/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.DataSQL;
import entity.DatabaseLogin;
import entity.SinhVien;

/**
 *
 * @author Yahiko
 */
public class SinhvienDao {
    public SinhVien FindSV(String MaSV,DataSQL dt) throws Exception{
        String sql="SELECT * FROM [dbo].[SinhVien] WHERE [MaSV]=?";
            try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
            {
                dtb.setString(1,MaSV);
                try(ResultSet rs=dtb.executeQuery())
            {
                if (rs.next())
            {
                SinhVien sb=new SinhVien();
                sb.setMaSV(rs.getString("MaSV"));
                sb.setHoTen(rs.getString("HoTen"));
                sb.setSDT(rs.getString("SDT"));
                sb.setGioiTinh(rs.getInt("GioiTinh"));
                sb.setEmail(rs.getString("Email"));
                return sb;
            }    
            }
            }
        
        return null;
}
    public List<SinhVien> FindAllSV(DataSQL dt) throws Exception{
     String sql="SELECT * FROM [dbo].[SinhVien]";
     
      try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
        try(ResultSet rs=dtb.executeQuery())
        {
            List<SinhVien> list =new ArrayList<SinhVien>();
            while (rs.next())
            {
                SinhVien sb=new SinhVien();
                sb.setMaSV(rs.getString("MaSV"));
                sb.setHoTen(rs.getString("HoTen"));
                sb.setSDT(rs.getString("SDT"));
                sb.setGioiTinh(rs.getInt("GioiTinh"));
                sb.setEmail(rs.getString("Email"));
                list.add(sb); 
            }
            return list;
        }
        }     
    }
    public boolean Insert(SinhVien SV,DataSQL dt) throws Exception{
        String sql="INSERT INTO [dbo].[SinhVien]([MaSV],[HoTen],[GioiTinh],[SDT],[Email])" +
                " VALUES(?,?,?,?,?)";
    try{
        try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
            dtb.setString(1,SV.getMaSV() );
            dtb.setString(2,SV.getHoTen() );
            dtb.setInt(3,SV.getGioiTinh());
            dtb.setString(4,SV.getSDT());
            dtb.setString(5,SV.getEmail());
            
            return dtb.executeUpdate()>0;
        }    
        } 
        catch (Exception e) {
            
        }
    return false; 
}
public boolean Update(SinhVien SV,DataSQL dt) throws  Exception{
    String sql="UPDATE [dbo].[SinhVien] "+
            " SET [HoTen]= ?,[GioiTinh]= ?,[SDT]= ?,[Email]=?" +
                " WHERE [MaSV]= ?";
    try{    
        try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
            dtb.setString(1,SV.getHoTen() );
            dtb.setInt(2,SV.getGioiTinh());
            dtb.setString(3,SV.getSDT());
            dtb.setString(4,SV.getEmail());
            dtb.setString(5,SV.getMaSV());
            
            return dtb.executeUpdate()>0;
        }    
        } 
        catch (Exception e) {
            
        }
    return false;    
    }
public boolean Delete(SinhVien SV,DataSQL dt) throws  Exception {
    String sql= "DELETE [dbo].[SinhVien] "+
                " WHERE [MaSV]= ?";
        try{
            try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
            dtb.setString(1, SV.getMaSV());
            return dtb.executeUpdate()>0;
        }  
            } 
        catch (Exception e) {
            
        }
    return false; 
             
    }
public SinhVien getSinhVienbByContainsQuery(String params , DataSQL dt) throws Exception{
          String sql = "SELECT * FROM [dbo].[SinhVien] WHERE [MaSV] =? OR [Hoten]=? OR [SDT]=? OR [Email]=? ";
        try (
                 Connection con = DatabaseLogin.openConnection(dt);  
                 PreparedStatement dtb = con.prepareStatement(sql);
            ) 
        {
            dtb.setString(1, params);
            dtb.setString(2, params);
            dtb.setString(3, params);
            dtb.setString(4, params);
            
            try ( ResultSet rs = dtb.executeQuery()) {
                if (rs.next()) {
                    SinhVien sb = new SinhVien();
                    sb.setMaSV(rs.getString("MaSV"));
                    sb.setHoTen(rs.getString("HoTen"));
                    sb.setSDT(rs.getString("SDT"));
                    sb.setGioiTinh(rs.getInt("GioiTinh"));
                    sb.setEmail(rs.getString("Email"));
                    return sb;
                }

            }
        }
        return null ;      
    }
}
