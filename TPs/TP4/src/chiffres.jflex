%%
%public
%class Lexer
%unicode
%standalone

%{
public int count = 0;
public int sum = 0;
%}

%{eof
	System.out.println("nobre des chiffres = "+ count);
	System.out.println("somme des chiffres = "+ sum);
	System.out.println("moyenne des chiffres = "+ (count == 0 ? 0 : sum/count));
%eof}

chiffre = [0-9]

%%
{chiffre}     {count++; sum += Integer.parseInt(yytext()); System.out.println(yytext());}

[^]           {}
