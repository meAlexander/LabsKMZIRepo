package labs4;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		System.out.println("1ва задача:");
		encrypted("БАНКА БИОХИМ ОБЯВЯВА ТЪРГ НА 01МАРТ", "41532"); 						// 1ва ЗАДАЧА
		System.out.println("2ра и 5та задача(с ключ 8 цифрено число):");
		encrypted("ДЕПОЗИРАЙТЕ_СУМАТА_ОТ_200000_ЛВ", "71683524"); 						// 2ра ЗАДАЧА и 5та ЗАДАЧА
		System.out.println("3та задача:");
		encrypted("АДЖ_ВИЕЕ_ШЕЛНС_O_ЕНМГО_OРТДУОН__", "2143"); 							// 3та ЗАДАЧА
		System.out.println("4та задача:");
		decrypted("БАНКА БИОХИМ ОБЯВЯВА ТЪРГ НА 01МАРТ", "КРИСТАЛ"); 					// 4та ЗАДАЧА

		// Можем да повишим надежността на метода като вместо ключ от числа,
		// използваме като ключ дума, като определим коя буква се намира по наред
		// в азбуката. Напр: Ключ ТОПКА - 53421, буквата А се намира пред
		// всички останали и я записваме като номер 1, след това е К като номер 2,
		// след това О и т.н. По този начин шифрираме и ключа, не само текста. 			//6та ЗАДАЧА
	}

	public static void encrypted(String text, String key) {
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

	public static void decrypted(String text, String key) {
		char[] chars = { 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 
				'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 
				'Ъ', 'Ь', 'Ю', 'Я', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_' };
		int lengthOfKey = key.length();
		int count = 0;
		int[] numberKey = new int[lengthOfKey];

		for (int j = 0; j < chars.length; j++) {
			for (int i = 0; i < lengthOfKey; i++) {
				if (chars[j] == key.charAt(i)) {
					numberKey[i] = ++count;
				}
			}
		}
		String strOfInts = Arrays.toString(numberKey).replaceAll("\\[|\\]|,|\\s", "");
		encrypted(text, strOfInts);
	}
}