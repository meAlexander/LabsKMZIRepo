package lab10.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class GreetingClient {
	public static void main(String[] args) {
		try {
			int port = 8088;

			// Declare p, g, and Key of client
			int q = 23;
			int a = 5;
			int privateKey = 6;
			int Kab, serverPublicKey;

			Socket client = new Socket("localhost", port);

			// Sends the data to client
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF(Integer.toString(q)); // Sending q

			out.writeUTF(Integer.toString(a)); // Sending a

			int Ya = (int) ((Math.pow(a, privateKey)) % q); // calculation of Ya
			out.writeUTF(Integer.toString(Ya)); // Sending Ya

			// Client's Private Key
			System.out.println("Client : Private Key = " + privateKey);

			// Accepts the data
			DataInputStream in = new DataInputStream(client.getInputStream());

			serverPublicKey = Integer.parseInt(in.readUTF());
			System.out.println("From Server : Public Key = " + serverPublicKey);

			Kab = (int) ((Math.pow(serverPublicKey, privateKey)) % q); // calculation of Kab

			System.out.println("Secret Key Kab = " + Kab);

			String P = decrypt(in.readUTF(), Integer.toString(Kab));
			System.out.println("Decrypted message from server: " + P);
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String decrypt(String strToDecrypt, String Kab) {
		SecretKeySpec secretKey;
		byte[] key;
		MessageDigest sha = null;
		try {
			key = Kab.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
}