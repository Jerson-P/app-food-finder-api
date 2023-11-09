package com.foodfinder.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Servicio que permite encriptar o desencriptar las contraseñas
 *
 *
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class EncryptDecrypt {

	private static String secretKey = "";
	
	//@Value("${seguridad.llave}")
	private String llave;
	
	/**
	 * Metodo de encriptacion de la contraseña acesso.
	 * 
	 * @param texto
	 * @return Contraseña encriptada.
	 */
	
	public String encriptar(String texto) {
		log.info("--------------------------ENTRADA TEXTO------------------------  " + texto);
		secretKey = this.llave;// llave para encriptar datos
		String base64EncryptedString = "";
		log.info("--------------------------ENTRADA llave------------------------  " + llave);

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception e) {
			log.error("Error encriptar: " + e.getMessage());
			e.printStackTrace();
		}
		return base64EncryptedString;
	}
	
	/**
	 * Metodo de desencriptacion de contraseña.
	 * 
	 * @param textoEncriptado
	 * @return contraseña desencriptada.
	 * @throws Exception
	 */
	
	public String desencriptar(String textoEncriptado) {
		log.info("Ingreso desencriptar:  " );
		secretKey = this.llave; // llave para desenciptar datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception e) {
			log.error("Error desencriptar: " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Salida desencriptar:  " );
		return base64EncryptedString;
	}
	
	public static void main(String[] args) throws Exception {
		EncryptDecrypt ed = new EncryptDecrypt();
		System.out.println("clave " + ed.encriptar("welcome1"));
	}

}
