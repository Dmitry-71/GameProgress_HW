import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(15, 100, 3, 115.7);
        GameProgress game2 = new GameProgress(45, 500, 5, 320.71);
        GameProgress game3 = new GameProgress(100, 900, 11, 950.77);
        saveGame("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game1.dat", game1);
        saveGame("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game2.dat", game2);
        saveGame("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game1.dat");
        arrayList.add("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game2.dat");
        arrayList.add("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game3.dat");
        zipFiles("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\zip.zip", arrayList);
        File game1Dat = new File("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game1.dat");
        File game2Dat = new File("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game2.dat");
        File game3Dat = new File("C:\\Users\\User\\OneDrive\\Рабочий стол\\Работа\\Тест\\Учеба\\Games\\savegames\\game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zouts.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zouts.write(fis.read());
                    }
                    zouts.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}