package ch.hevs.tools.crypt;


import ch.hevs.errors.BusinessException;
import ch.hevs.errors.ErrorCode;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;

public class FileEncryption {

    public static void encrypt(byte[] keyMaterial, File inputFile, File outputFile) throws BusinessException {
        Cipher cipher;

        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | NoSuchProviderException e) {
            throw new BusinessException("Failed to get a Cipher object", ErrorCode.CRYPTO_PROVIDER_ERROR);
        }

        SecureRandom rnd = new SecureRandom();

        byte[] ivMaterial = new byte[cipher.getBlockSize()];
        rnd.nextBytes(ivMaterial);

        SecretKeySpec key = new SecretKeySpec(keyMaterial, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivMaterial);

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new BusinessException("Failed to initialize the cipher", ErrorCode.INVALID_PARAMETER_ERROR);
        }

        try (BufferedInputStream plaintext = new BufferedInputStream(new FileInputStream(inputFile));
             BufferedOutputStream ciphertext = new BufferedOutputStream(new FileOutputStream(outputFile))) {

            byte[] payload = new byte[(int) inputFile.length()];
            plaintext.read(payload);

            // Encrypt the plaintext
            payload = cipher.doFinal(payload);

            // Write the IV...
            ciphertext.write(ivMaterial);
            // ... and the ciphertext
            ciphertext.write(payload);
        } catch (IOException e) {
            throw new BusinessException("Failed to read/write data", ErrorCode.IO_ERROR);
        } catch (BadPaddingException e) {
            throw new BusinessException("Failed to decrypt data", ErrorCode.BAD_PADDING_ERROR);
        } catch (IllegalBlockSizeException e) {
            throw new BusinessException("Illegal block size", ErrorCode.ILLEGAL_BLOCK_SIZE_ERROR);
        }
    }

    public static void decrypt(byte[] keyMaterial, File inputFile, File outputFile) throws BusinessException {

        Cipher cipher;

        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | NoSuchProviderException e) {
            throw new BusinessException("Failed to get a Cipher object", ErrorCode.CRYPTO_PROVIDER_ERROR);
        }

        byte[] payload = new byte[(int) inputFile.length()];
        // Our payload contains the IV and at least a block, i.e. 32 bytes
        if (payload.length < 2*cipher.getBlockSize()) {
            throw new BusinessException("Invalid ciphertext", ErrorCode.CIPHERTEXT_ERROR);
        }

        try (BufferedInputStream ciphertext = new BufferedInputStream(new FileInputStream(inputFile));
             BufferedOutputStream plaintext = new BufferedOutputStream(new FileOutputStream(outputFile))) {

            ciphertext.read(payload);

            byte[] ivMaterial = new byte[cipher.getBlockSize()];
            byte[] data = new byte[payload.length-cipher.getBlockSize()];

            System.arraycopy(payload, 0, ivMaterial, 0, cipher.getBlockSize());
            System.arraycopy(payload, cipher.getBlockSize(), data, 0, data.length);

            SecretKeySpec key = new SecretKeySpec(keyMaterial, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivMaterial);

            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            data = cipher.doFinal(data);
            plaintext.write(data);

        } catch (IOException e) {
            throw new BusinessException("Failed to read/write data", ErrorCode.IO_ERROR);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new BusinessException("Failed to initialize the cipher", ErrorCode.INVALID_PARAMETER_ERROR);
        } catch (BadPaddingException e) {
            throw new BusinessException("Failed to decrypt data", ErrorCode.BAD_PADDING_ERROR);
        } catch (IllegalBlockSizeException e) {
            throw new BusinessException("Illegal block size", ErrorCode.ILLEGAL_BLOCK_SIZE_ERROR);
        }
    }
}
