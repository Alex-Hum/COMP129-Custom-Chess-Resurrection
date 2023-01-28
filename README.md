# Overview

There are around two major features that will be implemented: fixing the code redundancy found in updateAttackLists() and adding the ability to upload and make a custom chess piece via file loading. The former of the two is simply making the code have a better format since there’s quite a bit of duplication occurring. The second, and larger implementation, is creating a way to easily upload a custom chess piece into the game with a text file. For instance, a file can indicate the movement of 1 Vertical, 2 Horizontal, and 3 Diagonal and when the file is uploaded a new custom chess piece will have those movements.

Pseudocode

## Implementation 1: Code Cleanup

if attacking piece is white\
&emsp; Make a temporary list to contain the white attack list\
endif\
if the attacking piece is black\
&emsp; Make a temporary list to contain the black attack list\
endif\
Fill the temporary list to contain no attacks\
For each piece\
&emsp; Update in the temporary list of the piece is attacking\
End for\
Update the original attack list with the temporary list’s values

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
