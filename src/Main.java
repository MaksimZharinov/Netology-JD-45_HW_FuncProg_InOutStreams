import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static Date date = new Date();
    public static StringBuilder logger = new StringBuilder();

    public static void main(String[] args) {
        String logPath = "C:/Users/minim/STUDY/JD-45/Games/temp/temp.txt";
        List<String> pathObjects = new ArrayList<>();
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/src");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/res");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/temp");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/src/main");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/src/test");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/src/main/Main.java");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/src/main/Utils.java");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/res/drawables");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/res/vectors");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/res/icons");
        pathObjects.add("C:/Users/minim/STUDY/JD-45/Games/savegames");
        pathObjects.add(logPath);

        for (String file : pathObjects) {
            if (file.contains(".")) {
                createFile(file);
            } else {
                createDirectory(file);
            }
        }

        try (FileWriter writer = new FileWriter(logPath)) {
            writer.write(logger.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        GameProgress game1 = new GameProgress(100, 100, 1, 0.00);
        GameProgress game2 = new GameProgress(75, 50, 50, 666.00);
        GameProgress game3 = new GameProgress(15, 5, 80, 1234.56);

        saveGame("C:/Users/minim/STUDY/JD-45/Games/savegames/save1.dat", game1);
        saveGame("C:/Users/minim/STUDY/JD-45/Games/savegames/save2.dat", game2);
        saveGame("C:/Users/minim/STUDY/JD-45/Games/savegames/save3.dat", game3);

        List<String> filesToZip = new ArrayList<>();
        filesToZip.add("C:/Users/minim/STUDY/JD-45/Games/savegames/save1.dat");
        filesToZip.add("C:/Users/minim/STUDY/JD-45/Games/savegames/save2.dat");
        filesToZip.add("C:/Users/minim/STUDY/JD-45/Games/savegames/save3.dat");

        zipFiles("C:/Users/minim/STUDY/JD-45/Games/savegames/zip_savegames.zip", filesToZip);

        openZip("C:/Users/minim/STUDY/JD-45/Games/savegames" +
                "/zip_savegames.zip", "C:/Users/minim/STUDY/JD-45/Games/savegames/");

        System.out.println(openProgress("C:/Users/minim/STUDY/JD-45/Games/savegames/save3.dat"));
    }

    public static boolean createFile(String filePath) {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                logger.append(date + " create file " + filePath + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean createDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        if (dir.mkdir()) {
            logger.append(date + " create directory " + directoryPath + "\n");
        } else {
            return false;
        }
        return true;
    }

    public static boolean writeThisFile(String filePath) {
        return true;
    }

    public static boolean saveGame(String saveFilePath, GameProgress game) {
        try (FileOutputStream outSave = new FileOutputStream(saveFilePath);
             ObjectOutputStream objOutSave = new ObjectOutputStream(outSave)) {
            objOutSave.writeObject(game);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean zipFiles(String zipPath, List<String> toZip) {
        try (ZipOutputStream zipOutStream = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String file : toZip) {
                try (FileInputStream zipThisFile = new FileInputStream(file)) {
                    String[] names = file.split("/");
                    ZipEntry entry = new ZipEntry(names[names.length - 1]);
                    zipOutStream.putNextEntry(entry);
                    byte[] buffer = new byte[zipThisFile.available()];
                    zipThisFile.read(buffer);
                    zipOutStream.write(buffer);
                    zipOutStream.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
                File deleleThisFile = new File(file);
                deleleThisFile.delete();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean openZip(String zipFilePath, String dirToOpenZip) {
        try (ZipInputStream unZipStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            String name;
            while ((entry = unZipStream.getNextEntry()) != null) {
                name = entry.getName();
                try (FileOutputStream unZipFileStream = new FileOutputStream(dirToOpenZip + name)) {
                    for (int c = unZipStream.read(); c != -1; c = unZipStream.read()) {
                        unZipFileStream.write(c);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            unZipStream.closeEntry();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static GameProgress openProgress(String openFilePath) {
        try (FileInputStream showThisFile = new FileInputStream(openFilePath);
             ObjectInputStream showFileObj = new ObjectInputStream(showThisFile)) {
            return (GameProgress) showFileObj.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
