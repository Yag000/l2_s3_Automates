%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


//définition d'expressions régulières

%{
public double count = 0;
public double sum = 0;
%}

%{eof
	System.out.println("nbre des chiffres = "+ count);
	System.out.println("nbre des chiffres = "+ sum);
	System.out.println("nbre des chiffres = "+ sum/count);
%eof}


chiffre = [:digit:]

entier = ("+"|"-")?{chiffre}+

float = ("+"|"-")?{chiffre}*("."{chiffre}*)?

%%

//les règles:

{float}  {count++;sum += Double.parseDouble(yytext()); System.out.println(yytext());}

[^] {}