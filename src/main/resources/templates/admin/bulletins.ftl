[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title='Bulletins beheren' /]
<body>

[#if flash ? has_content]
<div class="alert-overlay">
    <div class="alert-overlay__content">
        ${flash} <br/> <br/>
        <button class="h5 button alert-overlay__close-button">Ok!</button>
    </div>
</div>
[/#if]

[@navigationUtil.navigation title='Admin' subtitle='bulletins' bgColor='bg-darkred' back='/admin' /]

<div class="grid">

    [@layout.sectionWithLayout
    content={'layout': '100'}
    title='berichten'
    backGroundColor="darkgreen"
    ]
    <form action="/admin/bulletins" class="form bulletins-form" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

        <div class="grid-gutter bulletins">
            [#list bulletins]
            [#items as bulletin]
            <div class="form-row bulletin-admin row">
                <input type="hidden" name="bulletins[${ bulletin ? index }].id" value="${bulletin.id}"/>
                <div class="grid">

                    <div class="col col-12-5">
                        <input type="text" name="bulletins[${ bulletin ? index }].key" class="bulletin-admin__key" value="${bulletin.key}" placeholder="bulletin link*"/>
                    </div>
                    <div class="col col-12-6">
                        <textarea name="bulletins[${ bulletin ? index}].text" class="bulletin-admin__text" placeholder="bulletin titel">${ bulletin.text }</textarea>
                    </div>
                    <div class="col col-12-5">
                        <input type="text" name="bulletins[${ bulletin ? index }].description" class="bulletin-admin__key" value="${bulletin.description}" placeholder="bulletin omschrijving"/>
                    </div>
                    <div class="col col-12-6">
                        <textarea name="bulletins[${ bulletin ? index}].date" class="bulletin-admin__text" placeholder="bulletin datum">${ bulletin.date }</textarea>
                    </div>
                    <div class="delete delete-bulletin col-12-1">
                        <i class="glyph glyph-close c-gold"></i>
                    </div>
                </div>
            </div>
            [/#items]
            [/#list]
        </div>

        <div class="grid-gutter form-button-container bulletins-form-button-container">
            <input type="button" class="add-bulletin" value="Toevoegen"/>
            <input type="submit" value="Opslaan" />
        </div>
    </form>


    [/@layout.sectionWithLayout]

</div>

[@footerUtil.footer /]
</body>
</html>