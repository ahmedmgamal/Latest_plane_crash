	-run the Listener class 
	-open browser on localhost:999
	- You can use any stress tool to make multiple concurrent request 


TODO:
	-implement listener  
	-implement http client 
	-send http (GET,PUT) using Non-blocking to avoid the Fibonacci delays from client side  
	-implement method getForUpdate which make the server keeps ref to client to update client when file changed 
	- the above point to be implement using file watcher abd totlay decoupled from update the file logic 
	-create update client that submit the new article with the previous versionid (checksum of the file )
	-make update client which get the update version of the document form server using push not pull 
		
		



approaches to handle version conflict 
	-in addition to options mentioned before 
	-auto merge 
		-this will assume most updates is kind of append and   no really full update for every line
		-there will be check if it is not possible to auto merge , error appear to user   

design approaches and trade offs  
	-use of non blocking IO , it was planned to to use this in client too 
	- use of standard http REST approach
	-use of JEF(Java execution framework ) to handle multiple worker thread so there is no need to create thread for each high traffic 
	- the use of JEF allows JVM to make schduler based on cores, I choose to make 12 concurrent worker  thread just to be the same like no of cores but this does not have grantted impact it is up to JVM schduler 
	- we need to set good env and choose good JVM that consider parallel programming 
	- the code handle each request through 2 workers (it could be 3 if we made another worker for fibbnaci processing ). this allows executer to make more scheduling 
	- the size of each schedule task is reasonable which make the cost of change context worth it(one read and process the request and one write the file to client )
	- I would like to add monitoring for the schedule to know how much task are running
	- I would like to handle more http codes like 404 , 500 and more 
	
