/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoCifradoAES;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author datasoft-edgardo
 */
public class DemoEncriptAES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            encriptarFrases();
            encriptarArchivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void encriptarFrases() {
        try {
            System.out.println("===Encriptar Frases===");
            String original = "Here is some data to encrypt!";

            AesEncryptDecrypt aesEncryptDecrypt = new AesEncryptDecrypt();

            byte[] encrypted = aesEncryptDecrypt.Encrypt256(original.getBytes());
            String b64Encrypted = AesEncryptDecrypt.byteArrayToB64(encrypted);
            String roundtrip = new String(aesEncryptDecrypt.Decrypt256(encrypted));

            //Display the original data and the decrypted data.
            System.out.println("Original:    " + original);
            System.out.println("Encriptado:  " + b64Encrypted);
            System.out.println("Descriptado: " + roundtrip);
            System.out.println("");
        } catch (Exception e) {
        }
    }

    private static void encriptarArchivos() {
        try {
            System.out.println("===Encriptar Archivos===");

            Path rutaOriginal = Paths.get("C:\\Users\\datasoft-edgardo\\Desktop\\pdf\\12MB testing001.pdf");
            Path rutaDestinoEnc = Paths.get("C:\\Users\\datasoft-edgardo\\Desktop\\160KB Prueba - Test Standard.AES256");
            Path rutaDestinoDesEnc = Paths.get("C:\\Users\\datasoft-edgardo\\Desktop\\160KB Prueba - Test Standard DESCENC.pdf");

            AesEncryptDecrypt aesEncryptDecrypt = new AesEncryptDecrypt();

            byte[] archivoOriginal = Files.readAllBytes(rutaOriginal);
            byte[] encrypted = aesEncryptDecrypt.Encrypt256(archivoOriginal);
            Files.write(rutaDestinoEnc, encrypted);

            byte[] archivoEncriptado = Files.readAllBytes(rutaDestinoEnc);
            byte[] desencrypted = aesEncryptDecrypt.Decrypt256(archivoEncriptado);
            Files.write(rutaDestinoDesEnc, desencrypted);

            System.out.println("Archivo Encriptado en: " + rutaDestinoEnc);
            System.out.println("Archivo Desencriptado en: " + rutaDestinoDesEnc);
            System.out.println("");
        } catch (Exception e) {
        }
    }
}
