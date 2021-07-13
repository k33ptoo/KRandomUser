package com.k33ptoo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author KeepToo
 */
public class RandomUserFX {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();
  
    public static void fetchRandomUserList(int size, RandomUserFXCallback randomUserCallback) {

        //create a task
        Task<List<RandomUserFXModel>> t = new Task() {
            @Override
            protected List<RandomUserFXModel> call() throws Exception {
                List<RandomUserFXModel> list = null;
                try {
                    list = new Gson().fromJson(readUrl("https://random-data-api.com/api/users/random_user?size=" + size), new TypeToken<List<RandomUserFXModel>>() {
                    }.getType());
                } catch (Exception e) {
                }
                return list;
            }
        };

        //submit task
        executorService.submit(t);
        t.setOnSucceeded(workerStateEvent -> {
            randomUserCallback.onSuccess(t.getValue());
        });        
        t.setOnFailed(workerStateEvent -> {
            randomUserCallback.onError("Failed Fetching Random Data");
        });
        
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
