__author__ = 'apple1'
class Transition:
    def __init__(self,value,state,cost,change,up):
        self.value=value
        self.state=state
        self.change=change
        self.cost=cost
        self.up=up
class State(Transition):
    def __init__(self,name,isfinal):
        self.name=name
        self.isfinal=isfinal
        self.transi=[]
    def add_transition(self, value):
        self.transi.append(value)

    def mixcost(self, value):
        send = []
        for x in self.transi:
            if x.value == value:
                send.append(x)
        return send

    def mincost(self, value):
        for x in self.transi:
            if x.value == value:
                return x
        return None

class Tranducers(State):
    def __init__(self):
        self.allnames=[]
        self.allstates=[]
    def add(self,istate,ostate,value,cost,change):
        if(value==change):
            up=0
        else:
            up=1
        if len(self.allnames) == 0 :
            self.allnames.append(istate)
            self.allnames.append(ostate)

        if self.allnames.__contains__(istate) & self.allnames.__contains__(ostate):
            index1 = self.allnames.index(istate)
            index2 = self.allnames.index(ostate)
            t=Transition(value,ostate,cost,change,up)
            i=self.allstates.__getitem__(index1)
            x=State().mincost(value)
            if x != None:
                x.change=x.change+" "+change
                x.up=1
            else:
                i.transi.add(x)
        else:
            self.allnames.append(istate)
            self.allnames.append(ostate)
            self.allstates.append(istate)
            self.allstates.append(ostate)
            t=Transition(value,ostate,cost,change,up)
            index1=self.allnames.index(istate)
            z=State(istate,False)
            z=State(self.allstates.__getitem__(index1))


t=Tranducers()
t.allnames.append("q1")
t.allnames.append("q2")
#tr=Transition("a","q2",0.0,"a",0)
t.add("q1","a","q2",0.0,"a")
for x in t.allstates:
    for y in x.transi:
        print (y.value)
        print (y.cost)
        print (y.state)

