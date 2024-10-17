# IT-Academy-S1.05-Java Utils

### Nivel 1

- Exercici 1  
  Crea una classe que llisti alfabèticament el contingut d'un directori rebut per paràmetre.

- Exercici 2  
  Afegeix a la classe de l’exercici anterior, la funcionalitat de llistar un arbre de directoris amb el contingut de tots els seus nivells (recursivament) de manera que s'imprimeixin en pantalla en ordre alfabètic dins de cada nivell, indicant a més si és un directori (D) o un fitxer (F), i la seva última data de modificació.

- Exercici 3  
  Modifica l’exercici anterior. Ara, en lloc de mostrar el resultat per la pantalla, guarda el resultat en un fitxer TXT.

- Exercici 4  
  Afegeix la funcionalitat de llegir qualsevol fitxer TXT i mostra el seu contingut per consola.

- Exercici 5  
  Ara el programa ha de serialitzar un Objecte Java a un fitxer .ser i després l’ha de desserialitzar.

### Nivel 2

### Tecnologies Utilitzades

Java

### Requisits

Java 23.0

### Instal·lació

Clona aquest repositori: -> git clone  
Accedeix al directori del projecte:   cd Nivell1-Biblioteca

### Contribucions

Les contribucions són benvingudes! Per favor, segueix els següents passos per a contribuir:

Fes un fork del repositori  
Crea una nova branca:  git checkout -b feature/NovaFuncionalitat  
Fes els teus canvis i commiteja'ls: git commit -m 'Afegeix Nova Funcionalitat'  
Puja els canvis a la teva branca: git push origin feature/NovaFuncionalitat  
Fes un pull request

### Notes
He creat la classe DirectoryContent amb diferents mètodes static que implementen el que es demana als exercicis.
A més, he afegit un nou directory al src/main per afegir totes les files que es van generant:
- directoryContent.txt: fitxer de text amb els directoris i files llistats del main folder
- myclass.ser: classe serialitzada
- directoryContentParametrized.txt: fitxer de text amb el llistat de directoris i files utilizant els parametres de resources/config.properties
- directoryContent.encrypted: encrypted directoryContent.txt
- directoryContentDecrypted.txt: decrypted directoryContent.txt