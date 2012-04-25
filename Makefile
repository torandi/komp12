.PHONY: src/parse clean

all: parser mjc

parser:
	lib/javacc/javacc -output_directory=src/parse/ src/parse/minijava.jj 

mjc: src/
	javac -cp src/ src/mjc/JVMMain.java -d bin/

clean:
	rm -rf src/parse/*.java
	rm -rf bin/*

tigris.tar.gz: clean src/ report.pdf DESC lib/ build.xml
	tar -czf tigris.tar.gz src/ lib/ DESC report.pdf build.xml 
