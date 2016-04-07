find -name "*.java" > sources.txt
javac -cp ./src/Parking_System/itextpdf-5.5.9.jar  @sources.txt
java -cp .;src/Parking_System/Client.class
