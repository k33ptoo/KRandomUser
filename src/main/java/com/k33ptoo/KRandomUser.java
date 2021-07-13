package com.k33ptoo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author KeepToo
 */
public class KRandomUser {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void fetchRandomUserList(int size, KRandomUserCallback randomUserCallback) throws InterruptedException, Exception {

        //create a task
        Callable<List<KRandomUserModel>> t = () -> {
            List<KRandomUserModel> list = null;
            try {
                list = new Gson().fromJson(readUrl("https://random-data-api.com/api/users/random_user?size=" + size), new TypeToken<List<KRandomUserModel>>() {
                }.getType());
            } catch (Exception e) {
            }
            return list;
        };
        //submit task
        executorService.submit(t);
        randomUserCallback.onFinish(t.call());
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
