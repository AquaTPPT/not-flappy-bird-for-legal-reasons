First of all, congratulations for the incredible job you did! Not only you delivered a functioning game but, looking at your code, it is clear you dedicated a good amount of time and effort! You did an genuinely impressive amount of work!
Having said this, let's tackle some points where you can improve your game and, most importantly, your programming habits.

The most important question you need to ask, when starting a project is: "what is this class responsible for?". 

# OOP
- `Game`
	- just from looking at the number of properties `Game`, 16, we can tell this class has way to much responsability!!!
	- basically, the entire game state is crammed into one object
	- ask the question: what is the responsibility of this class?
		- you can think of `Game` as a coordinator, rather that a container for a bunch of variables 
		- but now, `Game` is a bit God like: it knows and does everything
			- including the complex logic of checking collisions between different entities

## Circular dependencies
- as you know, a Java program is a bunch of objects delegating to other objects, that is, a class may be dependent on another class to properly function
- you stumbled upon a common obstacle: where to instantiate objects and how to pass them references to the objects of which they depend, via constructor or setter?
	- add to this problem, the fact that your classes do not have clear responsabilities and you get... circular dependencies!
		- class A depends on class B, that depends on class A
- in your program:
	- `Game` creates and has a reference to `Playspace`, that creates an instance of `Menus`, that as a reference to `Playspace`, passed to `PauseMenu`, `Playspace` has a reference back to `Game` (that is not used there)
	- it works, because you cleverly used the keyword `this`
	- nearly every class has a reference to either `Game` or `Playspace` or both
- the result?
	- you have a very tightly coupled program, where almost no class can be understood on its own
		- every time you change something in a class, it will effect a bunch of others
		- it would be very difficult to test
- modularity, that is, classes isolated from each other, improves the system because it's easier to change parts of the code without breaking other parts

- `Playspace` creates `Menus`, `Menus` takes `Playspace` as a constructor argument, `GameOver` (inside `Menus`) calls back into playspace.getGame() to check mute state
	- `Menus` and `Playspace` are tightly coupled, meaning each one's responsabilities is not yet well determined and separated

## abstraction
- you have a `Tube` class, composed of a pair of other objects
	- `UpperTube` and `LowerTube` are inner classes, which makes sense, but... they are exactly the same!
	- so, we have here a lot of code repetition that went by unnoticed
	- the outer class `Tubes` exposes a lot of information about the inner classes, which is a bit smelly
		- maybe the collision detection could be handled here, instead of somewhere exterior
- or it could be handled by a `TubeManager`
	- this class could manage the spawning an movement of all half pipes, instead of the three separate instances of `Tube` in `Game`
- instead of a `TubeManager`, at least a `List` would be more manageble and maybe a `TubeFactory`

- `Menus` is another class with three inner classes, leaking all their internal geometry to the `MouseInteraction` class, where the clickings are being compared tith the buttons

- `Player` represents a good example of abstraction
	- properties and methods deal almost exclusivelly with being a `Player`
	- except for the reference to `Playspace`, that you use to deal with a `Menu`
		- which has nothing to do with `Player` ehhehehehe

## interfaces
- you have none...
	- which means every class is coupled to a concrete implementation
	- maybe you want to change the sound library or the scoring system
		- you would need to change every occurence in your source code
	- using interfaces, you would need to change only the instance 

# Logic

##  `ActionListener` and `Timer`
- it's amazing that you found `ActionListener`... but...
	- you've stumbled into lambda-style callbacks and event-driven animation, whatever that is!
	- it's genuinely clever that you are using `Timer` and the flag that you created, probably to deal with the fact that the `Timer` fires the event repeatedly
	- at the same time, you're not explicitly dealing with `Thread`, which would be a bit lower level, but also one very usefull obstacle to conquer
	- by using `javax.swing.Timer`, you found a trapdoor and skipped the hard part! ehhehehe

- please, be aware that it's not mandatory to deal with `Thread` in this assignment, it's just normal that it happens
	

## Design Patterns
- it could be interesting to try and implement the State DP, because of the discipline it imposes
	- you could have `PlayState`, `PauseState`, `StopState`
	- each state knows its own setup, rendering, teardown and transitions
	- the circular dependency largely dissolves because `Game` becomes the single authority that holds the current state and triggers transitions
		- menus no longer need to reach into `Playspace` to do things
	- the `Menus` god-class disappears entirely
		- each menu becomes its own class with a clear lifecycle



# Next steps
- by no means it's expected that you refactor your game to tackle the above mentioned situations
- I suggest:
	- include in the menu info about what keys to use
	- implement high score using File I/O
	- check repeated code
		- try to encapsulate in classes or simply deal with one `Tube` class and a `TubeManager`
- then, tackle the spaguetti code situation, in the next weeks, when you have time

