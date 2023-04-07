Name/UTA-ID - Jayachandra Jarajapu/1001964536
Course level - 5360
___________________________________________________________________________________________________
code done in Python 3.10.2 
___________________________________________________________________________________________________
Functionalities include :

imported functionalities both maxconnect4.py and maxconnect4game.py from given
Done minmax class, 
	- decision method to makes decisions 
	- build min max functions for alpha - beta pruning 
	- utlity function keep track of scores
Included an interactive mode function to play with computer or human in maxconnect4.py
___________________________________________________________________________________________________
Instructions for execution:
Use the following commands in the command prompt to run the code

For interactive mode -:
Python maxconnect4.py interactive [input_file] [computer-next/human-next] [depth]
>python maxconnect4.py interactive input1.txt computer-next 5

For one-move mode -:
python maxconnect4.py one-move [input_file] [output_file] [depth]
>python maxconnect4.py one-move input1.txt output.txt 6

___________________________________________________________________________________________________

Time complexity:
one-move mode - time analysis
Input file used for this  -  input1.txt
The times are based on mac-osx (pc)

syntax :- time python maxconnect4.py one-move [input_file] [output_file] [depth]
>time python maxconnect4.py one-move input1.txt output.txt 1

my pc returned .02 at everydepth for one-mode (it might be rounding off 2 decimals) 
please find file (time-complex1.txt)

!!!----So, i wrote some script to get time-complexity for both the modes while returning script itself ----!!!
please find file (code-output.txt) ---- (one-move mode)
______________________________________
___________________ 


    depth   |   Time (in seconds)
    ________|____________________
    1       |   0.00263214111328125
    2       |   0.005290985107421875
    3       |   0.007795095443725586
    4       |   0.009219884872436523
    5       |   0.011301040649414062
    6       |   0.013425111770629883
    7       |   0.015295028686523438
    8       |   0.018194913864135742
    9       |   0.019390106201171875
    10      |   0.020722150802612305
    11      |   0.02347087860107422
    12      |   0.02553391456604004
	13      |   0.027420997619628906
    14      |   0.028211116790771484
    15      |   0.03121805191040039
    16      |   0.0326690673828125
    17      |   0.03481793403625488
    18      |   0.0355960807800293
	19      |   0.038791972732543945
    20      |   0.03833603858947754
	21      |   0.041979074478149414
    22      |   0.043015947341918945
    23      |   0.04612898826599121
    24      |   0.04613995552062988
    25      |   0.050093889236450195
    26      |   0.050582170486450195
    27      |   0.05397796630859375
	28      |   0.0583798885345459
    29      |   0.05672001838684082
    30      |   0.0592198371887207
    31      |   0.0605158805847168
    32      |   0.06232404708862305
	33      |   IndexError: list index out of range
	34      |   0.0688180923461914

from depth level 35 onwards is triggering error -  IndexError: list index out of range
_________________________________________________________________________________________________
