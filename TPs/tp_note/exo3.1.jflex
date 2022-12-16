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

%%

//les règles:

[:digit:]+(1|8)","([:digit:]|".")+","(1|3|5) {counter++;}

[^] {}