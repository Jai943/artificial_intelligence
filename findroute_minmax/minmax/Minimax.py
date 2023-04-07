from MaxConnect4Game import *
import copy


# class mm(a.k.a minmax) contains few functions to initialize variables, making desicions, min (alpha-beta), max(alpha-beta)
class MM:
	def __init__(self, game, depth):
		self.currentTurn = game.currentTurn
		self.game = game
		self.maxDepth = int(depth)

	# decision tree - figures moves possibles for the computer
	def _decision_(self):
		mp = _pm_(self.game.gameBoard)
		a = 0
		c = 0
		for move in mp:
			c+=1
			o = _result_(self.game,move)
			re = self._minval_(o,float('inf'),-float('inf'))
			if mp[a] < re:
				a=c
		mc = mp[a]
		return mc

	#function of min operations along with alpha - beta pruning of min tree
	def _minval_(self, state, Alpha, Beta):
		if state.pieceCount == 42 or state.nodeDepth == self.maxDepth:
			return self._u_(state)
		z = float('inf')
		for move in _pm_(state.gameBoard):
			newState = _result_(state,move)
			z = min(z,self._maxval_(newState,Alpha,Beta))
			if z <= Alpha:
				return z
			Beta = min(Beta, z)
		return z

	#function of max operations along with alpha - beta pruning of min tree
	def _maxval_(self, state, Alpha, Beta):
		if state.pieceCount == 42 or state.nodeDepth == self.maxDepth:
			return self._u_(state)
		z = -float('inf')
		for move in _pm_(state.gameBoard):
			newState = _result_(state,move)
			z = max(z,self._minval_( newState,Alpha,Beta ))
			if z >= Beta:
				return z
			Alpha = max(Alpha, z)
		return z

	#utility stores playerbscores
	def _u_(self,state):
		if self.currentTurn == 1:
			_u_ = int(state.player1Score * 2 - state.player2Score)
		elif self.currentTurn == 2:
			_u_ = int(state.player2Score * 2 - state.player1Score)
		return _u_
	

#returns the moves possible on the board
def _pm_(board):
	moves = []
	for col, colVal in enumerate(board[0]):
		if colVal == 0:
			moves.append(col)
	return moves
		
#cals the moves in previous games and makes decisions for new games
def _result_(Current_Game, column):
	New_Game = maxConnect4Game()
	#trycatch depth should be 1 , or it may considered as 1
	try:																
		New_Game.nodeDepth = Current_Game.nodeDepth + 1					
	except AttributeError:
		New_Game.nodeDepth = 1

	New_Game.pieceCount = Current_Game.pieceCount	

	# copies current moves in gameboard and saves to new gameboard					
	New_Game.gameBoard = copy.deepcopy(Current_Game.gameBoard)
    
    #if cuurent position is empty increment the piece count
	if not New_Game.gameBoard[0][column]:								
		for i in range(5, -1, -1):										
			if not New_Game.gameBoard[i][column]:
				New_Game.gameBoard[i][column] = Current_Game.currentTurn
				New_Game.pieceCount += 1
				break

	#to giveout the player turns as in alternative way	
	if Current_Game.currentTurn == 1:									
		New_Game.currentTurn = 2										
	elif Current_Game.currentTurn == 2:									
		New_Game.currentTurn = 1

	New_Game.checkPieceCount()
	New_Game.countScore()

	return New_Game
