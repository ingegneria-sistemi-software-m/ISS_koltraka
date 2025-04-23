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
with Diagram('sonarled2025Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_observers', graph_attr=nodeattr):
          observer_builder=Custom('observer_builder','./qakicons/symActorWithobjSmall.png')
          sonar_observer=Custom('sonar_observer','./qakicons/symActorDynamicWithobj.png')
     with Cluster('ctx_sonar', graph_attr=nodeattr):
          sonar_publisher=Custom('sonar_publisher(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='ostacolo', **evattr, decorate='true', fontcolor='darkgreen') >> sonar_observer
     sys >> Edge( label='ostacolo_sparito', **evattr, decorate='true', fontcolor='darkgreen') >> sonar_observer
diag
