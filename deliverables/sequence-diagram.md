# Homework 1

1. A player joins a match
    ```text
    +---------+              +---------+
    | Client  |              | Server  |
    +---------+              +---------+
    |                        |
    | join(credentials)      |
    |----------------------->|
    |                        |
    |            gameState() |
    |<-----------------------|
    |                        |
    ```
   - The `credentials` are an object that contains the player's display name.
   - If the credentials are valid, the server responds with the game state. Otherwise, the server responds with an error. 
2. A player picks a set of cards from the living room and places them in his bookshelf
   ```text
   +---------+                        +---------+
   | Client  |                        | Server  |
   +---------+                        +---------+
        |                                  |
        | pickCards(from, to)              |
        |--------------------------------->|
        |                                  |     
        |                    chosenCards() |
        |<---------------------------------|
        |                                  |
        | placeCards(cards, column)        |
        |--------------------------------->|
        |                                  |
        |              newBookshelfState() |
        |<---------------------------------|
        |                                  |
   ```
   - `from` and `to` are the coordinates of the first and last cards chosen by the player. By allowing the player to only choose the first and last cards, the server can easily tell if it's a valid move.
   - If the `cards` are then placed in a valid `column`, the server responds with the player's new bookshelf. Otherwise, the server responds with an error. 
      
3. A player completes a common goal
    ```text
    +---------+ +---------+             +---------+
    | Client1 | | Client2 |             | Server  |
    +---------+ +---------+             +---------+
         |           |                       |
         |           |                       | ----------------------------------\
         |           |                       |-| Client1 completed a common goal |
         |           |                       | |---------------------------------|
         |           |                       |
         |           |      newScoresState() |
         |<----------------------------------|
         |           |                       |
         |           |      newScoresState() |
         |           |<----------------------|
         |           |                       |
    ```
   - When a player completes a common goal, all the players receive the new scores from the server.