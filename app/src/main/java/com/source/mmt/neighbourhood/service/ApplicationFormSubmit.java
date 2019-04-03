package com.source.mmt.neighbourhood.service;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.source.mmt.neighbourhood.model.ApplicationForm;
import com.source.mmt.neighbourhood.model.Contactinfo;
import com.source.mmt.neighbourhood.model.NeighbourHood;
import com.source.mmt.neighbourhood.model.ServiceRequest;

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

public class ApplicationFormSubmit extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... params) {
        Integer result = 0;
        BufferedReader reader = null;

        try {

            NeighbourHood nhInst = NeighbourHood.getInstance();
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url_base+"/formSubmit/AddForm");
            System.out.println("shiva calling url AddForm");

            ApplicationForm input = new ApplicationForm();
            input.setSubject(params[0]);
            input.setDetail(params[1]);
            input.setAction("add");
            input.setType(params[2]);
            input.setState("Open");
            input.setUsrId(nhInst.getUsrInfo().getUsrId());
            input.setAptId(nhInst.getUsrInfo().getAptId());
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();

            StringEntity stringEntity = new StringEntity(gson.toJson(input));
            postRequest.setEntity(stringEntity);
            postRequest.setHeader("Content-Type", "application/json");

            HttpResponse httpResponse = httpclient.execute(postRequest);


            String line;
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            if((line = reader.readLine())!=null){
                System.out.println("shiva submit answer response "+line);
            }
            //System.out.println("Status code "+httpResponse.getStatusLine().getStatusCode());

            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                System.out.println("shiva successful application form ");
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
        nhInst.setContactDetail(objectMapper.readValue(result, new TypeReference<List<Contactinfo>>(){}));
        //System.out.println("shiva size of contact info is "+contactinfos.size() +"and name "+contactinfos.get(0).getEmailId());
    }

}
