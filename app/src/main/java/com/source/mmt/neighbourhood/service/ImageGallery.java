package com.source.mmt.neighbourhood.service;

import android.os.AsyncTask;
import android.provider.SyncStateContract;

import com.source.mmt.neighbourhood.model.ImageGalleryCategoryModel;
import com.source.mmt.neighbourhood.model.ImageGallerySubcategoryModel;
import com.source.mmt.neighbourhood.model.NeighbourHood;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class ImageGallery extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... params) {
        Integer result = 0;
        BufferedReader reader = null;

        try {

            NeighbourHood cubs = NeighbourHood.getInstance();
            HttpClient httpclient = new DefaultHttpClient();
            StringBuilder URL = new StringBuilder("http://apicubspreschool.myprelaunch.in/api/Gallery/1");

            HttpResponse httpResponse = httpclient.execute(new HttpGet(URL.toString()));

            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if((line = reader.readLine())!=null){
                System.out.println("shiva line is "+line);
            }
            JSONObject jsonObject = new JSONObject(line);

            Integer statusCode = jsonObject.optInt("StatusCode");

            if (statusCode == 200) {
                System.out.println("shiva successful New user created ");
                parseResult(line);
                result = 1;
            } else {
                System.out.println("shiva failure response received");
            }
        } catch (Exception e) {
            System.out.println("shiva"+e.getCause());
            e.printStackTrace();
            System.out.println("shiva exception occurred ");
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        // Download complete. Lets update UI

        if (result == 1) {

        } else {

        }
    }

    /**
     * Parsing the feed results and get the list
     *
     * @param result
     */
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("ObjectResult");

            NeighbourHood cubs_inst = NeighbourHood.getInstance();
            System.out.println("shiva array size"+posts.length());
            cubs_inst.getDailyImages().clear();
            cubs_inst.getAcademicImages().clear();
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                ImageGalleryCategoryModel item = new ImageGalleryCategoryModel();
                item.setTitle(post.getString("AlbumTitle"));
                item.setUrl(post.getString("AlbumThumnail"));
                String str = post.getString("AlbumDate");
                System.out.println("shiva album name "+item.getTitle()+" date "+str);
                String[] str_split = str.split("T");
                String[] str_split1 = str_split[0].split("-");
                Date d = new Date();
                d.setYear(Integer.parseInt(str_split1[0]));
                d.setMonth(Integer.parseInt(str_split1[1]));
                d.setDate(Integer.parseInt(str_split1[2]));
                item.setDateTime(d);
                //System.out.println("shiva 1");
                JSONArray GalleryList = post.optJSONArray("GalleryList");
                for(int j = 0; j < GalleryList.length(); j++){
                    JSONObject thumbImage = GalleryList.optJSONObject(j);
                    ImageGallerySubcategoryModel image = new ImageGallerySubcategoryModel();
                    image.setUrl(thumbImage.getString("URL"));
                   // image.setIsDownload(thumbImage.getInt("CanDownload"));
                    image.setTitle(thumbImage.getString("Title"));
                    System.out.println("shiva 3 "+image.getIsDownload());
                    item.getSubcategories().add(image);

                }
                if(item.getTitle().contains("mmt")) {
                    String[] title = item.getTitle().split("-");
                    item.setTitle(title[1]);
                    cubs_inst.getAcademicImages().add(item);
                    }
                else
                    cubs_inst.getDailyImages().add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
