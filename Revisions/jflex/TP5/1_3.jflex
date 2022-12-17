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

not_correct = {mot}({consonant}{consonant}|{vowel}{vowel}){mot}




%%

//les règles:

{not_correct}     {}
{mot}         {System.out.println(yytext());counter++;}

[^] {}