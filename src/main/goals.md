##BASIC
- From browser, it should return a greeting with your name and the current date/time of the server. 
For instance, the browser would display: 
 
 ```"Hello Bob - the time on the server is 10:48pm on 14 March 2018"```

_you need to have appropriate tests!_
* Include in your solution a test case for proving that the "greeting" portion of the system is functioning correctly.

##ENHANCE!
Their requirements are as follows:

* You should be able to add additional people to the list. 

```For example, say you want to add Mary to your hello world, after calling the appropriate http requests it should display "Hello Bob and Mary - the time on the server is 10:59pm on 14 March 2018" _(Yes, we know that's no the late 1990's!)```


* You should then be able to add Sue to your hello world, ```"Hello Bob, Mary and Sue - the time on the server is 10:59pm on 14 March 2018"```

_ADDING_ : POST request to add to a local database, ie: an Array of Users or key value pairing with an ID.

_READING_ : GET request to get the users Array, display users. 

* You can also remove people from the greeting, you could remove Mary while keeping Sue , ```"Hello Bob & Sue - the time on the server is 11:01pm on 14 March 2018"```

_REMOVING_ : DELETE request, write method that takes a string or ID that will be the name of person to delete.

* You should be able to have a custom url to get just a list of people's names without the greetings

_CUSTOM URL_ : This is just creating another end point eg: "localhost:3000 _/all_"

* You should be able to change someones name, if you have already added Sue and Sue now want's to be called Dave, you should be able to do that.

_UPDATING_ : PUT request, get the user and replace their name.

* You can never remove yourself from the world - in this world Bob will always be there, in your world you should always be there!

_REMOVING SELF_ : add check to start of DELETE method that doesnt allow deletion of self.

* You can also assume that everyone in your hello world has a unique name, there is only ever one Bob, one Dave, one Mary, etc.
* just interact using curl or postman or your http tool of choice to send http requests to modify the state of the resource.

_Automated tests: you need to have appropriate tests for all important logic!_