package lab12;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.Cipher;

public class RsaExample {	
	public static void main(String argv[]) throws Exception {
		// First generate a public/private key pair
		KeyPair pair = generateKeyPair();
		// KeyPair pair = getKeyPairFromKeyStore();

		// Our secret message
		String message = "Да се изпрати справка в срок от три дни по документ с No А 64.4040.01-01Е13; Е32.2121.05-06 Б22; Ц14.5553.03-02 К41.";
		
		// Encrypt the message
		String cipherText = encrypt(message, pair.getPublic());
		System.out.println(pair.getPublic());
		
		// Now decrypt it
		String decipheredMessage = decrypt(cipherText, pair.getPrivate());
		System.out.println(pair.getPrivate());
		
		System.out.println(decipheredMessage);

		// Let's sign our message
		String signature = sign("KMZI", pair.getPrivate());

		// Let's check the signature
		boolean isCorrect = verify("KMZI", signature, pair.getPublic());
		System.out.println("Signature correct: " + isCorrect);
	}

	public static KeyPair generateKeyPair() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048, new SecureRandom());
		KeyPair pair = generator.generateKeyPair();

		return pair;
	}

	public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

		byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF8"));

		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(cipherText);

		Cipher decriptCipher = Cipher.getInstance("RSA");
		decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

		return new String(decriptCipher.doFinal(bytes), "UTF8");
	}

	public static String sign(String plainText, PrivateKey privateKey) throws Exception {
		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		privateSignature.initSign(privateKey);
		privateSignature.update(plainText.getBytes("UTF8"));

		byte[] signature = privateSignature.sign();

		return Base64.getEncoder().encodeToString(signature);
	}

	public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
		Signature publicSignature = Signature.getInstance("SHA256withRSA");
		publicSignature.initVerify(publicKey);
		publicSignature.update(plainText.getBytes("UTF8"));

		byte[] signatureBytes = Base64.getDecoder().decode(signature);

		return publicSignature.verify(signatureBytes);
	}
}