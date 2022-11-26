%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


//définition d'expressions régulières

mot = [:letter:]+ 

invariable = {mot}("x"|"s"|"z")

alExceptions = "bal" | "carnaval" | "festival" | "recital"
al = {mot}"al"

au_euExceptions = "bleu" | "pneu"
au_eu = {mot}"au" | {mot}"eu"

%%

//les règles:
{invariable}                {System.out.println(yytext());}

{alExceptions}              {System.out.println(yytext() + "s");}
{al}                        {System.out.println(yytext().substring(0, yytext().length() - 1) + "ux");}

{au_euExceptions}           {System.out.println(yytext() + "s");}
{au_eu}                     {System.out.println(yytext() + "x");}

{mot}                       {System.out.println(yytext() + "s");}

[^]             {}