# CleanArchitecture
**Summary**
-----------------
This is a sample android app based on MVP Clean Architecture.

The UI is not completed yet, just backend code(retrieve users) from server for studying now...

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
RxJava + Dagger2 + Retrofit2 + OkHttp3 + ButterKnife 

**Testing**
-----------------
- **data layer：** Android instrumentation / espresso

- **domain layer：** JUnit + mockito

- **presentation layer：** Robolectric + junit + mockito

**License**
-----------------
    Copyright 2015 fojut

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
