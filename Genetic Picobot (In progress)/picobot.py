import random
class Picobot:
#rules are store in hashmap where key is tuple of state and surroundings and value is next move
    def __init__(self, rules):
        self.rules=rules
        map={

        }
        for x in rules:
            tu=(x.first, x.surroundings)
            map[tuple(tu)]=tuple((x.move,x.end))
        self.map=map

    def __str__(self):
        allRules=""
        for x in self.rules:
            allRules+=str(x)
        return allRules

#given is a tuple of the current state and the surroundings
    def nextMove(self, given):
        return self.map[(given)]



            
class Rules:

    def __init__(self, first, surroundings, move, end):
        self.first=first
        self.surroundings=surroundings
        self.end=end
        self.move=move

    def __str__(self):
        return str(self.first)+" "+str(self.surroundings)+" ->"+str(self.end)+" "+str(self.move)

class Room:
    loc=(1,1)
    history={(1,1):1}
    def __init__(self, width, height):
        self.width=width
        self.height=height
        loc=(random.randint(1,width),random.randint(1,height))
    
  
        
    #a way to move the bot to a particular location in the room (so long as the location is not a wall).
    def go(self,x,y):
            self.loc=(x,y)
            self.history[(x,y)]=1
    
    #a way to get the bot’s current surroundings.
    def around(self):
        list=""
        south=tuple((self.loc[0],self.loc[1]+1))
        east=tuple(((self.loc[0]+1),self.loc[1]))
        north=tuple((self.loc[0],(self.loc[1]-1)))
        west=tuple(((self.loc[0]-1),self.loc[1]))
        if self.notValid(north):
            list+="N"
        else:
            list+="*"
        if self.notValid(east):
            list+="E"
        else:
            list+="*"
        if self.notValid(south):
            list+="S"
        else:
            list+="*"
        if self.notValid(west):
            list+="W"   
        else:
            list+="*"
        return list

    #a way to calculate the percentage of spaces that the bot has visited
    def perc(self):
        return 100*len(self.history)/(self.width*self.height)

    def notValid(self,position):
        x=0
        if position[0]==0 or position[0]==self.width-1:
            return True
        if position[1]==0 or position[1]==self.height-1:
            return True
        return False


    def __str__(self):
        arr=[]
        for w in range(self.width):
            arr.append([0]*self.height)
        for i in range(len(arr[0])):
            arr[0][i]="+"
            arr[self.width-1][i]="+"

        for i in range(len(arr)):
            arr[i][0]="+"
            arr[i][self.height-1]="+"


        for w in range(self.width):
            for h in range(self.height):
                if tuple((w,h)) in self.history:
                    arr[w][h]="▒"
                if self.loc==(w,h):
                    arr[w][h]="o"
        statement=""
        for x in arr:
            line=""
            for y in x:
                if(y!=0):
                    line+=str(y)
                else:
                    line+=" "
            statement+=line
            statement+="\n"

        return statement

    
