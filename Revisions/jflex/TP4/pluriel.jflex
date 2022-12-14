%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


%{
%}

%{eof
%eof}


//définition d'expressions régulières

mot = [:letter:]+

invariable = {mot}("x","s","z")

al = {mot}"al"
al_exceptions = "bal"|"carnaval"|"festival"

au_eu = {mot}("au"|"eu")
eu_exceptions = "pneu"|"bleu"
%%

//les règles:

{invariable}|{eu_exceptions} {System.out.println(yytext());}

{al_exceptions} {System.out.println(yytext() + "s");}
{al} {System.out.println(yytext().substring(1,yytext().length()) + "aux");}

{au_eu} {System.out.println(yytext() + "x");}
{mot} {System.out.println(yytext() + "s");}



[^] {}