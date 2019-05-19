package observer;

import javafx.util.Pair;
import scanEngine.ScanEngine;

import java.io.File;

public class ObserverScaner implements Observer {

    private ScanEngine scanEngine = null;

    ObserverScaner(ScanEngine scanEngine){
        this.scanEngine = scanEngine;
    }


    public void update(File file) {

        String virusName = scanEngine.scan(file.getPath());
        Pair<String,String> resForFile = new Pair<String, String>(file.getPath(), virusName);

        System.out.println(resForFile.getKey() + " : " + resForFile.getValue());

    }
}
