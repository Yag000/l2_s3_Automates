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

[:digit:]+[02468]","[:digit:]+("."[:digit:]*)?","[012] { counter++; sum += Double.parseDouble(yytext().substring(9,yytext().length() - 2)); }

                                                                
                                                            


[^] {}