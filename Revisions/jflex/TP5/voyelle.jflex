%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


%{
    public int counter = 0;
%}

%{eof
System.out.println(counter);
%eof}


//définition d'expressions régulières

mot  = [:letter:]*

vowel=[aeiouyAEIOUY]
consonant = [[:letter:]&&[^aeiouyAEIOUY]]

motOnlyOneVowel = {mot}{vowel}{mot}
motMoreThanOneVowel = {motOnlyOneVowel}{vowel}{mot}




%%

//les règles:

{motMoreThanOneVowel}     {}
{motOnlyOneVowel}         {System.out.println(yytext());counter++;}

[^] {}