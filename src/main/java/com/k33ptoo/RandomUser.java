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
public class RandomUser {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    static List<RandomUserModel> userModelList = new ArrayList<>();

    public static List<RandomUserModel> fetchRandomUserList(int size, RandomUserCallback randomUserCallback) {

        Task<List<RandomUserModel>> t = new Task() {
            @Override
            protected List<RandomUserModel> call() throws Exception {
                List<RandomUserModel> list = null;
                try {
                    list = new Gson().fromJson(readUrl("https://random-data-api.com/api/users/random_user?size=" + size), new TypeToken<List<RandomUserModel>>() {
                    }.getType());
                } catch (Exception e) {
                }
                return list;
            }
        };

        executorService.submit(t);
        t.setOnSucceeded(workerStateEvent -> {
            userModelList = t.getValue();
            randomUserCallback.onSuccess();
        });
        t.setOnFailed(workerStateEvent -> {
            randomUserCallback.onError("Failed Fetching Random Data");
        });
        return userModelList;
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
