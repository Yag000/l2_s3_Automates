%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


%{
    public int counter = 0;
%}

%{eof
    
    System.out.println("Nombre de mots: " + counter);
%eof}


//définition d'expressions régulières

%%

//les règles:


"men"  {counter ++;}

[^] {}