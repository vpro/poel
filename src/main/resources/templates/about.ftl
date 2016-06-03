[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]
[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/layout.ftl" as layout]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title='VPRO EK-poel 2016' /]

<body>

[@navigationUtil.navigation title='VPRO EK-poel 2016' back='/' bgColor='bg-greybat' /]

<div class="grid about">

    [@layout.sectionWithLayout
    content={'layout': '100'}
    collapsible=true
    opened=false
    title='Poelregels'
    backGroundColor="greyelephant"
    addContainerCss=''
    addCss='prediction-section'
    closeColorClass='bg-greyelephant'
    openColorClass='bg-white'
    ]

    <div class="collapsible-section-content grid grid-gutter">

        <div class="col col-12-2"></div>
        <div class="col col-12-7">
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
        </div>
    <div class="col col-12-2"></div>
    </div>
    [/@layout.sectionWithLayout]


    [@layout.sectionWithLayout
    content={'layout': '100'}
    collapsible=true
    title='Bulletins'
    backGroundColor="greyelephant"
    addContainerCss=''
    addCss='prediction-section'
    closeColorClass='bg-greyelephant'
    openColorClass='bg-white'
    opened=true
    ]

    <div class="collapsible-section-content grid grid-gutter">

        [#list bulletins?reverse]
        [#items as bulletin]
        <div class="col col-12-6">

            <div class="col-gutter">
            [#if bulletin.date ?has_content]
                <div class="bulletin-date">${bulletin.date}</div>
            [/#if]

            [#if bulletin.text ?has_content]
                <div class="bulletin-title h5">${bulletin.text}</div>
            [/#if]

            [#if bulletin.description ?has_content]
                <div class="bulletin-description">${bulletin.description}</div>
            [/#if]

            [#if bulletin.key ?has_content]
                <a class="bulletin-link" target="_blank" href="${bulletin.key}">lees verder</a>
            [/#if]
            </div>
        </div>
        [/#items]
        [/#list]

    </div>
    [/@layout.sectionWithLayout]


</div>
[@footerUtil.footer /]
</body>
</html>