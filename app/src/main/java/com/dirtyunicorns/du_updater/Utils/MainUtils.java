/*
* Copyright (C) 2014 Dirty Unicorns
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.dirtyunicorns.du_updater.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mazwoz on 12/18/14.
 */
public class MainUtils {



    private static String[] dirs;
    private static List<List<Map<String, String>>> files;

    private static final String TAG_MASTER = "dev_info";
    private static final String TAG_FILES = "dev";

    private static ConnectivityManager connectivityManager;
    private static boolean connected = false;

    public static String[] getDirs() {


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> groups = new HashMap<String, String>();

                Looper.prepare();
                JSONParser jsonParser = new JSONParser();

                String path = "http://download.dirtyunicorns.com/json.php?device=" + Build.BOARD;
                JSONObject json = jsonParser.getJSONFromUrl(path);
                JSONArray folders = null;
                try{
                    if (json != null) {
                        folders = json.getJSONArray(TAG_MASTER);
                        dirs = new String[folders.length()];
                        for (int i = 0; i < folders.length(); i++) {
                            JSONObject d = folders.getJSONObject(i);
                            String id = d.getString("filename");
                            dirs[i] = id;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        while (t.isAlive()) {
            SystemClock.sleep(200);
        }
        return dirs;
    }

    public static String[] getFiles(final String dir) {


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> groups = new HashMap<String, String>();

                Looper.prepare();
                JSONParser jsonParser = new JSONParser();

                String path = "http://download.dirtyunicorns.com/json.php?device=" + Build.BOARD + "/" + dir;

                JSONObject json = jsonParser.getJSONFromUrl(path);
                JSONArray folders = null;
                try{
                    if (json != null) {
                        folders = json.getJSONArray(TAG_MASTER);
                        dirs = new String[folders.length()];
                        for (int i = 0; i < folders.length(); i++) {
                            JSONObject d = folders.getJSONObject(i);
                            String id = d.getString("filename").replace(".zip","");
                            System.out.println(id);
                            dirs[i] = id;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        while (t.isAlive()) {
            SystemClock.sleep(200);
        }
        return dirs;
    }

    public static boolean isOnline(Context ctx) {
        try {
            connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;


        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }

}
