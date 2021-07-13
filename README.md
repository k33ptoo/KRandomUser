# KRandomUser
A JavaFX /Java Swing Consumable Library for getting random users data with abundant details from api provided by https://random-data-api.com/

**Usage** 

Maven

``` xml
<dependency>
  <groupId>com.k33ptoo</groupId>
  <artifactId>KRandomUser</artifactId>
  <version>1.0.0</version>
</dependency>

```

Code

``` java

//example for javafx
//create your observable list from your Model
private ObservableList<T> data = FXCollections.observableArrayList();

//for java swing you can simply create a List<T>

//add the random data from the lib
//specify the size of data you want to be generated

           try {
            KRandomUser.fetchRandomUserList(30, list -> {
                for (KRandomUserModel f : list
                ) {
                  //populate your javafx list e.g
                    data.add(new T(f.getFirstName() + " " + f.getLastName(), f.getPhoneNumber(), f.getEmail() /*etc*/));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

```
Other user details are as follows

![](https://github.com/k33ptoo/KRandomUser/blob/main/getMore.png)

Credits to [Marko Manojlovic](https://github.com/thecookieorg) for developing such an awesome free api.

For more information about the api visit https://random-data-api.com/documentation