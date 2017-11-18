The application is called TrivagoNyTimes which demonstrates using Nytimes api to show most viewed articles and
to search for articles.

The app has two screens.
1.MostViewed - which displays most viewed articles
2.Article Search Screen - which triggers search when user enters search keywords and also loading of articles
is done page by page i.e when user reaches the end of the screen then more items are fetched and loaded.


I have used following components for the application

1.Retrofit for accessing webservice(A http library)
2.Picasso for dynamic Image loading and caching images
3.Gson for json parsing
4.Other UI components such as recyclerview,appcompat,v4 and v7 support libraries


Architecture Of TrivagoNyTimes Application

TrivagoNytimes application follows MVP architecture i.e Model View Presenter.
There are three main modules that are present in the application

UI(or View)(For Example SearchArticleActivity)
Presenter(For Example SearchArticlePresenter)
Repository(NyTimesRepository)

Ui & Presenter:
A Presenter provides the data for a specific UI component , such as a SearchArticleActivity and handles the
communication with the business part of data handling, such as calling other components(namely NytimesRespository) to load the
data.I have followed package by feature approach to easily segregate between screens(namely Home and Search)

NyTimesRepository & model:
Presenter could have  directly called the Webservice to fetch the data and assign it back to the user object.
Even though it works, the  app will be difficult to maintain as it grows. It gives too much responsibility
to the Presenter class which goes against the principle of separation of concerns.Instead, our Presenter
will delegate this work to a new NyTimesRepository module.NyTimesRepository modules are responsible for handling data operations. They provide a
clean API to the rest of the app.It uses retrofit to communicate to the server and parse the data into repective model objects.

This repository module can be extended to accommodate persisting data by following DAO pattern.

Other Supporting Modules or Packages:
api module : Supporting Retrofit Module
custom module:contains custom implementation for endlessrecyclerviewscroll and space decoration for recyclerview.
util: contains date util class to convert date to more readable string.