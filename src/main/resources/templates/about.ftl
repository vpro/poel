[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]
[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/layout.ftl" as layout]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title='VPRO EK-poel 2016' /]

<body>

[@navigationUtil.navigation title='VPRO EK-poel 2016' back='/' bgColor='bg-greybat' /]

<div class="grid theme-white bg-white about">
    <div class="component">

            [@layout.sectionWithLayout
            content={"layout":'58'}
            title='Hoe werkt het?'
            collapsible=true
            opened=false
            addCss='about-section c-greybat'
            addContainerCss='bg-white'
            backGroundColor="white"]


            <p class=" about__text">
                De <b>inleg</b> voor VPRO's EK-poel bedraagt vijf euro, overmaken naar F. Hermsen, Amsterdam, NL35
                INGB000 4707382
            </p>

            <p class=" about__text">
                <b>Voorspel per wedstrijd de uitslag.</b> Je kunt de voorspelling tot aan het begin van elke wedstrijd
                aanpassen. Na aanvang van de wedstrijd mag en kan je niets meer aan die wedstrijdvoorspelling
                veranderen. (Je mag natuurlijk ook alle wedstrijden in één keer invullen. Dus ook al je op
                vakantie/offline gaat, kun je gewoon meedoen.)
            </p>

            <p class="about__text">
                Per ronde mag je een een vastgesteld aantal <b>'jokers'</b> inzetten, waarmee je score verdubbelt.
            </p>

            <p class="about__text">
                Uitslag goed: 3 punten, met joker 6 punten<br/>
                Winnaar goed: 1 punt, met joker 2 punten
            </p>


            <p class=" about__text">
                Per ronde beantwoord je ook een aantal <b>'bolusvragen'</b>, voor een wisselend aantal punten per vraag
                (te verdubbelen met joker).
            </p>

            <p class="about__text">
                In de <b>knock-outfase</b> (waarin zonodig een verlenging/strafschoppen een winnaar opleveren) telt voor
                de poel als uitslag: de stand na het laatste fluitsignaal, d.w.z. tot aan een eventuele
                strafschoppenreeks. Een gelijke stand – namelijk na 120 minuten – is hier dus ook mogelijk als
                voorspelling.
            </p>

            <p class="about__text">
                De '<b>pot</b>' wordt alsvolgt verdeeld:<br/>
                nummer 1 – 50%<br/>
                nummer 2 – 20%<br/>
                nummer 3 – 15%<br/>
                nummer 4 – 10%<br/>
                nummer 5 – 5%<br/>
            </p>

            <p class="about__text">
                Extra: de <b>Grobprijs</b> wordt uitgereikt aan degene die als laatste eindigt van degenen die tot het
                eind hebben meegedaan (nietmeerinvullers vallen dus hierbuiten).
            </p>

            <p class="about__text">
                Voor het '<b>afdelingsklassement</b>' deelt de poelleiding de poeldeelnemers in naar afdeling. Een
                afdeling bestaat uit tenminste vier deelnemers. </p>

            [/@layout.sectionWithLayout]



    </div>
</div>

<div class="grid theme-white bg-white about">
    <div class="component">

            [@layout.sectionWithLayout
            content={"layout":"83"}
            title='Poelbulletins'
            collapsible=true
            opened=true
            addCss='about-section c-greybat'
            addContainerCss='bg-white'
            backGroundColor="white"]

            [#list bulletins]
                [#items as bulletin]
                    <!--TODO link styling-->
                    ${bulletin}
                [/#items]
            [/#list]

            [/@layout.sectionWithLayout]


    </div>
</div>
[@footerUtil.footer /]
</body>
</html>