package com.source.mmt.neighbourhood.service;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.source.mmt.neighbourhood.model.Contactinfo;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.model.SurveyQuestions;
import com.source.mmt.neighbourhood.model.Userinfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.source.mmt.neighbourhood.util.constants.url_base;

public class GetSurveyQuestions extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... params) {
        Integer result = 0;
        BufferedReader reader = null;

        try {

            NeighbourHood cubs = NeighbourHood.getInstance();
            HttpClient httpclient = new DefaultHttpClient();
            StringBuilder URL = new StringBuilder(url_base+"/getContacts");
            HttpPost postRequest = new HttpPost(url_base+"/getSurveyQuestions");
            System.out.println("shiva calling url"+URL.toString() + "input "+params[0]);

            SurveyQuestions inParam = new SurveyQuestions();
            inParam.setAptId(Integer.parseInt(params[0]));
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();

            StringEntity stringEntity = new StringEntity(gson.toJson(inParam));
            postRequest.setEntity(stringEntity);
            postRequest.setHeader("Content-Type", "application/json");

            HttpResponse httpResponse = httpclient.execute(postRequest);


            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if((line = reader.readLine())!=null){
                System.out.println("shiva get contacts response "+line);
            }
            System.out.println("Status code "+httpResponse.getStatusLine().getStatusCode());

            int statusCode = httpResponse.getStatusLine().getStatusCode();

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
    private void parseResult(String result) throws IOException {
        NeighbourHood nhInst = NeighbourHood.getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        nhInst.setSurveyQList(objectMapper.readValue(result, new TypeReference<List<SurveyQuestions>>(){}));
        //System.out.println("shiva size of contact info is "+contactinfos.size() +"and name "+contactinfos.get(0).getEmailId());
    }

}
