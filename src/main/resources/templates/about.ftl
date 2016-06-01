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
                De <b>inleg</b> voor VPRO's EK-poel bedraagt vijf euro, overmaken naar F. Hermsen, Amsterdam,
                NL35INGB0004707382
            </p>

            <p class="c-green about__text">
                <b>Voorspel per wedstrijd de uitslag.</b> Je kunt de voorspelling tot aan het eerste fluitsignaal van
                elke wedstrijd aanpassen. Na aanvang van de wedstrijd mag en kan je niets meer aan die
                wedstrijdvoorspelling veranderen.
            </p>

            <p class="c-green about__text">
                Ga je op vakantie tijdens het toernooi? Geen probleem, je kunt je voorspellingen bij alle wedstrijden
                ook van tevoren invullen.
            </p>

            <p class="c-green about__text">
                Per ronde mag je een vastgesteld aantal <b>'jokers'</b> inzetten, waarmee je score verdubbelt.
            </p>

            <p class="c-green about__text">
                Uitslag goed: 3 punten, met joker 6 punten
            </p>

            <p class="c-green about__text">
                Winnaar goed: 1 punt, met joker 2 punten
            </p>

            <p class="c-green about__text">
                In de <b>knock-outfase</b> (waarin mogelijk een verlenging c.q. strafschoppen een winnaar opleveren)
                telt voor de poel als uitslag: de stand na het laatste fluitsignaal, d.w.z. tot aan een eventuele
                strafschoppenreeks. Een gelijke stand – namelijk na 120 minuten – is hier dus ook mogelijk als
                voorspelling.
            </p>

            <p class="c-green about__text">
                De '<b>pot</b>' wordt alsvolgt verdeeld:
            </p>

            <p class="c-green about__text">
                nummer 1 – 50%
            </p>

            <p class="c-green about__text">
                nummer 2 – 20%
            </p>

            <p class="c-green about__text">
                nummer 3 – 15%
            </p>

            <p class="c-green about__text">
                nummer 4 – 10%
            </p>

            <p class="c-green about__text">
                nummer 5 – 5%
            </p>

            <p class="c-green about__text">
                Extra: de <b>Grobprijs</b> wordt uitgereikt aan degene die als laatste eindigt van degenen die tot het
                eind hebben meegedaan (nietmeerinvullers vallen dus hierbuiten).
            </p>

            <p class="c-green about__text">
                Voor het '<b>afdelingsklassement</b>' deelt de poelleiding de poeldeelnemers in naar afdeling. Een
                afdeling bestaat uit tenminste vier deelnemers. </p>

        </div>
        <div class="col col-12-3"></div>
    </div>
</div>
[@footerUtil.footer /]
</body>
</html>