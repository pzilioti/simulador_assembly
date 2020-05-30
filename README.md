Esse arquivo zip contém 6 arquivos, além do LEIAME.

- diretório source
Contém todos os códigos do projeto.

- Arquitetura.png
É o desenho da arquitetura que seguimos para desenvolver o projeto. Nele é possível ver
a numeração das portas usadas nos comandos horizontais.

- Documentação do Projeto.docx e Documentação do Projeto.odt
Contém todas as convenções que seguimos para escrever os microprogramas. Tamanho em bits,
o que significam e etc. Contém também todo o código dos microprogramas para consulta.

- projeto netbeans.zip
É o projeto do netbeans, é possível importar esse zip pelo programa.

- EP2OCD.jar
Executável java do programa.

Como funciona o programa:
Primeiro escreva o código no campo de texto de cima, após isso clique em Carregar Programa.
A partir daí, a cada clique no botão Executar Próximo a próxima linha do microprograma, que
esta marcada, é executada. O comando assembly que está sendo executado também fica marcado.

Em qualquer momento da execução, é possível mudar o código assembly e reiniciar o programa, é
só clicar novamente em Carregar Programa. Isso irá zerar todos os registradores e fazer a
execução voltar ao inicio.

Para a lista de comandos suportados, consultar a Documentação do Projeto. AX, BX e etc
devem ser escritos sem o X e em maiúsculas. Os números são tratados em hexa, inclusive
a, b, c, d, e, f, que devem ser escritos em minúsculas.

Cada linha do assembly ocupa uma posição na memória, iniciando do zero. Números entre [ ]
são tratados como posição de memória, então é possível sobreescrever o código. As posições
de memória são sempre em decimal.