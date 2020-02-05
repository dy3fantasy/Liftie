package com.kaylajocarroll.liftie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Contains simple methods to write to external storage
 */
class Storage {
    private String filename = "";
    private String filepath = "";
    private String myData = "";
    File myExternalFile;

    public void setExternalFile(Context ma, String fileName, String filePath){ myExternalFile = new File(ma.getExternalFilesDir(filePath), fileName);}
    public File getExternalFile(){ return myExternalFile; }
    public File getExternalFile(Context ma){ return new File(ma.getExternalFilesDir("Liftie"), "LiftieLog.csv");}
    public String getData(){ return myData; }
    public void setData(String str){ myData = str; }

    /**
     * Exports external file
     */
    public void export(Context appContext, Context ma)throws Exception{
        //Uri pathE = FileProvider.getUriForFile(appContext, "com.kaylajocarroll.liftie.fileprovider", myExternalFile);
        Uri pathE = FileProvider.getUriForFile(appContext, "com.kaylajocarroll.liftie.fileprovider", getExternalFile(ma));
        Intent fileIntent = new Intent(Intent.ACTION_SEND);
        fileIntent.setType("text/csv");
        fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Lifts");
        fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        fileIntent.putExtra(Intent.EXTRA_STREAM, pathE);
        ma.startActivity(Intent.createChooser(fileIntent,"Export"));
    }

    /**
     * Writes (over) a file with the passed in str
     * @param ma
     * @throws IOException
     */
    public void writeFile(Context ma)throws IOException{
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            throw new IOException("ERROR! External Storage is not available.");
        }else if(myExternalFile == null){
            throw new IOException("Error! File is null.");
        }else {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(myData.getBytes());
            fos.close();
            readFile();
        }
    }

    /**
     * Reads an external file into data(string)
     * @throws IOException
     */
    public void readFile() throws IOException {
        myData = "";
        if(myExternalFile == null || !myExternalFile.exists()) {
            Log.e("Warning","Warning! File is null or does not exist.");
            return;
        }
        FileInputStream fis = new FileInputStream(myExternalFile);
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br =
                new BufferedReader(new InputStreamReader(in));
        String strLine;

        while ((strLine = br.readLine()) != null) {
            myData = myData + strLine + "\n";

        }

        in.close();
    }

    /**
     * METHODS THAT DEAL WITH VALIDITY OF EXTERNAL STORAGE
     */
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
