This App simulates a “market place” when many “sellers & buyers”, or “producers & consumers” can track the evolution of availability of products. Technically the App shows the beauty of RecyclerView & its Adapter abilities to add / delete specific Items to the list by Just updating the data provider of type ArrayList containing any data model class used in the binding of the fragment single views data.

Homepage Recycler data filled
![screenshot_1550767283](https://user-images.githubusercontent.com/20923486/53185763-f089b500-35ff-11e9-8679-523631429fb3.png) 


The concept of this App is to use Timer (from Android SDK) to delete random Items every 4 seconds, and to add new Items every 3 seconds.

A button represents a “producer” will set a Timer that will add Items to the RecyclerView every 3 seconds. Another button “Consumer” will set another Timer that will start removing Items every 4 seconds. 

The idea is to simulate the market of offer – purchase of any products by a “Producer” and a “Consumer”.

To add more complexity to my project, I made the App allowing many “Producers” and many “Consumers” to come all to the same RecyclerView (imagine a stock market) and they all randomly start adding Items and removing Items at different speeds (3 seconds for the Producers and 3 seconds for the Consumers)

What was challenging in my project?

- It was harsh to follow how the Timer of 1 Consumer cannot create error by reaching “out of boundaries exception” when for example removing an Item from the data provider = ArrayList that is the “active” one that fills the RecyclerView. I had to make some debugging to figure out finally which values & conditions to set to avoid “out of boundaries exception”

- The other small challenge I made for myself was that I wanted to place the Item added by any “Producer” on the top if the RecyclerView. So I just updated the data provider = ArrayList to Swap the new Item location with the current Item located at 0, which is the top of the Recycler., so I made the top old Item take the place of the new added Item (at the end) and the new becomes the top.

- Another thing was to worry about when for example there will be no more Item to remove from the RecyclerView. That might happen if for example we click to create so many Consumers who each will remove an Item from the list. At some point we might reach that the RecyclerView has no Items anymore, so we shouldn’t allow the creation of more Consumers (Timers) and we should not allow the Timer to create a new Timer when the RecylcerView has no more Items!

- To solve the challenge of repetitive code call after 3 or 4 seconds, I made the Timer create a new Timer when the delay ends. This is called “recursive function. It’s a function that calls itself when a certain condition is true. In that case the condition was not to reach the number of Items existent in the RecyclerView less than 0 

- Another important point is that we can’t update the UI from the Timer thread! We should use runOnUiThread() when we want the Adapter to update the RecyclerView and when that is triggered from the Timer thread (after 3 or 4 seconds)!

I like Reactive programming honestly, but sure I didn’t use directly here Databinding or RxJava, but I used UI interaction designs when the delays of 3 seconds & 4 seconds ends to for example setVisibility of certain layouts to “gone” or to change the background colors from “red” when the Item is being removed, to “green” when the Item is being added. I also show the Title of the Item that was removed in a notification section.

I also like Kotlin and I was doing Kotlin in my job since January 2018, more than 1 year now. So I mixed Java and Kotlin in same project.

I tried to make a nice UI, removing the ActionBar and replacing it with styled buttons and notification area

------------ Some Screenshots --------------

Adding a Consumer

![screenshot_1550767328](https://user-images.githubusercontent.com/20923486/53185811-0c8d5680-3600-11e9-96fe-9a3e5c7f9dd5.png)

Reactive

![screenshot_1550767368](https://user-images.githubusercontent.com/20923486/53185837-2169ea00-3600-11e9-8b6d-d0615d2cc127.png) 


Consumer reached the last element to be removed

![screenshot_1550768303](https://user-images.githubusercontent.com/20923486/53186958-55dea580-3602-11e9-921d-6699514da57c.png) 

Adding a Producer // Adding many producers and many Consumers

![screenshot_1550767636](https://user-images.githubusercontent.com/20923486/53186191-ce446700-3600-11e9-804b-ddb68acdaef4.png) 

And

![screenshot_1550767780](https://user-images.githubusercontent.com/20923486/53186342-16fc2000-3601-11e9-8b68-18bb79d32527.png) 

