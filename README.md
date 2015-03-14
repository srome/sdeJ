# sdeJ
A lightweight first order stochastic differential equation solver with support for ODEs.

# Running Instructions
sdeJ is designed to solve first order stochastic differential equations numerically and return a usable Java Function
as an output. Multiple examples of use are in the test package. The basic steps are:
1) Create and populate an instance of the class Problem.
2) Run the getSolutionAsFunction() method on Problem to return the desired function.

# Test Suite
Run the JUnit test package to create a folder "output" in the sdeJ directory with plots of the solutions of the tests.

