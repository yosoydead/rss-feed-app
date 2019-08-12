* Name of the app: **RssFeed**.
* API used: apple's rss feed. [This is the link to the api](https://rss.itunes.apple.com/en-us). Format used: **XML ATOM**.
* Technologies used so far:
  1. **Kotlin's coroutines** for doing background tasks.
  2. **HttpsURLConnection** for opening an URL and downloading the xml content.
  3. **XML Pull Parser** for parsing the incoming xml.
  4. **Android Architecture Components**: Live Data and View Models. Used live data to expose some properties to every fragment created. Created a view model that is shared by all the fragments so I don't waste more memory and computational power to download and parse data for every fragment everytime a new fragment and view model is created.
  5. **Android Navigation Components**: Used the navigation graph to create 3 **fragments** for every type of data I wanted to download. Hosted that graph on the main activity. I've overridden the default behaviour of the back button when using the 3 fragments; instead of popping the back stack, the app minimizes. Also, the back button from the ActionBar doesn't show up when navigating away from the main fragment.
  6. **Picasso** for downloading pictures in the background.
  7. **Material Design library**. I've used the bottom navigation component from this library.
  8. **Recycler View**
  9. **Gesture detection**. Created a class that implements the **RecyclerView.SimpleOnItemTouchListener()** interface so I can use it to set an **itemTouchListener** on the recycler view. This class contains an interface that has 2 methods. The **gesture detection object** overrides the **onSingleTapUp** and **onLongPress** methods.
  10. **Intents**. **Explicit** intent was used to open a new activity that shows details of a movie/book/song. **Implicit** intent was used to send some text via apps that can send text. **Tested it using the facebook messenger and it works**.
  11. **Animation resources** for animating fragment transition.
* Architecture used: **MVVM**. Used models to create and store data about songs, books or movies.
* Version control: **git**.
* Icon creator: [link](https://android-material-icon-generator.bitdroid.de).
* Icon resource: [link](https://flaticon.com/free-icon/rss-file-format-symbol_29040#term=rss&page=1&position=51).
