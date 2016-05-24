[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title="Admin" /]

<body>

    [@navigationUtil.navigation title='Admin' subtitle='groepen' back='/' /]

    <div class="grid">

    [@layout.sectionWithLayout
    content={'layout': '100'}
    title=''
    addCss='theme-primary'
    backGroundColor="bg-darkgreen"
    ]
    <div class="grid-gutter">
        [#list userGroups]
            <form action="/admin/groups" class="form group-admin" method="post">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                [#items as userGroup]
                <div class="grid">
                    ${ userGroup.name }
                </div>
                [/#items]

            <div class="form-footer bg-green">
                <button class="h5 button submit-button" type="submit">Opslaan</button>
            </div>
        </form>
        [#else]
            No groups at this time, sorry!
        [/#list]
    </div>

    [/@layout.sectionWithLayout]
    </div>
    [@footerUtil.footer /]

</body>

</html>