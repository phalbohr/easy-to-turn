**Drehfreudig?**

In der Informatik kann ein Baum so aufgebaut werden: Man beginnt mit einem Knoten, der Wurzel.
Dann wird der Baum wiederholt dadurch erweitert, dass unter einen vorhandenen Knoten u ein neuer Knoten gehängt wird, 
den man ein Kind von u nennt. Wir zeichnen die Kinder eines Knotens von links nach rechts in der Reihenfolge, 
in der sie eingefügt wurden. Ein Knoten ohne Kinder heißt Blatt.

Es ist leicht, einen gegebenen Baum durch ein großes Rechteck aus kleineren Rechtecken so wie in den folgenden 
Abbildungen darzustellen:

docs/Beispiel 1.png

Jeder Knoten wird also durch ein Rechteck dargestellt, und für jeden Knoten u, der kein Blatt ist, gilt, 
dass die Rechtecke seiner Kinder die gleiche Breite haben und die Vereinigung ihrer Oberseiten die Unterseite 
des Rechtecks von u ergibt.

Unter einer Zeichnung eines Baums mit der Wurzel oben kann man den Baum zusätzlich um 180° gedreht mit der Wurzel 
unten zeichnen. Wenn man nun auch noch Rechtecke nach den obigen Regeln dazu zeichnet, kommt die Frage auf, 
ob erreicht werden kann, dass sich die Blätter in der Mitte passgenau treffen, das heißt, dort dieselben 
Rechtecke benutzen. Dann nennen wir den Baum drehfreudig.

Die Abbildung A zeigt, dass es mindestens einen drehfreudigen Baum gibt. Die Abbildung B zeigt, dass es Bäume gibt, 
die nicht drehfreudig sind. docs/Beispiel 2.png

**Aufgabe 1**

Schreibe ein Programm, das einen Baum einliest, herausfindet, ob er drehfreudig ist, und, falls ja, 
ein Bild ausgibt, das seine Drehfreudigkeit zeigt.

Der Baum ist in der Eingabe in natürlicher Weise durch eine Folge von Klammern repräsentiert. 
Jeder Knoten entspricht einem Klammerpaar; zwischen den Klammern sind die Kinder des Knotens angegeben. 
Zum Beispiel werden die oben links und rechts gezeigten nicht drehfreudigen Bäume 
durch die Folgen ((()())(()()())) bzw. ((()())((()())())) repräsentiert.

Wende dein Programm mindestens auf alle Beispiele an, die du auf den BWINF-Webseiten findest, 
und dokumentiere die Ergebnisse.

Zu dieser Aufgabe gibt es 15 Beispieleingaben:

drehfreudig01.txt - der drehfreunde Baum aus Abbildung A des Aufgabenblattes.
drehfreudig02.txt und drehfreudig03.txt - nicht drehfreudige Bäume der Abbildung B des Aufgabenblattes.
drehfreudig04.txt
drehfreudig05.txt
drehfreudig06.txt
drehfreudig07.txt
drehfreudig08.txt
drehfreudig09.txt
drehfreudig10.txt
drehfreudig11.txt
drehfreudig12.txt
drehfreudig13.txt
drehfreudig14.txt
drehfreudig15.txt
Jede Datei enthält eine Baumstruktur bestehend aus öffnenden und schließenden Klammern, 
wie auf dem Aufgabenblatt erläutert.

Hier eine Bespieleingabe:

(()())

In diesem Beispiel besteht der Baum aus einem Wurzelknoten mit zwei Kindknoten.

Würde man die Knoten zum besseren Verständnis benennen, dann würde diese Baumstruktur in Klammer-Notation

(A(B(D)(E(H)(I)))(C(F(J)(K))(G)))

aussehen, wie in diesem Bild dargestellt:

docs/Beispiel 3.png

Einlesedateien für die Arbeit mit Java: A1_EinlesenJava

**Struktur der Dokumentation**

Für jede bearbeitete Aufgabe sollst du im schriftlichen Teil deiner Einsendung (Dokumentation)

- deine Lösungsidee beschreiben;
- die Umsetzung der Idee in ein Programm erläutern;
- an genügend Beispielen zeigen, dass und wie deine Lösung funktioniert;
- und die wichtigsten Teile des Quelltextes anfügen.
Achtung: eine gute Dokumentation muss nicht lang sein, aber unbedingt die Beispiele enthalten! 
Der praktische Teil deiner Einsendung ist die Implementierung und umfasst den kompletten Quelltext
das ausführbare Programm (Windows, Linux, MacOS X oder Android).