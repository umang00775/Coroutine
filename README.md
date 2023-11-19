# what is coroutine?

# Functions:
A function is a sequence of instructions that takes input and gives us outputs.

A thread describes in which context this sequence of instructions should be executed.

# Why threading is important for android apps?
--> In normal app, all the programs are generally executed in a single thread (main thread).
--> We need it for complex calculations, network calls, database operations, etc.


# Coroutines can do all the stuff a thread do, even more than that.

# Coroutines vs Threads
--> Coroutines executed within a thread.
--> Coroutines are suspendable, but thread are not.
--> Coroutines cannot switch context.
 

---------------------------------------------------------------------------------------------

To implement coroutine, add these dependencies:
val coroutinesVersion = "1.6.4"
implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")


---------------------------------------------------------------------------------------------

delay() is different from sleep(). delay() will not block a coroutine, it will pause the coroutine.

coroutine will cancelled when main thread finishes.

delay() must be called from Coroutine or another suspend function.

We cannot call suspend function from outside of Coroutine. (In this code Coroutine --> Globalscope.launch{...})

--------------------------------------------------------------------------------------------

For context we need to pass Dispatcher in launch (Globalscope.launch(Dispatchers.Main or IO or Something){...})
Dispatchers.Main --> Starts coroutine in main thread, Useful to change UI on the main thread
Dispatchers.IO --> Usefull in all kind of data operations
Dispatchers.Default --> Used in complex and long running calculations (eg. List of some 10k elements)
Dispatchers.Unconfined --> Use if we are unconfied :

OR 

We can start our new thread by
Globalscope.launch(newSingleThreadContext("{name}")){...}

-------------------------------------------------------------------------------------------

# To switch context

GlobalScope.launch(Dispatchers.IO) {  /* Not in Main thread */
    delay(3000L)
    Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
    withContext(Dispatchers.Main){ /* Change context to main thread */
        Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
    }
}


----------------------------------------------------------------------------------------------

# Difference b/w GlobalScope.launch(Dispatchers.Main){...} and runBlocking{...}

--> GlobalScope doesn't block the main thread, but run blocking do.


--> We can use delay() in runBlocking as it is also a coroutine

--> runBlocking{delay(1000)} is exactly same as Thread.sleep(1000)

------------------------------------------------------------------------------------------------

We can launch a coroutine in runBlocking asynchronously
runBlocking {
    /* Main Thread */
    launch(Dispathers.IO) {
        /* Another Asynchronous thread */
    }
    /* Main Thread */
}

--> We can use multiple launch{...} inside a runBlocking coroutine.

--------------------------------------------------------------------------------------------------

# If we have to use coroutine inside a RecyclerViewAdapter (Inside onBindVH) then use CoroutineScope:

CoroutineScope(Dispatchers.IO).launch{
    val imageStore = Picasso.get().load(messageCard.imageUrl)

    withContext(Dispatchers.Main){
         imageStore.into(holder.dp)
    }
}
