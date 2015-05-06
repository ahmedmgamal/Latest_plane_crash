TODO:
	-implemnt listner  
	-implment http clint 
		-send http (GET,PUT) using Non blocking to avoid the fibnaci delays 
	-create update client that submit the new article with the previous original id 
	-make update clint which get the update version of teh document form server using push not pull 
		
		
approches to handle version conflict 
	-in addtion to options mentioned before 
	-auto merge 
		-this will assume most updates is kind of appned  no really full update for every line
		-there will be check if it is not possbiel to auto merge , eror appear to user   
	-to use push tech so client version (editor) is updateed once server is updated
		-to use file watcher  

design approches 
	-use of non blocking IO , it was plannieed to to use this in clint too 
	- use of stadard http REST approch
	-use of JEF to habdle mutliple worker thread so there is no need to creat thread for each high traffic 
	- the use of JEF allows JVM to make schduler based on cores, I choosed to make 12 conccurnt thread just to be the same like no of cores but this doesn not havegrantte impact it is up to JVM schdulekr 
	- we need to set good env and choose good JVM that consider paraller programming 
	- the cod handle each request throug 2 works (it could be 3 if we made another woorked for fibbnaci processing ). this allows exceuter to make more schduling 
	- the size of each schdule task is resoable which make the cos of change context worth it(one read and proess the request and one write the file to client )
	- I would like to add monitoring for the schdule to know how much task are running
	- I would like to handle more http codes like 404 , 500 and more 
	
	   
	   
	