package com.mobicom.security;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

@Service
public class Encryption {

  private static final String SECRET_KEY = "qwertyuiopasdfgh";

  public String encrypt(Integer id) throws Exception {
    SecretKeySpec secretKeySpec = new SecretKeySpec(
      SECRET_KEY.getBytes(),
      "AES"
    );
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
    byte[] encryptedBytes = cipher.doFinal(String.valueOf(id).getBytes());
    return Base64.getUrlEncoder().encodeToString(encryptedBytes);
  }

  public Integer decrypt(String encryptedId) throws Exception {
    SecretKeySpec secretKeySpec = new SecretKeySpec(
      SECRET_KEY.getBytes(),
      "AES"
    );
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
    byte[] decryptedBytes = cipher.doFinal(
      Base64.getUrlDecoder().decode(encryptedId)
    );
    String decryptedId = new String(decryptedBytes);
    return Integer.parseInt(decryptedId);
  }
}
