package com.kaylajocarroll.liftie;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;

class Lift extends Storage implements Parcelable{
    private String date = java.time.LocalDate.now().toString();
    private String name,sets,reps,weight;

    public Lift(){
        name = "";
        sets = "";
        reps = "";
        weight = "";
    }
    public Lift(String n, String s, String r, String w){
        name = n;
        sets = s;
        reps = r;
        weight = w;
    }
    public void setName(String n){ name = n; }
    public void setSets(String s){ sets = s; }
    public void setReps(String r){ reps = r; }
    public void setWeight(String w){ weight = w; }

    public String getName(){ return name; }
    public String getSets(){ return sets; }
    public String getReps(){ return reps; }
    public String getWeight(){ return weight; }

    public void reset(){
        name = "";
        sets = "";
        reps = "";
        weight = "";
    }

    public boolean save(Context ma) {

        try {
            setExternalFile(ma, "LiftieLog.txt", "Liftie");
            readFile();
            updateData();
            writeFile(ma);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void readFile() throws IOException {
        super.readFile();
    }

    public void updateData(){
        String data = getData();
        if(data.contains(date)){
            String pastDates = data.substring(0,data.indexOf(date));
            String currDateData = data.substring(data.indexOf(date));
            if(currDateData.contains(name)){
                String prevLifts = currDateData.substring(0, currDateData.indexOf(name));
                String nextLifts = currDateData.substring(currDateData.indexOf(name));
                String currLift = nextLifts.substring(0, nextLifts.indexOf("\n"));
                nextLifts = nextLifts.substring(nextLifts.indexOf("\n"));

                String[] strArr = currLift.split(",");
                String[] setReps = strArr[1].split("x");
                Lift existingLift = new Lift(strArr[0], setReps[0], setReps[1], strArr[2]);

                if(existingLift.getWeight().equals(this.getWeight()) && existingLift.getReps().equals(this.getReps()) && sets.equals("1")){
                    int numSets = Integer.parseInt(existingLift.getSets());
                    this.setSets(Integer.toString(++numSets));
                    currDateData = prevLifts + name + "," + sets + "x" + reps + "," + weight + nextLifts;
                }else if(existingLift.getWeight().equals(this.getWeight()) && existingLift.getReps().equals(this.getReps())){
                    currDateData = prevLifts + name + "," + sets + "x" + reps + "," + weight + nextLifts;
                }else{
                    currDateData = prevLifts + currLift +"\n" + name + "," + sets + "x" + reps + "," + weight + nextLifts;
                }

            }else{
                currDateData = currDateData + name + "," + sets + "x" + reps + "," + weight + "\n";
            }
            data = pastDates + currDateData;
        }else{
            data = data + date + "\n" + name + "," + sets + "x" + reps + "," + weight + "\n";
        }

        setData(data);
    }

    /** METHODS THAT OVERRIDE PARCEL METHODS*/
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeStringArray(new String[] {name, sets, reps, weight, this.getData(), this.getExternalFile().toString()});
    }

    public static final Parcelable.Creator<Lift> CREATOR = new Parcelable.Creator<Lift>(){
        public Lift createFromParcel(Parcel in){
            return new Lift(in);
        }

        public Lift[] newArray(int size){
            return new Lift[size];
        }
    };

    private Lift(Parcel in){
        String[] arr = new String[6];
        in.readStringArray(arr);
        name = arr[0];
        sets = arr[1];
        reps = arr[2];
        weight = arr[3];
        this.setData(arr[4]);
    }
}
