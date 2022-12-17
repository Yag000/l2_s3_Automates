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

word = [:letter:]+

specialChar = [\.\-\_]

alphabet = [:letter:]|[:digit:]|{specialChar}|"@"


correctSubExpression = ({word}{specialChar}?)*{word}

correctEmail ={correctSubExpression}+@{correctSubExpression}+


%%

//les règles:

{correctEmail} {System.out.println(yytext());}

{alphabet}* {}

[^] {}