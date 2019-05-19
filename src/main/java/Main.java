import javafx.util.Pair;
import observer.AntivirusObserver;

import java.util.*;

public class Main {

    public static void printHelp(){
        // System.out.println("HELP!!!");

        System.out.println("help     : help page(this)");
        System.out.println("scan     : запуск в режиме сканирования");
        System.out.println("observer : наблюдение за системой в реальном вреени");
    }

    public static void scan(String directoryPath){
        AntivirusMinimal antivirusMinimal = new AntivirusMinimal();
        List<Pair<String, String>> results = antivirusMinimal.scan(directoryPath);

        System.out.println("Результаты сканирования:");
        for(Pair<String, String> res : results){
            System.out.println(res.getKey() + " : " + res.getValue());
        }
        System.out.println("Всего проверено файлов: " + results.size());
    }

    public static void observer(String directoryPath){
        AntivirusObserver antivirusObserver = new AntivirusObserver();
        antivirusObserver.scan(directoryPath);
    }

    public static void main(String[] args) {


        if(args.length > 0 && args[0].equalsIgnoreCase("help")){
            printHelp();
        } else if(args.length > 1 && args[0].equalsIgnoreCase("scan")){
            scan(args[1]);
        } else if(args.length > 1 && args[0].equalsIgnoreCase("observer")){
            observer(args[1]);
        } else {
            scan(".");
        }

    }
}
