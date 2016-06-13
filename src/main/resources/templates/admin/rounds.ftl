[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>

<html lang="nl">

[@headUtil.head title="Speelrondes beheren" /]
<body>


[#if flash ? has_content]
<div class="alert-overlay">
    <div class="alert-overlay__content">
    ${flash} <br/> <br/>
        <button class="h5 button alert-overlay__close-button">Ok!</button>
    </div>
</div>
[/#if]

[@navigationUtil.navigation title='Admin' subtitle='speelrondes' back='/admin' /]

<div class="grid main">

[@layout.sectionWithLayout
content={'layout': '100'}
title='speelrondes'
backGroundColor="darkgreen"
]



<form class="form rounds-form" action="#" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<div class="grid-gutter rounds entities">
    [#list rounds as round]
        <div class="form-row round-admin entity-admin row">

            <input type="hidden" name="rounds[${round?index}].id" value="${round.id}" />
            <input type="text" class="col-12-6" name="rounds[${round?index}].name" value="${round.name}" />

            <span class="delete delete-entity">
                <i class="glyph glyph-close c-gold col-12-1"></i>
            </span>
        </div>
[/#list]
</div>

    <div class="col-gutter form-button-container rounds-form-button-container">
        <input type="button" class="add-entity" value="Toevoegen"/>
        <input type="submit" value="Opslaan" />
    </div>

</form>

[/@layout.sectionWithLayout]

</div>

[@footerUtil.footer /]

</body>

</html>