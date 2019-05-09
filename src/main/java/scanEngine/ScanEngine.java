package scanEngine;

import ahoCorasick.AhoCorasick;
import ahoCorasick.SearchResult;
import ahoCorasick.Tree;
import recordManager.AVRecord;
import scanObject.ScanObject;
import scanObject.ScanObjectFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import java.io.RandomAccessFile;

public class ScanEngine {
    private Tree tree;

    public ScanEngine(Tree tree){
        this.tree = tree;
    }

    public String scan(String filePath){

        //Получаем ScanObject
        ScanObjectFactory scanObjectFactory = new ScanObjectFactory();
        ScanObject scanObject = scanObjectFactory.getScanObject(filePath);

        //Ищем вхождение сигнатуры
        AhoCorasick achoCorasick = new AhoCorasick(tree);
        for (Iterator iter = achoCorasick.search(scanObject.getBytes()); iter.hasNext();) {
            SearchResult result = (SearchResult) iter.next();
            //Если нашли вхождение: проверяем хэш
            if(checkHash(result, (AVRecord)result.getOutputs().toArray()[0])){
                //Если хэш совпал: возвращаем название вируса
                return ((AVRecord)result.getOutputs().toArray()[0]).getName();
            }

        }

        //Если сигнатуры не найдены возвращается "None"
        return "None";
    }


    private boolean checkHash(SearchResult result, AVRecord avRecord){
        return true;
    }
}
