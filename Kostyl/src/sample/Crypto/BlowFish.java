package sample.Crypto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import sample.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * Created by Kiril on 27.04.2017.
 */
public abstract class BlowFish {

    private static final String ALGORITHM = "Blowfish";
    private static String keyString = "cicada";

    public static void encrypt(File inputFile, File outputFile)
            throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile);
        System.out.println("File encrypted successfully!");
    }

    public static void decrypt(File inputFile, File outputFile)
            throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);
        System.out.println("File decrypted successfully!");
    }

    private static void doCrypto(int cipherMode, File inputFile,
                                 File outputFile) throws Exception {

        Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipherMode, secretKey);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

    }

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

    public static void decryptDirectory(File inputFile, File outputFile) {

        if(inputFile.isDirectory()) {
            if(inputFile.listFiles().length>0) {
                ArrayList<Thread> threads = new ArrayList<>();
                long startTime = System.currentTimeMillis();
                outputFile = new File(outputFile.getPath() + "/" + inputFile.getName().substring(0, inputFile.getName().length() - 3) + " de");
                outputFile.mkdir();
                for (File file1 : inputFile.listFiles()) {
                    File finalOutputFile = outputFile;
                    new Thread(()->{ try {
                        File outputFile1 = new File(finalOutputFile.getPath() + "/" + file1.getName().substring(0, file1.getName().lastIndexOf('.')) + ".txt");
                        BlowFish.decrypt(file1, outputFile1);
                    } catch (Exception ignored) {}
                    }).start();
                }
                for (File file1 : inputFile.listFiles()) {
                    File finalOutputFile = outputFile;
                     try {
                        File outputFile1 = new File(finalOutputFile.getPath() + "/" + file1.getName().substring(0, file1.getName().lastIndexOf('.')) + ".txt");
                        BlowFish.decrypt(file1, outputFile1);
                    } catch (Exception ignored) {}
                    }
                  for (Thread thread : threads)
                    thread.start();
                for (Thread thread : threads)
                    if (thread.isAlive())
                        try {
                            thread.join();
                        } catch (InterruptedException e) {}
                long timeSpentWithout = System.currentTimeMillis() - startTime;
                        long timeSpent = System.currentTimeMillis() - startTime;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Decryption is done! Time: " + timeSpent + " ms."+"\nDecryption time without threads:"+timeSpentWithout+" ms.",ButtonType.OK);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.initOwner(Main.stage);
                alert.showAndWait();

                FXMLLoader root = null;
                try {
                    root = new FXMLLoader(Main.class.getResource("sample.fxml"));
                } catch (Exception ignored) {}
                Main.stage.setTitle("P E/D");
                Scene scene = null;
                try {
                    scene = new Scene(root.load(), 200, 150);
                } catch (IOException ignored) {}
                Main.stage.setScene(scene);
            } else
                new Alert(Alert.AlertType.WARNING,"Files dismissed", ButtonType.OK).show();
        }
        else
        {System.out.println("File ain't folder");}

    }
}
