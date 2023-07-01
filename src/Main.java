import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

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
                logger.append(date +" создан файл C:/Users/minim/STUDY/JD-45/Games/src/main/Main.java\n");
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

        String log = logger.toString();

        try (FileWriter writer = new FileWriter("C:/Users/minim/STUDY/JD-45/Games/temp/temp.txt", true)) {
            writer.write(log);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
