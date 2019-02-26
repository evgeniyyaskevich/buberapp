package by.epam.javaweb.evgeniyyaskevich.finalproject.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordManager {
    private static final int ITERATIONS_NUMBER = 1000;
    private static final int SALT_SIZE = 16;

    private static final class SingletonHolder {
        private static final PasswordManager INSTANCE = new PasswordManager();
    }

    private PasswordManager() {}

    public static PasswordManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String generatePasswordHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generateSalt();

        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS_NUMBER, 64 * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        return ITERATIONS_NUMBER + ":" + toHex(salt) + ":" + toHex(hash);
    }

    public boolean validatePassword(char[] originalPassword, String storedPasswordHash)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        String[] parts = storedPasswordHash.split(":");
        int iterationsNumber = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword, salt, iterationsNumber, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();


        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; ++i) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[SALT_SIZE];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private String toHex(byte[] array) {
        BigInteger bigInteger = new BigInteger(1, array);
        String hex = bigInteger.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            hex = String.format("%0" + paddingLength + "d", 0) + hex;
        }
        return hex;
    }

    private byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PasswordManager passwordManager = new PasswordManager();
        String hash = passwordManager.generatePasswordHash("123".toCharArray());
        System.out.println(hash);
        System.out.println(hash.length());
        System.out.println(passwordManager.validatePassword("password1".toCharArray(), hash));
        System.out.println(hash);
    }
}