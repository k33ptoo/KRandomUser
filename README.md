# KRandomUser
A JavaFX /Java Swing Consumable Library for getting 100 random users data with abundant details from api provided by https://random-data-api.com/

**Usage** 

Maven

``` xml
<dependency>
  <groupId>com.k33ptoo</groupId>
  <artifactId>KRandomUser</artifactId>
  <version>1.0.1</version>
</dependency>

```

Code

``` java

 /**
  * How to use on Java Swing
  * InvokeLater to prevent UI Blocking
  */
   SwingUtilities.invokeLater(()->{
    KRandomUser.fetchRandomUserList(50, (List<KRandomUserModel> data) -> {
               //do something with the list of data
    });            
  });    
  

 /**
   * How to use on JavaFX without blocking the UI
   * You have to run on a task
   */
  ExecutorService es = Executors.newCachedThreadPool();
  Task<ObservableList<T>> t = new Task() {
        @Override
        protected Object call() throws Exception {
            ObservableList<T> oLst = FXCollections.observableArrayList();
            KRandomUser.fetchRandomUserList(50, list -> {
                for (KRandomUserModel f : list) {
                    oLst.add(/*whatever infor you want*/);
                }
            });
            return oLst;
        }

    };
    es.submit(t);
    t.setOnSucceeded((evt) -> {
        tableView.setItems(t.getValue());
    });


```
Other user details are as follows

![](https://github.com/k33ptoo/KRandomUser/blob/main/getMore.png)

Credits to [Marko Manojlovic](https://github.com/thecookieorg) for developing such an awesome free api.

For more information about the api visit https://random-data-api.com/documentation
