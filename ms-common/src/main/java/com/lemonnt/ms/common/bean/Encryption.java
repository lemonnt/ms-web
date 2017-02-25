/**
 * @author : Gavin Li
 * @date   : Oct 27, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.Encryption
 */
package com.lemonnt.ms.common.bean;

import java.security.MessageDigest;
import com.lemonnt.ms.common.exception.RootException;

/**
 * @author : Gavin Li
 * @date   : Oct 27, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.Encryption
 */
public class Encryption {
    
    private String salt;
    private EncryptionType encryptionType = EncryptionType.MD5;
    
    /**
     * 
     */
    public Encryption() {
        
    }
    
    public Encryption(EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }
    
    public Encryption(EncryptionType encryptionType,String salt) {
        this.encryptionType = encryptionType;
        this.salt = salt;
    }
    
    
    public String encryptionLow(String value){
        return md5(value);
    }
    
    public String encryptionNormal(String value,String salt){
        salt = null == salt ? String.valueOf(System.currentTimeMillis()):salt;
        return md5(value+md5(salt));
    }
    
    private String md5(String value) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(encryptionType.getName());
            char[] charArray = value.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++)
                byteArray[i] = (byte) charArray[i];
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            throw new RootException("Failed to create md5 []");
        }
    }
    
    
    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the encryptionType
     */
    public EncryptionType getEncryptionType() {
        return encryptionType;
    }
    /**
     * @param encryptionType the encryptionType to set
     */
    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }
    
    

}
