package com.kaylajocarroll.liftie;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;

class Lift extends Storage implements Parcelable{
    private String date = java.time.LocalDate.now().toString();
    private String name,sets,reps,weight;

    //constructors
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

    //setters
    public void setName(String n){ name = n; }
    public void setSets(String s){ sets = s; }
    public void setReps(String r){ reps = r; }
    public void setWeight(String w){ weight = w; }

    //getters
    public String getName(){ return name; }
    public String getSets(){ return sets; }
    public String getReps(){ return reps; }
    public String getWeight(){ return weight; }

    //reset lift values
    public void reset(){
        name = "";
        sets = "";
        reps = "";
        weight = "";
    }

    //To save data try reading the current file, updating the data, and rewriting the file
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

    //Updates data
    public void updateData(){
        String data = getData();

        //if previous data contains this lift's date check data under this lift's date
        if(data.contains(date)){
            String pastDates = data.substring(0,data.indexOf(date));
            String currDateData = data.substring(data.indexOf(date));

            //if the data under our current date contains the lift's name (match)
            if(currDateData.contains(name)){
                //save stored lifts before the match
                String prevLifts = currDateData.substring(0, currDateData.indexOf(name));
                //save lifts including and after the match
                String nextLifts = currDateData.substring(currDateData.indexOf(name));
                //get the match
                String currLift = nextLifts.substring(0, nextLifts.indexOf("\n"));
                //reset nextLifts to be after the match (excluding)
                nextLifts = nextLifts.substring(nextLifts.indexOf("\n"));

                //get the values in the match and save it in a lift
                String[] strArr = currLift.split(",");
                String[] setReps = strArr[1].split("x");
                Lift existingLift = new Lift(strArr[0], setReps[0], setReps[1], strArr[2]);

                //if the existing lift's weight equals the same as the lift's we want to store, and the existing lift's reps equals the same as the lift's we want to store, and this lift's sets is equal to one
                if(existingLift.getWeight().equals(this.getWeight()) && existingLift.getReps().equals(this.getReps()) && sets.equals("1")){
                    //increment sets by one of the existing lift
                    int numSets = Integer.parseInt(existingLift.getSets());
                    this.setSets(Integer.toString(++numSets));

                    //overwrite existing lift in currDateData
                    currDateData = prevLifts + name + "," + sets + "x" + reps + "," + weight + nextLifts;

                //if the existing lift's weight equals the same as the lift's we want to store, and the existing lift's reps equals the same as the lift's we want to store
                }else if(existingLift.getWeight().equals(this.getWeight()) && existingLift.getReps().equals(this.getReps())){
                    //overwrite existing lift in currDateData
                    currDateData = prevLifts + name + "," + sets + "x" + reps + "," + weight + nextLifts;

                //if existing lift's weight and reps do not match the lift we want to store
                }else{
                    //add another lift under match
                    currDateData = prevLifts + currLift +"\n" + name + "," + sets + "x" + reps + "," + weight + nextLifts;
                }

            //if this date doesn't contain this lift's name then add this lift normally
            }else{
                currDateData = currDateData + name + "," + sets + "x" + reps + "," + weight + "\n";
            }
            data = pastDates + currDateData;

        //if this data doesn't contain our date, add a date header and our lift
        }else{
            data = data + date + "\n" + name + "," + sets + "x" + reps + "," + weight + "\n";
        }

        //setData to updated data
        setData(data);
    }

    /** METHODS THAT OVERRIDE PARCEL METHODS*/
    @Override
    public int describeContents(){
        return 0;
    }

    //write obj data to passed in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeStringArray(new String[] {name, sets, reps, weight, this.getData(), this.getExternalFile().toString()});
    }

    //regenerate Lift object. CREATOR implements these two methods.
    public static final Parcelable.Creator<Lift> CREATOR = new Parcelable.Creator<Lift>(){
        public Lift createFromParcel(Parcel in){
            return new Lift(in);
        }

        public Lift[] newArray(int size){
            return new Lift[size];
        }
    };

    //constructor that rebuilds a lift from Parcel input
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
