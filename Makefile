.PHONY: parser clean mjc 

all: parser mjc

parser:
	ant parser

mjc: src/
	ant

clean:
	ant clean
	rm -rf tigris
	rm -rf tigris_mail
	rm -rf testcases/execute/*/*.output

tigris.tar.gz: clean src/ report.pdf DESC lib/ build.xml
	tar -czf tigris.tar.gz src/ lib/ DESC report.pdf build.xml

tigris: clean src/ report.pdf DESC lib/ build.xml
	mkdir tigris/
	cp -rf src/ report.pdf DESC lib/ build.xml tigris/
	tar -cf - tigris | gzip | uuencode x > tigris_mail  #mailx -r taran@kth.se -s '' submit@tigris.csc.kth.se
