# DistriRMIPt2

Stappenplan om te runnen:

Zorg dat de 3 extra files (Dockx, Hertz en trips) ook in de src-folder staan!

1. Compileren in de src-folder: javac -d /home/faes/git/DistriRMIPt2/RMI2/src client/* rental/* agency/* 
Als je hier een fout krijgt, verwijder al uw .class-files.
2. Doe in die terminal: rmic agency.ManagerSession (en misschien ook RentalSession, in case of 'StubNotFoundException').
3. Start rmiregistry in de src-folder ("rmiregistry &")
4. Run de agency (in een nieuwe terminal): zorg dat je in de src-folder staat en voer dan volgend commando uit: java -classpath /home/faes/git/DistriRMIPt2/RMI2/src -Djava.rmi.server.codebase=file:/home/faes/git/DistriRMIPt2/RMI2/src agency.Agency
5. Start de rentalserver (in een nieuwe terminal): zorg dat je in de src-folder staat en doe: java -classpath /home/faes/git/DistriRMIPt2/RMI2/src -Djava.rmi.server.codebase=file:/home/faes/git/DistriRMIPt2/RMI2/src rental.RentalServer
6. Start de client in de src-folder, in een andere terminal: java  -classpath /home/faes/git/DistributedSystemsRMI/RMI/src client.Client

Vergeet de rmiregistry achteraf niet te killen ("killall rmiregistry")!
