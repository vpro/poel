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

            Todo groepen toevoegen en wijzigen

        </div>
    [/@layout.sectionWithLayout]
    </div>
    [@footerUtil.footer /]

</body>

</html>