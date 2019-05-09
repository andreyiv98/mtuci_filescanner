import javafx.util.Pair;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        AntivirusMinimal antivirusMinimal = new AntivirusMinimal();
        List<Pair<String, String>> results = antivirusMinimal.scan(".");

        System.out.println("Результаты сканирования:");
        for(Pair<String, String> res : results){
            System.out.println(res.getKey() + " : " + res.getValue());
        }
        System.out.println("Всего проверено файлов: " + results.size());

    }
}
