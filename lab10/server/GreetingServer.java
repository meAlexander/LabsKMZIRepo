package lab10.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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

public class GreetingServer {
	public static void main(String[] args) throws IOException {
		try {
			int port = 8088;
			int privateKey = 15;

			// Client q, a, and publicKey
			int q, a, clientPublicKey, Xa, Kab;

			ServerSocket serverSocket = new ServerSocket(port);
			Socket server = serverSocket.accept();

			// Server's Private Key
			System.out.println("Server : Private Key = " + privateKey);

			// Accepts the data from client
			DataInputStream in = new DataInputStream(server.getInputStream());

			q = Integer.parseInt(in.readUTF()); // to accept q
			System.out.println("From Client : Q = " + q);

			a = Integer.parseInt(in.readUTF()); // to accept a
			System.out.println("From Client : A = " + a);

			clientPublicKey = Integer.parseInt(in.readUTF()); // to accept clientPublicKey
			System.out.println("From Client : Public Key = " + clientPublicKey);

			Xa = (int) ((Math.pow(a, privateKey)) % q); // calculation of Xa

			// Sends data to client
			OutputStream outToclient = server.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToclient);

			out.writeUTF(Integer.toString(Xa)); // Sending B

			Kab = (int) ((Math.pow(clientPublicKey, privateKey)) % q); // calculation of Bdash

			System.out.println("Secret Key Kab = " + Kab);

			String P = encrypt(Integer.toString(Kab));
			System.out.println("Encrypted message: " + P);
			out.writeUTF(P);
			
			server.close();
			serverSocket.close();
		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}

	public static String encrypt(String Kab) {
		String strToEncrypt = "Hello from Server!";
		SecretKeySpec secretKey;
		byte[] key;
		MessageDigest sha = null;
		try {
			key = Kab.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
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