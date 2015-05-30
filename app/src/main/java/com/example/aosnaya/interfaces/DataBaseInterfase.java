package com.example.aosnaya.interfaces;

import java.util.Vector;

/**
 * Created by aosnaya on 19/05/15.
 */
public interface DataBaseInterfase {

    public boolean storeData(String name, String last_name, String score);
    public Vector<String> vecNameList(int size);
    public Vector<String> vecSearchByID(long id);
    public Vector<String> vecSearchByName(String name);
    public Vector<String> vecSearchByLastName(String last_name);
    public Vector<String> vecSearchByScore(int score);

}
