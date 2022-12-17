%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


word = [:letter:]+

specialChar = [\.\-\_]

correctSubExpression = ({word}{specialChar}?)*{word}

correctEmail = {correctSubExpression}+@{correctSubExpression}+


%%
{correctEmail}         {System.out.println(yytext());}

([:letter:] | @ | {specialChar})* {}


[^]                    {}