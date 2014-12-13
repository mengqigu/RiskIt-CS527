set CLASSPATH=..\lib\derbyrun.jar;..\lib\derbytools.jar;..\lib\junit.jar;..\lib\derbyclient.jar;..\lib\derbynet.jar;..\lib\derby.jar

java -Xms64m -Xmx256m -cp %CLASSPATH%;..\lib\riskit.jar com.riskIt.test.AllTests ..\cfg\riskit.properties

pause
