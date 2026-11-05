# TP4 – Chat en UDP (Java)

## Étape 2 – Chat unidirectionnel

**Principe :** le client envoie des messages au serveur, précédés de son nom (**[amal] : bonjour**), et le serveur les affiche avec l'**IP et le port** de l'expéditeur.

![Serveur4_2](capture/Capture%20d'%C3%A9cran%202026-11-05%20184137.png)

Code du serveur : il se lie à son port avec **InetSocketAddress** et attend les paquets dans une boucle.

![Client4_2](capture/Capture%20d'%C3%A9cran%202026-11-05%20184147.png)

Code du client : il demande le **nom d'utilisateur** puis envoie chaque message au serveur.

![Serveur lancé](capture/Capture%20d'%C3%A9cran%202026-11-05%20184216.png)

Le serveur est lancé et attend les messages.

![Client](capture/Capture%20d'%C3%A9cran%202026-11-05%20184251.png)

Le client **amal** envoie **bonjour** et **bonsoir**, puis quitte avec **quit**.

![Serveur reçoit](capture/Capture%20d'%C3%A9cran%202026-11-05%20184257.png)

Le serveur affiche chaque message avec l'**IP** et le **port** du client.

---

## Étape 3.1 – Chat bidirectionnel

**Principe :** le client et le serveur peuvent **envoyer et recevoir** en même temps grâce à un **thread** en plus du **main** :
- Client : le **main** envoie, le thread **Reception** reçoit.
- Serveur : le **main** reçoit, le thread **Envoi** lit le clavier et répond au dernier client.

![Serveur4_3_1](capture/Capture%20d'%C3%A9cran%202026-11-05%20184745.png)

Code du serveur : le **main** reçoit les messages, retient l'adresse du **client** et lance le thread **Envoi**.

![Envoi](capture/Capture%20d'%C3%A9cran%202026-11-05%20184751.png)

Classe **Envoi** (**extends Thread**) : elle lit le clavier du serveur et envoie **[Serveur] : ...** au client.

![Client4_3_1](capture/Capture%20d'%C3%A9cran%202026-11-05%20184758.png)

Code du client : le **main** envoie les messages et lance le thread **Reception**.

![Reception](capture/Capture%20d'%C3%A9cran%202026-11-05%20184805.png)

Classe **Reception** (**extends Thread**) : elle reçoit et affiche les messages en temps réel.

![Serveur lancé](capture/Capture%20d'%C3%A9cran%202026-11-05%20184817.png)

Le serveur est lancé et attend les messages.

![Client envoie](capture/Capture%20d'%C3%A9cran%202026-11-05%20184926.png)

Le client **amal** envoie **123** et **bonjour**.

![Serveur reçoit et répond](capture/Capture%20d'%C3%A9cran%202026-11-05%20184944.png)

Le serveur reçoit les messages du client et répond **hi** depuis son clavier.

![Client reçoit la réponse](capture/Capture%20d'%C3%A9cran%202026-11-05%20184950.png)

Le client reçoit **[Serveur] : hi** : la communication marche dans les **deux sens**.

---

## Étape 3.2 – Diffusion à plusieurs clients

**Principe :** le serveur **enregistre** chaque nouveau client dans une liste (**Set&lt;SocketAddress&gt;**) et **renvoie** chaque message à **tous les autres** clients, sauf l'expéditeur.

![Serveur4_3_2](capture/Capture%20d'%C3%A9cran%202026-11-05%20185134.png)

Code du serveur : une seule boucle (**sans thread**) qui reçoit, enregistre l'expéditeur, puis diffuse aux autres.

![Client4_3_2](capture/Capture%20d'%C3%A9cran%202026-11-05%20185139.png)

Code du client : identique à l'étape 3.1, il réutilise le thread **Reception**.

![Reception](capture/Capture%20d'%C3%A9cran%202026-11-05%20185143.png)

Classe **Reception** : la même classe sert aux deux clients de l'étape 3.

![Client amal](capture/Capture%20d'%C3%A9cran%202026-11-05%20190536.png)

Le client **amal** reçoit les messages de mariem (**cv**, **bonsoir**), mais jamais les siens.

![Client mariem](capture/Capture%20d'%C3%A9cran%202026-11-05%20190544.png)

Le client **mariem** reçoit **[amal] : bonne nuit**, mais pas **bonjour**, envoyé avant qu'elle soit enregistrée.

![Serveur4_3_2 exécution](capture/Capture%20d'%C3%A9cran%202026-11-05%20190551.png)

Le serveur voit deux clients sur deux **ports différents** et diffuse chaque message à l'autre.

---

## Conclusion

- **Étape 2 :** envoyer et recevoir des datagrammes UDP.
- **Étape 3.1 :** utiliser des **threads** pour envoyer et recevoir en même temps.
- **Étape 3.2 :** garder une **liste de clients** pour faire de la **diffusion**.
