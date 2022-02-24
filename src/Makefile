IN=tests/in/$(CTG)/test$(TEST).in
OUT=tests/out/$(CTG)/test$(TEST).out
AUX=tests/aux/$(CTG)/test$(TEST).aux

build:
	javac  kCliqueBKT.java 
	javac  kCliqueReduction.java

run_backtracking:
	java kCliqueBKT $(IN) > $(OUT)

run_reduction:
	java kCliqueReduction $(IN) > $(AUX)

clean:
	rm -rf *.class
