package lab4;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		System.out.println("1�� ������:");
		encryptedWithKeyNumbers("����� ������ ������� ���� �� 01����", "41532"); 						// 1�� ������
		
		System.out.println("2�� � 5�� ������(� ���� 8 ������� �����):");
		encryptedWithKeyNumbers("�����������_������_��_200000_��", "71683524"); 						// 2�� ������ � 5�� ������
		
		System.out.println("3�� ������:");
		encryptedWithKeyNumbers("���_����_�����_O_�����_O������__", "2143"); 							// 3�� ������
		
		System.out.println("4�� ������:");
		String key =  decryptedKey("�������");											
		encryptedWithKeyWord("����� ������ ������� ���� �� 01����", key);								// 4�� ������
		
		// ����� �� ������� ����������� �� ������ ���� ������ ���� �� �����,			//6�� ������
		// ���������� ���� ���� ����, ���� ��������� ��� ����� �� ������ �� �����
		// � ��������. ����: ���� ����� - 53421, ������� � �� ������ ����
		// ������ �������� � � ��������� ���� ����� 1, ���� ���� � � ���� ����� 2,
		// ���� ���� � � �.�. �� ���� ����� ��������� � �����, �� ���� ������.	
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
		char[] chars = { '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', 
				'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', 
				'�', '�', '�', '�', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_' };
		int lengthOfKey = key.length();
		int count = 0;
		int[] numberKey = new int[lengthOfKey];
		
		//����������� �����(key) � ��������� � numberKey �����, ������ ���� ��� ����� �� ����� �����, ��� ����� � �.�. � {�} ���� � (1) �(2) �(3)
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