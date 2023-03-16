/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.DataSQL;
import entity.DatabaseLogin;
import entity.DiemThi;
import entity.MonHoc;
import entity.SinhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yahiko
 */
public class DiemThiDao {
    public List<DiemThi> FindAllSV(DataSQL dt) throws Exception{
     String sql="SELECT * FROM [dbo].[DiemThi]";
     
      try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
        try(ResultSet rs=dtb.executeQuery())
        {
            List<DiemThi> list =new ArrayList<DiemThi>();
            while (rs.next())
            {
                DiemThi sb=new DiemThi();
                sb.setMaSV(rs.getString("MaSV"));
                sb.setMaMH(rs.getString("MaMH"));
                sb.setDiemGK(rs.getFloat("DiemGK"));
                sb.setDiemCK(rs.getFloat("DiemCK"));
                sb.setDiemTK(rs.getFloat("DiemTK"));
                list.add(sb); 
            }
            return list;
        }
        }     
    }  
     public DiemThi FindDiem(String MaMH,String MaSV,DataSQL dt) throws Exception{
        String sql="SELECT * FROM [dbo].[DiemThi] WHERE [MaMH]=? AND [MaSV]=?";
            try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
            {
                dtb.setString(1,MaMH);
                dtb.setString(2,MaSV);
                try(ResultSet rs=dtb.executeQuery())
            {
                if (rs.next())
            {
                DiemThi sb=new DiemThi();
                sb.setMaSV(rs.getString("MaSV"));
                sb.setMaMH(rs.getString("MaMH"));
                sb.setDiemGK(rs.getFloat("DiemGK"));
                sb.setDiemCK(rs.getFloat("DiemCK"));
                sb.setDiemTK(rs.getFloat("DiemTK"));
                return sb;
            }    
            }
            }
        
        return null;
}
     public boolean Insert(DiemThi SV,DataSQL dt) throws Exception{
        
    String sql="INSERT INTO [dbo].[DiemThi]([MaMH],[MaSV],[DiemGK],[DiemCK],[DiemTK])" +
                " VALUES(?,?,?,?,?)";
    try {
            
        try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
            dtb.setString(1,SV.getMaMH() );
            dtb.setString(2,SV.getMaSV() );
            dtb.setFloat(3,SV.getDiemGK());
            dtb.setFloat(4,SV.getDiemCK());
            dtb.setFloat(5,SV.getDiemTK());
            return dtb.executeUpdate()>0;
        }    
        } 
        catch (Exception e) {
        }
    return false;
}
    public  boolean Update(DiemThi SV,DataSQL dt) throws  Exception{
    String sql="UPDATE [dbo].[DiemThi] "+
            " SET [DiemGK]= ?,[DiemCK]= ?,[DiemTK]=?" +
                " WHERE [MaSV]= ? AND [MaMH]=?";
    try {
         try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
            dtb.setString(4,SV.getMaSV() );
            dtb.setString(5,SV.getMaMH() );
            dtb.setFloat(1,SV.getDiemGK());
            dtb.setFloat(2,SV.getDiemCK());
            dtb.setFloat(3,SV.getDiemTK());
            return dtb.executeUpdate()>0;
        }  
        } 
        catch (Exception e) {
        }
    return false;
    }
    public boolean Delete(DiemThi SV,DataSQL dt) throws  Exception {
        String sql= "DELETE [dbo].[DiemThi] "+
                " WHERE [MaSV]= ? AND [MaMH]=?";
    try {
        try (
           Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
            dtb.setString(1, SV.getMaSV());
            dtb.setString(2, SV.getMaMH());
            return dtb.executeUpdate()>0;
        } 
        } 
        catch (Exception e) {
            
        }
    return false;         
    }
    // Trung
    public List<DiemThi> FinMMH(String MaMH, DataSQL dt) throws Exception{
     String sql="SELECT * FROM [dbo].[DiemThi]  WHERE [MaMH]=?  GROUP BY [MAMH], [MASV], [DiemGK],[DiemCK],[DiemTK]";
     
      try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
              
            )
        {dtb.setString(1,MaMH);
        try(ResultSet rs=dtb.executeQuery())
        {
            List<DiemThi> list =new ArrayList<DiemThi>();
            while (rs.next())
            {
                DiemThi sb=new DiemThi();
                sb.setMaSV(rs.getString("MaSV"));
                sb.setMaMH(rs.getString("MaMH"));
                sb.setDiemGK(rs.getFloat("DiemGK"));
                sb.setDiemCK(rs.getFloat("DiemCK"));
                sb.setDiemTK(rs.getFloat("DiemTK"));
                list.add(sb); 
            }
            return list;
        }
        }     
    }  
              
    public List<DiemThi> FinMSV(String MaSV, DataSQL dt) throws Exception{
     String sql="SELECT  * FROM [dbo].[DiemThi]  WHERE [MaSV]=?";
     
      try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
              
            )
        {dtb.setString(1,MaSV);
        try(ResultSet rs=dtb.executeQuery())
        {
            List<DiemThi> list =new ArrayList<DiemThi>();
            while (rs.next())
            {
                DiemThi sb=new DiemThi();
                sb.setMaSV(rs.getString("MaSV"));
                sb.setDiemTK(rs.getFloat("DiemTK"));
                list.add(sb); 
            }
            return list;
        }
        }     
    }  
                 //trung
     public List<DiemThi> FindAllSVDistinct(DataSQL dt) throws Exception{
     String sql="SELECT DISTINCT MaSV FROM [dbo].[DiemThi] ";
     
      try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
        try(ResultSet rs=dtb.executeQuery())
        {
            List<DiemThi> list =new ArrayList<DiemThi>();
            while (rs.next())
            {
                DiemThi sb=new DiemThi();
                sb.setMaSV(rs.getString("MaSV"));
               // sb.setMaMH(rs.getString("MaMH"));
               //sb.setDiemGK(rs.getFloat("DiemGK"));
               // sb.setDiemCK(rs.getFloat("DiemCK"));
               // sb.setDiemTK(rs.getFloat("DiemTK"));
                list.add(sb); 
            }
            return list;
        }
        }     
    }  
                  
                  //TRUNG   
     public List<DiemThi> FindAllSvASC(DataSQL dt) throws Exception{
     String sql="SELECT * FROM [dbo].[DiemThi] GROUP BY [MAMH], [MASV], [DiemGK],[DiemCK],[DiemTK] ORDER BY MaSV ASC ";
     
      try (
            Connection con=DatabaseLogin.openConnection(dt);
            PreparedStatement dtb= con.prepareStatement(sql);
            )
        {
        try(ResultSet rs=dtb.executeQuery())
        {
            List<DiemThi> list =new ArrayList<DiemThi>();
            while (rs.next())
            {
                DiemThi sb=new DiemThi();
                sb.setMaSV(rs.getString("MaSV"));
                sb.setMaMH(rs.getString("MaMH"));
                sb.setDiemGK(rs.getFloat("DiemGK"));
                sb.setDiemCK(rs.getFloat("DiemCK"));
                sb.setDiemTK(rs.getFloat("DiemTK"));
                list.add(sb); 
            }
            return list;
        }
        }     
    }  
    
}