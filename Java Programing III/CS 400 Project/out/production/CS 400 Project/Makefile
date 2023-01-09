run: compile 
	java Main

compile: Main.java Bucket.java Game.java GamesConsole.java InitializeDatabase.java MapADT.java HashTableMap.java
	javac Main.java Bucket.java Game.java GamesConsole.java InitializeDatabase.java MapADT.java HashTableMap.java

test: UnitTests.class
	java -jar junit5.jar -cp . --scan-classpath -n UnitTests

UnitTests.class: UnitTests.java
	javac -cp .:junit5.jar UnitTests.java

clean:
	rm *.class
