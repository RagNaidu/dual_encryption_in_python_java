import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private BigInteger primeP, primeQ, modulus, phi, publicKey, privateKey;
    private static int bitLength = 1024;
    private Random random;
    boolean isPrime;

    public RSA() {
        random = new Random();

        primeP = BigInteger.probablePrime(bitLength, random);
        int lengthP = String.valueOf(primeP).length();
        isPrime = primeP.isProbablePrime(1);
        if (isPrime) {
            System.out.println("The big integer primeP is a probable prime number");
        } else {
            System.out.println("The big integer primeP is not a prime number...please execute again");
        }
        System.out.println("The length of primeP is - " + lengthP);

        primeQ = BigInteger.probablePrime(bitLength, random);
        int lengthQ = String.valueOf(primeQ).length();
        isPrime = primeQ.isProbablePrime(1);
        if (isPrime) {
            System.out.println("\nThe big integer primeQ is a probable prime number");
        } else {
            System.out.println("The big integer primeQ is not a prime number...please execute again");
        }
        System.out.println("The length of primeQ is - " + lengthQ);

        modulus = primeP.multiply(primeQ);
        int lengthModulus = String.valueOf(modulus).length();
        System.out.println("\nThe length of modulus is - " + lengthModulus);

        phi = primeP.subtract(BigInteger.ONE).multiply(primeQ.subtract(BigInteger.ONE));

        publicKey = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(publicKey).compareTo(BigInteger.ONE) > 0 && publicKey.compareTo(phi) < 0) {
            publicKey = publicKey.add(BigInteger.ONE); // Fix: Update the publicKey variable
        }
        privateKey = publicKey.modInverse(phi);
    }

    public RSA(BigInteger publicKey, BigInteger privateKey, BigInteger modulus) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.modulus = modulus;
    }

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        DataInputStream in = new DataInputStream(System.in);
        String testString;
        System.out.println("----------- Enter the text to be encrypted -----------");
        testString = in.readLine();

        System.out.println("\n------------ Generating very large prime numbers of given bit length --------------");
        RSA rsa = new RSA();

        System.out.println("\n------------ Encrypting text --------------");
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        System.out.println("Encrypted String: " + new String(encrypted));

        System.out.println("\n------------ Decrypting text --------------");
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted String: " + new String(decrypted));

        if (testString.equals(new String(decrypted))) {
            System.out.println("\nx-------------- RSA Algorithm is successful ------------x");

            long end = System.currentTimeMillis();
            System.out.println("\nThe run time for bit length " + bitLength + " is " + (end - start) / 1000F + " seconds");
        }
    }

    private static String bytesToString(byte[] encrypted) {
        StringBuilder result = new StringBuilder();
        for (byte b : encrypted) {
            result.append(Byte.toString(b));
        }
        return result.toString();
    }

    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(publicKey, modulus).toByteArray();
    }

    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(privateKey, modulus).toByteArray();
    }
}
