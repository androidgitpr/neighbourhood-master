package com.source.mmt.neighbourhood.dummy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.source.mmt.neighbourhood.model.HomeFeatures;

import java.util.ArrayList;

/**
 * Created by Panacea-Soft on 14/6/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class DirectoryHome7Repository {

    public static ArrayList<HomeFeatures> getCategoryList() {
        return new Gson().fromJson(json, new TypeToken<ArrayList<HomeFeatures>>() {
        }.getType());
    }

    private static String json = "[\n" +
            "  {\n" +
            "    \"name\" : \"SmartGuard\",\n" +
            "    \"image\" : \"security\",\n" +
            "    \"color\" : \"#c42e2e\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"MyDues\",\n" +
            "    \"image\" : \"mydues\",\n" +
            "    \"color\" : \"#b50b5c\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Facilities\",\n" +
            "    \"image\" : \"building\",\n" +
            "    \"color\" : \"#7800a5\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Tickets\",\n" +
            "    \"image\" : \"ticket\",\n" +
            "    \"color\" : \"#4683d4\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Events\",\n" +
            "    \"image\" : \"event\",\n" +
            "    \"color\" : \"#5618ab\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Polls\",\n" +
            "    \"image\" : \"poll\",\n" +
            "    \"color\" : \"#3e35a2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Gallery\",\n" +
            "    \"image\" : \"gallery\",\n" +
            "    \"color\" : \"#466fd5\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Classified\",\n" +
            "    \"image\" : \"classified\",\n" +
            "    \"color\" : \"#4196a8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Contacts\",\n" +
            "    \"image\" : \"contacts\",\n" +
            "    \"color\" : \"#2e7a6a\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Opinion\",\n" +
            "    \"image\" : \"opinion\",\n" +
            "    \"color\" : \"#2b6559\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Complaints\",\n" +
            "    \"image\" : \"complaint\",\n" +
            "    \"color\" : \"#abb710\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Discussion\",\n" +
            "    \"image\" : \"discussion\",\n" +
            "    \"color\" : \"#e67e00\"\n" +
            "  }\n" +
            "]";

}
