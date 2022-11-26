%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone
%line
%column


//définition d'expressions régulières

annee = [:digit:]{4}

%%

//les règles:
{annee}          {  System.out.print("Ligne: " + yyline);
                    System.out.print(" Position: " + yycolumn);
                    System.out.println(" Année: " + yytext());
                }

[^]             {}