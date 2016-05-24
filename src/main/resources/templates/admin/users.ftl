[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]
[#import "../macros/usergroups.ftl" as usergroupUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Admin'/]
    <body>

    [@navigationUtil.navigation title='Admin' subtitle='deelnemers' back='/admin' /]
    <div class="grid">

    [@layout.sectionWithLayout
    content={'layout': '100'}
    addCss='theme-primary'
    backGroundColor="bg-darkgreen"
    ]
        <div class="grid-gutter theme-text">
            [#list users]
            <form class="form" action="/admin/users" method="post">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                <div class="grid row">
                    <div class="col col-2-1"><h2 class="h5">Alle deelnemers</h2></div>
                    <div class="col col-2-1"><h2 class="h5">Afdeling</h2></div>
                </div>
                <br />
                [#items as u]
                <div class="grid row">
                    <div class="col col-2-1">${u.realName} (${u.username})</div>
                    <div class="col col-2-1">
                        [#assign formPath = "users[${ u_index }]" ]
                        <input type="hidden" name="${ formPath }.userId" value="${u.id}"/>

                        [#if u.userGroup ? has_content]
                            [@usergroupUtil.usergroupSelection userGroups=userGroups formPath="${ formPath }.userGroupId" selectedUserGroupId=(u.userGroup.id) /]
                        [#else]
                            [@usergroupUtil.usergroupSelection userGroups=userGroups formPath="${ formPath }.userGroupId" /]
                        [/#if]
                    </div>
                </div>
                [/#items]

                <p>
                    Todo: <br />
                    Een export van emailadressen kunnen doen<br />
                </p>

                <div class="form-footer bg-green">
                    <button class="h5 button submit-button" type="submit">Opslaan</button>
                </div>
            </form>
            [#else]
                Er zijn geen deelnemers. :o(
            [/#list]
        </div>
    [/@layout.sectionWithLayout]

    [@footerUtil.footer /]
    </body>
</html>
