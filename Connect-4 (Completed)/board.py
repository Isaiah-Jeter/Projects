################################################################################
# Configuration
###############################################################################

DEFAULT_WIDTH = 7
DEFAULT_HEIGHT = 6
WINNING_COUNT = 4

################################################################################
# Checker
################################################################################
Checker = str     # a cheap way to represent a Checker :)

X = Checker('X')
O = Checker('O')
BLANK = Checker(' ')

################################################################################
# Board
################################################################################


class Board:
    '''
    Represents a Connect-4 board.
    '''

    def __init__(self, width=DEFAULT_WIDTH, height=DEFAULT_HEIGHT):
        '''
        Initializes a Connect-4 board of a specified width and height

        Data members:
          _width:  the number of columns in the board
          _height: the number of rows in the board
          _cells:  a map from coordinate to occupant. A coordinate is a tuple of the
                   form (column, row). An occupant is a Checker.
        '''
        self._width = width
        self._height = height
        self._cells = {}
        self.reset()

        

    def numColumns(self):

        ''' 
        Returns the number of columns
        '''
        # returns numColumns
        return self._width

    def numRows(self):
        ''' 
        Returns the number of rows
        '''
        return self._height

    def getCell(self, coord):
        ''' 
        Returns the value of the cell at input coord
        If the coordinate is invalid, it raises an assertion error
        '''
        if(not self.validCell(coord)):
            raise AssertionError

        return self._cells[coord]

    def setCell(self, coord, checker):
        ''' 
        Inputs a coordinate and a checker to add at the coordinate
        Adds the checker to the board at the coordinate
        Raises an assertion error if coordinate is invalid
        '''
        if(not self.validCell(coord)):
            raise AssertionError
        self._cells[coord] = checker

    def row(self, n):
        ''' 
        Input n, a number
        Returns the nth row
        Raises an asserion error if n is not a valid row
        '''
        if(not self.validRow(n)):
            raise AssertionError
        return [self._cells[(x,n)] for x in range(self._width)]

    def column(self, n):
        ''' 
        Input n, a number
        Returns the nth column
        Raises an asserion error if n is not a valid column
        '''
        if(not self.validColumn(n)):
            raise AssertionError
        return [self._cells[(n,x)] for x in range(self._height)]

    def rows(self):
        ''' 
        Returns all rows of self
        '''
        return [self.row(x) for x in range(self._height)]

    def columns(self):
        ''' 
        Returns all columns
        '''
        return [self.column(x) for x in range(self._width)]

    def validCell(self, coord):
        ''' 
        Returns true if the coordinate is valid
        '''
        col,row = coord 
        if(row>=self._height or row<0):
            return False
        if(col>=self._width or col<0):
            return False
        return True
    
    def validRow(self, n):
        ''' 
        Returns true if the row is valid
        '''
        return (n>=0 and n<self._height)
    
    def validColumn(self, n):
        ''' 
        Returns true if the column is valid
        '''
        return (n>=0 and n<self._width)
    ###########################################################################
    # The remaining methods are already implemented, but rely on other methods.
    ###########################################################################

    def isColumnFull(self, col):
        '''
        Returns True if the specified column is full
        '''
        return all(cell is not BLANK for cell in self.column(col))

    def isColumnEmpty(self, col):
        '''
        Returns True if the specified column is cell
        '''
        return all(cell is BLANK for cell in self.column(col))

    def isFull(self):
        '''
        Returns True if every cell has a checker.
        '''
        return all([self.isColumnFull(col) for col in range(self.numColumns())])

    def dropChecker(self, col, checker):
        '''
        Drops the checker in the specified column.
        '''
        colValues = self.column(col)
        assert any(cell is BLANK for cell in colValues), 'No Room!'
        firstEmptyRow = colValues.index(BLANK)
        self.setCell((col, firstEmptyRow), checker)

    def setBoard(self, moveString, startChecker=X):
        ''' 
        Accepts a string of column numbers and places alternating checkers in those 
        columns, starting with X.

        For example, call b.setBoard('012345') to see Xs and Os alternate on the
        bottom row, or b.setBoard('000000') to see them alternate in the left 
        column.

        moveString must be a string of integers
        '''
        currentChecker = startChecker
        nextChecker = O if startChecker is X else X
        for colDigit in moveString:
            col = int(colDigit)
            self.dropChecker(col, currentChecker)
            currentChecker, nextChecker = nextChecker, currentChecker

    def removeChecker(self, col):
        '''
        Removes the top-most checker from the column.
        '''
        # can't remove a checker if the column is empty
        assert not self.isColumnEmpty(col), 'No Checkers!'

        # if the column is full, we'll remove the checker from the last (top) row
        if self.isColumnFull(col):
            lastCheckerRow = self.numRows() - 1

        # otherwise, find the row of the last (i.e., top-most) blank;
        #  we'll remove the checker in the row below it
        else:
            lastCheckerRow = self.column(col).index(BLANK) - 1

        # remove the checker
        self.setCell((col, lastCheckerRow), BLANK)

    def winsFor(self, checker):
        '''
        Returns True if the given checker has won.
        '''

        def checkWinner(cells):
            '''
            Check whether a list of WINNING_COUNT cells is a winner
            '''
            assert len(cells) == WINNING_COUNT
            return all(cell is checker for cell in cells)

        # some helpful variables
        colMax = self.numColumns() - WINNING_COUNT
        rowMin = self.numRows() - WINNING_COUNT
        rowMax = WINNING_COUNT - 1

        # check for a winner in rows
        for r in self.rows():
            for i in range(colMax+1):
                if checkWinner(r[i:i+WINNING_COUNT]):
                    return True

        # check for a winner in columns
        for c in self.columns():
            for i in range(rowMin+1):
                if checkWinner(c[i:i+WINNING_COUNT]):
                    return True

        # check for a winner in diagonals
        def upF(r, i): return r+i
        def downF(r, i): return rowMax+r-i

        def checkDiagonals(cellF):
            for c in range(colMax+1):
                for r in range(rowMin+1):
                    diagCells = [(c+i, cellF(r, i))
                                 for i in range(WINNING_COUNT)]
                    diags = [self.getCell(cell) for cell in diagCells]
                    if checkWinner(diags):
                        return True
            return False

        # going up
        if checkDiagonals(upF):
            return True

        # going down
        return checkDiagonals(downF)

    def _stringRow(self, character, values):
        '''
        A helper method to print `values`, in a string delimited by `character` and
        which begins and ends with `character`.

        The number of values must be the same as the number of columns in the board.
        '''
        assert len(values) == self._width, values
        return f'{character}{character.join(values)}{character}'

    def __str__(self):
        '''
        Returns a string representation of the board.
        '''
        results = []
        for row in reversed(self.rows()):
            results.append(self._stringRow('|', row))
        results.append(self._stringRow('-', '-'*self._width))
        results.append(self._stringRow(
            ' ', [str(i) for i in range(self._width)]))
        return '\n'.join(results)

    def reset(self):
        '''
        Fills in every cell with a blank
        '''
        for c in range(self._width):
            for r in range(self._height):
                self.setCell((c, r), BLANK)
