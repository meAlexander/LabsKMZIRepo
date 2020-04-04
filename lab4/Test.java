package lab4;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		System.out.println("1ва задача:");
		encryptedWithKeyNumbers("БАНКА БИОХИМ ОБЯВЯВА ТЪРГ НА 01МАРТ", "41532"); 						// 1ва ЗАДАЧА
		
		System.out.println("2ра и 5та задача(с ключ 8 цифрено число):");
		encryptedWithKeyNumbers("ДЕПОЗИРАЙТЕ_СУМАТА_ОТ_200000_ЛВ", "71683524"); 						// 2ра ЗАДАЧА и 5та ЗАДАЧА
		
		System.out.println("3та задача:");
		encryptedWithKeyNumbers("АДЖ_ВИЕЕ_ШЕЛНС_O_ЕНМГО_OРТДУОН__", "2143"); 							// 3та ЗАДАЧА
		
		System.out.println("4та задача:");
		String key =  decryptedKey("КРИСТАЛ");											
		encryptedWithKeyWord("БАНКА БИОХИМ ОБЯВЯВА ТЪРГ НА 01МАРТ", key);								// 4та ЗАДАЧА
		
		// Можем да повишим надежността на метода като вместо ключ от числа,			//6та ЗАДАЧА
		// използваме като ключ дума, като определим коя буква се намира по наред
		// в азбуката. Напр: Ключ ТОПКА - 53421, буквата А се намира пред
		// всички останали и я записваме като номер 1, след това е К като номер 2,
		// след това О и т.н. По този начин шифрираме и ключа, не само текста.	
	}

	public static void encryptedWithKeyNumbers(String text, String key) {
		StringBuilder builder = new StringBuilder();
		int lengthOfText = text.length();
		int lengthOfKey = key.length();

		while (lengthOfText % lengthOfKey != 0) {
			text = text.concat("_");
			lengthOfText = text.length();
		}

		for (int i = 0; i < text.length(); i += lengthOfKey) {
			String part = text.substring(i, i + lengthOfKey);
			for (int j = 1; j <= lengthOfKey; j++) {
				int a = key.indexOf(Integer.toString(j));
				builder.append(part.charAt(a));
			}
		}
		System.out.println(builder);
	}

	public static String decryptedKey( String key) {
		char[] chars = { 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 
				'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 
				'Ъ', 'Ь', 'Ю', 'Я', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_' };
		int lengthOfKey = key.length();
		int count = 0;
		int[] numberKey = new int[lengthOfKey];
		
		//Проверяваме ключа(key) и записваме в numberKey цифра, според това коя буква се среща първа, коя втора и т.н. в {М} Така А (1) И(2) К(3)
		for (int j = 0; j < chars.length; j++) {
			for (int i = 0; i < lengthOfKey; i++) {
				if (chars[j] == key.charAt(i)) {
					numberKey[i] = ++count;
				}
			}
		}
		String strOfInts = Arrays.toString(numberKey).replaceAll("\\[|\\]|,|\\s", "");
		return strOfInts;
	}
	
	public static void encryptedWithKeyWord(String text, String key) {
		StringBuilder builder = new StringBuilder();
		int lengthOfText = text.length();
		int lengthOfKey = key.length();
		
		while (lengthOfText % lengthOfKey != 0) {
			text = text.concat("_");
			lengthOfText = text.length();
		}
		// encrypt text
		for (int i = 1; i <= lengthOfKey; i++) {
			int a = key.indexOf(Integer.toString(i));
			for (int j = a; j < lengthOfText; j += lengthOfKey) {
				String ch = Character.toString(text.charAt(j));
				if (ch.equals(" ")) {
					ch = "_";
				}
				builder.append(ch);
			}
		}
		System.out.println(builder);
	}
}