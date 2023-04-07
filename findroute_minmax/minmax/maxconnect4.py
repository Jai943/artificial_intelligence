
#imported time and sys libraries for execution time and system args
#importing Minmax code - to make desicion (AI code)
#importing  maxconnect4game to get borad features and contraints to how to play a game

import sys
from MaxConnect4Game import *
from Minimax import *
import time

def oneMoveGame(currentGame,depth):
    
    start = time.time()
    
    if currentGame.pieceCount == 42:    # Is the board full already?
        print ("BOARD FULL\n\nGame Over!\n")
        sys.exit(0)
    # Make a move (only random is implemented)
    mp = MM(currentGame,depth)
    move = mp._decision_()
    currentGame.playPiece(move)
    nextMove(currentGame,move)
    currentGame.gameFile.close()

    end = time.time()
    print("one-move mode: time-taken", end-start)


def nextMove(currentGame,move):
    
    print('\n\nMove no. %d: Player %d, column %d\n' % (currentGame.pieceCount, currentGame.currentTurn, move+1))
    if currentGame.currentTurn == 1:
        currentGame.currentTurn = 2
    elif currentGame.currentTurn == 2:
        currentGame.currentTurn = 1

    print ("Game state after move:")
    currentGame.printGameBoard()

    currentGame.countScore()
    print('Score: Player 1 = %d, Player 2 = %d\n' % (currentGame.player1Score, currentGame.player2Score))

    currentGame.printGameBoardToFile()

#start = time.time()
#end = time.time()
#print(end - start)


def interactiveGame(currentGame,depth):
    # Fill me in
    # Interactive mode is implemented
    
    while not currentGame.pieceCount == 42:

        if currentGame.currentTurn == 1:
            start = time.time()
            playerMove = int(input("Choose a column to drop your piece - available columns [1-7] : "))
            if not 0 < playerMove < 8:
                print ("there are only 7 columns : choose from [1-7]")
                continue
            if not currentGame.playPiece(playerMove-1):
                print ("Alert: column full, please choose diffrent one")
                continue
            try:
                currentGame.gameFile = open("human.txt", 'w')
            except:
                sys.exit('Error opening output file.')
            nextMove(currentGame,playerMove-1)
            end = time.time()
            print("player-1time :", end-start)
            

        elif not currentGame.pieceCount == 42:
            start = time.time()
            mp = MM(currentGame,depth)
            move = mp._decision_()
            currentGame.playPiece(move)
            try:
                currentGame.gameFile = open("comupter.txt", 'w')
            except:
                sys.exit('Error opening output file.')
            nextMove(currentGame,move)
            end = time.time()
            print("player-2time :", end-start)
            
            
    currentGame.gameFile.close() # Create a game
   
    if currentGame.player1Score > currentGame.player2Score:
        print ("Player 1 wins")
    elif currentGame.player2Score > currentGame.player1Score:
        print ("Computer wins")
    else:
        print ("It's a draw")
        print ("Thank you for playing MaxConnect4Game")

def main(argv):
    # Make sure we have enough command-line arguments
    if len(argv) != 5:
        print ('Four command-line arguments are needed:')
        print('Usage: %s interactive [input_file] [computer-next/human-next] [depth]' % argv[0])
        print('or: %s one-move [input_file] [output_file] [depth]' % argv[0])
        sys.exit(2)

    game_mode, inFile = argv[1:3]
    
    if not game_mode == 'interactive' and not game_mode == 'one-move':
        print('%s is an unrecognized game mode' % game_mode)
        sys.exit(2)

    currentGame = maxConnect4Game() # Create a game

    # Try to open the input file
    try:
        currentGame.gameFile = open(inFile, 'r')
    except IOError:
        sys.exit("\nError opening input file.\nCheck file name.\n")

    # Read the initial game state from the file and save in a 2D list
    file_lines = currentGame.gameFile.readlines()
    currentGame.gameBoard = [[int(char) for char in line[0:7]] for line in file_lines[0:-1]]
    currentGame.currentTurn = int(file_lines[len(file_lines)-1][0])
    currentGame.gameFile.close()

    print ('\nMaxConnect-4 game\n')
    print ('Game state before move:')
    currentGame.printGameBoard()

    # Update a few game variables based on initial state and print the score
    currentGame.checkPieceCount()
    currentGame.countScore()
    print('Score: Player 1 = %d, Player 2 = %d\n' % (currentGame.player1Score, currentGame.player2Score))

    

    if game_mode == 'interactive':
        if argv[3] == 'computer-next': #override current turn according to commandline arguments
            currentGame.currentTurn = 2
        else: #human-next
            currentGame.currentTurn = 1
        interactiveGame(currentGame,argv[4]) # Be sure to pass whatever else you need from the command line
    else: # game_mode == 'one-move'
        # Set up the output file
        outFile = argv[3]
        try:
            currentGame.gameFile = open(outFile, 'w')
        except:
            sys.exit('Error opening output file.')
        oneMoveGame(currentGame,argv[4]) # Be sure to pass any other arguments from the command line you might need.


if __name__ == '__main__':
    main(sys.argv)
    
    