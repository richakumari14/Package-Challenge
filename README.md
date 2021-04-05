# Package Challenge

**Introduction**
You want to send your friend a package with different things.
Each thing you put inside the package has such parameters as index number, weight and cost. The
package has a weight limit. Your goal is to determine which things to put into the package so that the
total weight is less than or equal to the package limit and the total cost is as large as possible.
You would prefer to send a package which weighs less in case there is more than one package with the
same price.

**Input	sample**
Your API should accept as its first argument a path to a filename. The input file contains several
lines. Each line is one test case.
Each line contains the weight that the package can take (before the colon) and the list of things you
need to choose. Each thing is enclosed in parentheses where the 1st number is a thing's index number,
the 2nd is its weight and the 3rd is its cost. E.g.

`81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)`

`8 : (1,15.3,€34)`

`75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)`

`56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)`


**Output	sample**
For each set of things that you put into a package provide a new row in the output string (items’ index
numbers are separated by comma). E.g.

`4`

`-`

`2,7`

`8,9`

**Additional constraints:**
1. Max weight that a package can take is ≤ 100
2. There might be up to 15 items you need to choose from
3. Max weight and cost of an item is ≤ 100

## My Implementation

## Requirement
* Java 11
* Maven

## Building and Running Instructions
* Build the project by running `mvn clean install` inside root directory.  
 
The command downloads libraries(if required, only first time), builds app. If everything goes well, last line in the console will be

![Alt text](static/buildSuccess.PNG?raw=true "Optional Title")  

I am passing filePath as <B>Command Line Arguments</B>  
Please follow below steps to run the application.  

### Step 1: Open the Class Run Configurations Settings  
From the class editor, right click <B>App.java</B> and chose "Run As" -> "Run Configurations...".   

 ![Alt text](static/firstStep.PNG?raw=true "Optional Title") 

### Step 2: Specify the Program Arguments which in this case is absolute file path in the Arguments Tab  
In the pop up window, click on the Arguments tab. Then provide the command line arguments value in the "Program Arguments" text box.  

![Alt text](static/runSteps.PNG?raw=true "Optional Title")  

<I>Output</I> will be printed on console.  

![Alt text](static/output.PNG?raw=true "Optional Title")  

**Approach/Strategy:**  
•	Used 0-1 Knapsack algorithm  
The requirement is to determine which things to put into the package so that the total weight is less than or equal to the package limit and the total cost is as large as possible.Hence , I have used <I>Knapsack Algorithm</I>. The objective of the algorithm is to maximize cost within given supply capacity with the minmal time complexity.  
•   have used  Object-Oriented Design methodology and SOLID principles



