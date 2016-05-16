[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Error' /]
    <body class="bg-orange c-white">

    [@navigationUtil.navigation title='Error' back='/' /]

    <div class="grid grid-gutter">

            <h1 class="h4">Er is iets fout gegaan</h1>

        </div>

    </body>

</html>