Esse arquivo zip cont�m 6 arquivos, al�m do LEIAME.

- diret�rio source
Cont�m todos os c�digos do projeto.

- Arquitetura.png
� o desenho da arquitetura que seguimos para desenvolver o projeto. Nele � poss�vel ver
a numera��o das portas usadas nos comandos horizontais.

- Documenta��o do Projeto.docx e Documenta��o do Projeto.odt
Cont�m todas as conven��es que seguimos para escrever os microprogramas. Tamanho em bits,
o que significam e etc. Cont�m tamb�m todo o c�digo dos microprogramas para consulta.

- projeto netbeans.zip
� o projeto do netbeans, � poss�vel importar esse zip pelo programa.

- EP2OCD.jar
Execut�vel java do programa.

Como funciona o programa:
Primeiro escreva o c�digo no campo de texto de cima, ap�s isso clique em Carregar Programa.
A partir da�, a cada clique no bot�o Executar Pr�ximo a pr�xima linha do microprograma, que
esta marcada, � executada. O comando assembly que est� sendo executado tamb�m fica marcado.

Em qualquer momento da execu��o, � poss�vel mudar o c�digo assembly e reiniciar o programa, �
s� clicar novamente em Carregar Programa. Isso ir� zerar todos os registradores e fazer a
execu��o voltar ao inicio.

Para a lista de comandos suportados, consultar a Documenta��o do Projeto. AX, BX e etc
devem ser escritos sem o X e em mai�sculas. Os n�meros s�o tratados em hexa, inclusive
a, b, c, d, e, f, que devem ser escritos em min�sculas.

Cada linha do assembly ocupa uma posi��o na mem�ria, iniciando do zero. N�meros entre [ ]
s�o tratados como posi��o de mem�ria, ent�o � poss�vel sobreescrever o c�digo. As posi��es
de mem�ria s�o sempre em decimal.