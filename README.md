
# Money App


This app only can calculate compound interest. Eventually this will do more but right now this is my first attempt at learning Android development.

Learning goals with this iteration

* MVVM Architecture
* Composables
* Handling State

____

# Learning Journal

* I created a Hint for the TextField. This was rough because the app needed to first display the hint text in light gray color.
Then the text needed to be cleared when it was focussed for the first time, which means I needed to capture how many times it was focused.
Then the text needed to change to black to indicate that the hint won't come back. That part was the hardest, because I didn't know how files and classes are instantiated.
In essence, whenever the I typed in the text field and then moved to the next, the text would change from black to light gray. Solution for me was to call Hint separately and create multiple
instances for each TextField I made.

## New Learning Goal

Ask if there's a way to handle Hint better. It feels weird with the way I'm handling it.

# Cloning the repo

## HTTPS

`git clone https://github.com/jjhansen30/money-app.git`

## SSH

`git clone git@github.com:jjhansen30/money-app.git`
