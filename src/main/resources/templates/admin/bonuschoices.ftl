[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/bonuses.ftl" as bonusUtil]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>

<html lang="nl">

[@headUtil.head title="bonus mogelijkheden beheren" /]
<body>


[#if flash ? has_content]
<div class="alert-overlay">
    <div class="alert-overlay__content">
    ${flash} <br/> <br/>
        <button class="h5 button alert-overlay__close-button">Ok!</button>
    </div>
</div>
[/#if]

[@navigationUtil.navigation title='Admin' subtitle='bonus mogelijkheden' back='/admin' /]

<div class="grid">

[@layout.sectionWithLayout
content={'layout': '100'}
title='bonus mogelijkheden'
backGroundColor="darkgreen"
]



<form class="form bonus-choices-form" action="#" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<div class="grid-gutter bonus-choices entities">
    [#list choices]

        [#items as choice]
        <div class="form-row bonus-choice-admin entity-admin row">

            <input type="hidden" name="choices[${choice?index}].id" value="${choice.id}" />
            <input type="text" class="col-12-6" name="choices[${choice?index}].value" value="${choice.value}" placeholder="Waarde" />

            [@bonusUtil.bonusCategorySelection categories=categories formPath='choices[${choice_index}].category' selectedCategory=(choice.category!) addCss='col-12-5' /]

            <span class="delete delete-entity">
                <i class="glyph glyph-close c-gold col-12-1"></i>
            </span>

        </div>
    [/#items]
[/#list]
</div>

    <div class="col-gutter form-button-container choices-form-button-container">
        <input type="button" class="add-entity" value="Toevoegen"/>
        <input type="submit" value="Opslaan" />
    </div>

</form>

[/@layout.sectionWithLayout]

</div>

[@footerUtil.footer /]

</body>

</html>