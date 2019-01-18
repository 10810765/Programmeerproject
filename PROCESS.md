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
