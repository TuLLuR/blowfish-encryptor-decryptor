# Blowfish Encryptor/Decryptor
A program for encrypting and decrypting folders with using the Blowfish encryption algorithm.
# Blowfish
Blowfish is a symmetric-key block cipher, designed in 1993 by Bruce Schneier and included in a large number of cipher suites and encryption products. Blowfish provides a good encryption rate in software and no effective cryptanalysis of it has been found to date. However, the Advanced Encryption Standard (AES) now receives more attention.
Schneier designed Blowfish as a general-purpose algorithm, intended as an alternative to the aging DES and free of the problems and constraints associated with other algorithms. At the time Blowfish was released, many other designs were proprietary, encumbered by patents or were commercial or government secrets. Schneier has stated that, "Blowfish is unpatented, and will remain so in all countries. The algorithm is hereby placed in the public domain, and can be freely used by anyone."
Notable features of the design include key-dependent S-boxes and a highly complex key schedule.

# Blowfish in program
Algorithm and keystring:
 ```java 
    private static final String ALGORITHM = "Blowfish";
    private static String keyString = "cicada";
 ```
 
 In abstract class BlowFish, I have 1 encrypting method:
 ```java 
  public static void encrypt(File inputFile, File outputFile)
            throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile);
        System.out.println("File encrypted successfully!");
    } 
 ```
  ```java 
 public static void decrypt(File inputFile, File outputFile)
            throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);
        System.out.println("File decrypted successfully!");
    }

 ```
 
 
