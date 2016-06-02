[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title='poelregels' /]

<body>

[@navigationUtil.navigation title='poelregels' back='/' bgColor='bg-greybat' /]

<div class="grid grid-gutter bg-white about">
    <div class="component">
        <div class="col col-12-3"></div>

        <div class="col col-12-6">
            <h1 class="h4 about__title"><span class="c-greybat">Hoe werkt het?</span></h1>

            <p class="c-green about__text">
                De <b>inleg</b>  voor VPRO's EK-poel bedraagt vijf euro, overmaken naar F. Hermsen, Amsterdam, NL35 INGB000 4707382
            </p>

            <p class="c-green about__text">
                <b>Voorspel per wedstrijd de uitslag.</b> Je kunt de voorspelling tot aan het begin van elke wedstrijd aanpassen. Na aanvang van de wedstrijd mag en kan je niets meer aan die wedstrijdvoorspelling veranderen. (Je mag natuurlijk ook alle wedstrijden in één keer invullen. Dus ook al je op vakantie/offline gaat, kun je gewoon meedoen.)
            </p>

            <p class="c-green about__text">
                Per ronde mag je een een vastgesteld aantal <b>'jokers'</b> inzetten, waarmee je score verdubbelt.
            </p>

            <p class="c-green about__text">
                Uitslag goed: 3 punten, met joker 6 punten<br />
                Winnaar goed: 1 punt, met joker 2 punten
            </p>


            <p class="c-green about__text">
                Per ronde beantwoord je ook een aantal <b>'bolusvragen'</b>, voor een wisselend aantal punten per vraag (te verdubbelen met joker).
            </p>

            <p class="c-green about__text">
                In de <b>knock-outfase</b> (waarin zonodig een verlenging/strafschoppen een winnaar opleveren) telt voor de poel als uitslag: de stand na het laatste fluitsignaal, d.w.z. tot aan een eventuele strafschoppenreeks. Een gelijke stand – namelijk na 120 minuten – is hier dus ook mogelijk als voorspelling.
            </p>

            <p class="c-green about__text">
                De '<b>pot</b>' wordt alsvolgt verdeeld:<br />
                nummer 1 – 50%<br />
                nummer 2 – 20%<br />
                nummer 3 – 15%<br />
                nummer 4 – 10%<br />
                nummer 5 – 5%<br />
            </p>

            <p class="c-green about__text">
                Extra: de <b>Grobprijs</b> wordt uitgereikt aan degene die als laatste eindigt van degenen die tot het eind hebben meegedaan (nietmeerinvullers vallen dus hierbuiten).
            </p>

            <p class="c-green about__text">
                Voor het '<b>afdelingsklassement</b>' deelt de poelleiding de poeldeelnemers in naar afdeling. Een afdeling bestaat uit tenminste vier deelnemers.  </p>

        </div>
        <div class="col col-12-3"></div>
    </div>
</div>
[@footerUtil.footer /]
</body>
</html>