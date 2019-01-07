# Eindproject minor Programmeren (Universiteit van Amsterdam)

## Marijn Meijering - 10810765
  
### Problem statement
De eSports community wordt alsmaar groter, waardoor er steeds meer wedstrijden en streams over het internet verspreid zijn. Het wordt hierdoor onoverzichtelijk wanneer er professionele wedstrijden gespeeld worden van favoriete games. Net als mensen graag voetbal volgen, zijn er ook mensen die graag wedstrijden van eSports volgen. Daarnaast wil je graag snel kunnen zien of er streamers zijn die je wil zien of die je misschien leuk lijken. Hiervoor wil je niet naar een hoop verschillende websites gaan, maar dit wil je overzichtelijk hebben in één app! Dit is momenteel echter nog niet mogelijk.

#### eSportsGids
Mijn app zal gericht zijn op (voornamelijk jongeren) met een interesse voor competitive gaming. Met competitive gaming bedoel ik hier het volgen van eSports net als je wedstrijden van je lievelings voetbalclub zou volgen. De app moet het eenvoudig maken om wedstrijden en streams te vinden van je lievelings game(s). Je moet kunnen zien welke streams er momenteel bezig zijn op twitch (wellicht ook youtube) en welke professionele wedstrijden er gespeeld gaan worden.
Zo kan je in één oog opslag zien wat er nu bezig is en wat er in de toekomst gaat komen. Mijn app is als het ware een tv-gids voor eSports!

### Schets

https://pr.to/O6H70O/

##### Hoofd functies:
*	De game kiezen waar je in geïnteresseerd bent
*	Kijken welke wedstrijden er vandaag zijn en welke er gaan komen
*	Kijken welke streamers je gekozen game streamen (in eerste instantie alleen twitch)

##### Optineel (wellicht extra als er tijd over is):
* Ook YouTube streamers integreren, momenteel heb ik daar echter nog geen duidelijke API voor gevonden.
* Eventueel een Google Maps API om te laten zien waar professionele wedstrijden zich plaats vinden (ik weet echter momenteel niet of die informatie er bij staat).

### Prerequisites

#### Data sources
https://pandascore.co/pricing -> Informatie over professionele wedstrijden.  
https://dev.twitch.tv/docs/api/reference#get-streams -> Streamers die de games spelen.

#### External components
Momenteel geen.

#### Similar
Waarschijnlijk kan ik inspiratie voor de layout halen uit apps gemaakt als Tv-gids.

#### Hardest parts
Het lastigst is denk ik het integreren van de API's, zodat deze de informatie geven die ik nodig heb. Daarnaast lijkt het me best lastig om een duidelijk overzicht te krijgen van alle informatie.
Tevens zou ik graag een soort calender of tijdlijn willen integreren om wedstrijden aan te kondigen (indien mogelijk).
