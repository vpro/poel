[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head /]
<body>
[@navigationUtil.navigation /]

<div class="grid grid-gutter">
    <h1 class="h4">Messages</h1>

[#list messages]
<form>
<ul>
    [#items as message]
    <li>${message.key} - ${message.value}</li>
    [/#items]
</ul>
</form>
[#else]
    No messages at this time, sorry!
[/#list]
</div>

TODO: Message beheer (aanpassen teksten)<br/>

[@footerUtil.footer /]
</body>
</html>