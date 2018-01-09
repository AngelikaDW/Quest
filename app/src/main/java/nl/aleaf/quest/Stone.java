/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package nl.aleaf.quest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Stone {
    //public static final String TAG = getSimpleName();
    public int id;
    public String name;
    public String name_DE;
    public String name_NL;
    public String addres;


    public int tour;

    public String description;
    public String description_DE;
    public String description_NL;

    public int lat;
    public int lng;
    public int match;
    public String imageUrl;


    public static ArrayList<Stone> getStonesFromFile(String filename, Context context){
    final ArrayList<Stone> stonesList = new ArrayList<>();

        /*Get Tournumber from SelectTourActivity*/
        SharedPreferences tourselected = MyApplication.getInstance().getSharedPreferences(SelectTourActivity.PREFS_NAME, Context.MODE_PRIVATE);
        int numberTour = tourselected.getInt("Tournumber", 0);
        //Log.i("TourNbr StoneClass", String.valueOf(numberTour));
        String tourNbr = "tour"+numberTour;

        /*Get Display Language from Shared Pref*/
        SharedPreferences deviceLanguage = MyApplication.getInstance().getSharedPreferences(SelectTourActivity.PREFS_NAME, Context.MODE_PRIVATE);
        String language = deviceLanguage.getString("displayLanguage", "error");
        // /language = "nl";
        //Log.i("Language StoneClass", language);

    try {
      // Load data
      String jsonString = loadJsonFromAsset("quest.json", context);
      JSONObject json = new JSONObject(jsonString);

      //Depending on Tour selected (saved in shared Preferences), tour gets selected from json file
      JSONArray stones = json.getJSONArray(tourNbr); // Here to fork into different tours create different arrays in json

      // Get stone objects from data
      for(int i = 0; i < stones.length(); i++){
        Stone stone = new Stone();

        stone.id = stones.getJSONObject(i).getInt("id");

        //Get name of stone in 3 languages depending on device language
        if (language.equals("de")) {
            stone.name = stones.getJSONObject(i).getString("name_DE");
        }
        else {
            if (language.equals("nl")) {
                stone.name = stones.getJSONObject(i).getString("name_NL");
            }
            else {
                stone.name = stones.getJSONObject(i).getString("name");
            }
        }

        stone.addres = stones.getJSONObject(i).getString("addres");

        //Get description of stone in 3 languages depending on device language
          if (language.equals("de")) {
              stone.description = stones.getJSONObject(i).getString("description_DE");
          }
          else {
              if (language.equals("nl")) {
                  stone.description = stones.getJSONObject(i).getString("description_NL");
              }
              else {
                  stone.description = stones.getJSONObject(i).getString("description");
              }
          }

        stone.lat = stones.getJSONObject(i).getInt("lat");
        stone.lng = stones.getJSONObject(i).getInt("lng");

        stone.imageUrl = "image"+ stones.getJSONObject(i).getInt("id");
        stonesList.add(stone);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return stonesList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
    String json = null;

    try {
      InputStream is = context.getAssets().open(filename);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
    }
    catch (java.io.IOException ex) {
      ex.printStackTrace();
      return null;
    }

    return json;
    }

    }
