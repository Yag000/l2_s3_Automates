%%
%public
%class Lexer  //générera le fichier Lexer.java
%unicode
%standalone


%{
//éventuellement code java qui sera recopié au début de du lexer généré

    public int compteBlabla = 0;
    public int tailleMoustiques = 0;
    public int compteMoustique = 0;

%}

//définition d'expressions régulières

moustique = z+


%{eof
   //éventuellement code java qui sera recopié en fin du lexer généré

  System.out.println("\n nombre de blabla = "+ compteBlabla);
  System.out.println("Taille moyenne d'un moustique: " + ((compteMoustique == 0) ?  0: tailleMoustiques/compteMoustique));

%eof}

%%

//les règles:

bla(bla)+      {
                compteBlabla++;
                System.out.println(yytext() + " : " + compteBlabla);
                }
{moustique}     {
                    System.out.println("il y a un moustique! (longueur: " + yylength() + ")");
                    tailleMoustiques += yylength();
                    compteMoustique++;
                }
[^]             {}