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

    try {
      // Load data
      String jsonString = loadJsonFromAsset("quest.json", context);
      JSONObject json = new JSONObject(jsonString);

//      TODO: create here the fork to get different tours!
      JSONArray stones = json.getJSONArray("tour1"); // Here to fork into different tours create different arrays in json

      // Get Recipe objects from data
      for(int i = 0; i < stones.length(); i++){
        Stone stone = new Stone();

        stone.id = stones.getJSONObject(i).getInt("id");
        stone.name = stones.getJSONObject(i).getString("name");
        //TODO: Insert logic of different languages

        stone.addres = stones.getJSONObject(i).getString("addres");
        stone.description = stones.getJSONObject(i).getString("description");
        //TODO : insert logic of different languages
        stone.lat = stones.getJSONObject(i).getInt("lat");
        stone.lng = stones.getJSONObject(i).getInt("lng");

        //stone.imageUrl = stones.getJSONObject(i).getString("image");
        //stone.instructionUrl = stones.getJSONObject(i).getString("url");
        //stone.label = stones.getJSONObject(i).getString("dietLabel");
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
