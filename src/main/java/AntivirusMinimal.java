import ahoCorasick.Tree;
import javafx.util.Pair;
import recordManager.RecordManager;
import scanEngine.ScanEngine;
import walker.Walker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AntivirusMinimal {
    private RecordManager recordManager = null;
    private ScanEngine scanEngine = null;

    public AntivirusMinimal(){
        recordManager = RecordManager.getInstance();
        Tree tree = recordManager.getTree();
        scanEngine = new ScanEngine(tree);
    }

    public List<Pair<String, String>> scan(String directoryPath){

        ////получаем список файлов для сканирования
        Walker walker = new Walker();
        //получаем список расширений, для которых есть сигнатуры в нашей базе
        ArrayList<String> targetExtensions = recordManager.getExistingFileExtensionsInVirusDB();
        //получаем список файлов с расширениями, для которых есть сигнатуры в нашей базе
        ArrayList<File> files = walker.getListFiles(directoryPath, targetExtensions);

        //Лист с результатами
        List<Pair<String,String>> scanResults = new ArrayList<Pair<String,String>>();
        //Проверяем каждый подозрительный файл
        for(File file : files){
            String virusName = scanEngine.scan(file.getPath());
            Pair<String,String> resForFile = new Pair<String, String>(file.getPath(), virusName);

            scanResults.add(resForFile);
        }

        return scanResults;
    }
}
