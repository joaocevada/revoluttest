# Notes

Regarding async aproach: 
I opted to use a mix a coroutines with channels and LiveData's instead of RxJava/LiveDataReactiveStreams. I did this to check how this project could be implemented without RxJava but in the end I wasn't happy with the result since it ended up being hard to mix LiveData's with coroutines channels. So, now If I would have to do this now I would follow a different approach.


Regarding the MVVM architecture:
I used an MVVM architecture with an interface for actions and a ViewState for holding UI information. For the ViewState I ended up implementing a crude reducer but If I had more time I would have implemented a more formal reducer with the help of coroutines actors in order to make some operations like ordering lists async.


Regarding testing:
I just implemented some basic UI and Unit tests just to show how this project can be tested. Also the UI tests can be run both on a physical device or using roboelectric without a physical device. 
