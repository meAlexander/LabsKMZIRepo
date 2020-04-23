package lab5;

import java.util.Arrays;

public class Demo {
	public static void main(String[] args) {
		System.out.println("1�� ������:");
		String key1 = decryptedKey("�������"); 																
		encryptedValue("����� ������ ������� ���� �� 01����", key1);										// 1�� ������ (6�� ������)

		System.out.println("2�� ������:");
		String key2 = decryptedKey("������"); 																
		encryptedValue("���� �� �� ���������� �� ����� �������� � ��� ��������", key2);						// 2�� ������  (6�� ������)

		System.out.println("3�� ������:");
		String key3 = decryptedKey("�������");
		encryptedValue("���������� �� �������� � ������ ��������", key3); 									// 3�� ������  (6�� ������)

		System.out.println("4�� ������:");
		String key4 = decryptedKey("���������");
		encryptedValue("��� ����� ����� �� �������������� ��������� �����", key4); 							// 4�� ������  (6�� ������)

		System.out.println("5�� ������:");
		String key5 = decryptedKey("�������");													
		decryptedValue("__�������1����_��_������0��������_�", key5);										// 5�� ������
	}

	public static String decryptedKey(String key) {
		char[] chars = { '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
				'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				'_' };
		int lengthOfKey = key.length();
		int count = 0;
		int[] numberKey = new int[lengthOfKey];

		// ����������� �����(key) � ��������� � numberKey �����, ������ ���� ��� �����
		// �� ����� ����� � {�} ���� � (1) �(2) �(3) �������
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