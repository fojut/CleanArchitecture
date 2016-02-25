# CleanArchitecture
**Summary**
-----------------
This is a sample android app based on MVP Clean Architecture.

The UI is not completed yet, just backend code(retrieve users) form server for studying now...

**MVP-Architecture**
-----------------
![MVP-Architecture](http://upload-images.jianshu.io/upload_images/268450-3951595406461dee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**Clean-Architecture**
-----------------
![Clean-Architecture](http://www.jcodecraeer.com/uploads/20150921/1442799178594699.jpg)

**Android-Architecture**
-----------------
![Android-Architecture](http://www.jcodecraeer.com/uploads/20150921/1442799178912704.jpg)

**Reactive Stream**
-----------------
![Reactive-Stream](http://www.jcodecraeer.com/uploads/20150921/1442799526265247.jpg)

**Project Structure**
-----------------
- **data：** Data Repository, Request Service for Rest API, Request Client

- **domain：** Use Case

- **presentation：** Presenters, Views, Thread Executor, Domain and Data Mapper

**Using Libraries**
-----------------
RxJava + Dagger + Retrofit + ButterKnife 

**Testing**
-----------------
- **data layer：** Android instrumentation / espresso

- **domain layer：** JUnit + mockito

- **presentation layer：** Robolectric + junit + mockito




