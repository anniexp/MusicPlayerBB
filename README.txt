A README.exe file of android studio java project for a  music player application

The application is a simple interpretation of a music player made with a recyclerview player.
Its written on Java. The application plays all music files in the directories of an external device. 
The project suppoerts landscape/portrait view.  Compiles API 29:Android 10.0, min sdk version is 26
The project has several Java classes - 4 activities(MainActivity, SongActivity, LoginAvtivity and RegisterActivity),
 6 layouth fies in /layouth directory an adapter, a local SQlite database and a model.
All four activies are declared in the AndroidManifest file and SongActivity is filtered as the first one to run as the application starts running.
The list of songs has a  MainActivity - the main activity is where the actual music player functionality of the 
application is being held. 
methods -onCreate(), setAudio(), getAudioFiles(), playAudio(int pos),setAudioProgress(),prevAudio(),nextAudio(), 
setPause(), timerConversion(), checkPermission()
On the playlist page is constructed of a list of songs. Each song can be played by clicking on it. Every song has
 a name and an artist as decription. There are buttons which play previous/next song, 
there is also a play/pause button at the bottom of the page



The 
activity_main is the layouth of the retrieved from the device storage. Since the playlist is a recycledview, a model,
 an adapter and a layout for a single row were nessecary. The playlist itself is a 
SongActivity - the defauth activity, which is a home page, in it there is a button for the other three activities, 
as for the button to the MainActivity can be accessed only if the user has been logged in the application.


RecyclerView.Adapter AudioAdapter - has nessesary structure of a constructor, onCreateViewHolder and onBindViewHolder methods. 
onCreateViewHolder - ecycling the view holder and putting them in positions
constructor AudioAdapter - constructs a single entry in the list
onBindViewHolder - retrieves needed data and creates the view holder
Its used to to render the item.

In the login/register activities are held onCreate methods for creating a new user/session with the rewuired and format validations.
The users information(email, password) is being stored in the database. For this application, the only register/login option for now 
is by the email/password method. 
In the dbhelper class there is theCRUD operaions (add, insert, read, drop) logic. 


 There are two build.gradle files in every android application - on app and on package level.
Inthe first one are given implementations of external lobraries and in the second one are given the dependancies to 
external repositories - f.ex. jcenter gevis access to JSON libraries and google() to being able to signin/up with google profile and more
RecyclerView AndroidX library  to the Gradle build file is needed as well as 
To be able to read/write into external directories, a permission android.permission.READ_EXTERNAL_STORAGE" is required=
 Permisssions are given in the AndroidManifest file.

The button images are being held internaly in the drawable directory 
