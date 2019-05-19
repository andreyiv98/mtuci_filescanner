package observer;

import recordManager.RecordManager;
import walker.Walker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class ObservableWalker{

    private List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer channel) {
        this.observers.add(channel);
    }

    public void removeObserver(Observer channel) {
        this.observers.remove(channel);
    }

    public void notifySubscribers(File file) {
        for (Observer channel : this.observers) {
            channel.update(file);
        }
    }

    //--------------------------------------------

    private RecordManager recordManager = null;
    private List<String> wasScaned = null;

    ObservableWalker(RecordManager recordManager){
        this.recordManager = recordManager;
        this.wasScaned = new ArrayList<String>();
    }

    public void run(String directoryPath){
        ////получаем список файлов для сканирования
        Walker walker = new Walker();
        //получаем список расширений, для которых есть сигнатуры в нашей базе
        ArrayList<String> targetExtensions = recordManager.getExistingFileExtensionsInVirusDB();
        //получаем список файлов с расширениями, для которых есть сигнатуры в нашей базе
        ArrayList<File> files = walker.getListFiles(directoryPath, targetExtensions);


        for(File file : files){
            String fileStr = file.getPath();
            if(!wasScaned.contains(fileStr)){   //
                wasScaned.add(fileStr);
                notifySubscribers(file);
            }
        }
    }


}
