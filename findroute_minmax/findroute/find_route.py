#importing sys and heap queue (a.k.a priority queue )

import sys
import heapq 

#we are using lists for intializing fringe and closed 
#nodeE - nodes expanded
#nodeG - nodes generated
#sys.argv into args , and args are stored in dictonary retreive more easier

fringelist = []
closedlist = []
nodeE = 0
nodeG = 1
args = sys.argv
filesdict={"inputfile":args[1],"Start":args[2],"Goal":args[3],"heuristicfile":None}
input_file = args[1]
Start = args[2]
Goal = args[3]

#if length of arguments passsed is 5 then do a* - using heuristic inputfile
if len(args) == 5:
  filesdict["heuristicfile"] = args[4]  
else:
  filesdict["heuristicfile"] = None

#Reading input file
read_if = open(filesdict["inputfile"],'r').read().split('\n')

#input and heuristic are taken into dictonaryfiles after read 
inpdict = {}
for i in read_if:
  if i == 'END OF INPUT':
    break
  space = i.rfind(' ')
  inpdict[i[:space]] = int(i[space+1:])
heudict = {}
if filesdict["heuristicfile"]:
  read_hf = open(filesdict["heuristicfile"],'r').read().split('\n')
  for i in read_hf:
    if i == 'END OF INPUT':
      break
    space = i.split(' ')
    heudict[space[0]] = int(space[1])


#defining function for expanded nodes and traversal, using priority queue to traverse the path heappush and pop the fringe
def expandednode(node):
  nodenamelist = node['node']
  if nodenamelist in closedlist:
    return None
  resultlist = []
  for i in read_if:
    if i == 'END OF INPUT':
      break
    if nodenamelist in i:
      invnode = i.split(' ')
      if nodenamelist == invnode[0]:
        node1,node2,distance = invnode[1],nodenamelist,0
        if (node1+' '+node2) in inpdict.keys():
          distancecovered = inpdict[node1+' '+node2]
        elif (node2+' '+node1) in inpdict.keys():
          distancecovered = inpdict[node2+' '+node1]
        elif (node1+' '+node2) | (node2+' '+node1) not in inpdict.keys():
          distancecovered = -1
        if invnode[1] in heudict.keys():
          heuinvnode = heudict[invnode[1]]
        else:
          heuinvnode = 0
        
        resultlist.append({'node': invnode[1], 'start':node, 'totaldistance': node['totaldistance'] + distancecovered, 'heuristicdistance': heuinvnode})

      else:

        node1,node2,distance = invnode[0],nodenamelist,0
        if (node1+' '+node2) in inpdict.keys():
          distancecovered = inpdict[node1+' '+node2]
        elif (node2+' '+node1) in inpdict.keys():
          distancecovered = inpdict[node2+' '+node1]
        else:
          distancecovered = -1
        if invnode[0] in heudict.keys():
          heuinvnode = heudict[invnode[0]]
        else:
          heuinvnode = 0
        resultlist.append({'node': invnode[0], 'start':node, 'totaldistance': node['totaldistance'] + distancecovered, 'heuristicdistance': heuinvnode})
  for result in resultlist:
    heapq.heappush(fringelist, (result['totaldistance'] + result['heuristicdistance'], result))

  closedlist.append(nodenamelist)
  return resultlist

if Start in heudict.keys():
  heuinvnode = heudict[Start]
else:
  heuinvnode = 0
start = {'node': Start, 'start': None, 'totaldistance': 0, 'heuristicdistance':heuinvnode}
for result in [start]:
    heapq.heappush(fringelist, (result['totaldistance'] + result['heuristicdistance'], result))

#calculating nodes expanded and nodes generated
visited = "False"
while len(fringelist) > 0:
  nodeE += 1
  node = heapq.heappop(fringelist)
  if node:
    node = node[1]
  if node['node'] == Goal:
    visited = "True"
    break
  expandedlist = expandednode(node)
  if expandedlist is not None:
    nodeG = nodeG + len(expandedlist)

print('nodes expanded:',nodeE)
print('nodes generated:',nodeG)

#calculating distance and route
#if node can be visited and path is found
if visited == "True":  
  print('distance:',node['totaldistance'])
  print('route:')
  #getting route
  Expandedlist = []
  while node is not None:
    if node['start'] == None:
      break
    typecasted_distance = str(node['totaldistance'] - node['start']['totaldistance']) 
    Expandedlist.append(node['start']['node'] + " to " + node['node'] +", "+ typecasted_distance + " km")
    node = node['start']
  print('\n'.join(Expandedlist[::-1]))

#if no path found ,node cant be visited
else:
  print('distance: infinity')
  print('route:')
  print('none')