package recordManager;

import ahoCorasick.Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class RecordManager {
    private  static RecordManager instance;

    private RecordManager(){}

    public static RecordManager getInstance(){
        if(instance == null){
            instance = new RecordManager();
        }
        return instance;
    }

    //----------------


    private String filePath = "";
    private String fileName = "signatureDB.bin";

    private ArrayList<AVRecord> avRecords = null;
    private Tree avTree = null;
    private ArrayList<String> existingFileExtensionsInVirusDB = null;

    public ArrayList<AVRecord> getAVRecords(){
        if(avRecords == null) {
            avRecords = update();
        }

        return avRecords;
    }

    public void addAVRecord(AVRecord avRecord){
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            ArrayList<AVRecord> avRecords = getAVRecords();
            avRecords.add(avRecord);
            objectOutputStream.writeObject(avRecords);

            fileOutputStream.close();           //переделать
            objectOutputStream.close();         //переделать

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        avRecords = update();
    }





    private ArrayList<AVRecord> update() {
        ArrayList<AVRecord> list = new ArrayList<AVRecord>();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<AVRecord>) objectInputStream.readObject();

            fileInputStream.close();            //переделать
            objectInputStream.close();          //переделать

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }


        return list;
    }



    //----

    public Tree getTree(){
        if(avTree == null){
            updateTree();
        }

        return avTree;
    }

    private void updateTree(){
        if(avRecords == null){
            avRecords = update();
        }

        Tree tree = new Tree();


        for(int i = 0; i < avRecords.size(); i++){
            tree.add(avRecords.get(i).getFirstBytes(), avRecords.get(i));
        }

        tree.prepare();

        this.avTree = tree;
    }



    //--------------

    public ArrayList<String> getExistingFileExtensionsInVirusDB(){
        if(existingFileExtensionsInVirusDB == null){
            updateExistingFileExtensionsInVirusDB();
        }

        return existingFileExtensionsInVirusDB;
    }

    private void updateExistingFileExtensionsInVirusDB(){
        if(avRecords == null){
            avRecords = update();
        }

        ArrayList<String> fileExtensions = new ArrayList<String>();
        for(int i = 0; i < avRecords.size(); i++){
            fileExtensions.add(avRecords.get(i).getFileType());
        }

        ArrayList<String> uniqueFileExtensions = new ArrayList<String>();
        HashSet<String> uniqueValues = new HashSet<String>(fileExtensions);
        for (String value : uniqueValues) {
            uniqueFileExtensions.add(value);
        }

        existingFileExtensionsInVirusDB = uniqueFileExtensions;
    }


}
