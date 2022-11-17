%%
%public
%class Lexer
%unicode
%standalone

%{
public int count = 0;
public double sum = 0;
%}

%{eof
	System.out.println("nobre des chiffres = "+ count);
	System.out.println("somme des chiffres = "+ sum);
	System.out.println("moyenne des chiffres = "+ (count == 0 ? 0 : sum/count));
%eof}

chiffre = [0-9]
nombre = ("+"|"-")?{chiffre}*("."{chiffre}*)?

%%
{nombre}     {count++; sum += Double.parseDouble(yytext()); System.out.println(yytext());}

[^]           {}
