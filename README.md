# TheaterSeating

**Program Description**

This program parses a theater layout and a list of ticket requests and produce a list of tickets or explanations in the same order as the requests.
 
few rules to follow 
1.	Fill as many orders as possible
2.	Put parties as close to the front as possible.
3.	If there are not enough seats available in the theater to handle a party, tell them "Sorry, we can't handle your party."
4.	Each party must sit in a single row in a single section.  If they won't fit, tell them "Call to split party".
 
The theater layout is made up of 1 or more rows.  Each row is made up of 1 or more sections separated by a space.

After the theater layout, there is one empty line, followed by 1 or more theater requests.  The theater request is made up of a name followed by a space and the number of requested tickets.

Your program must produce results to standard output in the same order as the requests, with the name of the person who requested the ticket and either the row and section of the ticket or the explanations "Sorry, we can't handle your party" or "Call to split party."

** Sample input&output

Sample input: 
  6 6 
  3 5 5 3 
  4 6 6 4 
  2 8 8 2 
  6 6

Smith 2 
Jones 5 
Davis 6 
Wilson 100 
Johnson 3 
Williams 4 
Brown 8 
Miller 12
end

Sample output:

Smith Row 1 Section 1 
Jones Row 2 Section 2 
Davis Row 1 Section 2 
Wilson Sorry, we can't handle your party. 
Johnson Row 2 Section 1 
Williams Row 1 Section 1 
Brown Row 4 Section 2 
Miller Call to split party.


**Execution steps: 

1. The build depends on Apache ANT
2. Open command prompt and navigate to TheaterSeating project directory.
3. The build script is “build.xml”.
4. Run the command “ant -f build.xml” to build the project
5. An executable jar file is created under dist directory with the name TheaterSeating-<version>.<build.number>.jar
6. Run the command "java -jar TheaterSeating-<version>.<build.number>.jar <input-file-path>” to execute the program.
7. The output is printed to console.
8. “Input-file-path” is the path of the file where the theater layout and seating requests are defined. 

Example:
Save “input.txt” with the content as below

 6 6 
 3 5 5 3 
 4 6 6 4 
 2 8 8 2 
 6 6 
 
 Smith 2 
 Jones 5 
 Davis 6 
 Wilson 100 
 Johnson 3 
 Williams 4 
 Brown 8 
 Miller 12 

Run the command “java -jar TheaterSeating-1.0.2.jar input.txt”

The following output is printed to console

 Smith Row 1 Section 1 
 Jones Row 2 Section 2 
 Davis Row 1 Section 2 
 Wilson Sorry, we can't handle your party. 
 Johnson Row 2 Section 1 
 Williams Row 1 Section 1 
 Brown Row 4 Section 2 
 Miller Call to split party. 
