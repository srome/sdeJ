# sdeJ
A lightweight first order stochastic differential equation solver with support for ordinary differential equations.

# Running Instructions
sdeJ is designed to solve first order stochastic differential equations numerically and return a class which extends
Function<Double, Double> as an output. This class will act as a function representing the solution to the SDE
via the evaluate(Double t) and apply(Double t) methods. Multiple examples of use are in the test package. We will also
walk through an example here:

Say you wish to solve for the solution y of the SDE dy = f(t,y)*dt + g(t,y)*dW. sdeJ will return to you the class
InterpolatedFunction which is a representation of the solution y. Then, one may evaluate this approximate solution at
any point within its domain.
The basic steps are:
1) Create and populate an instance of the class Problem.
2) Run the getSolutionAsFunction() method on the Problem instance to return the desired function.
3) One is now able to evaluate the numerical solution y at any point t in its domain by using the evaluate(t) or apply(t)
methods. 

# Test Suite
Run the JUnit test package to create a folder "output" in the sdeJ directory with plots of the solutions of the tests.
The test suite requires the library JOpenChart version 0.94 which can be downloaded at http://jopenchart.sourceforge.net/.

