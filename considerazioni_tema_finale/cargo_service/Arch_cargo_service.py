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
with Diagram('cargo_serviceArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_sonar_emitter', graph_attr=nodeattr):
          sonar_emitter=Custom('sonar_emitter','./qakicons/symActorWithobjSmall.png')
     sonar_emitter >> Edge( label='container_present', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonar_emitter >> Edge( label='container_absent', **eventedgeattr, decorate='true', fontcolor='red') >> sys
diag
