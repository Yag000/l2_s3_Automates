%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


//définition d'expressions régulières

word  = [:letter:]*

vowel=[aeiouyAEIOUY]
consonant = [[:letter:]&&[^aeiouyAEIOUY]]
labialConsonant = [pbmfvPBMFV]

badWord = [:letter:]{labialConsonant}{word}


%%

// {motMaj}         {System.out.println(yytext());}
{badWord}     {}
{word}         {System.out.println(yytext());}

[^]             {}