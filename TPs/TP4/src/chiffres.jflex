%%
%public
%class Lexer
%unicode
%standalone

%{
public int count = 0;
%}

%{eof
	System.out.println("nbre des chiffres = "+ count);
%eof}

chiffre = [0-9]

%%
{chiffre}     {count++; System.out.println(yytext());}

[^]           {}
