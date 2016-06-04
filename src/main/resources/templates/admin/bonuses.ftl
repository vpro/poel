[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/bonuses.ftl" as bonusUtil]
[#import "../macros/rounds.ftl" as roundUtil]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>

<html lang="nl">

[@headUtil.head title="bonus vragen beheren" /]
<body>


[#if flash ? has_content]
<div class="alert-overlay">
    <div class="alert-overlay__content">
    ${flash} <br/> <br/>
        <button class="h5 button alert-overlay__close-button">Ok!</button>
    </div>
</div>
[/#if]

[@navigationUtil.navigation title='Admin' subtitle='bonus vragen' back='/admin' /]

<div class="grid">

[@layout.sectionWithLayout
content={'layout': '100'}
title='bonus vragen'
backGroundColor="darkgreen"
]



<form class="form bonuses-form" action="#" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<div class="grid-gutter bonuses entities">
    [#list bonuses]

        [#items as bonus]
        <div class="form-row bonus-admin entity-admin row">

            <input type="hidden" name="bonuses[${bonus?index}].id" value="${bonus.id}" />
            <input type="text" class="col-12-3" name="bonuses[${bonus?index}].question" value="${bonus.question}" placeholder="Vraag" />
            <input type="datetime-local" class="col-12-3" name="bonuses[${bonus?index}].start" value="${bonus.start?string["yyyy-MM-dd'T'HH:mm"]}" />
            <input type="text" class="col-12-1" name="bonuses[${bonus?index}].score" value="${bonus.score}" placeholder="Te behalen punten" />
            [@bonusUtil.bonusCategorySelection categories=categories formPath='bonuses[${bonus_index}].category' selectedCategory=(bonus.category!) addCss='col-12-1' /]

            [#if bonus.category ? has_content]
                [#if bonus.category == 'COUNTRY']
                    [@bonusUtil.bonusAnswerSelection answers=countries formPath='bonuses[${bonus_index}].answerId' selectedAnswer=(bonus.answer!) addCss='col-12-2' /]
                [#elseif bonus.category == 'PLAYER']
                    [@bonusUtil.bonusAnswerSelection answers=players formPath='bonuses[${bonus_index}].answerId' selectedAnswer=(bonus.answer!) addCss='col-12-2' /]
                [/#if]
            [/#if]


            [#assign roundId = -1]
            [#if bonus.round ? has_content]
                [#assign roundId = bonus.round.id]
            [/#if]
            [@roundUtil.roundSelection rounds=rounds formPath='bonuses[${bonus_index}].roundId' selectedRoundId=RoundId addCss='col-12-1' /]

            <span class="delete delete-entity">
                <i class="glyph glyph-close c-gold col-12-1"></i>
            </span>

        </div>
    [/#items]
[/#list]
</div>

    <script id="roundSelection" type="text/template">
        [@roundUtil.roundSelection rounds=rounds formPath='bonuses[{{@root.index}}].roundId' addCss='col-12-2' /]
    </script>

    <script id="bonusCategorySelection" type="text/template">
        [@bonusUtil.bonusCategorySelection categories=categories formPath='bonuses[{{@root.index}}].category' addCss='col-12-1' /]
    </script>

    <script id="bonusAnswerSelectionCOUNTRY" type="text/template">
        [@bonusUtil.bonusAnswerSelection answers=countries formPath='bogus-only-its-options-are-used' addCss='col-12-2' /]
    </script>
    <script id="bonusAnswerSelectionPLAYER" type="text/template">
        [@bonusUtil.bonusAnswerSelection answers=players formPath='bogus-only-its-options-are-used' addCss='col-12-2' /]
    </script>

    <div class="col-gutter form-button-container choices-form-button-container">
        <input type="button" class="add-entity" value="Toevoegen"/>
        <input type="button" class="sort-entities" value="Sorteren (datum)"/>
        <input type="submit" value="Opslaan" />
    </div>

</form>

[/@layout.sectionWithLayout]

</div>

[@footerUtil.footer /]

</body>

</html>