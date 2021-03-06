# APCS-Final-2015
==========

Project Information
----------

Project Name: Snakes and Escalators! :)

Group Members: Sally Bao and Xiuzhen (Tiffany) Lei

How to run: Compile and run Game.java in Game folder.

Gameplay: You are walking along the 10th floor in Stuy when you suddenly notice that your phone has gone missing. A strange monster has taken your phone and disappeared off to the 2nd floor! Your goal is to fight the monsters on every floor in Stuy on your way to reach the second floor (and your phone...). You must eliminate all monsters on a floor before receiving a key to move down to the floor below (through the escalators or stairs). Tip: Pick up and use the bagels and coffee you pick up along the way to replenish your HP and PP.

    -----Directions-----
    Up, down, left, right --> Move in direction
    Spacebar --> Attack monsters with books
    Shift --> Change books
    B --> Use bagel
    C --> Use coffee

Project Log

5/21/15 - Fixed Animation class. Player can move in 4 directions and animates properly. Have yet to set boundaries. - Sally 

5/22/15 - Worked on Tile and TileMap classes. Acquired some wall and floor tilesets, which will be implemented through the two aforementioned classes. - Sally 

5/23/15 - Tile and TileMap classes functional. Made Character abstract class, moved Player code into Character. Encountered problems when implementing boundary dectection. - Sally 
 
5/23/15 - Worked on AStar search for monster movement (since the monsters' movements depend on the current location of the player) - Tiffany

5/25/15 - Boundary detection mostly functional. Does not work when approaching from left or above. Attack function working. - Sally

5/25/15 - Boundary detection complete. Working on collision detection. - Sally

5/28/15 - Refactored common methods into superclass MapObject. Added function of regaining power by walking. - Sally

5/31/15 - Fixed boundary detection issues. Having issues with monster generation. - Sally

6/2/15 - Previous issue resolved. Updated Floor class. Player can now move around entire floor. - Sally

6/7/15 - Added HP and PP bars. - Sally

6/10/15 - Added item dropping (monsters now leave items). - Tiffany

6/11/15 - Started key generation for each floor. A monster from a random room is chosen to hold the key. - Sally

6/13/15 - Locked floors so player cannot proceed without key. Added an art room. - Sally

6/12/15 - Safe spot added. Monsters cannot enter unless provoked by player (either by moving into their range or attacking them) - Tiffany

