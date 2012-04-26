%{
static int index;
%}

%%

">"	{ (void)printf("program[%d] = 0;\n", index++); }
"<"	{ (void)printf("program[%d] = 1;\n", index++); }
"+"	{ (void)printf("program[%d] = 2;\n", index++); }
"-"	{ (void)printf("program[%d] = 3;\n", index++); }
"."	{ (void)printf("program[%d] = 4;\n", index++); }
","	{ (void)printf("program[%d] = 5;\n", index++); }
"["	{ (void)printf("program[%d] = 6;\n", index++); }
"]"	{ (void)printf("program[%d] = 7;\n", index++); }
.	;
