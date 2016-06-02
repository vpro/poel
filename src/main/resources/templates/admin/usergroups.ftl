[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>

<html lang="nl">

[@headUtil.head title="groepen beheren" /]
<body>


[#if flash ? has_content]
<div class="alert-overlay">
    <div class="alert-overlay__content">
    ${flash} <br/> <br/>
        <button class="h5 button alert-overlay__close-button">Ok!</button>
    </div>
</div>
[/#if]

[@navigationUtil.navigation title='Admin' subtitle='groepen' back='/admin' /]

<div class="grid">

[@layout.sectionWithLayout
content={'layout': '100'}
title='groepen'
backGroundColor="darkgreen"
]



<form class="form usergroup-form" action="#" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="grid-gutter user-groups entities">
        [#list userGroups]

            [#items as userGroup]
                <div class="form-row user-group-admin entity-admin row">

                    <input type="hidden" name="userGroups[${userGroup?index}].id" value="${userGroup.id}" />
                    <input type="text" class="col-12-6" name="userGroups[${userGroup?index}].name" value="${userGroup.name}" />

                    <span class="delete delete-entity">
                        <i class="glyph glyph-close c-gold col-12-1"></i>
                    </span>

                </div>
            [/#items]
        [/#list]
    </div>

    <div class="col-gutter form-button-container user-groups-form-button-container">
        <input type="button" class="add-entity" value="Toevoegen"/>
        <input type="submit" value="Opslaan" />
    </div>

</form>

[/@layout.sectionWithLayout]

</div>

[@footerUtil.footer /]

</body>

</html>