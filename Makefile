.PHONY: src/parse

all: src/parse parser

src/parse:
	cd src/parse ; \
	javacc minijava.jj

parser: src/
	javac -cp src/ src/parse/Main.java -d bin/

clean:
	rm -rf bin/*
