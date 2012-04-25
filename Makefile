.PHONY: src/parse clean

all: src/parse parser

src/parse:
	cd src/parse ; \
	javacc minijava.jj

parser: src/
	javac -cp src/ src/mjc/JVMMain.java -d bin/

clean:
	rm -rf src/parse/TokenMgrError.java src/parse/ParseException.java src/parse/Token.java src/parse/SimpleCharStream.java
	rm -rf bin/*
