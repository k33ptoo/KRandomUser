package com.k33ptoo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KeepToo
 */
public class KRandomUser {
    
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    
    public static void fetchRandomUserList(int size, KRandomUserCallback randomUserCallback) {
        if (size < 101) {
            //create a callable
            Callable<List<KRandomUserModel>> t = () -> {
                return new Gson().fromJson(readUrl("https://random-data-api.com/api/users/random_user?size=" + size), new TypeToken<List<KRandomUserModel>>() {
                }.getType());
            };
            
            FutureTask<List<KRandomUserModel>> ft = new FutureTask<>(t);
            //submit the task
            executorService.submit(ft);
            try {
                randomUserCallback.onFinish(ft.get());
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(KRandomUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ft.isDone()) {
                executorService.shutdown();
            }
        } else {
            try {
                throw new Exception("Sorry the api only allows maximum list of 100");
            } catch (Exception ex) {
                Logger.getLogger(KRandomUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
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
