# Definitions of two sequences, which we will use later in the assignment
from functools import cache
EGS = 'GTACGTCGATAACTG'
WGS = 'TGATCGTCATAACGT'

def lcs_len(seq1, seq2):
    '''
    Returns the length of the longest common subsequence of seq1 and seq2.

    Here are some example uses of lcs_len:
    
    >>> lcs_len('x', 'y')
    0

    >>> lcs_len('x', 'y')
    0

    >>> lcs_len('xy', 'xz')
    1

    >>> lcs_len('car', 'cat')
    2

    >>> lcs_len('can', 'ant')
    2

    >>> lcs_len('human', 'chimpanzee')
    4
    '''

    # base case: the length of the LCS of an empty string and another string is 0
    if not seq1 or not seq2: 
        return 0

    # if the first characters of each string agree, count the character towards the LCS
    if seq1[0] == seq2[0]:             
        useIt = 1 + lcs_len(seq1[1:], seq2[1:]) 
    else:
        useIt = 0

    # make the problem smaller by peeling away the first character of seq2 or 
    # the first character of seq1 and computing the resulting LCS
    loseIt1 = lcs_len(seq1[1:], seq2)                        
    loseIt2 = lcs_len(seq1, seq2[1:])

    # choose the longest answer 
    return max(useIt, loseIt1, loseIt2)

def lcs(seq1, seq2):
    '''
    Returns the length of the longest common subsequence of seq1 and seq2.

    Here are some example uses of lcs_len:
    
    >>> lcs("x", "y")
    (0, '#', '#')

    >>> lcs("spam", "")
    (0, '####', '')

    >>> lcs("spa", "m")
    (0, '###', '#')

    >>> lcs("cat", "car")
    (2, 'ca#', 'ca#')

    >>> lcs("cat", "lca")
    (2, 'ca#', '#ca')

    >>> lcs("human", "chimpanzee")
    (4, 'h#man', '#h#m#an###')
    '''

    # base case: the length of the LCS of an empty string and another string is 0
    if not seq1 or not seq2: 
        return 0, '#'*len(seq1),'#'*len(seq2)

    # if the first characters of each string agree, count the character towards the LCS
    if seq1[0] == seq2[0]:
        store=lcs(seq1[1:], seq2[1:])             
        useIt = store[0]+1, seq1[0]+store[1], seq1[0]+store[2]
    else:
        store=lcs(seq1[1:], seq2[1:])
        useIt =store[0],"#"+store[1],"#"+store[2]

    # make the problem smaller by peeling away the first character of seq2 or 
    # the first character of seq1 and computing the resulting LCS
    store=lcs(seq1[1:], seq2)
    loseIt1 = store[0], "#"+store[1], store[2]    
    store2=lcs(seq1, seq2[1:])                 
    loseIt2 = store2[0], store2[1], "#"+store2[2]  
   
    # choose the longest answer 
    big= max(useIt[0], loseIt1[0], loseIt2[0])
    if useIt[0]==big:
        return useIt
    elif loseIt1[0]==big:
        return loseIt1
    else:
        return loseIt2

@cache
def align(seq1, seq2):
    '''
    Returns the length of the longest common subsequence of seq1 and seq2.

    Here are some example uses of lcs_len:

    >>> align("ATTGC", "GATC")
    (3, '-ATTGC', 'GAT--C')
  
    '''

    # base case: the length of the LCS of an empty string and another string is 0
    if not seq1 or not seq2: 
        if(seq1):
            return 0,seq1,"-"*len(seq1)
        if(seq2):
            return 0,"-"*len(seq2),seq2
        else:
            return 0, "",""

    # if the first characters of each string agree, count the character towards the LCS
    if seq1[0] == seq2[0]:
        store=align(seq1[1:], seq2[1:])             
        useIt = store[0]+1, seq1[0]+store[1], seq2[0]+store[2]
    else:
        store=align(seq1[1:], seq2[1:])
        useIt =store[0], "-"+seq1[0]+store[1], seq2[0]+"-"+store[2]

    # make the problem smaller by peeling away the first character of seq2 or 
    # the first character of seq1 and computing the resulting LCS
    store=align(seq1[1:], seq2)
    loseIt1 = store[0], seq1[0]+store[1], "-"+store[2]    
    store2=align(seq1, seq2[1:])                 
    loseIt2 = store2[0], "-"+store2[1], seq2[0]+store2[2]  
    
    # choose the longest answer 
    big= max(useIt[0], loseIt1[0], loseIt2[0])
    if useIt[0]==big:
        return useIt
    elif loseIt1[0]==big:
        return loseIt1
    else:
        return loseIt2

if __name__ == '__main__':

   import doctest

   print(doctest.testmod())
