package com.mycompany.bowl.analizadores;
import java_cup.runtime.*;
%%
%class LexicoLenguaje
%type java_cup.runtime.Symbol
%cupsym SintaxisLenguajesSym
%public
%cup
%full
%line
%column
espacio=[\t|\r|\n|\f|\v|" "| ]+
letrasmi=[a-z]
letrasma=[A-Z]
cero=[0]
OneNine = [1-9]
Number = ({OneNine}|{cero})
%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type, Object value){
        System.out.println(value);
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        System.out.println(type);
        return new Symbol(type, yyline, yycolumn);
    }
%}

%state STRING

%%

<YYINITIAL> ("|") {return new Symbol(SintaxisLenguajesSym.or, yycolumn, yyline, yytext());}
<YYINITIAL> ("/*")(.|{espacio})*("*/") {/*NO PRESTAR ATENCION*/}
<YYINITIAL> ("//")(.)*("\n") {/*NO PRESTAR ATENCION*/}
/* Palabras Reservadas */

<YYINITIAL> ("nombre") {return new Symbol(SintaxisLenguajesSym.nom, yycolumn, yyline, yytext());}
<YYINITIAL> ("version") {return new Symbol(SintaxisLenguajesSym.version, yycolumn, yyline, yytext());}
<YYINITIAL> ("autor") {return new Symbol(SintaxisLenguajesSym.autor, yycolumn, yyline, yytext());}
<YYINITIAL> ("lanzamiento") {return new Symbol(SintaxisLenguajesSym.lanzamiento, yycolumn, yyline, yytext());}
<YYINITIAL> ("extension") {return new Symbol(SintaxisLenguajesSym.extension, yycolumn, yyline, yytext());}
<YYINITIAL> ("terminal") {return new Symbol(SintaxisLenguajesSym.term, yycolumn, yyline, yytext());}
<YYINITIAL> ("no") {return new Symbol(SintaxisLenguajesSym.no, yycolumn, yyline, yytext());}
<YYINITIAL> ("RESULT") {return new Symbol(SintaxisLenguajesSym.result, yycolumn, yyline, yytext());}
<YYINITIAL> ("printf") {return new Symbol(SintaxisLenguajesSym.printf, yycolumn, yyline, yytext());}

/* Espacios en blanco */
{espacio}+ {/*Ignore*/}

<YYINITIAL> ("{") {return new Symbol(SintaxisLenguajesSym.llavea, yycolumn, yyline, yytext());}
<YYINITIAL> ("&") {return new Symbol(SintaxisLenguajesSym.y, yycolumn, yyline, yytext());}
<YYINITIAL> ("}") {return new Symbol(SintaxisLenguajesSym.llavec, yycolumn, yyline, yytext());}
<YYINITIAL> ("%d") {return new Symbol(SintaxisLenguajesSym.pord, yycolumn, yyline, yytext());}
<YYINITIAL> (",") {return new Symbol(SintaxisLenguajesSym.coma, yycolumn, yyline, yytext());}
<YYINITIAL> ("=") {return new Symbol(SintaxisLenguajesSym.igual, yycolumn, yyline, yytext());}
<YYINITIAL> ("[") {return new Symbol(SintaxisLenguajesSym.corchetea, yycolumn, yyline, yytext());}
<YYINITIAL> ("]") {return new Symbol(SintaxisLenguajesSym.corchetec, yycolumn, yyline, yytext());}
<YYINITIAL> ("(") {return new Symbol(SintaxisLenguajesSym.parentesisa, yycolumn, yyline, yytext());}
<YYINITIAL> (")") {return new Symbol(SintaxisLenguajesSym.parentesisc, yycolumn, yyline, yytext());}
<YYINITIAL> ("+") {return new Symbol(SintaxisLenguajesSym.mas, yycolumn, yyline, yytext());}
<YYINITIAL> ("*") {return new Symbol(SintaxisLenguajesSym.mult, yycolumn, yyline, yytext());}
<YYINITIAL> ("-") {return new Symbol(SintaxisLenguajesSym.menos, yycolumn, yyline, yytext());}
<YYINITIAL> ("/") {return new Symbol(SintaxisLenguajesSym.div, yycolumn, yyline, yytext());}
<YYINITIAL> ("%%") {return new Symbol(SintaxisLenguajesSym.pp, yycolumn, yyline, yytext());}
<YYINITIAL> ("%") {return new Symbol(SintaxisLenguajesSym.percent, yycolumn, yyline, yytext());}
<YYINITIAL> (";") {return new Symbol(SintaxisLenguajesSym.puntocoma, yycolumn, yyline, yytext());}
<YYINITIAL> ("::") {return new Symbol(SintaxisLenguajesSym.dpp, yycolumn, yyline, yytext());}
<YYINITIAL> (":") {return new Symbol(SintaxisLenguajesSym.dosp, yycolumn, yyline, yytext());}
<YYINITIAL> ("\\n") {return new Symbol(SintaxisLenguajesSym.lin, yycolumn, yyline, yytext());}
<YYINITIAL> ("\\t") {return new Symbol(SintaxisLenguajesSym.tab, yycolumn, yyline, yytext());}
<YYINITIAL> ("\\b") {return new Symbol(SintaxisLenguajesSym.esp, yycolumn, yyline, yytext());}
<YYINITIAL> ("\"") {return new Symbol(SintaxisLenguajesSym.comilla, yycolumn, yyline, yytext());}
<YYINITIAL> ("?") {return new Symbol(SintaxisLenguajesSym.pre, yycolumn, yyline, yytext());}
<YYINITIAL> (".") {return new Symbol(SintaxisLenguajesSym.punto, yycolumn, yyline, yytext());}

/* Expresiones Regulares */

<YYINITIAL> {letrasmi}|{letrasma} {return new Symbol(SintaxisLenguajesSym.caracter, yycolumn, yyline, Integer.parseInt(yytext()));}
<YYINITIAL> {letrasmi}+ {return new Symbol(SintaxisLenguajesSym.idt, yycolumn, yyline, yytext());}
<YYINITIAL> {letrasma}+ {return new Symbol(SintaxisLenguajesSym.idn, yycolumn, yyline, yytext());}
<YYINITIAL> "-"{Number}+|{Number} {return new Symbol(SintaxisLenguajesSym.number, yycolumn, yyline, yytext());}

<YYINITIAL> ({letrasmi}|{letrasma}|{Number})+ {return new Symbol(SintaxisLenguajesSym.id, yycolumn, yyline, yytext());}
<YYINITIAL> ({letrasmi}|{letrasma}|{Number}|" ")+ {return new Symbol(SintaxisLenguajesSym.nombre, yycolumn, yyline, yytext());}

<YYINITIAL> (.)+ {return new Symbol(SintaxisLenguajesSym.codigo, yycolumn, yyline, yytext());}