Name: Jayachandra Jarajapu
UTA ID: 1001964536
sec_id - CSE-5360-002

Tower of Hanoi:

Constants : A,B,C,disk1,disk2,disk3,disk4,disk5,X

Predicates: (X object) - X is an object
	    (X Empty) - X is Empty
	    (Clear X) - Clear the object X
	    (on X Y) - Move the object from X to Y
	    (smaller X Y) - conditioner place smaller disk on top (Place X on Y) 



7-Puzzle Problem:

Constants: one,two,three,four,five,six,seven,eight,nine,1,2,3,4,5,6,7,X

Predicates: (X Tile) - The Tile is X
	    (X Location) - The location is X
	    (X Empty) - X is Empty
	    (on X Y)   - X is On position Y
	    (Clear X)  - Clears the X in the table
	    (adj X Y) - adjacent node of X is Y


Program compilation:-

Run on UTA OMEGA SERVER
then enter below command

#Make graph plan

make graphplan

#execute with input and ops file

graphplan -o [operators_file] -f [facts_file]

		
		