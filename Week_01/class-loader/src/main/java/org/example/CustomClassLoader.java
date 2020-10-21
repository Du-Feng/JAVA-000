package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CustomClassLoader extends ClassLoader {
    public static final String HELLO_CLASS_NAME = "Hello";
    public static final String HELLO_METHOD_NAME = "hello";
    public static final String HELLO_CLASS_FILE = "Hello.class";
    public static final String HELLO_XLASS_FILE = "Hello.xlass";
    private static String WorkingDirectory;
    private static FileType FileType;

    public static void setFileType(org.example.FileType fileType) {
        FileType = fileType;
    }

    public static String getWorkingDirectory() {
        return WorkingDirectory;
    }

    public static void setWorkingDirectory(String workingDirectory) {
        WorkingDirectory = workingDirectory;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.equals(HELLO_CLASS_NAME)) {
            String classFile = WorkingDirectory + "\\src\\main\\resources\\";
            if (FileType == FileType.CLASS) {
                classFile += HELLO_CLASS_FILE;
            } else {
                classFile += HELLO_XLASS_FILE;
            }
            System.out.println("Hello Class File Path: " + classFile);
            String helloBase64 = encodeFile(classFile);
            System.out.println(helloBase64);
            byte[] bytes = decode(helloBase64);
            return defineClass(name, bytes, 0, bytes.length);
        } else {
            return super.findClass(name);
        }
    }

    public String encode(String value) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
    }

    public byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public String encodeFile(String filePath) {
        String base64 = "";
        File file = new File(filePath);
        try (FileInputStream inputFile = new FileInputStream(file)) {
            byte fileContent[] = new byte[(int) file.length()];
            inputFile.read(fileContent);
            if (FileType == org.example.FileType.CLASS) {
                base64 = Base64.getEncoder().encodeToString(fileContent);
            } else {
                byte tempFileContent[] = new byte[(int) fileContent.length];
                for (int i = 0; i < fileContent.length; i++) {
                    tempFileContent[i] = (byte) (255 - fileContent[i]);
                }
                base64 = Base64.getEncoder().encodeToString(tempFileContent);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64;
    }

    public void decodeFile(String base64, String filePath) {
        try (FileOutputStream outputFile = new FileOutputStream(filePath)) {
            byte[] imageByteArray = Base64.getDecoder().decode(base64);
            outputFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }
}
