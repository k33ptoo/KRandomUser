# KRandomUserFX
A JavaFX Consumable Library for getting random users with abundant details from api provided by https://random-data-api.com/documentation

Usage 

Maven

``` xml
<dependency>
  <groupId>com.k33ptoo</groupId>
  <artifactId>KRandomUserFX</artifactId>
  <version>1.0.0</version>
</dependency>

```

Code

``` java
//create your observable list from your Model
private ObservableList<T> data = FXCollections.observableArrayList();

//add the random data from the lib
//specify the size of data you want to be generated

 RandomUserFX.fetchRandomUserList(100/*Size Goes here*/, new RandomUserFXCallback()) {
              @Override
              public void onSuccess(List<RandomUserFXModel> list) {
                for (RandomUserFXModel f : list) {
                    //populate your list e.g
                    data.add(
                        new T(f.getFirstName(),
                              f.getLastName(), 
                              f.getPhoneNumber(), 
                              f.getEmail()));
                }
              }
             @Override
             public void onError(String s) {
               //you can show your error here
             }
           });

```
Other user details are as follows

![](https://github.com/k33ptoo/KRandomUserFX/blob/main/getMore.png)

Credits to [Marko Manojlovic](https://github.com/thecookieorg) for developing such an awesome free api.

For more information about the api visit https://random-data-api.com/documentation