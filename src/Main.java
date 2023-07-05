import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        Date date = new Date();
        StringBuilder logger = new StringBuilder();

        File dirSrc = new File("C:/Users/minim/STUDY/JD-45/Games/src");
        if (dirSrc.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/src/\n");
        }

        File dirRes = new File("C:/Users/minim/STUDY/JD-45/Games/res");
        if (dirRes.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/res/\n");
        }

        File dirSavegames = new File("C:/Users/minim/STUDY/JD-45/Games/savegames");
        if (dirSavegames.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/savegames/\n");
        }

        File dirTemp = new File("C:/Users/minim/STUDY/JD-45/Games/temp");
        if (dirTemp.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/temp/\n");
        }

        File dirMain = new File("C:/Users/minim/STUDY/JD-45/Games/src/main");
        if (dirMain.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/src/main/\n");
        }

        File dirTest = new File("C:/Users/minim/STUDY/JD-45/Games/src/test");
        if (dirTest.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/src/test/\n");
        }

        File fileMain = new File("C:/Users/minim/STUDY/JD-45/Games/src/main/", "Main.java");
        try {
            if (fileMain.createNewFile()) {
                logger.append(date + " создан файл C:/Users/minim/STUDY/JD-45/Games/src/main/Main.java\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File fileUtils = new File("C:/Users/minim/STUDY/JD-45/Games/src/main/", "Utils.java");
        try {
            if (fileUtils.createNewFile()) {
                logger.append(date + " создан файл C:/Users/minim/STUDY/JD-45/Games/src/main/Utils.java\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File dirDrawables = new File("C:/Users/minim/STUDY/JD-45/Games/res/drawables");
        if (dirDrawables.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/res/drawables/\n");
        }

        File dirVectors = new File("C:/Users/minim/STUDY/JD-45/Games/res/vectors");
        if (dirVectors.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/res/vectors/\n");
        }

        File dirIcons = new File("C:/Users/minim/STUDY/JD-45/Games/res/icons");
        if (dirIcons.mkdir()) {
            logger.append(date + " создана директория C:/Users/minim/STUDY/JD-45/Games/res/icons/\n");
        }

        File fileTemp = new File("C:/Users/minim/STUDY/JD-45/Games/temp", "temp.txt");
        try {
            if (fileTemp.createNewFile()) {
                logger.append(date + " создан файл C:/Users/minim/STUDY/JD-45/Games/temp/temp.txt\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileWriter writer = new FileWriter("C:/Users/minim/STUDY/JD-45/Games/temp/temp.txt", true)) {
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
}
