# Final Report - eSports Gids

## Marijn Meijering (10810765)

<img align="right" width="410" height="220" src="https://github.com/10810765/Programmeerproject/blob/master/doc/eSports_Gids_Preview_5.png">

### Korte omschrijving App
Het hoofddoel van mijn app is het vereenvoudigen van het kijken van eSports wedstrijden en streams van je favoriete game.
Mijn app maakt dit mogelijk door wedstrijden en streams bij elkaar te brengen in één overzichtelijke app. Navigeer eenvoudig tussen matches en streams en filter deze op de game waar je interesse in hebt of bewaar je favoriete stream!
<br/>

### Technisch ontwerp App
Hieronder is een schets te zien van het technisch ontwerp van de eSports Gids app zoals deze nu is. Zoals op de schets te zien is heeft mijn app slechts één *activity*, namelijk de *MenuActivity*. Wanneer de app wordt opgestart is dit het eerste scherm wat geladen wordt. Vervolgens wordt dit scherm aangevult met een *fragment*, wanneer er niet genavigeerd is, is de eerste *fragment* die geladen wordt altijd de *FeaturedFragment*. Doormiddel van de *MenuActivity* kan er door de app genavigeerd worden. Deze *activity* verzorgt namelijk de menu balk die altijd op de bodem van ieder scherm te zien is. 

Dat de *MenuActivity* alle *fragments* aanstuurt is ook te zien in het technisch ontwerp, namelijk aan de pijlen tussen de verschillende *classes*. In het technisch ontwerp zijn alle in de app gebruikte *classes* te zien. De richtingen van de pijlen geven aan hoe de communicatie verloopt en welke *classes* er met elkaar communiceren. Bijvoorbeeld de *MatchesFragment* communiceert met *MatchesRequest* en *MatchesAdapter*, maar alleen *MatchesRequest* communiceert (als het ware) terug. Daarnaast wordt de door *MatchesRequest* verkregen informatie opgeslagen in *StreamsInformation* en voorziet *StreamsInformation* de nodige informatie aan *StreamsAdapter* om de *listView* in *FavouritesFragment* te vullen met streams.

Naast het feit dat alle *listViews on-click* functies hebben om naar de website van de desbetreffende match of stream te gaan, heeft *FeaturedFragment* geen *listView* maar twee functies voor klikbare *LinearLayouts* met het zelfde doel, hebben *MatchesFragment* en *StreamsFragment* functies voor *spinners* (drop-down selectie menu's) en hebben *StreamsAdapter* en *FavouritesAdapter* functies voor klikbare favoriet knoppen.

**Note:** Mocht deze schets niet goed te lezen klik dan hier [Technical Design](https://www.draw.io/?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Technical_design.html#R7V1dc6u2Fv01mel9yBmE%2BPJjTtK0nTlnbm7T29vTl45iFJsbjFzAcXJ%2BfSWQ%2BJDAAYKMk5DMxEYSSIi1F2vvLcgZvNw8%2FRSj7for8XF4Zhr%2B0xm8OjPpj%2BXRD1bynJd4hpsXrOLAz4tAWXAbfMe80OClu8DHSa1hSkiYBtt64ZJEEV6mtTIUx2Rfb3ZPwnqvW7TCSsHtEoVq6f8CP13zszDdsvxnHKzWomfgLPKaDRKN%2BZkka%2BSTfaUI%2FngGL2NC0vzb5ukSh2zyxLzk%2B1231BYDi3GUdtnBWl1ew2%2FpFfwvtmL%2F8c%2B%2FbtL%2FnPOjPKJwx0%2F4No0x2iS%2F4r93OEn50NNnMR%2FJPtiEKKJbn%2B9JlN7yGoNuL9dB6H9Bz2THxpOkaPkgtj6vSRx8p%2B1RSKsALaDVccovt%2BmwowVheElCEmf9QGyw39qet%2ByIvK8YJ3TfG3HyQCr6ip5qDb%2BgJBWjJGGItklwl42b7bhB8SqIPpM0JRveSJzldX1Q99kPrUdhsIpo2ZL2hWMxF%2FnZAItu82nFcYqfWq8XKFBAzQeTDU7jZ9qE72B5HDjccqAL8%2B19iUPg8DbrKgYNXog49lfFsUt40C8cIT3QYlsKXM5MJ2QXOKjBxPl7x2CdTct5ks3LBW0A4PYpmxxRT7%2Bt2OdliJLkjAE4PxodXSDq%2FADTGoyjSv3reosFttXOMsNFD7TOiBCKGXGx3n%2FbB%2BlyTb9c3PzCzp8QVhVE9ySmdp4NkNCLTT%2FC4BFng8us6JNiPxQPmT2km9ISYvKABcoikttWBXi8SMEcQ1dAmeqCV2wC32fdfN6vgxTfbtGS9bmnvMzOmewiH%2Fsc31W4wq5wbScQFcMcs9SyO0HWtTQh1mnjt1%2FEtSPRzHGTcZy9qHOcbXgqYNwmjgPaOM7Wy3GNnMMJJeMask1CtGKUF2Unzbhohe%2FiXfCQDSsSDTvRj%2FHD7zh%2BiPEqO1pl7yql%2Fev985TTm6eaYOfp4imvjaeuY7TasFObSWoykpKEGBDbVbR4TSRl6SIpa6HgpQSKsck4w6f6JSjMfUmyj5wAclIZh9Y41bSIt2S5xvGG8dC%2Ft2woAes4L6RfvmfU9f%2Bx9V0YJOnvAd63Cbz69NQpU%2FBuusd4vDnaBtQ7jRsniHVyCc8uFvnfqBjgCm1wMZzmJtQWcfT%2Budvrzd2N1gh0kbfQIip7X%2FhoyyZyJu%2BpyNtxZS%2Fa7uaSLLRxtxpz0S8wD90Scu2Z3xMo68Ur%2FFgRiyUvZnsei6ffP6sBoxm4PV1nRxurmQpOv%2BJod7FMg8cgfZ45bbrIoFPnNKtBjx43MAhV%2F%2BVonKZSWfadauEd%2FbhD4QOTn1f4%2B3h6DhU20Cx6090uTgtOJVscyd43vf4Jswqq3%2FzxxnXPfYDGGTtVRk1oZRCtfiO06uoclCVf8L2wYF7yK8cyGJeIzVGIWFsME6hR968opT7UnKSZnIpldSkc8wlzNOpt%2B2PkaG5Q5KPbJYnZxot5mpTgB7JJ0mCV3SxyezpVihyR66wT5zo1YcO5bk7YnALfAYnwrEVDwqaR8PQlbKbwp0dL2JTM05qpkYntA2RrQP90TRPotKVrgOrvcJqa8zWTc1Sxdqx3vgZqi%2FmpeJkqX8MJp811Pf18TaNyE%2Bw75rAOZG3OakmaD8DHI6VgDF2ELBZZqoQ8p2Am52PZSbY7akZ9KRjNPvLIKZiS4%2BYUzKi0VlmHfZIpGFPF6TVG6S7G%2FqwzJ%2Be1c2BLxGYAFTCNqxe1Rf8s9T44ldC850htocPOSlNZ%2BoLydYuN62LybPUHYK7%2BOYsmIGqL45lqzuIaPZJdTKdoTltMTl2elEGGTtcwnj7mmp8t6fhsyT01pDjAablqEcfMB817vItfNf5xZqH59N87J594bsVUcyslJ8%2FplVPg5YWkKG3Q8Mxf8%2BJWbbz8pp%2BHaePK%2BbGYKie8OrCnLdFiNgTOC86afeDJCcuTn41xzY4usKmNsBpuc1O5wAVUX%2BsET5duQWGIX9ae750m%2B%2Bc%2FmkC%2F0EWTsCHsU2BvzoBMzZLAkFMgTsenUDT621CBjPCUh2cqVHqYsxajkxEcJ2uhzc%2BETdk16ZrgyL9gb%2Bcp57pyefBTkP7Bpu2Tzbe%2B8Ulk36%2BeqhvPwswZCYlD3oWE0UteyAxfHDjy%2BVa2f0TPNu%2FHtMX2t6Ip3Si7yrbKvhh8xOuAzOJiYl95l5DEEHQOKCkv8YHZE0uL6NBX%2BGBOviUEXLnudsNlF2UxDqmr9FgfcBMWeA83JMgEFAcrlIWfLRFVfqJ8rxJRyoHkh1mABT5Zpme7gP%2BVYJrPi3LcDKnFLLwCvE3urm7wlkiswpAf4SAQe4NeK3bbshLHgWTxpq0CSWNBUtjkCxikVwI9V5ptWYOkfcDFzV3qp4R0fsRxAa66R6fOzu7psPPbIGfHGskSHMWkpiVn97XYFaAC%2FYhW5tAuaBZmUjUSLjQO2IlOdm5xYo%2BDSU8KbXtjQVITOXtTkLPTTzoL7DUhvK%2BoHaZb%2FAriO5mFXoRbHenZagl7H8kU5Dc7OgNtYQGlA8lZIM187KgxhH58rKJh8G2%2FxK%2BpVZeMCFdx9U4crgvpJX1wqKsHDEmXQDm6pRuvTWtMZrx2plenK15bVkIcB6%2FAULLo5id7IMPKzzuaC%2FlQujHbLyDRIAkOgnaoSHAHgpbbVBWzqpKYBLNwUo71oARZSdUOlQSWe2S49nPR9CjYHuA8NQkr3nH%2FIlwXU8JVDjDAxWIYXGVvDooFT8eCa9OLiyZXBG9FEIDOAnZStHoKWqVDdEWrK%2FtbRrfYw2hoVV%2F7egJofTP6tasUsCeWr4akBUyJEztrgYWsBY4LV6GdR5Wub06BTuq7A8PzxgGTa7t1MDlHBlPTa15mYdn9Xt05NjppmsCRXt4BvYF4dWCdRaG84FQ3Xs0R8Trw1lwFa4ndt4FX8R67l%2FHashzqSIsOpDQRtAd6QspbEsBxHXe3Xyy%2FEa8jIKhFVoIXsDuQ2cfEa2d%2BnRSvUNKE7tClAefy6hWrY%2FKpbyIWSobhcCesNctgH2yvJ3ErFlOelmc2VCIfP7PQOZAwqThx5cemvYFpBRfUVfnRxclrVzl%2B7EAC6BxJMGe4jgHXflHaOQc2EK7WtHCVcgGDc2CuI4Uqjh336hem7QvXEXzBF5YhakOieFfviYvkc2mBliNL284iWUoY2PJKL81IFOehJQI7PLowJqLsKYECpcySO3Rp1DmAVp2zOi6N6u1NSf6f47zgTZkH2%2Ff1puhm%2BR%2FP8%2Bbl%2F42HP%2F4D).
<br/>
<br/>

![Technisch Ontwerp](https://github.com/10810765/Programmeerproject/blob/master/doc/Technical_design.svg)


### Gemaakte keuzes tijdens ontwikkeling
Under construction

### Verdediging gemaakte keuzes
Under construction







