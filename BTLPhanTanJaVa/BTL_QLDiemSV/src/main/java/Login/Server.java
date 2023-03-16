/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import entity.DataSQL;
import entity.DiemThi;
import entity.MonHoc;
import entity.SinhVien;

import dao.DiemThiDao;
import dao.MonHocDao;
import dao.SinhvienDao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Des
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Yahiko
 */
public class Server {
   public static String Encrypt(String value,String Key) throws NoSuchAlgorithmException, NoSuchPaddingException, 
           InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
    try {
			String originalText = value;
			String key = "12345678";
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] byteEncrypted = cipher.doFinal(originalText.getBytes());
			String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);
			return encrypted;
        } catch (Exception ex) {
		ex.printStackTrace();
        }
    return null;
   }
   public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, 
           NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
                    IllegalBlockSizeException, BadPaddingException {
        ServerSocket  server = new ServerSocket(9999);
        System.out.println("Server is running ") ;
        while (true)
        {
            Socket sclient = server.accept();
            DataInputStream DTT=new DataInputStream(sclient.getInputStream());
            DataOutputStream OTT=new DataOutputStream(sclient.getOutputStream());
            ObjectInputStream OIS = new ObjectInputStream(sclient.getInputStream()) ;
            ObjectOutputStream OOS = new ObjectOutputStream(sclient.getOutputStream());
            int flag=DTT.readInt();
            if (flag==1)      //Thêm Sinh Viên
            {
                SinhVien stu = (SinhVien) OIS.readObject();
                Thread.sleep(15);
                String MaSV=stu.getMaSV();
                String Hoten=stu.getHoTen();
                String Email=stu.getEmail();
                String Sdt=stu.getSDT();
                String des=Encrypt(MaSV,MaSV);
                System.out.println(des);
                stu.setMaSV(Encrypt(MaSV,MaSV));
                stu.setSDT(Encrypt(Sdt,MaSV));
                stu.setEmail(Encrypt(Email,MaSV));
                DataSQL data=(DataSQL) OIS.readObject();
                    try {
                            SinhvienDao dao=new SinhvienDao();
                            Boolean tg=dao.Insert(stu,data);
                            OTT.writeBoolean(tg);
                        }           
                    catch (Exception e) {
                    }
            }
            else
            if (flag==2)
            {
                SinhVien stu = (SinhVien) OIS.readObject();
                Thread.sleep(15);
                String MaSV=stu.getMaSV();
                String Email=stu.getEmail();
                String Sdt=stu.getSDT();
                String des=Encrypt(MaSV,MaSV);
                System.out.println(des);
                stu.setMaSV(Encrypt(MaSV,MaSV));
                stu.setSDT(Encrypt(Sdt,MaSV));
                stu.setEmail(Encrypt(Email,MaSV));
                DataSQL data=(DataSQL) OIS.readObject();
                try {
                    SinhvienDao dao=new SinhvienDao();
                    Boolean tg=dao.Update(stu,data);
                    OTT.writeBoolean(tg);
                    }
                catch (Exception e) {
                }
            }
            else
            if (flag==3)
            {
                SinhVien stu = (SinhVien) OIS.readObject();
                Thread.sleep(15);
                String MaSV=stu.getMaSV();
                String Hoten=stu.getHoTen();
                String Email=stu.getEmail();
                String Sdt=stu.getSDT();
                String des=Encrypt(MaSV,MaSV);
                System.out.println(des);
                stu.setMaSV(Encrypt(MaSV,MaSV));
                stu.setSDT(Encrypt(Sdt,MaSV));
                stu.setEmail(Encrypt(Email,MaSV));
                DataSQL data=(DataSQL) OIS.readObject();
                try {
                    SinhvienDao dao=new SinhvienDao();
                    Boolean tg=dao.Delete(stu,data);
                    OTT.writeBoolean(tg);
                    }
                catch (Exception e) {
                }
            }
            else  
            if (flag==4)
            {
                DiemThi stu = (DiemThi) OIS.readObject();
                Thread.sleep(15);
                String MaSV=stu.getMaSV();
                stu.setMaSV(Encrypt(MaSV,MaSV));
                DataSQL data=(DataSQL) OIS.readObject();
                try {
                    DiemThiDao dao=new DiemThiDao();
                    System.out.println(stu.getMaSV());
                    float tk=(stu.getDiemCK()+stu.getDiemGK())/2;
                    System.out.println(stu.getDiemGK());
                    System.out.println(stu.getDiemCK());
                    stu.setDiemTK(tk);
                    System.out.println(stu.getDiemTK());
                    System.out.println(data.getUsername());
                    boolean checkkk=dao.Insert(stu,data);
                    System.out.println(checkkk);
                    OTT.writeBoolean(checkkk);
                    }
                catch (Exception e) {
                }
            }
            else  
            if (flag==5)
            {
                DiemThi stu = (DiemThi) OIS.readObject();
                Thread.sleep(15);
                String MaSV=stu.getMaSV();
                stu.setMaSV(Encrypt(MaSV,MaSV));
                DataSQL data=(DataSQL) OIS.readObject();
                try {
                    DiemThiDao dao=new DiemThiDao();
                    float tk=(stu.getDiemCK()+stu.getDiemGK())/2;
                    stu.setDiemTK(tk);
                    boolean checkk=dao.Update(stu,data);
                    OTT.writeBoolean(checkk);
                    }
                catch (Exception e) {
                }
            }
            else 
                if(flag==6) {
                    Thread.sleep(120);
                    SinhvienDao sinhvienDao = new SinhvienDao();
                    MonHocDao monhocDao = new MonHocDao();
                    DiemThiDao diemDao = new DiemThiDao();
                    DataSQL  dt= (DataSQL) OIS.readObject() ;
                    Thread.sleep(120);
                    String receiverParams= DTT.readUTF();
                    System.out.println(receiverParams);
                    try {
                        SinhVien SV= sinhvienDao.getSinhVienbByContainsQuery(receiverParams, dt) ;
                        if (SV!=null) {
                        OOS.writeBoolean(true) ;
                        List<MonHoc> subject =  monhocDao.FindAllMH(dt) ;
                        List<DiemThi> diemthi = new ArrayList<DiemThi>() ;
                        for(MonHoc sub : subject) {
                            DiemThi score = diemDao.FindDiem(sub.getMaMH(), SV.getMaSV(), dt) ;
                            if (score!=null) {
                            diemthi.add(score) ;
                            }
                        }
                        OOS.writeObject(SV);
                        OOS.writeObject(diemthi) ;
                        }
                        else{
                            
                            OOS.writeBoolean(false); ;
                            SV= null;
                            OOS.writeObject(SV);
                        
                        }
                    }
                    catch(Exception e){
                    }
                }
                else  
            if (flag==7)
            {
               
                String maSV =DTT.readUTF();  
                DataSQL datasql=(DataSQL) OIS.readObject();
                System.out.println(maSV);
                Thread.sleep(15);
                try {
                   DiemThiDao diemThiDao = new DiemThiDao();
                     List <DiemThi> list= diemThiDao.FinMSV(maSV,datasql);
                      int dem =0;
                      float tong = 0;
                      for(DiemThi sb :list){
                          dem ++;
                          tong +=  sb.getDiemTK();
                         
                          System.out.println( sb.getDiemTK());
                      }
                      float diemTB = tong / dem;
                      
                      DiemThi diemThi = new DiemThi();
                        if(diemTB >= 8.0 ){ diemThi.setXepLoai("Giỏi");}
                      else 
                        if(diemTB >= 6.5){ diemThi.setXepLoai("Khá"); }
                      else 
                        if(diemTB >= 5.0){ diemThi.setXepLoai("Trung Bình");}
                      else 
                        if(diemTB >= 3.5){ diemThi.setXepLoai("Yếu"); }
                      else { diemThi.setXepLoai("Kém"); } 
                     
                      System.out.println(diemThi.getXepLoai());  
                      OTT.writeUTF(diemThi.getXepLoai());
                      
                    }catch (Exception e) 
                    {
                    }
            }
            else  
                if (flag==0)
                {
                    OTT.writeBoolean(true);
                }
        }
    }
}
