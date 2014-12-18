package com.dirtyunicorns.du_updater.Utils;

import android.app.ActionBar;

/**
 * Created by mazwoz on 12/18/14.
 */
public class Vars {

    private static ActionBar actionBar;
    public static int position;
    public static String[] dirs;
    public static String[] files;

    public static void SetActionBar(ActionBar actionBarl){
        actionBar = actionBarl;
    }

    public static ActionBar GetActionBar() {
        return actionBar;
    }

}
