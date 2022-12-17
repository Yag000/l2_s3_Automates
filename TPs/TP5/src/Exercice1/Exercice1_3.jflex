%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


//définition d'expressions régulières

word  = [:letter:]*

vowel=[aeiouyAEIOUY]
consonant = [[:letter:]&&[^aeiouyAEIOUY]]

badVowelWord = {word}{vowel}{vowel}{word}
badConsonantWord = {word}{consonant}{consonant}{word}

%%

// {motMaj}         {System.out.println(yytext());}
{badVowelWord}     {}
{badConsonantWord}     {}
{word}         {System.out.println(yytext());}

[^]             {}