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

ou = {mot}"ou"
ou_Exceptions = "bijou" | "caillou" | "chou" | "joujou" | "genou" | "pou" | "hibou"

au_euExceptions = "bleu" | "pneu"
au_eu = {mot}"au" | {mot}"eu"

ail = {mot}"ail"
ail_Exceptions = "bail" | "travail" | "soupirail" | "émail" | "vitrail" | "corail"


%%

//les règles:

"œil" {System.out.println("yeux");}

"ciel" {System.out.println("cieux");}

{invariable}                {System.out.println(yytext());}

{alExceptions} | {au_euExceptions}               {System.out.println(yytext() + "s");}
{ou_Exceptions}             {System.out.println(yytext() + "x");}


{al}                        {System.out.println(yytext().substring(0, yytext().length() - 1) + "ux");}
{ail_Exceptions}                        {System.out.println(yytext().substring(0, yytext().length() - 2) + "ux");}

{au_eu}                     {System.out.println(yytext() + "x");}

{ou} | {mot} | {ail}             {System.out.println(yytext() + "s");}

{mot}                       {System.out.println(yytext() + "s");}

[^]             {}