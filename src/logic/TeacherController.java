package logic;

import service.Service;
import shared.TeacherDTO;

/**
 * Created by emilstepanian on 12/10/2016.
 */
public class TeacherController {

    private TeacherDTO currentUser;
    private Service service;


    // laver gennemsnittet af ratings
    public double average() {
        /*
        Review arraylist skal hentes og derfra kun trækkes ratings.
         */
        int sum = 0;
        double average;

        //Hver rating bliver lagt sammen
        for(int i=0; i < array.length; i++){
            sum = sum + array[i];
        }

        //samlet rating bliver divideret med antallet af ratings
        average = (double) sum/array.length;
        //gennemsnittet bliver returneret
        return average;
    }
}