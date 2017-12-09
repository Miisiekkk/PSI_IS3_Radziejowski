clear all; close all; clc;  

%DEKLARACJE DANYCH WEJSCIOWYCH - TABLIC z LITERAMI
 TestLiteraA = [0 1 1 1 0;
               1 0 0 0 1;
               1 1 1 1 1;
               1 0 0 0 1;
               1 0 0 0 1];
 
 TestLiteraB = [1 1 1 1 0;
               1 0 0 0 1;
               1 1 1 1 0;
               1 0 0 0 1;
               1 1 1 1 0]; 

 TestLiteraC = [0 1 1 1 1;
               1 0 0 0 0;
               1 0 0 0 0;
               1 0 0 0 0;
               0 1 1 1 1];
 
 TestLiteraD = [1 1 1 1 0;
               1 0 0 0 1;
               1 0 0 0 1;
               1 0 0 0 1;
               1 1 1 1 0];
 
 TestLiteraE = [1 1 1 1 1;
               1 0 0 0 0;
               1 1 1 1 0;
               1 0 0 0 0;
               1 1 1 1 1];
 
 TestLiteraF = [1 1 1 1 1;
               1 0 0 0 0;
               1 1 1 1 0;
               1 0 0 0 0;
               1 0 0 0 0]; 
 
 TestLiteraG = [0 1 1 1 1;
               1 0 0 0 0;
               1 0 1 1 1;
               1 0 0 0 1;
               0 1 1 1 0];
 
 TestLiteraH = [1 0 0 0 1;
               1 0 0 0 1;
               1 1 1 1 1;
               1 0 0 0 1;
               1 0 0 0 1];
 
 TestLiteraI = [0 1 1 1 0;
               0 0 1 0 0;
               0 0 1 0 0;
               0 0 1 0 0;
               0 1 1 1 0];
 
 TestLiteraJ = [0 1 1 1 1;
               0 0 0 0 1;
               0 0 0 0 1;
               0 1 0 0 1;
               0 0 1 1 1];
 
 TestLiteraK = [1 0 0 1 1;
               1 0 1 0 0;
               1 1 0 0 0;
               1 0 1 0 0;
               1 0 0 1 1];
 
 TestLiteraL = [1 0 0 0 0;
               1 0 0 0 0;
               1 0 0 0 0;
               1 0 0 0 0;
               1 1 1 1 1];
 
 TestLiteraM = [1 0 0 0 1;
               1 1 0 1 1;
               1 0 1 0 1;
               1 0 0 0 1;
               1 0 0 0 1];
 
 TestLiteraN = [1 0 0 0 1;
               1 1 0 0 1;
               1 0 1 0 1;
               1 0 0 1 1;
               1 0 0 0 1];
  
 TestLiteraO = [0 1 1 1 0;
               1 0 0 0 1;
               1 0 0 0 1;
               1 0 0 0 1;
               0 1 1 1 0];
 
 TestLiteraP = [1 1 1 1 0;
               1 0 0 0 1;
               1 1 1 1 0;
               1 0 0 0 0;
               1 0 0 0 0]; 
 
 TestLiteraR = [1 1 1 1 0;
               1 0 0 0 1;
               1 1 1 1 0;
               1 0 0 1 0;
               1 0 0 0 1]; 
 
 TestLiteraS = [0 1 1 1 1;
               1 0 0 0 0;
               0 1 1 1 0;
               0 0 0 0 1;
               1 1 1 1 0]; 
 
 TestLiteraT = [1 1 1 1 1;
               0 0 1 0 0;
               0 0 1 0 0;
               0 0 1 0 0;
               0 0 1 0 0]; 

 TestLiteraU = [1 0 0 0 1;
               1 0 0 0 1;
               1 0 0 0 1;
               1 0 0 0 1;
               0 1 1 1 0];
 
%DEKLARACJA TABLICY LITER
Litery = ["A" "B" "C" "D" "E" "F" "G" "H" "I" "J" "K" "L" "M" "N" "O" "P" "R" "S" "T" "U"];
           
iterator=1; %TWORZENIE SIECI
dane = zeros(5*5, 20); %ZEROWANIE MACIERZY POMOCNICZNEJ DO WYNIKOW KONCOWYCH

for i = 1 : 5 
    for j = 1 : 5 
    dane(iterator, :) = [TestLiteraA(i, j) TestLiteraB(i, j) TestLiteraC(i, j) TestLiteraD(i, j) TestLiteraE(i, j) TestLiteraF(i, j) TestLiteraG(i, j) TestLiteraH(i, j) TestLiteraI(i, j) TestLiteraJ(i, j) TestLiteraK(i, j) TestLiteraL(i, j) TestLiteraM(i, j) TestLiteraN(i, j) TestLiteraO(i, j) TestLiteraP(i, j) TestLiteraR(i, j) TestLiteraS(i, j) TestLiteraT(i, j) TestLiteraU(i, j)];
    iterator=iterator+1;
    end
end

dane = dane'; %TRANSPONOWANIE MACIERZY
test =[1 0 0 0 1, 1 0 0 0 1, 1 0 1 0 1, 1 1 0 1 1, 1 0 0 0 1]; %MACIERZ DO TESTOWANIA KONRETNEGO ZNAKU (LITERA W)
Wagi = zainicjuj_wagi(size(dane,2)); %INICJACJA WAG LOSOWYMI WARTOŒCIAMI Z ZAKRESU <-1, 1>
Hebb_Test(Wagi, dane, Litery); %TESTOWANIE SIECI HEBBA DLA DANYCH POCZATKOWYCH
Wagi = Hebb_Trening(Wagi, dane, 0.2, 10000, 2); %TRENOWANIE
Hebb_Test(Wagi, dane, Litery); %TESTOWANIE HEBBA DLA ZAKTUALIZOWANYCH DANYCH
wyznacz_wartosc(test, Wagi) %WYZNACZENIE WARTOSCI NA WYJSCIU DLA TESTOWANEJ LITERY

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%FUNKCJA INICJUJACA WAGI
function [wagi] = zainicjuj_wagi(rozmiar)
    wagi = (rand(rozmiar,1)*2) - 1;
end

%FUNKCJA WYZNACZAJACA WARTOSC DLA PODANEJ LITERY TESTOWEJ
function [wyjscie] = wyznacz_wartosc(dane,wagi)
    wektor_wag = wagi'*dane';
    wyjscie = sin(wektor_wag);
end

%FUNKCJA AKTUALIZUJACA WAGI BEZ WSPOLCZYNNIKA ZAPOMINANIA
function [aktualizowane_wagi] = aktualizuj_wagi_bez_zapominania(wagi,wartosc,dane,wspolczynnik_uczenia)
    aktualizowane_wagi = wagi + (wspolczynnik_uczenia*wartosc)*dane';
end

%FUNKCJA AKTUALIZUJACA WAGI ZE WSPOLCZYNNIKIEM ZAPOMINANIA
function [aktualizowane_wagi] = aktualizuj_wagi_z_zapominaniem(wagi,wartosc,dane,wspolczynnik_uczenia,wspolczynnik_zapominania)
    aktualizowane_wagi = wagi*(1-wspolczynnik_zapominania) + (wspolczynnik_uczenia*wartosc)*dane';
end

%FUNKCJA WYPISUJACA WEKTOR WAG
function [] = wypisz_wektor_wag(wektor_wag)
    for i = 1:numel(wektor_wag)
        fprintf(' %f \n',wektor_wag(i));
    end
    fprintf('\n');
end

%FUNKCJA REALIZUJACA UCZENIE METODA HEBBA
function [wagi] = Hebb_Trening(wagi, dane_wejsciowe,wspolczynnik_uczenia,kroki_uczenia,zapominanie)
fprintf('WAGI WEJSCIOWE:\n');
wypisz_wektor_wag(wagi);
for k = 1:kroki_uczenia
    for i = 1:size(dane_wejsciowe,1)
        wartosc = wyznacz_wartosc(dane_wejsciowe(i,:),wagi);
        if (zapominanie == 1)
        wagi = aktualizuj_wagi_bez_zapominania(wagi,wartosc,dane_wejsciowe(i,:),wspolczynnik_uczenia);
        end
        if(zapominanie == 2)
        wagi = aktualizuj_wagi_z_zapominaniem(wagi,wartosc,dane_wejsciowe(i,:),wspolczynnik_uczenia, 0.5);
        end
    end
end
fprintf('WAGI WYJSCIOWE:\n');
wypisz_wektor_wag(wagi);
end

%FUNKCJA REALIZUJACA TESTOWANIE SIECI UCZONEJ METODA HEBBA
function [] = Hebb_Test(wagi,dane,Litery) 
    fprintf('TEST: \n');
    rozmiar = size(dane,1);
    for i = 1:rozmiar
        wartosc = (wyznacz_wartosc(dane(i,:),wagi));
        fprintf('%f \n', wartosc);
    end
end