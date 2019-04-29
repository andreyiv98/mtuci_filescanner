package recordManager;

import java.io.*;
import java.util.ArrayList;

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




}
