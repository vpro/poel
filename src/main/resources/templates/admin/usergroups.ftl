[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>

<html lang="nl">

[@headUtil.head title="groepen beheren" /]
<body>

[@navigationUtil.navigation title='Admin' subtitle='groepen' back='/admin' /]

<div class="grid">

[@layout.sectionWithLayout
content={'layout': '100'}
title='groepen'
backGroundColor="bg-darkgreen"
]

[#list userGroups]

<form class="form usergroup-form" action="#" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="grid-gutter user-groups">

        [#items as userGroup]
            <div class="form-row user-group row">

                <input type="hidden" name="userGroups[${userGroup?index}].id" value="${userGroup.id}" />
                <input type="text" class="col-12-6" name="userGroups[${userGroup?index}].name" value="${userGroup.name}" />

                <span class="delete delete-user-group">
                    <i class="glyph glyph-close c-gold col-12-1"></i>
                </span>

            </div>
        [/#items]

    </div>

    <div class="col-gutter form-button-container user-groups-form-button-container">
        <input type="button" class="add-user-group" value="Toevoegen"/>
        <input type="submit" value="Opslaan" />
    </div>

</form>
[/#list]

[/@layout.sectionWithLayout]

</div>

[@footerUtil.footer /]

</body>

</html>