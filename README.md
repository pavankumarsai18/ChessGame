# Chess Game
* [How to run](#how-to-run)
* [How to play](#how-to-play)
* [Features](#features)
* [Rules Implemented](#rules-implemented)
* [Menu Bar](#menu-bar)
* [Libraries Used](#libraries-used)
* [Data Structures Used](#data-structures-used)
* [Further Improvements](#further-improvements)
* [Credits](#credits)

## How to run
1. Download the code.
2. Go to 'Game' folder.
3. To start the game please run the following command in terminal.

   **javac main.java**
   
   **java main**
4. You can enjoy the game now!!!

## How to play
The user interface is pretty intuitive. We just click the pieces and move them to the new highlighted squares. This implementation uses the normal rules of chess which include enpassent, castling, and promotion. 

![HighlightImage](/pics/Highlight.png) 

## Features
* Title Bar
* Menu Bar
  * Change Theme
  * Save Game
  * New Game
  * Load Game
* Highlight Features


## Menu Bar
* File
* Theme

### File
Using this option we can save, load, or create a new game. The save feature uses a .txt file to save all the moves that have been played in the game with the date and time stamp. The load feature asks the user for a .txt file and opens the game where the players have left off.

![FileImage](/pics/File.png)

### Theme
Using this option we can change the background of the chess board. Users can pick from three options Blue, Brown, and Green and play the game according to their preference.

![ThemeImage](/pics/Theme.png)

#### Blue Theme

![BlueTheme](/pics/BlueTheme.png)

#### Brown Theme

![BrownTheme](/pics/BrownTheme.png)

#### Green Theme

![Green Theme](/pics/GreenTheme.png)

## Rules Implemented
This game uses standard chess rules whih include castling, enpassent, and promotion.


## Libraries Used
* Java Swing
* Java Awt
* Java Util
* Java Net

## Data Structures Used
* HashSets
* HashMaps
* Arrays

## Further Improvements
The next version could include a multiplayer game which requires the use of socket programming. Additional features such as backtracking and hints could also be implemented.

## Credits
* Me
* Lcp17c


