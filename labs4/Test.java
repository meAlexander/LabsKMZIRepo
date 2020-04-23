package labs4;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		System.out.println("1�� ������:");
		encrypted("����� ������ ������� ���� �� 01����", "41532"); 						// 1�� ������
		System.out.println("2�� � 5�� ������(� ���� 8 ������� �����):");
		encrypted("�����������_������_��_200000_��", "71683524"); 						// 2�� ������ � 5�� ������
		System.out.println("3�� ������:");
		encrypted("���_����_�����_O_�����_O������__", "2143"); 							// 3�� ������
		System.out.println("4�� ������:");
		decrypted("����� ������ ������� ���� �� 01����", "�������"); 					// 4�� ������

		// ����� �� ������� ����������� �� ������ ���� ������ ���� �� �����,
		// ���������� ���� ���� ����, ���� ��������� ��� ����� �� ������ �� �����
		// � ��������. ����: ���� ����� - 53421, ������� � �� ������ ����
		// ������ �������� � � ��������� ���� ����� 1, ���� ���� � � ���� ����� 2,
		// ���� ���� � � �.�. �� ���� ����� ��������� � �����, �� ���� ������. 			//6�� ������
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
		char[] chars = { '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', 
				'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', 
				'�', '�', '�', '�', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_' };
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