package com.kgkt.tust.member.util;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zgq
 * @description 处理RSA加密的工具类
 */
public final class RsaUtil {

  private static final String RSA_ALGORITHM = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

  private static final Logger logger = LoggerFactory.getLogger(RsaUtil.class);

  private RsaUtil() {}

  /**
   * @param publicKeyText 公钥
   * @param text 明文
   * @return String
   * @description 公钥加密
   */
  public static String encryptByPublicKey(String publicKeyText, String text) {
    X509EncodedKeySpec x509EncodedKeySpec2 =
        new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
    KeyFactory keyFactory = null;
    byte[] result = null;
    try {
      keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
      Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      result = cipher.doFinal(text.getBytes());
    } catch (Exception e) {
      logger.error("\u001B[31m" + "error:" + e.getClass(), e.getCause());
      OutUtil.getInfo(e);
    }
    return Base64.encodeBase64String(result);
  }

  /**
   * @param privateKeyText :私钥
   * @param text :公钥加密后的密文
   * @return String
   * @description 私钥解密
   */
  public static String decryptByPrivateKey(String privateKeyText, String text) {
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 =
        new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
    KeyFactory keyFactory = null;
    try {
      keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
      Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] result = cipher.doFinal(Base64.decodeBase64(text));
      return new String(result, StandardCharsets.UTF_8);
    } catch (Exception e) {
      logger.error("\u001B[31m" + "error:" + e.getClass(), e.getCause());
      OutUtil.getInfo(e);
      return "";
    }
  }

  /** @description 构建 RSA密钥对 */
  public static RSAKeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

    keyPairGenerator.initialize(2048);

    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

    String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());

    String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());

    return new RSAKeyPair(publicKeyString, privateKeyString);
  }

  /**
   * @author zgq
   * @description RSA密钥对对象
   */
  public static class RSAKeyPair {

    private final String publicKey;

    private final String privateKey;

    public RSAKeyPair(String publicKey, String privateKey) {
      this.publicKey = publicKey;
      this.privateKey = privateKey;
    }

    public String getPublicKey() {
      return publicKey;
    }

    public String getPrivateKey() {
      return privateKey;
    }
  }
}
