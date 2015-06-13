# sdeJ
A RESTful API for a first order stochastic differential equation solver
with a front end to explore solutions to brownian motion and geometric brownian motion.
The front end is implemented with a combination of HTML and Javascript (jQuery, ajax), and the
API is built around a numerical SDE solver implemented using an elementary time-stepping algorithm.

# Running Instructions
Option 1) Start the application by running the main method in the com.Application class.
          Then, go to http://localhost:8080/.
Option 2) Create a jar with the command "mvn clean package" from the command line inside the directory with the pom.xml.
          Then, at the command prompt in the /target folder run the jar with "java -jar sdeJ-0.0.1-SNAPSHOT.jar".
          Then, go to http://localhost:8080/.



