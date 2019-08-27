# The Wikipedia app

The best Wikipedia experience on your Mobile device. With the Wikipedia app, you can search and explore 40 million+ articles, no matter where you are. The wikipedia app is an app where you can easily find and read all the articles on wikipedia, you can also save your favorite articles and look in your history to see what you have already read.

## The flow

![Screenshot](<https://github.com/HoGentTIN/native-apps-1-android-creative-app-huntfalow/blob/master/Untitled%20Diagram%20(1)%20(2).png>)

As seen in the flowchart above, the app starts at the home/explore screen. On this screen the app loads in 15 random articles for you to read. Starting from the explore page you have the option to check your favorite articles and or your search history. From the explore page you also have the option to search for new and interesting articles you might like with the search bar. On the second screen (the favorites screen) you can see all the articles you have favorites before. On the third screen (the history screen) you see all the articles you have read before. 

## Project hierarchy

All the code can be foun in src/app/ when located in the main directory. The main folder was divided in 2 subfolders.

| Folder | Functionality                                                |
| :----- | ------------------------------------------------------------ |
| java   | This folder contains the code of the application that gives functionality to the app |
| res    | This folder contains the layout files for de app. These files represent de application. This folder contains the layout files for the app needed to display elements. |

The java folder is then organised in the following packages

| Package      | Functionality                                                |
| ------------ | ------------------------------------------------------------ |
| Activities   | Contains all the activities used in the app.                 |
| Adapters     | Adapters are used as a bridge between UI components and data source. |
| Fragments    | Contains all the fragments used in the app.                  |
| Holders      | Contains the viewholders, A viewholder describes an item view and metadata about its place within the RecyclerView. |
| Managers     | The manager manages al our repositories and al it's functions and the DataProvider. |
| Models       | Contains all the models, contains the data.                  |
| Providers    | A content provider manages access to a central repository of data. |
| Repositories | Contains all the repositories, a repository is central place where the data is stored and maintained. |





## Auteur

This project belongs to Vincent Coutuer - 3rd year student Applied Informatics - Hogeschool gent

