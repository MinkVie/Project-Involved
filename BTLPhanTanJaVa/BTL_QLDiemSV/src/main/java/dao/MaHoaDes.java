/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class MaHoaDes {
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
    public static String Decrypt(String value,String Key) throws NoSuchAlgorithmException, NoSuchPaddingException, 
           InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
    try {
			String encryptText = value;
			String key = "12345678";
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptText));
			return (new String(decrypted));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    return null;
   }
}
