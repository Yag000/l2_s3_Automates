%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


//définition d'expressions régulières

EspaceChar = [ \n\r\f\t]

word  = [:letter:]*
motMaj = [:uppercase:]{mot}

vowel=[aeiouyAEIOUY]
consonant = [[:letter:]&&[^aeiouyAEIOUY]]
motOnlyOneVowel = {word}{vowel}{word}
motMoreThanOneVowel = {motOnlyOneVowel}{vowel}{word}

%%

// {motMaj}         {System.out.println(yytext());}
{motMoreThanOneVowel}     {}
{motOnlyOneVowel}         {System.out.println(yytext());}

[^]             {}
