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
with Diagram('sonar_simulatorArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_simulator', graph_attr=nodeattr):
          emitter=Custom('emitter','./qakicons/symActorWithobjSmall.png')
          perceiver=Custom('perceiver','./qakicons/symActorWithobjSmall.png')
     emitter >> Edge( label='misurazione', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='misurazione', **evattr, decorate='true', fontcolor='darkgreen') >> perceiver
diag
