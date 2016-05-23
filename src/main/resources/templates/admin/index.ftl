[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title="Admin" /]

<body>

    [@navigationUtil.navigation title='Admin' back='/' bgColor='bg-greybat' /]

    <div class="grid grid-gutter bg-blue">

        <ul>
            <li><a href="/admin/matches">Wedstrijden</a></li>
            <li><a href="/admin/messages">Berichten</a></li>
            <li><a href="/admin/users">Gebruikers</a></li>
        </ul>

    </div>
    [@footerUtil.footer /]

</body>

</html>