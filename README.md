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
 And 1 decrypting:
  ```java 
 public static void decrypt(File inputFile, File outputFile)
            throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);
        System.out.println("File decrypted successfully!");
    }

 ```
 # Folder (Encrypt/Decrypt) with Threads
 For decryption and encryption folders I use methods encryptDirectory and decryptDirectory.
 This two methods use  threads to encrypt and decrypt.( For each file in the folder, a thread will be created that executes the     encryption and decryption method).
 
 E/D Methods (for folders) for example: 
 ```java
  public static void encryptDirectory(File inputFile, File outputFile) {
        if(inputFile.isDirectory()) {
            if(inputFile.listFiles().length>0) {
                ArrayList<Thread> threads = new ArrayList<>();
                long startTime = System.currentTimeMillis();
                outputFile = new File(outputFile.getPath() + "/" + inputFile.getName() + " en");
                outputFile.mkdir();
                for (File file1 : inputFile.listFiles()) {
                    File finalOutputFile = outputFile;
                    threads.add(new Thread(()->{try {
                        File outputFile1 = new File(finalOutputFile.getPath() + "/" + file1.getName().substring(0, file1.getName().lastIndexOf('.')) + ".en");
                        BlowFish.encrypt(file1, outputFile1);

                    } catch (Exception ignored) {}
                    }));
                }
                for (File file1 : inputFile.listFiles()) {
                    File finalOutputFile = outputFile;
                    try {
                        File outputFile1 = new File(finalOutputFile.getPath() + "/" + file1.getName().substring(0, file1.getName().lastIndexOf('.')) + ".en");
                        BlowFish.encrypt(file1, outputFile1);

                    } catch (Exception ignored) {}
                       }long timeSpentWithout = System.currentTimeMillis() - startTime;
                for (Thread thread : threads)
                    thread.start();
                for (Thread thread : threads)
                    if (thread.isAlive())
                        try {
                            thread.join();
                        } catch (InterruptedException e) {}

                long timeSpent = System.currentTimeMillis() - startTime;

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Encryption is done! Time: " + timeSpent + " ms."+"\nEncryption time without threads: "+timeSpentWithout+" ms.", ButtonType.OK);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.initOwner(Main.stage);
                alert.showAndWait();
            }
            else
                new Alert(Alert.AlertType.WARNING,"Files dismissed", ButtonType.OK).show();
        }
        else {
            System.out.println("File ain't folder");}
    }
 ```
 Thread:
 ```java
 threads.add(new Thread(()->{try {
                        File outputFile1 = new File(finalOutputFile.getPath() + "/" + file1.getName().substring(0, file1.getName().lastIndexOf('.')) + ".en");
                        BlowFish.encrypt(file1, outputFile1);

                    } catch (Exception ignored) {}
                    }));
 ```
After encryption the program creates .en files. 
If we want decrypt folder, we need to LogIn and after this we can decrypt files in our folder.

![LogIn image](https://github.com/TuLLuR/blowfish-encryptor-decryptor/blob/master/logInscreen.PNG)

If LogIn was successful, program will start decrypt method.
# Program structure
* Main-main class;
*	Controller-GUI;
sample.fxml; 

Folder  “Crypto”:
*	BlowFish – class wich contain BlowFish algorithm and encrtyption/decryption methods;

Folder  “LogIn”:
*	LogINController- GUI of LogIN screen;
*	loginForm – main class for LogIn screen;

And logScreen.fxml;
# Technical requirements
OS:

* Windows;

*	MacOS;

*	Linux;

And Java 8;



 
