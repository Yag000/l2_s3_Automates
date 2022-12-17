


%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


%{
    public int counter = 0;
    public double sum = 0;
%}

%{eof
    
    System.out.println(sum/counter);
%eof}


//définition d'expressions régulières

%%

//les règles:

[:digit:]{8}",20,"[:digit:] { System.out.print(yytext().substring(0,10)); System.out.print(yytext().substring(0,10)); System.out.println(yytext().charAt(yytext().length()-1)) ; }

[:digit:]{8}","[:digit:]+("."[:digit:]*)?","[:digit:]  {System.out.println(yytext());}                                 
                                                            


[^] {}