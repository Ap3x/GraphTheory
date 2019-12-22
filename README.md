# Shortest Path Algorithum

## Why?
I was very intruiged with the way Google Maps was able to calculate the shortest path to a destination and wanted to create my own version for solving the problem.

This program is an attempt to find the shortest path using Java to visualize nodes and possible paths that can be traveled. I made this during my sophomore year in college where I double majored in Computer Science and Computer Engineering so I at the time during the making of this project I haven't taken Data Structures or Algorithms classes.

## How Does it work?
The nodes are connected if they are in a certain distance of another node. When they are connected the connection is stored in an array. The first coordinates in the array is the blue node and the last coordinates in the array is the red node. To calculate getting from the blue node to the red node the program draws a line and finds the closest node to that line. Once it finds the closest node to that line then the connection from the previous node is drawn in purple to that line. The process then starts over and another line is drawn from the current node to the red node and finds the next closest node to the line. In addition when choosing the node to connect to next it chooses the node that has a longer distance to reach the destination faster or the red dot faster.

## Examples
![Alt text](Photos/GraphingTheory1.PNG?raw=true "1")
![Alt text](Photos/GraphingTheory2.PNG?raw=true "2")
![Alt text](Photos/GraphingTheory3.PNG?raw=true "3")
![Alt text](Photos/GraphingTheory4.PNG?raw=true "4")
![Alt text](Photos/GraphingTheory5.PNG?raw=true "5")
