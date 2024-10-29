package br.com.cotiinformatica.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class CryptoHelper {

	/*
	 * Método para criptografar um valor no padrão SHA-256
	 */
	public static String SHA256Encrypt(String value) {
		try {
			// Obter uma instância do algoritmo SHA-256
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Gerar o hash a partir da string de entrada
			byte[] encodedHash = digest.digest(value.getBytes(StandardCharsets.UTF_8));

			// Converter o byte[] em uma string hexadecimal
			return HexFormat.of().formatHex(encodedHash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Erro ao criptografar usando SHA-256", e);
		}
	}
}
