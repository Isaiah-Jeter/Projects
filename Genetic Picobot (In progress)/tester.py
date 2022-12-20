import picobot

r1=picobot.Rules(0,"***x","S",0)
r2=picobot.Rules(0,"***S","E",2)
r3=picobot.Rules(2,"*x**","E",2)
r4=picobot.Rules(2,"*E**","N",3)
# state 0 with nothing N: go one step N
r5=picobot.Rules(3,"x***","N",3)

# state 0 with something to the N: go W + into st 1 
# ** This will crash if picobot has a wall to the W! **
r6=picobot.Rules(3,"N***","W",1)

# state 1 with nothing to the S: go one step S
r7=picobot.Rules(1,"***x","S",1)
r8=picobot.Rules(1,"***S","X",3)

arr=[r1,r2,r3,r4,r5,r6,r7,r8]

Tali=picobot.Picobot(arr)
room=picobot.Room(10,10)
print(room)

