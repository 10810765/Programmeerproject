### day 1 (09/01/2019)
Vandaag vooral bezig geweest met het werkend krijgen van de API's. In de zin van kijken of ik de gewenste data bij de API's kan opvragen. Dit is zo ver als ik weet gelukt en nu bruikbaar in de app. Daarnaast heb ik het gros van de nodige activiteiten en classes aangemaakt die nodig zijn voor de app. Hierbij heb ik aan sommige activiteit alvast een begin gemaakt voor het design (hier morgen mee verder).

### day 2 (10/01/2019)
Vandaag de app aangevult en alle activities die nodig zijn om de app werkend te krijgen aangemaakt. Tevens de indeling van de activities grotendeels gedaan en buttons toegevoegd. De buttons verwijzen naar de juiste activiteiten waardoor er momenteel een soort klikbaar prototype is ontstaan.

### day 3 (11/01/2019)
Bezig geweest met het implementeren van de eerste API (pandascore), deze is nu bijna werkend. De juiste informatie is al gefilterd alleen de textvelden moeten nog gevuld worden. Hier liep ik echter tegen een probleem aan wat ik moet verhelpen. Daarna kan ik verder met de tweede API (twitch). Plus matches information class toegevoegd.

### day 4 (12/01/2019)
(Eindelijk) De call naar de pandascore API werkend gekregen, deze haalt nu de juiste informatie op en geeft het weer in de app!

### day 5 (13/01/2019)
De tweede API geÏmplementeerd (Twitch API), deze haalt nu de juiste informatie op en laat deze zien in textvelden. Tevens is, om de informatie op te slaan, een streams informatie class aangemaakt.

### day 6 (14/01/2019)
List adapter voor MatchesInformation (een lijst met professionele matches) bijna werkend. Daarnaast er na feedback voor gekozen om extra informatie bij matches weer te geven in de overview activity en de matches information activity. 

### day 7 (15/01/2019)
List adapter voor zowel de matches als de streamers list werkend. Deze halen nu informatie op doormiddel van een API call en laten dit in een listView zien. Tevens na overleg met Martijn ervoor gekozen om wat wijzigingen aan het disign van de app toe te brengen. Zo zal ik een navigatie balk maken die altijd zichtbaar is om sneller tussen verschillende activities te wisselen. Tevens zal de overview activity een featured activity worden waar je suggesties kan zien ipv een voorvertoning van je gekozen game.

### day 8 (16/01/2019)
Menu bar toegevoegd aan alle activities voor betere navigatie. Deze is nu volledig werkend en geeft aan op welke pagina je bent door midden van het icoontje een andere kleur te geven in het menu. 

### day 9 (17/01/2019)
Drop-down menu toegevoegd aan StreamsActivity en MatchesActivity. Hierdoor is het mogelijk om in de activity te kiezen van welke game je informatie wil hebben. Dit is nu volledig werkend in beide activities. Tevens is er nu de optie om in de StreamsActivity meer games toe te voegen dan er bij MatchesActivity mogelijk zijn. Voorheen werd de game keue voor beide Activities gebruikt en was er een limitatie voor 4 games (aangezien de pandascore API er niet meer ondersteund). Dit is nu niet meer het geval (biede individueel).

Hierdoor is de CategoryActivity overbodig geworden en dus verwijderd.

### day 10 (18/01/2019)
Na overleg ervoor gekozen om de activities die je kan opnenen doormiddel van een klik op de menu balk te vervangen door fragments. Hierdoor is duplicate code verwijderd en hoeft de menu balk niet opnieuw geladen te worden bij het openen van een activity. De activities zijn vandaag omgezet naar fragments en werken naar behoren!

Als laatst is het nu mogelijk om op een stream te klikken, deze wordt dan geopend in je webbrowser.

### day 11 (19/01/2019)
Afbeeldingen toegevoegd aan de macthes en streamer listview. Daarnaast is het nu bij streamers mogelijk om een taal te kiezen. De streams worden dan hierop gefilterd.

### day 12 (20/01/2019)
Begonnen met werken aan de mogelijkheid streams te favouriten. ToggleButton is toegevoegd aan de listview maar nog niet werkend.


### day 13 (21/01/2019)
Het is nu mogelijk om streams te favouriten (toggle button in listview). Tevens worden de favourites onthouden als je de app draait of afsluit. Verder wat kleine aanpassingen gedaan ter verbeteing van de app, hier en daar wat state-restoration.

### day 14 (22/01/2019)
Verder gewerkt aan de mogelijkheid om streamers te favouriten. Dit is nu volledig werkend. Na overleg met een TA besloten om alle streamers die iemand heeft geselecteerd als favoriet in een eigen fragment weer te geven. Op deze manier blijft het overzicht behouden, anders zou de StreamersFragment nogal druk worden. Nu kan de gebruiker simpelweg op het sterretje recht op de menu balk drukken en alle streamers die hij heeft aangegeven als favoriet worden daar wergegeven, mits deze streamers ook live zijn. Zo kan het voorkomen dat iemand 2 streamers als favoriet heeft maar er geen in de favorieten fragment staan. Dit geeft aan dat er geen favoriete streamers streamen op dat moment. Daarnaast wordt bij de favoriete streamers aangegeven welk spel ze op dat moment streamen en kan je ze zowel in de streamers als favourites fragment weer on-favorieten.

### day 15 (23/01/2019)
Vandaag de on click listener van de matches fragment gemaakt. Deze had wat meer tijd nodig aangezien het ook voor kan komen dat een bepaalde match geen url heeft (url is dan null). Om dit te verhelpen worden alle cases waar een url null is deze vervangen door de officiele website van het desbetreffende spel!


### day 16 (24/01/2019)
Bezig geweest met de featured pagina. Deze is nu qua inhoud helemaal compleet! Hiervoor moest de macthRequest (met de API call) nog wat worden aangepast om de logo's van de teams op te halen. Daarnaast is de streamRequest ook iets aangepest om een preview van de stream te kunnen tonen op de featured pagina. De match en stream worden weergegeven in een clickable linearLayout zodat je direct naar de match of wedstrijd kan gaan door erop te klikken. Aangezien de linear layout clickable gemaakt kon worden door de "background" optie aan te passen kon ik de kleur niet meer veranderen. Daarom zit er nog een linearLayout achter de clickable linearLayout (precies dezelfde afmetingen) om ook de kleur te kunnen veranderen.

### day 17 (25/01/2019)
Alvast begonnen aan het opschonen van de code. Daarnaast hier en daar de layout veranderd (nog niet klaar). Tijdens het testen van de app verschillende bugs tegen gekomen die nu verholpen zijn, vooral dit heeft redelijk wat tijd gekost.

### day 18 (26/01/2019)
State restoratie geimplementeerd voor de drop down menu's (spinners) bij het draaien van de app of het volledig afsluiten van de app blijft je voorheen gekozen game en taal geselecteerd. Tevens blijft bij het draaien van de app de fragment die je geopend heb nu ook open, bij het aflsuiten van de app kom je echter wel weer terecht op de featured fragment. Dit is na overleg met een TA e wat user opinies besloten. Tevens is de tijd en datum nu geformateerd, deze had een standaard format wat onhandig las (is nog wel GMT). 

### day 19 (27/01/2019)
Vandaag de tijd omgezet van GMT naar GMT+1 zodat voor ons de correcte tijd van matches wordt weergegeven. Daarnaast verder gegaan met het opschonen van de code. Het opschonen zou nu (zo goed als) klaar moeten zijn.
