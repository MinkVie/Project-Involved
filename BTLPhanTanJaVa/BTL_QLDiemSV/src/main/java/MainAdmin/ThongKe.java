/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainAdmin;

import  entity.DataSQL;
import  entity.DiemThi;
import  entity.MonHoc;
import  entity.SinhVien;

import  Check.MessageHelper;
import  dao.DiemThiDao;
import  dao.MaHoaDes;
import  dao.MonHocDao;
import  dao.SinhvienDao;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author Yahiko
 */
public class ThongKe extends javax.swing.JPanel {
    String user,pass,port;
    private MainFrame parentform;
    private DefaultTableModel tblTableModell;
    DataSQL datasql=new DataSQL();
    String[] arr=new String[100];
    String[] arr1=new String[100];
    String[] arr2=new String[100];
    int i=0,i1=0;
    private List<DiemThi> listDiem = new ArrayList<>();
    
    /**
     * Creates new form QL_Diem
     */
    public ThongKe() {
        initComponents();
    }
    public ThongKe(DataSQL dt)
    {
        initComponents();
        datasql.setUsername(dt.getUsername());
        datasql.setPassword(dt.getPassword());
        datasql.setPort(dt.getPort());
        initTable();              
        LoadDataTableMaSV();
     //   initTable();
        itemMonHoc();
     
    }
    public void itemMonHoc(){
        cbb_mh.removeAllItems();
        try {
            MonHocDao SV = new MonHocDao();
            List<MonHoc> list = SV.FindAllMH(datasql);
            for (MonHoc cb : list){
               arr1[i1]=cb.getTenMH(); 
               arr2[i1]=cb.getMaMH();
               i1++;

               }             
         } catch (Exception e) {
         }
   
         for (int j = 0; j<i1; j++) cbb_mh.addItem(arr1[j]);
         
    }
    public void initTable(){
        tblTableModell = new DefaultTableModel();
        JScrollPane pane=new JScrollPane();
        tbl_thongke.add(pane,BorderLayout.CENTER);
        tblTableModell.setColumnIdentifiers(new String[]{"Mã Sinh Viên","Họ Và Tên",
            "Giới Tính","SĐT","Email","Môn Học","Giữa Kì","Cuối Kì","Tổng Kết"});
        tbl_thongke.setModel(tblTableModell);
        tbl_thongke.getTableHeader().setFont(new java.awt.Font("Times New Roman", 3, 14));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tbl_thongke.setDefaultRenderer(Object.class,rightRenderer);
    }
    
    public void intTableHocLuc(){
         tblTableModell = new DefaultTableModel();
        JScrollPane pane=new JScrollPane();
        tbl_thongke.add(pane,BorderLayout.CENTER);
        tblTableModell.setColumnIdentifiers(new String[]{"Mã Sinh Viên","Họ Và Tên",
            "Giới Tính","SĐT","Email","Xếp loại Học Lực"});
        tbl_thongke.setModel(tblTableModell);
        tbl_thongke.getTableHeader().setFont(new java.awt.Font("Times New Roman", 3, 14));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tbl_thongke.setDefaultRenderer(Object.class,rightRenderer);
    }
     public void intTableMaSV(){
         tblTableModell = new DefaultTableModel();
        JScrollPane pane=new JScrollPane();
        tbl_thongke.add(pane,BorderLayout.CENTER);
        tblTableModell.setColumnIdentifiers(new String[]{"Mã Sinh Viên","Họ Và Tên",
            "Giới Tính","SĐT","Email","Môn Học","Giữa Kì","Cuối Kì","Tổng Kết"});
        tbl_thongke.setModel(tblTableModell);
        tbl_thongke.getTableHeader().setFont(new java.awt.Font("Times New Roman", 3, 14));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tbl_thongke.setDefaultRenderer(Object.class,rightRenderer);
    }
    
   
 
    private void LoadDataTableMH(String MMH){
    try {
           DiemThiDao sinhvien=new DiemThiDao();
           List <DiemThi> list=sinhvien.FinMMH(MMH,datasql);
           tblTableModell.setRowCount(0);
           String gioitinh;
           for(DiemThi sb :list){
               String masv=MaHoaDes.Decrypt(sb.getMaSV(),sb.getMaSV());
               SinhvienDao sb1=new SinhvienDao();
               SinhVien sb2=sb1.FindSV(sb.getMaSV(),datasql);
               MonHocDao sb3=new MonHocDao();
               MonHoc sb4=sb3.FindMH(sb.getMaMH(), datasql);
               if (sb2.getGioiTinh()==1) {
                   gioitinh="Nam";
               }
               else 
                   gioitinh="Nữ";
               tblTableModell.addRow(new Object[]{
                   masv,sb2.getHoTen(),gioitinh,MaHoaDes.Decrypt(sb2.getSDT(),MMH),MaHoaDes.Decrypt(sb2.getEmail(),MMH),sb4.getTenMH(),sb.getDiemGK(),sb.getDiemCK(),sb.getDiemTK()});
           }
           tblTableModell.fireTableDataChanged();
        } catch (Exception e) {
        }
    }
    
     private void LoadDataTableMaSV(){
    try {
        DiemThiDao sinhvien=new DiemThiDao();
        List <DiemThi> list=sinhvien.FindAllSvASC(datasql);
        tblTableModell.setRowCount(0);
           String gioitinh;
           for(DiemThi sb :list){ 
               
               SinhvienDao sb1=new SinhvienDao();
               SinhVien sb2=sb1.FindSV(sb.getMaSV(),datasql);
               String Masv=MaHoaDes.Decrypt(sb2.getMaSV(), sb2.getMaSV());
               String Email=MaHoaDes.Decrypt(sb2.getEmail(),sb2.getMaSV());
               String Sdt=MaHoaDes.Decrypt(sb2.getSDT(),sb2.getMaSV());
               MonHocDao sb3=new MonHocDao();
               MonHoc sb4=sb3.FindMH(sb.getMaMH(), datasql);
               if (sb2.getGioiTinh()==1) {
                   gioitinh="Nam";
               }
               else 
                   gioitinh="Nữ";
               tblTableModell.addRow(new Object[]{
                   Masv,sb2.getHoTen(),gioitinh,Sdt,Email,sb4.getTenMH(),sb.getDiemGK(),sb.getDiemCK(),sb.getDiemTK()});
           }
           tblTableModell.fireTableDataChanged();
        } catch (Exception e) {
        }
    }
    
     private void LoadDataTableHocLuc(){
    try {
      
        DiemThiDao sinhvien=new DiemThiDao();
           List <DiemThi> list=sinhvien.FindAllSVDistinct(datasql);
           tblTableModell.setRowCount(0);
           String gioitinh;
           for(DiemThi sb :list){
                 String xeploai ="";
                    try {
                Socket client = new Socket("localhost" ,9999) ;
                DataInputStream DT=new DataInputStream(client.getInputStream());
                DataOutputStream OT=new DataOutputStream(client.getOutputStream());
                ObjectOutputStream OOS = new ObjectOutputStream(client.getOutputStream()) ;
                ObjectInputStream  IS = new ObjectInputStream(client.getInputStream()) ;
                String maSV = sb.getMaSV().trim();
                    OT.writeInt(7);
                    OT.writeUTF(maSV);
                    OOS.writeObject(datasql);
                     xeploai =String.valueOf(DT.readUTF()) ;
                }
                 catch (IOException ex) {
            ex.printStackTrace();
            }
               SinhvienDao sb1=new SinhvienDao();
               SinhVien sb2=sb1.FindSV(sb.getMaSV(),datasql);
             //  MonHocDao sb3=new MonHocDao();
            //   MonHoc sb4=sb3.FindMH(sb.getMaMH(), datasql);
               //gui du lieu
              String Masv=MaHoaDes.Decrypt(sb2.getMaSV(), sb2.getMaSV());
              String Email=MaHoaDes.Decrypt(sb2.getEmail(),sb2.getMaSV());
              String Sdt=MaHoaDes.Decrypt(sb2.getSDT(),sb2.getMaSV());
             if (sb2.getGioiTinh()==1) {
                  gioitinh="Nam";
             }
               else 
                 gioitinh="Nữ";
             
               tblTableModell.addRow(new Object[]{
                   Masv,sb2.getHoTen(),gioitinh,Sdt,Email,xeploai});
          }
           tblTableModell.fireTableDataChanged();
        } catch (Exception e) {
        }
    }
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thongke = new javax.swing.JTable();
        btn_MSSV = new javax.swing.JButton();
        btn_MH = new javax.swing.JButton();
        btn_hocluc = new javax.swing.JButton();
        cbb_mh = new javax.swing.JComboBox<>();

        tbl_thongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_thongke);

        btn_MSSV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_MSSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon/statistic_32.png"))); // NOI18N
        btn_MSSV.setText("Thống Kê Theo MSSV");
        btn_MSSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MSSVActionPerformed(evt);
            }
        });

        btn_MH.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_MH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon/qldiem.png"))); // NOI18N
        btn_MH.setText("Thống Kê Theo Môn Học");
        btn_MH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MHActionPerformed(evt);
            }
        });

        btn_hocluc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_hocluc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon/Stats32.png"))); // NOI18N
        btn_hocluc.setText("Xếp loại học lực");
        btn_hocluc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hoclucActionPerformed(evt);
            }
        });

        cbb_mh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbb_mh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(75)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jScrollPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btn_MSSV)
        					.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
        					.addComponent(cbb_mh, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(btn_MH)
        					.addGap(33)
        					.addComponent(btn_hocluc)))
        			.addGap(85))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(59)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btn_MSSV)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(btn_hocluc)
        					.addComponent(btn_MH, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        					.addComponent(cbb_mh, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
        			.addGap(39)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_MHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MHActionPerformed
        // TODO add your handling code her
         try {
             String strMonHoc = arr2[cbb_mh.getSelectedIndex()];
             initTable();
             LoadDataTableMH(strMonHoc);
        } catch (Exception e) {
        }
        
      
        
         
          
    }//GEN-LAST:event_btn_MHActionPerformed

    private void btn_hoclucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hoclucActionPerformed
        // TODO add your handling code here:
        try {
             intTableHocLuc();
             LoadDataTableHocLuc();
             
        } catch (Exception e) {
        }
       
    }//GEN-LAST:event_btn_hoclucActionPerformed

    private void btn_MSSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MSSVActionPerformed
        // TODO add your handling code here:
        
          try {
            
               initTable();              
          LoadDataTableMaSV();
          }
          catch (Exception e) {
        }
        
    }//GEN-LAST:event_btn_MSSVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_MH;
    private javax.swing.JButton btn_MSSV;
    private javax.swing.JButton btn_hocluc;
    private javax.swing.JComboBox<String> cbb_mh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_thongke;
    // End of variables declaration//GEN-END:variables
}
