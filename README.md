# Jantar_dos_Filosofos
Resolução do clássico problema da computação na questão de sicronismo, o Jantar dos filósofos. Trabalho feito em JAVA para a matéria de Sistemas Operacionais, ministrada pelo professor Marco Câmara.
Nesta resolução cada filósofo é uma thread diferente que compartilham um mesmo vertor de booleanos que são os garfos. A rotina da thread é pesar, pegar garfo, tentar comer e soltar garfos.
Antes de qualquer alterção dos garfos a thread precisa passar por um semáforo, que faz a exclusão mútua para impedir que duas threads usem o vertor de garfos ao mesmo tempo.
