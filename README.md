# Design a Snakes and Ladder game LLD
Question Link: https://workat.tech/machine-coding/practice/snake-and-ladder-problem-zgtac9lxwntg

### There's a corner case in the problem
If the number of dice of more than one then there is a possibility of **deadlock**.
For example: Take 2 dice, if all players get to number of cells-1 position then no one can move to the final cell
as the minimum move will be 2, but players need 1 to get to the final cell. I have handled it by marking player won if
he exceeds the final cell as well.