%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone
%line
%column


%{
%}

%{eof
%eof}


annee = [:digit:]{4}

//définition d'expressions régulières

%%

//les règles:

{annee} {System.out.print("Ligne: " + yyline);
        System.out.print(" Colonne: " + yycolumn);
        System.out.println(" Année: " + yytext());}

[^] {}