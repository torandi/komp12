all: src/parse parser

src/parse: src/parse/minijava.jj
	javacc src/parse/minijava.jj

parser: src/
	javac -cp src/ src/parse/Main.java -d bin/

clean:
	rm -rf bin/*
