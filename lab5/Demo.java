package lab5;

import java.util.Arrays;

public class Demo {
	public static void main(String[] args) {
		System.out.println("1ва задача:");
		String key1 = decryptedKey("КРИСТАЛ"); 																
		encryptedValue("БАНКА БИОХИМ ОБЯВЯВА ТЪРГ НА 01МАРТ", key1);										// 1ва ЗАДАЧА (6та ЗАДАЧА)

		System.out.println("2ра задача:");
		String key2 = decryptedKey("ПРОЛЕТ"); 																
		encryptedValue("НЕКА ДА СИ ПРЕДСТАВИМ ЧЕ ИМАМЕ КОМПЮТЪР С НОВ ХАРДДИСК", key2);						// 2ра ЗАДАЧА  (6та ЗАДАЧА)

		System.out.println("3та задача:");
		String key3 = decryptedKey("ПЕЛИКАН");
		encryptedValue("СЪЗДАТЕЛЯТ НА БИТКОЙНА Е САТОШИ НАКАМОТО", key3); 									// 3та ЗАДАЧА  (6та ЗАДАЧА)

		System.out.println("4та задача:");
		String key4 = decryptedKey("ВИРТУАЛЕН");
		encryptedValue("БИЛ ГЕЙТС СМЯТА ЧЕ КРИПТОВАЛУТИТЕ ПРИЧИЯВАТ СМЪРТ", key4); 							// 4та ЗАДАЧА  (6та ЗАДАЧА)

		System.out.println("5та задача:");
		String key5 = decryptedKey("КРИСТАЛ");													
		decryptedValue("__АНРНХВР1БИБТ_БО_АТАОЯЪ0КИЯГМАМВ_А", key5);										// 5та ЗАДАЧА
	}

	public static String decryptedKey(String key) {
		char[] chars = { 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
				'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ь', 'Ю', 'Я', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				'_' };
		int lengthOfKey = key.length();
		int count = 0;
		int[] numberKey = new int[lengthOfKey];

		// Проверяваме ключа(key) и записваме в numberKey цифра, според това коя буква
		// се среща първа в {М} Така А (1) И(2) К(3) КРИСТАЛ
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

	public static void encryptedValue(String text, String key) {
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

	public static void decryptedValue(String text, String key) {
		StringBuilder builder = new StringBuilder();
		int lengthOfKey = key.length();
		int lengthOfText = text.length();
		
		while (lengthOfText % lengthOfKey != 0) {
			text = text.concat("_");
			lengthOfText = text.length();
		}
		// encrypt text
		for (int i = 0; i < lengthOfText / lengthOfKey; i++) {
			for (int j = 0; j < lengthOfKey; j++) {
				int a = Integer.parseInt(Character.toString(key.charAt(j)));
				String ch = Character.toString(text.charAt((a - 1) * (lengthOfText / lengthOfKey) + i));
				if (ch.equals(" ")) {
					ch = "_";
				}
				builder.append(ch);
			}
		}
		System.out.println(builder);
	}
}