/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Yahiko
 */
public class DiemThi    implements Serializable{
    float diemGK;
    float diemCK;
    float diemTK;
    String maMH;
    String maSV;
    float diemTB;
    String xepLoai;

    public float getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }
    public DiemThi() {
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public float getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }

    public float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }

    public float getDiemTK() {
        return diemTK;
    }

    public void setDiemTK(float diemTK) {
        this.diemTK = diemTK;
    }

    public DiemThi(String maMH, String maSV,float diemGK, float diemCK, float diemTK,float diemTB, String xepLoai) {
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemTK = diemTK;
        this.maMH = maMH;
        this.maSV = maSV;
        this.diemTB = diemTB;
        this.xepLoai = xepLoai;
    }
    
}
