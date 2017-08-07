# Train Routes

## Introduction
Graph problem that describes the routes between stations and the possible paths.

## How to compile
We use maven as build tool. Follow the next steps to obtain the executable jar.

```
cd %project_base_dir%
mvn clean install
```

As a result, a target directory is generated with the following files:

  - train-core-1.0-SNAPSHOT-jar-with-dependencies.jar: jar that contains the executable class and all required dependencies to execute standalone.
  - graph.txt: text file where the nodes are placed in order to execute the tests specified at the assignment.


## How to execute
To execute the JAR file, the only thing we need is the JAR file and the associated txt file (with nodes and paths within).

```
java -jar train-core-1.0-SNAPSHOT-jar-with-dependencies.jar graph.txt
```

As a result we obtain the next trace:

```
C:\Users\Usuario\IdeaProjects\train-core\target>java -jar train-core-1.0-SNAPSHOT-jar-with-dependencies.jar graph.txt
OUTPUT #1: 9
OUTPUT #2: 5
OUTPUT #3: 13
OUTPUT #4: 22
OUTPUT #5: NO SUCH ROUTE
OUTPUT #6: 2
OUTPUT #7: 3
OUTPUT #8: 9
OUTPUT #9: 9
OUTPUT #10: 7
```

## Github
To obtain the project, just download the source code with the following command:
```
git clone https://github.com/MrLouzao/train-routes.git
cd train-routes
````
