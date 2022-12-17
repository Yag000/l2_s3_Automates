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

%%

//les règles:

[A-Z][a-z]* {System.out.println(yytext());}

[^] {}