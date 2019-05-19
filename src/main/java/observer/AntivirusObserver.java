package observer;

import ahoCorasick.Tree;
import javafx.util.Pair;
import recordManager.RecordManager;
import scanEngine.ScanEngine;

public class AntivirusObserver {
    private RecordManager recordManager = null;
    private ScanEngine scanEngine = null;

    public AntivirusObserver(){
        recordManager = RecordManager.getInstance();
        Tree tree = recordManager.getTree();
        scanEngine = new ScanEngine(tree);
    }

    public void scan(String directoryPath){

        ObservableWalker observableWalker = new ObservableWalker(recordManager);
        ObserverScaner observerScaner = new ObserverScaner(scanEngine);

        observableWalker.addObserver(observerScaner);

        for(;;){
            observableWalker.run(directoryPath);
            try {
                Thread.sleep(10*1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
