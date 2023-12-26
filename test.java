import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.*;

public class test {

        static String[][] SBOX = {
                        { "63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab",
                                        "76" },
                        { "ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72",
                                        "c0" },
                        { "b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31",
                                        "15" },
                        { "04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2",
                                        "75" },
                        { "09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f",
                                        "84" },
                        { "53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58",
                                        "cf" },
                        { "d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f",
                                        "a8" },
                        { "51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3",
                                        "d2" },
                        { "cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19",
                                        "73" },
                        { "60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b",
                                        "db" },
                        { "e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4",
                                        "79" },
                        { "e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae",
                                        "08" },
                        { "ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b",
                                        "8a" },
                        { "70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d",
                                        "9e" },
                        { "e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28",
                                        "df" },
                        { "8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb",
                                        "16" }
        };

        static String[][] inverseSBox = {
                        { "52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40", "A3", "9E", "81", "F3", "D7",
                                        "FB" },
                        { "7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E", "43", "44", "C4", "DE", "E9",
                                        "CB" },
                        { "54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C", "95", "0B", "42", "FA", "C3",
                                        "4E" },
                        { "08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B", "A2", "49", "6D", "8B", "D1",
                                        "25" },
                        { "72", "F8", "F6", "64", "86", "68", "98", "16", "D4", "A4", "5C", "CC", "5D", "65", "B6",
                                        "92" },
                        { "6C", "70", "48", "50", "FD", "ED", "B9", "DA", "5E", "15", "46", "57", "A7", "8D", "9D",
                                        "84" },
                        { "90", "D8", "AB", "00", "8C", "BC", "D3", "0A", "F7", "E4", "58", "05", "B8", "B3", "45",
                                        "06" },
                        { "D0", "2C", "1E", "8F", "CA", "3F", "0F", "02", "C1", "AF", "BD", "03", "01", "13", "8A",
                                        "6B" },
                        { "3A", "91", "11", "41", "4F", "67", "DC", "EA", "97", "F2", "CF", "CE", "F0", "B4", "E6",
                                        "73" },
                        { "96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9", "37", "E8", "1C", "75", "DF",
                                        "6E" },
                        { "47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7", "62", "0E", "AA", "18", "BE",
                                        "1B" },
                        { "FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB", "C0", "FE", "78", "CD", "5A",
                                        "F4" },
                        { "1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12", "10", "59", "27", "80", "EC",
                                        "5F" },
                        { "60", "51", "7F", "A9", "19", "B5", "4A", "0D", "2D", "E5", "7A", "9F", "93", "C9", "9C",
                                        "EF" },
                        { "A0", "E0", "3B", "4D", "AE", "2A", "F5", "B0", "C8", "EB", "BB", "3C", "83", "53", "99",
                                        "61" },
                        { "17", "2B", "04", "7E", "BA", "77", "D6", "26", "E1", "69", "14", "63", "55", "21", "0C",
                                        "7D" }
        };

        static String[][] Mixcolumn = {
                        { "02", "03", "01", "01" },
                        { "01", "02", "03", "01" },
                        { "01", "01", "02", "03" },
                        { "03", "01", "01", "02" }
        };

        static String[][] Rcon = {
                        { "01", "00", "00", "00" },
                        { "02", "00", "00", "00" },
                        { "04", "00", "00", "00" },
                        { "08", "00", "00", "00" },
                        { "10", "00", "00", "00" },
                        { "20", "00", "00", "00" },
                        { "40", "00", "00", "00" },
                        { "80", "00", "00", "00" },
                        { "1b", "00", "00", "00" },
                        { "36", "00", "00", "00" }
        };

        static String[][] InvMixColumn = {
                        { "0e", "0b", "0d", "09" },
                        { "09", "0e", "0b", "0d" },
                        { "0d", "09", "0e", "0b" },
                        { "0b", "0d", "09", "0e" }
        };

        static List<String[][]> matrixList = new ArrayList<>();
        static List<String[][]> keylist = new ArrayList<>(11);
        static List<String[][]> decryptlist = new ArrayList<>();

        static void printMatrix(String[][] matrix) {
                for (int i = 0; i < matrix.length; i++) {
                        for (int j = 0; j < matrix[0].length; j++) {
                                System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println();
                }
        }

        static String[][] sbox_sub(String[][] a) {
                String[][] sboxsub_hex = new String[4][4];

                for (int i = 0; i < a.length; i++) {
                        for (int j = 0; j < a[0].length; j++) {
                                int a_int = Integer.parseInt(a[i][j], 16);
                                int row = a_int / 0x10;
                                int col = a_int % 0x10;
                                sboxsub_hex[i][j] = SBOX[row][col];
                        }
                }
                return sboxsub_hex;
        }

        static String[][] shift_rows(String[][] a) {
                for (int i = 1; i < a.length; i++) {
                        for (int k = 0; k < i; k++) {
                                String temp = a[i][0];
                                for (int j = 0; j < a[0].length - 1; j++) {
                                        a[i][j] = a[i][j + 1];
                                }
                                a[i][a[0].length - 1] = temp;
                        }
                }
                return a;
        }

        static String[][] mix_column(String[][] a) {
                String[][] b = new String[4][4];

                for (int j = 0; j < a[0].length; j++) {
                        for (int i = 0; i < a.length; i++) {
                                int[] product = new int[4];
                                for (int k = 0; k < a.length; k++) {
                                        int aVal = Integer.parseInt(a[k][j], 16);
                                        int mixVal = Integer.parseInt(Mixcolumn[i][k], 16);
                                        product[k] = multiplyInGF(aVal, mixVal);
                                }
                                int result = product[0] ^ product[1] ^ product[2] ^ product[3];
                                b[i][j] = String.format("%02x", result);
                        }
                }

                return b;
        }

        static int multiplyInGF(int a, int b) {
                int p = 0;
                int hiBitSet;
                for (int i = 0; i < 8; i++) {
                        if ((a & 0x80) != 0) {
                                hiBitSet = 1;
                        } else {
                                hiBitSet = 0;
                        }

                        if ((b & 1) != 0) {
                                p ^= a;
                        }

                        a <<= 1;
                        if (hiBitSet == 1) {
                                a ^= 0x1b;
                        }
                        b >>= 1;
                }
                return p & 0xFF;
        }

        static String[][] keyexpansion(String[][] a, int round) {
                String[] g_input = new String[a[0].length];

                String[][] nextround_key = new String[4][4];

                for (int l = 0; l < nextround_key[0].length; l++) {
                        g_input[l] = a[l][nextround_key[0].length - 1];
                }

                String[] g_output = g_function(g_input, round);

                for (int m = 0; m < nextround_key[0].length; m++) {
                        nextround_key[m][0] = String.format("%02x",
                                        Integer.parseInt(a[m][0], 16) ^ Integer.parseInt(g_output[m], 16));
                }

                for (int k = 1; k < nextround_key[0].length; k++) {
                        for (int j = 0; j < nextround_key.length; j++) {
                                nextround_key[j][k] = String.format("%02x", Integer.parseInt(a[j][k], 16)
                                                ^ Integer.parseInt(nextround_key[j][k - 1], 16));
                        }
                }

                return nextround_key;
        }

        static String[] g_function(String[] w3, int round) {
                String[] rot = new String[w3.length];
                String[] sub = new String[w3.length];
                String[] result = new String[w3.length];

                for (int i = 0; i < rot.length; i++) {
                        if (i != rot.length - 1) {
                                rot[i] = w3[i + 1];
                        } else {
                                rot[i] = w3[0];
                        }
                }

                for (int i = 0; i < sub.length; i++) {
                        int j = Integer.parseInt(rot[i], 16);
                        int q = j / 16;
                        int r = j % 16;
                        sub[i] = SBOX[q][r];
                }

                for (int i = 0; i < result.length; i++) {
                        result[i] = String.format("%02x",
                                        Integer.parseInt(sub[i], 16) ^ Integer.parseInt(Rcon[round][i], 16));
                }
                return result;
        }

        static String[][] addround(String[][] a, String[][] key) {
                String[][] b = new String[4][4];
                for (int i = 0; i < a.length; i++) {
                        for (int j = 0; j < a[0].length; j++) {
                                b[i][j] = String.format("%02x",
                                                Integer.parseInt(a[i][j], 16) ^ Integer.parseInt(key[i][j], 16));
                        }
                }
                return b;

        }

        static List<String[][]> aesencrypt(String filepath, String key) {

                String[][] text_hex = new String[4][4];
                String[][] key_hex = new String[4][4];
                String[][] newstate_hex = new String[4][4];
                String[][] sboxsub_hex_9 = new String[4][4];
                String[][] shiftrows_hex_9 = new String[4][4];
                String[][] Mixcolumn_hex_9 = new String[4][4];
                String[][] roundkey_hex_9 = new String[4][4];
                String[][] addroundkey_hex_9 = new String[4][4];

                StringBuilder text1 = new StringBuilder();

                try (FileReader fileReader = new FileReader(filepath);
                                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                        String text = "";
                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                                text += line;
                        }

                        int length = text.length();

                        for (int j = 0; j < length; j += 16) {

                                for (int k = 0; k < 16 && (j + k) < length; k++) {
                                        text1.append(text.charAt(j + k));
                                }

                                String text1String = text1.toString();

                                if (text1String.length() == 16) {
                                        // text = text1String;
                                        text1.setLength(0);
                                } else {
                                        int length1 = text1String.length();
                                        int diff = 16 - length1;
                                        for (int x = 0; x < diff; x++) {
                                                text1String = text1String + " ";
                                        }
                                        // String text_1 = text1String;
                                }
                                for (int i = 0; i < 4; i++) {
                                        for (int d = 0; d < 4; d++) {
                                                text_hex[d][i] = Integer
                                                                .toHexString((int) text1String
                                                                                .charAt(d + i * 4));
                                                key_hex[d][i] = Integer
                                                                .toHexString((int) key.charAt(d + i * 4));
                                                newstate_hex[d][i] = String.format("%02x",
                                                                Integer.parseInt(text_hex[d][i], 16)
                                                                                ^ Integer.parseInt(
                                                                                                key_hex[d][i],
                                                                                                16));

                                        }
                                }

                                String[][] sboxsub_hex = sbox_sub(newstate_hex);
                                String[][] shiftrows_hex = shift_rows(sboxsub_hex);
                                String[][] Mixcolumn_hex = mix_column(shiftrows_hex);
                                String[][] addroundkey_hex = addround(Mixcolumn_hex, keyexpansion(key_hex, 0));

                                String[][] round1key = keyexpansion(key_hex, 0);

                                for (int i = 2; i <= 9; i++) {

                                        String[][] sboxsub_hex_i = sbox_sub(addroundkey_hex);
                                        String[][] shiftrows_hex_i = shift_rows(sboxsub_hex_i);
                                        String[][] Mixcolumn_hex_i = mix_column(shiftrows_hex_i);
                                        String[][] roundkey_hex_i = keyexpansion(round1key, i - 1);

                                        String[][] addroundkey_hex_i = addround(Mixcolumn_hex_i,
                                                        keyexpansion(round1key, i - 1));
                                        round1key = roundkey_hex_i;
                                        addroundkey_hex = addroundkey_hex_i;

                                        for (int y = 0; y < 4; y++) {
                                                for (int z = 0; z < 4; z++) {
                                                        sboxsub_hex_9[y][z] = sboxsub_hex_i[y][z];
                                                        shiftrows_hex_9[y][z] = shiftrows_hex_i[y][z];
                                                        Mixcolumn_hex_9[y][z] = Mixcolumn_hex_i[y][z];
                                                        roundkey_hex_9[y][z] = roundkey_hex_i[y][z];
                                                        addroundkey_hex_9[y][z] = addroundkey_hex_i[y][z];

                                                }
                                        }

                                }

                                String[][] sboxsub_hex_10 = sbox_sub(addroundkey_hex_9);
                                String[][] shiftrows_hex_10 = shift_rows(sboxsub_hex_10);
                                String[][] addroundkey_hex_10 = addround(shiftrows_hex_10,
                                                keyexpansion(roundkey_hex_9, 9));
                                matrixList.add(addroundkey_hex_10);
                        }

                } catch (IOException e) {
                        e.printStackTrace();
                }

                return matrixList;

        }

        static List<String[][]> aesencrypt_text(String text, String key) {

                String[][] text_hex = new String[4][4];
                String[][] key_hex = new String[4][4];
                String[][] newstate_hex = new String[4][4];
                String[][] sboxsub_hex_9 = new String[4][4];
                String[][] shiftrows_hex_9 = new String[4][4];
                String[][] Mixcolumn_hex_9 = new String[4][4];
                String[][] roundkey_hex_9 = new String[4][4];
                String[][] addroundkey_hex_9 = new String[4][4];

                StringBuilder text1 = new StringBuilder();

                int length = text.length();

                for (int j = 0; j < length; j += 16) {

                        for (int k = 0; k < 16 && (j + k) < length; k++) {
                                text1.append(text.charAt(j + k));
                        }

                        String text1String = text1.toString();

                        if (text1String.length() == 16) {
                                // text = text1String;
                                text1.setLength(0);
                        } else {
                                int length1 = text1String.length();
                                int diff = 16 - length1;
                                for (int x = 0; x < diff; x++) {
                                        text1String = text1String + " ";
                                }
                                // String text_1 = text1String;
                        }
                        for (int i = 0; i < 4; i++) {
                                for (int d = 0; d < 4; d++) {
                                        text_hex[d][i] = Integer
                                                        .toHexString((int) text1String
                                                                        .charAt(d + i * 4));
                                        key_hex[d][i] = Integer
                                                        .toHexString((int) key.charAt(d + i * 4));
                                        newstate_hex[d][i] = String.format("%02x",
                                                        Integer.parseInt(text_hex[d][i], 16)
                                                                        ^ Integer.parseInt(
                                                                                        key_hex[d][i],
                                                                                        16));

                                }
                        }

                        String[][] sboxsub_hex = sbox_sub(newstate_hex);
                        String[][] shiftrows_hex = shift_rows(sboxsub_hex);
                        String[][] Mixcolumn_hex = mix_column(shiftrows_hex);
                        String[][] addroundkey_hex = addround(Mixcolumn_hex, keyexpansion(key_hex, 0));

                        String[][] round1key = keyexpansion(key_hex, 0);

                        for (int i = 2; i <= 9; i++) {

                                String[][] sboxsub_hex_i = sbox_sub(addroundkey_hex);
                                String[][] shiftrows_hex_i = shift_rows(sboxsub_hex_i);
                                String[][] Mixcolumn_hex_i = mix_column(shiftrows_hex_i);
                                String[][] roundkey_hex_i = keyexpansion(round1key, i - 1);

                                String[][] addroundkey_hex_i = addround(Mixcolumn_hex_i,
                                                keyexpansion(round1key, i - 1));
                                round1key = roundkey_hex_i;
                                addroundkey_hex = addroundkey_hex_i;

                                for (int y = 0; y < 4; y++) {
                                        for (int z = 0; z < 4; z++) {
                                                sboxsub_hex_9[y][z] = sboxsub_hex_i[y][z];
                                                shiftrows_hex_9[y][z] = shiftrows_hex_i[y][z];
                                                Mixcolumn_hex_9[y][z] = Mixcolumn_hex_i[y][z];
                                                roundkey_hex_9[y][z] = roundkey_hex_i[y][z];
                                                addroundkey_hex_9[y][z] = addroundkey_hex_i[y][z];

                                        }
                                }

                        }

                        String[][] sboxsub_hex_10 = sbox_sub(addroundkey_hex_9);
                        String[][] shiftrows_hex_10 = shift_rows(sboxsub_hex_10);
                        String[][] addroundkey_hex_10 = addround(shiftrows_hex_10,
                                        keyexpansion(roundkey_hex_9, 9));
                        matrixList.add(addroundkey_hex_10);
                }

                return matrixList;

        }

        static String[][] inv_shift_rows(String[][] a) {
                for (int i = 1; i < a.length; i++) {
                        int shiftAmount = i;
                        for (int k = 0; k < shiftAmount; k++) {
                                String temp = a[i][a[0].length - 1];
                                for (int j = a[0].length - 1; j > 0; j--) {
                                        a[i][j] = a[i][j - 1];
                                }
                                a[i][0] = temp;
                        }
                }
                return a;
        }

        static String[][] inv_sbox_sub(String[][] a) {
                String[][] inv_sboxsub_hex = new String[4][4];

                for (int i = 0; i < a.length; i++) {
                        for (int j = 0; j < a[0].length; j++) {
                                int a_int = Integer.parseInt(a[i][j], 16);
                                int row = a_int / 0x10;
                                int col = a_int % 0x10;
                                inv_sboxsub_hex[i][j] = inverseSBox[row][col];
                        }
                }
                return inv_sboxsub_hex;
        }

        static String[][] inv_mix_columns(String[][] a) {
                String[][] b = new String[4][4];

                for (int j = 0; j < a[0].length; j++) {
                        for (int i = 0; i < a.length; i++) {
                                int[] product = new int[4];
                                for (int k = 0; k < a.length; k++) {
                                        int aVal = Integer.parseInt(a[k][j], 16);
                                        int mixVal = Integer.parseInt(InvMixColumn[i][k], 16);
                                        product[k] = multiplyInGF(aVal, mixVal);
                                }
                                int result = product[0] ^ product[1] ^ product[2] ^ product[3];
                                b[i][j] = String.format("%02x", result);
                        }
                }

                return b;
        }

        static String aesdecrypt(List<String[][]> a, String key) {
                String[][] key_hex = new String[4][4];

                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                                key_hex[j][i] = Integer.toHexString((int) key.charAt(j + i * 4));
                        }
                }

                String[][] inv_shiftrows_hex_i = new String[4][4];
                String[][] inv_sboxsub_hex_i = new String[4][4];
                String[][] inv_mixcolumns_hex_i = new String[4][4];
                String[][] inv_addroundkey_hex_i = new String[4][4];
                String[][] inv_addroundkey_hex_10 = new String[4][4];
                keylist.add(key_hex);

                for (int z = 1; z <= 10; z++) {
                        keylist.add(keyexpansion(keylist.get(z - 1), z - 1));
                }

                for (int i = 0; i < a.size(); i++) {
                        String[][] newstate = new String[4][4];
                        String[][] input = a.get(i);

                        newstate = addround(input, keylist.get(10));

                        for (int p = 1; p <= 9; p++) {
                                inv_shiftrows_hex_i = inv_shift_rows(newstate);

                                inv_sboxsub_hex_i = inv_sbox_sub(inv_shiftrows_hex_i);

                                inv_addroundkey_hex_i = addround(inv_sboxsub_hex_i, keylist.get(10 - p));

                                inv_mixcolumns_hex_i = inv_mix_columns(inv_addroundkey_hex_i);

                                newstate = inv_mixcolumns_hex_i;

                        }

                        String[][] inv_shiftrows_hex_10 = inv_shift_rows(newstate);
                        String[][] inv_sboxsub_hex_10 = inv_sbox_sub(inv_shiftrows_hex_10);
                        inv_addroundkey_hex_10 = addround(inv_sboxsub_hex_10, keylist.get(0));

                        decryptlist.add(inv_addroundkey_hex_10);
                }

                StringBuilder decrypted_cipher_text_hex = new StringBuilder();

                for (int q = 0; q < decryptlist.size(); q++) {
                        String s[][] = decryptlist.get(q);
                        StringBuilder x = new StringBuilder();

                        for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                        x.append(s[j][i]);
                                        x.toString();

                                }
                        }
                        decrypted_cipher_text_hex.append(x);
                        decrypted_cipher_text_hex.toString();

                }

                String decrypted_cipher_text = hexToString(decrypted_cipher_text_hex.toString());

                return decrypted_cipher_text;
        }

        public static String hexToString(String hex) {

                BigInteger bigint = new BigInteger(hex, 16);

                byte[] bytes = bigint.toByteArray();

                return new String(bytes);
        }

        public static String stringToHex(String input) {
                // Convert the string to bytes
                byte[] bytes = input.getBytes();

                // Convert the bytes to a hexadecimal string
                StringBuilder hexString = new StringBuilder();
                for (byte b : bytes) {
                        hexString.append(String.format("%02X", b));
                        // System.out.println(hexString);
                }

                return hexString.toString();
        }
        public static String readTextFile(String filePath) {
            StringBuilder content = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        public static void writeTextToFile(String content, String outputPath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                writer.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        public static void main(String[] args) {
                test aes = new test();
                String filepath = "C:/Users/saira/Downloads/input.txt";
                String outputFilePath = "C:/Users/saira/Downloads/output.txt";

                String key = "Xs9p#RzLmKqo8vJw\r\n";
                String text = readTextFile(filepath);
                

                
                List<String[][]> encryptList = aesencrypt_text(text, key);
                String a = listtostring(encryptList);
                System.out.println(a);
                String decrypttext = aesdecrypt(encryptList, key);
                System.out.printf("\nDecrypted text: %s\n", decrypttext);
                writeTextToFile(a, outputFilePath);

        }

        public static String listtostring(List<String[][]> a) {
                StringBuilder encrypted_cipher_text_hex = new StringBuilder();

                for (int q = 0; q < a.size(); q++) { // Change matrixList to a
                        String s[][] = a.get(q); // Change matrixList to a
                        StringBuilder x = new StringBuilder();

                        for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                        x.append(s[j][i]);
                                        x.toString();
                                }
                        }

                        encrypted_cipher_text_hex.append(x);
                        encrypted_cipher_text_hex.toString();
                }

                String encrypted_cipher_text = hexToString(encrypted_cipher_text_hex.toString());

                return encrypted_cipher_text;
        }

        public static List<String[][]> stringtolist(String input) {
                List<String[][]> resultList = new ArrayList<>();

                String hex = stringToHex(input);
                // System.out.println(hex);
                String[][] matrix = new String[4][4];
                int length = hex.length();
                StringBuilder text1 = new StringBuilder();

                for (int j = 0; j < length / 2; j += 16) {

                        for (int k = 0; k < length && (j + k) < length; k++) {
                                text1.append(hex.charAt(j + k));
                                // System.out.println(text1);
                        }

                        int index = 0;

                        for (int i = 0; i < matrix.length && index < text1.length(); i++) {
                                for (int k = 0; k < matrix[i].length && index < text1.length(); k++) {
                                        matrix[k][i] = String.valueOf(text1.charAt(index));
                                        index++;

                                        if (index < text1.length()) {
                                                matrix[k][i] += String.valueOf(text1.charAt(index));
                                                index++;
                                        }
                                }
                        }

                        resultList.add(matrix);
                }

                return resultList;
        }

        private static void validateHexFormat(String hexValue) {
                if (!hexValue.matches("[0-9A-Fa-f]{2}")) {
                        throw new IllegalArgumentException("Invalid hexadecimal format: " + hexValue);
                }
        }
}