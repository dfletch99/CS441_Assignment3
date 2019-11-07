# CS441_Assignment3
Simplified Space Invaders

The app is a modified, faster-paced version of the classic game Space Invaders, where the player (a spaceship) must destroy all of the enemies before they reach the bottom of the screen.

# The Player
The player's ship is moving back and forth across the bottom of the screen at a constant speed, and cannot be controlled by the player. This means the player must be more precise with their shots, since they cannot stop or move the other way until they hit the side of the screen.

Whenever the screen is tapped, the spaceship fires a shot that moves directly upwards from wherever the ship was. There can only be one shot on the screen at a time, and the shot does not disappear unless it hits an enemy or misses and flies off the screen.

# The Enemies
The enemies are a 10x5 grid of android bots, and they begin the game by slowly moving across the screen. Whenever one side of the grid hits the edge of the screen, the entire grid moves downward a constant distance. If any of the robots reach the bottom of the screen and collide with the player, the game is over and the player loses.

As more and more enemies are destroyed, they move faster. This adds a sense of urgency as the number of androids wind down and the player's shots need to be more and more precise, lest they take too long and lose.

If, however, the player manages to destroy all 50 enemies, then the game ends with a victory screen.

The game keeps track of how many shots the player fires, and upon winning, displays the player's accuracy. It is possible for the accuracy to be above 100%, since a shot can hit two enemies at once if timed correctly (or, if you're really lucky/skilled, it can even hit three).
