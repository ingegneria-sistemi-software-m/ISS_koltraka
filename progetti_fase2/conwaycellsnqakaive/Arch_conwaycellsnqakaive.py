### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('conwaycellsnqakaiveArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxcells', graph_attr=nodeattr):
          caller=Custom('caller','./qakicons/symActorWithobjSmall.png')
          cell_0_0=Custom('cell_0_0','./qakicons/symActorWithobjSmall.png')
          cell_0_1=Custom('cell_0_1','./qakicons/symActorWithobjSmall.png')
          cell_1_0=Custom('cell_1_0','./qakicons/symActorWithobjSmall.png')
          cell_1_1=Custom('cell_1_1','./qakicons/symActorWithobjSmall.png')
     caller >> Edge( label='clearCell', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     caller >> Edge( label='startthegame', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='clearCell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_0
     sys >> Edge( label='startthegame', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_0
     sys >> Edge( label='curstate', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_0
     sys >> Edge( label='synch', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_0
     sys >> Edge( label='stopthecell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_0
     sys >> Edge( label='clearCell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_1
     sys >> Edge( label='startthegame', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_1
     sys >> Edge( label='curstate', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_1
     sys >> Edge( label='synch', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_1
     sys >> Edge( label='stopthecell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_0_1
     sys >> Edge( label='clearCell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_0
     sys >> Edge( label='startthegame', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_0
     sys >> Edge( label='curstate', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_0
     sys >> Edge( label='synch', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_0
     sys >> Edge( label='stopthecell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_0
     sys >> Edge( label='clearCell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_1
     sys >> Edge( label='startthegame', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_1
     sys >> Edge( label='curstate', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_1
     sys >> Edge( label='synch', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_1
     sys >> Edge( label='stopthecell', **evattr, decorate='true', fontcolor='darkgreen') >> cell_1_1
     caller >> Edge(color='blue', style='solid',  decorate='true', label='<changeCellState &nbsp; >',  fontcolor='blue') >> cell_1_0
     caller >> Edge(color='blue', style='solid',  decorate='true', label='<changeCellState &nbsp; >',  fontcolor='blue') >> cell_0_1
     caller >> Edge(color='blue', style='solid',  decorate='true', label='<changeCellState &nbsp; >',  fontcolor='blue') >> cell_0_0
diag
