__author__ = 'apple1'
class Transition:
    def __init__(self,value,state,cost,change,up):
        self.value=value
        self.state=state
        self.change=change
        self.cost=cost
        self.up=up
class State:
    def __init__(self,name,isfinal,trani,value):
        self.name=name
        self.isfinal=isfinal
        self.transi=[]
        self.add_transition(trani)
        self.trans=[]
        self.add_tran(value)
    def add_transition(self, trani):
        self.transi.append(trani)
#        self.trans.append(value.value)
    def add_tran(self, value):
         if value != "":
             self.trans.append(value)
    def mixcost(self, value):
        send = []
        for x in self.transi:
            if x.value == value:
                send.append(x)
        return send

    def mincost(self, value):
        for x in self.transi:
            if x.value == value.value:
                return x
        return None
    def set(self,value,change):
        for x in self.transi:
            if x.value == value.value:
                x.change=x.change + " " + change
    def setfinal(self,final):
        self.isfinal=final
class Tranducers:
    def __init__(self):
        self.allnames=[]
        self.allstates=[]
    def add(self,istate,value,ostate,cost,change):
        if(value==change):
            up=0
        else:
            up=1


        if (self.allnames.__contains__(istate)) & (self.allnames.__contains__(ostate)):
            index1 = self.allnames.index(istate)
            index2 = self.allnames.index(ostate)
            t=Transition(value,ostate,cost,change,up)
            i=self.allstates.__getitem__(index1)
            x=State().mincost(value)
            if x != None:
                x.change=x.change+" "+change
                x.up=1
            else:
                i.transi.add(Transition(x))
                i.tran.add(value)
        elif (istate not in self.allnames) & (ostate not in self.allnames):
            self.allnames.append(istate)
            self.allnames.append(ostate)
            #self.allstates.append(istate)
            #self.allstates.append(ostate)
            t1=Transition(value,ostate,cost,change,up)
            t2=Transition("","",0.0,"",0);
            z=State(istate,False,t1,value)

            self.allstates.append(z)
            z1=State(ostate,False,t2,"")
            self.allstates.append(z1)
        elif (self.allnames.__contains__(istate) & (ostate not in self.allnames)):
            index1=self.allnames.index(istate)
            i=self.allstates.__getitem__(index1)
            i.tran.add(value)
            t2=Transition("","",0.0,"",0);
            z=State(ostate,False,t2)
            t=Transition(value,ostate,cost,change,up)
            tran = i.mincost(t)
            if(tran == None):
                i.transi.append(t)
            else:
                i.set(t,change)
            self.allnames.append(ostate)
            self.allstates.append(z)
        elif (istate not in self.allnames) & (self.allnames.__contains__(ostate)):
            index=self.allnames.index(ostate)
            t=Transition(value,ostate,cost,change,up)
            z=State(istate,False,t,value)
            self.allnames.append(istate)
            self.allstates.append(z)
    def final_states(self,finalstates):
        for state in finalstates:
            index=self.allnames.index(state)
            z=self.allstates.__getitem__(index)
            z.setfinal(True)

    def possible_Transitions(self,q,word):
        pt=[]
        for i in range(0,len(word)):
            t=q.mincost(word[0:i])
            if t!=None:
                    pt.append(t)
        if(len(pt) == 0):
            return None
        return pt
    def best_possible_Transitions(self,q,word):
        pt=[]
        for i in range(0,len(word)):
            t=q.mincost(word[0:i])
            if t!=None:
                    pt.append(t)
                    break
        if(len(pt) == 0):
            return None
        return pt

    def set(self,q,output,tran):
        t2=Transition("","",0.0,"",0);
        tran.append(t2)
        for i in range(0,len(tran)):
            output.append(tran.__getitem__(i).value)


    def set0(self,q,c_output,tran):
        for i in range(0,len(tran)):
            if tran.__getitem__(i).change == tran.__getitem__(i).value:
                c_output.append(tran.__getitem__(i).change)
            else:
                c_output.append("£" + tran.__getitem__(i).change + " £")


    def update_final(self,final_output,output,index):
        temp=[]
        if len(final_output) ==0:
            for o in output:
                final_output.append(o)
            return final_output
        else:
            i=0
            for o in final_output:
                if i == index:
                    for j in output:
                        temp.append(o + j)
                else:
                    temp.append(o)
                i=i+1
        index=index+len(output)
        return temp

    def update_chh(self,ch_output,output,index):
        temp=[]
        if len(ch_output) ==0:
            for o in output:
                ch_output.append(o)
            return ch_output
        else:
            i=0
            for o in ch_output:
                if i == index:
                    for j in output:
                        temp.append(o + j)
                else:
                    temp.append(o)
                i=i+1
        index=index+len(output)
        return temp

    def Repe1(self,final_output,output,transitions,word,ch_output):
        temp1=[]
        stack1=[]
        stack2=[]
        c_output=[]
        for tran in transitions:
            for i in len(transitions):
                t=transitions.__getitem__(i)
                q=self.allstates.__getitem__(self.allnames.index(t.state))
                if len(output) == 0:
                    index=0
                else:
                    index=len(output) -1
                output.clear()
                c_output.clear()
                temp=self.possible_Transitions(q,word[len(final_output.__getitem__(i)),len(word)])
                if temp != None:
                    for j in len(temp):
                        temp1.append(temp.__getitem__(j))
                    self.set3(q,output,temp)
                    self.set33(q,c_output,temp)
                    ss=list(output)
                    ss1=list(c_output)
                    stack1.append(ss)
                    stack2.append(ss1)
            k=len(stack1)
            if k != 0:
                for i in range(k,0,-1):
                    output=stack1.pop()
                    c_output=stack2.pop()
                    final_output=self.update_final(final_output,output,i)
                    ch_output=self.update_chh(ch_output,c_output,i)
                if len(temp1) == 0:
                    transitions=None
                else:
                    transitions.clear()
                    for i in len(temp1):
                        transitions.append(temp1.__getitem__(i))
                    temp1.clear()
        return ch_output


    def finally_done(self,word):
        ch_output=[]
        remain =""
        subwords=""
        index1=0
        index2=0
        if word.__contains__("£"):
            index1=word.index("£")
            if index1==0:
                word=word.replace("£","",1)
                index2=word.index("£")
                subwords=word[index1:index2]
                if index2 != len(word):
                    remain= word[index2+1:len(word)]
                else:
                    remain=""
                arr=subwords.split(" ")
                for i in range(len(arr)):
                    if remain.__contains__("£"):
                        ch_output.append(arr[i] + remain)
                    else:
                        ch_output.append(arr[i])
            else:
                base= word[0:index1]
                word=word[index1:len(word)]
                word=word.replace("£","",1)
                index2=word.index("£")
                subwords=word[0:index2]
                if index2 != len(word):
                    remain== word[index2+1:len(word)]
                else:
                    remain=""
                arr=subwords.split(" ")
                for i in range(len(arr)):
                    ch_output.append(base + arr[i])
            remain = word[index2+1:len(word)]
            while remain.__contains__("£"):
                base=""
                index1=remain.index("£")
                if index1!=0:
                    base=remain[0:index1]
                    for k in range(len(ch_output)):
                        w=ch_output.__getitem__(k)
                        ch_output.__setitem__(k,w+base)
                    remain=remain[index1:len(remain)]
                else:
                    remain=remain.replace("£","",1)
                    index2=remain.index("£")
                    subwords=remain[0:index2]
                    arr1=subwords.split(" ")
                    siz=len(ch_output)
                    for i in range(len(arr1)-1):
                        for m in range(siz):
                            w=ch_output.__getitem__(m)
                            ch_output.append(w)
                    counter=0
                    for k in range(len(arr1)):
                        for m in range(siz):
                            w=ch_output.__getitem__(counter)
                            ch_output.__setitem__(counter,w+arr1[k])
                            counter=counter+1
                    if index2 != len(remain):
                        remain=remain[index2 +1 :len(remain)]
                    else:
                        remain=""
                    if "£" not in remain & (len(remain) >=0):
                        for i in range(len(ch_output)):
                            a=ch_output.__getitem__(i)
                            ch_output.__setitem__(i,a+remain)
        else:
            ch_output.append(word)
        return ch_output


    def is_exists(self,word,current):
        result=False
        if current==None:
            return False
        wordlen=len(word)
        i=1
        for m in range(1,len(wordlen)+1):
            subwords=word[0:1]
            if current.tran.contains(subwords):
                t=current.transi.get(current.tran.index(subwords))
                if m==len(word):
                    if self.allstates.__getitem__(self.allnames.index(t.state)).isfinal == True:
                        result=True
                        break
                    else:
                        result=False
                else:
                    result=result or self.is_exists(word[m:],self.allstates.__getitem__(self.allnames.index(t.state)))


    def exists(self,word):
        q=self.allstates.__getitem__(self.allnames.index("q1"))
        if self.is_exists(word,q):
            return True
        else:
            return False

    def upfinal(self,word):
        q=self.allstates.__getitem__(self.allnames.index("q1"))
        output=[]
        c_output=[]
        indexf=0
        ch_output=[]
        final_output=[]
        temp=self.possible_Transitions(q,word)
        if temp == None:
            return None
        self.set(q,output,temp)
        self.set0(q,c_output,temp)
        if len(output) == 0:
            return None
        else:
            final_output=self.update_final(final_output,output,indexf)
            ch_output=self.update_chh(ch_output,c_output,indexf)
        output.clear()
        final_output=self.Repe1(final_output,output,temp,word,ch_output)
        temp1=[]
        finallydone=[]
        for i in len(final_output):
            temp1=self.finally_done(final_output.__getitem__(i))
            for j in len(temp1):
                finallydone.append(temp1.__getitem__(j))
            temp1.clear()
        return finallydone
#State()
t=Tranducers()
#t.allnames.append("q1")
#t.allnames.append("q2")
#tr=Transition("a","q2",0.0,"a",0)
t.add("q1","a","q2",0.0,"a")
for x in t.allstates:
    for y in x.transi:
        print(y)
word="abcd"
t.final_states(["q1"])
for i  in range(0,len(word)):
    print(i)
