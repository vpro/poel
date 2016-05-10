<!DOCTYPE html>
<html lang="nl">
<body>
Alleen admins zoals ${user.displayName} (${user.username}/${user.role}) kunnen dit zien!

<#list users>
    <h2>Alle deelnemers</h2>
    <ul>
        <#items as u>
            <li>${u.displayName} (${u.username})</li>
        </#items>
    </ul>
<#else>
    Er zijn geen deelnemers. :o(
</#list>
</body>
</html>
