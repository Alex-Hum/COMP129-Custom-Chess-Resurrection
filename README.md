# Overview

There are around two major features that will be implemented: ~~fixing the code redundancy found in updateAttackLists()~~ and adding the ability to upload and make a custom chess piece via file loading. The former of the two is simply making the code have a better format since there’s quite a bit of duplication occurring. The second, and larger implementation, is creating a way to easily upload a custom chess piece into the game with a text file. For instance, a file can indicate the movement of 1 Vertical, 2 Horizontal, and 3 Diagonal and when the file is uploaded a new custom chess piece will have those movements.

# Pseudocode

## Implementation 1: Code Cleanup

### NOTE: 
This portion of coding was not actually implemented; due to an oversight on my part while looking at the original code I thought there was redundancy that could be fixed. However, upon further inspection I found that the code--although incredibly similar--has slight differences depending on if the pieces are black or white and require having the implementation that was originally present. As such, I had to revok making this change.

~~if attacking piece is white\
&emsp; Make a temporary list to contain the white attack list\
endif\
if the attacking piece is black\
&emsp; Make a temporary list to contain the black attack list\
endif\
Fill the temporary list to contain no attacks\
For each piece\
&emsp; Update in the temporary list of the piece is attacking\
End for\
Update the original attack list with the temporary list’s values~~

## Implementation 2: File Upload

### In Piece.java
If a custom text file exists in /group-project/src\
&emsp; Create a new chess piece\
&emsp; For each movement type listed in the text file\
&emsp; &emsp; For each space the custom chess piece can move\
&emsp; &emsp; &emsp; Calculate the individual movement types for the new chess piece\
&emsp; &emsp; End for\
&emsp; End for\
&emsp; Assign the custom chess piece its cost\
endif

### In PieceShopPane.java
If a custom text file exists in /group-project/src\
&emsp; Display a custom chess piece option to buy\
endif

# Demonstration

1. Add a new text file called Custom_Piece.txt to /group-project/src (if one is not present already)
2. Populate the contents of the text file with movement behavior for your custom piece
  - Text File format (Where x = number)\
&emsp; Vertical x\
&emsp; Horizontal x\
&emsp; Diagonal x\
&emsp; Cost x
3. Load up the game while on MainApplication.java
4. Proceed to start a game; on the piece purchasing screen a new option to buy your custom piece should appear below the Queen piece
5. Purchase and drag as many as your custom pieces onto the board as you want
6. Add the remaining pieces you want. Repeat Steps 5 and 6 for Player 2
7. Start playing a game of custom chess with your new custom piece! Movements for your custom piece will be limited to what you set in Custom_Piece.txt

## Visual Guide

### Step 4
![Screen Shot 2023-01-30 at 10 45 46 AM](https://user-images.githubusercontent.com/72994714/215567589-e717ab28-282a-4ccf-aed1-cdf338a629b7.png)

### Step 5-6
![Screen Shot 2023-01-30 at 10 46 23 AM](https://user-images.githubusercontent.com/72994714/215567738-9c1a6ae4-d509-4abe-a4b0-766b2000038f.png)

### Step 7
![Screen Shot 2023-01-30 at 10 46 34 AM](https://user-images.githubusercontent.com/72994714/215567801-99456166-2d3e-4a9f-ae71-881df51a316d.png)


