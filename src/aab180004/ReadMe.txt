UTD CS5V81-001 - Short Project 12

Project By : Achyut Bhandiwad (aab180004) and Saurav Sharma (sxs179830)

Submitted on: 11/25/2018

This Project was implemented as part of the course CS5V81-001 Implementation of Data Structures and Algorithms at University of Texas at Dallas.

The goal of the project was to:
   Implement the algorithm to find the diameter of a tree using the
   algorithm discussed in class, that runs BFS twice.  Code this
   algorithm without modifying Graph.java and BFSOO.java, using them
   from package rbk.

   int diameter(Graph g) { ... }  // assume that g is an acyclic, connected graph (tree).

File Name: SP12.java
Package name: aab180004


Results :


Input : 10 9   1 2 1   1 3 1   2 4 1   2 5 1   3 6 1   3 7 1   4 8 1   7 9 1   7 10 1 1 
									
									 1
								   /   \
								  2	    3 
								 / \   / \
                                4   5  6  7
							   /         / \
                              8         9   10
							  
Output : Diameter : 6