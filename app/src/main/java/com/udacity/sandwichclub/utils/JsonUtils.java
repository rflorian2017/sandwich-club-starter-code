package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Sandwich object
     * private String mainName;
     * private List<String> alsoKnownAs = null;
     * private String placeOfOrigin;
     * private String description;
     * private String image;
     * private List<String> ingredients = null;
     */
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String SANDWICH_KNOWN_AS = "alsoKnownAs";
    private static final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json){

        JSONObject sandwichJSON = null; //declare a JSON object to interpret the string given as a parameter
        try {
            sandwichJSON = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /**
         *
         * Sandwich object
         private String mainName;
         private List<String> alsoKnownAs = null;
         private String placeOfOrigin;
         private String description;
         private String image;
         private List<String> ingredients = null;
         * Gather all the data needed for the sandwich object and return directly a new object
         */

        String mainName = null;
        try {
            mainName = sandwichJSON.getJSONObject("name").getString(SANDWICH_MAIN_NAME);
        } catch (JSONException e) {
            /**
             * For simplification I am putting an empty string in case the data is not available in the JSON result
             */
            mainName = "";
            e.printStackTrace();
        }

        JSONArray knownAsArray;
        List<String> alsoKnownAs = new ArrayList<>();
        try {
            knownAsArray = sandwichJSON.getJSONObject("name").getJSONArray(SANDWICH_KNOWN_AS);
            for (int i=0; i< knownAsArray.length(); i++) {
                alsoKnownAs.add(knownAsArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String placeOfOrigin;
        try {
            placeOfOrigin = sandwichJSON.getString(SANDWICH_PLACE_OF_ORIGIN);
        } catch (JSONException e) {
            placeOfOrigin ="";
            e.printStackTrace();
        }
        String description;
        try {
            description = sandwichJSON.getString(SANDWICH_DESCRIPTION);
        } catch (JSONException e) {
            description = "";
            e.printStackTrace();
        }
        String image;
        try {
            image = sandwichJSON.getString(SANDWICH_IMAGE);
        } catch (JSONException e) {
            image = "";
            e.printStackTrace();
        }

        JSONArray ingredientsJSONArray;
        List<String> ingredients = new ArrayList<String>();
        try {
            ingredientsJSONArray = sandwichJSON.getJSONArray(SANDWICH_INGREDIENTS);
            for (int i=0; i< ingredientsJSONArray.length(); i++) {
                ingredients.add(ingredientsJSONArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
