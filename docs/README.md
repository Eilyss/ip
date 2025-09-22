
# NeoKortex User Guide

![NeoKortex Screenshot](Ui.png)

![Banner for NeoKortex](images/NeoKortex%20Banner.png)
# Welcome to the NeoKortex!
The digital processing unit when your biogical one needs rest.

__Here in the NeoKortex you can:__
* Store all your troubles away
* Make room for new information
* Talk to your very own friendly and lovable companion

### What's not to love?

Speaking of which...

## Meet Ace!
Your friendly robotic companion that will guide you through the
NeoKortex.

![It's ACE!](images/ACE_HAPPY.png)

## But before we begin...
Here's a summary of what you need to know to understand this user guide.
{Type t} - Curly Braces indicates the type of input, the following word will be what we refer to it as.
\[a/b\] - Square braces with a slash means either a or b

And... That's it!
# Let's get started!

## Adding ToDos
The `todo` command allows you to add a ToDo Task with a given taskDescription. A ToDo Task is just a task that needs to be completed at some point

### Command Format:
`todo {String taskDescription}`

Example: `todo laundry`


```
expected output
```
![todo demonstration!](images/Todo.png)

## Adding Deadline Tasks
The `deadline` command allows you to add a Deadline Tasks with a given taskDescription and a deadline.
ACE is pretty smart, so you can be pretty lose with what time you specify as a deadline.


### Command Format:
`deadline {String taskDescription} /by {String deadline}`

Example: `deadline homework /by today!!`


```
expected output
```
![deadline demonstration](images/Deadline.png)

## Adding Event Tasks
The `event` command allows you to add an Event Tasks with a given taskDescription, a start and 
an end time.

### Command Format:
`event {String taskDescription} /from {String startTime} /to {String endTime}`

Example: `event recess week /from tomorrow homework /to this Friday`


```
expected output
```
![event demonstration](images/Event.png)

## Listing Tasks
The `list` command will get ACE to recite all the Tasks that you have added so far.

### Command Format:
`list`

Example: `list`


```
expected output
```
![List demonstration](images/List.png)
