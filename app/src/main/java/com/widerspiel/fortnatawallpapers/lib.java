package com.widerspiel.fortnatawallpapers;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class lib {
    public static Context context;

    //region ERROR

    public static void err(int ERR_ID, Exception ex) {
        Log.e("ERR_" + ERR_ID, ex.getMessage() + "");

        try {
            FirebaseCrash.log(ERR_ID + "");
            FirebaseCrash.report(ex);
        } catch (Exception sex) {

        }
    }

    public static void err(int ERR_ID, IllegalArgumentException ex) {
        Log.e("ERR_" + ERR_ID, ex.getMessage() + "");

        try {
            FirebaseCrash.log(ERR_ID + "");
            FirebaseCrash.report(ex);
        } catch (Exception sex) {

        }
    }

    public static void err(int ERR_ID, FileNotFoundException ex) {
        Log.e("ERR_" + ERR_ID, ex.getMessage() + "");

        try {
             FirebaseCrash.log(ERR_ID + "");
            FirebaseCrash.report(ex);
        } catch (Exception sex) {

        }
    }

    public static void err(int ERR_ID, IOException ex) {
        Log.e("ERR_" + ERR_ID, ex.getMessage() + "");

        try {
             FirebaseCrash.log(ERR_ID + "");
              FirebaseCrash.report(ex);
        } catch (Exception sex) {

        }
    }

    public static void err(int ERR_ID, Exception ex, String MESSAGE) {
        Log.e("ERR_" + ERR_ID, ex.getMessage() + " - " + MESSAGE);

        try {
              FirebaseCrash.log(ERR_ID + "");
             FirebaseCrash.log(MESSAGE);
             FirebaseCrash.report(ex);
        } catch (Exception sex) {

        }
    }

    public static void err(int ERR_ID, OutOfMemoryError ex) {
        Log.e("ERR_OOM_" + ERR_ID, ex.getMessage() + "");

        try {
              FirebaseCrash.log(ERR_ID + "");
              FirebaseCrash.report(ex);
        } catch (Exception sex) {

        }
    }

    public static void err(int ERR_ID, OutOfMemoryError ex, String MESSAGE) {
        Log.e("ERR_OOM_" + ERR_ID, ex.getMessage() + " - " + MESSAGE);

        try {
            FirebaseCrash.log(ERR_ID + "");
              FirebaseCrash.log(MESSAGE);
              FirebaseCrash.report(ex);
        } catch (Exception sex) {

        }
    }

    //endregion

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        try {
            if (dir != null && dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
                return dir.delete();
            } else if (dir != null && dir.isFile()) {
                return dir.delete();
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }


    public static void message(int RES_ID) {
        try {
            Toast.makeText(context, context.getResources().getString(RES_ID), Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            err(687, ex);
        }
    }

}
