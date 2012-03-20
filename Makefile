all: src/parse parser

src/parse: src/parse/minijava.jj
	cd src/parse ; \
	javacc minijava.jj

parser: src/
	javac -cp src/ src/parse/Main.java -d bin/

clean:
	rm -rf bin/*
