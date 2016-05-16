[#import "macros/head.ftl" as headUtil]

[@headUtil.head /]
<body>

<div class="grid grid-gutter">
    <h1>Current Matches</h1>
[#list matches]
<form>
<ul>
    [#items as match]
    <li>${match.homeTeam.name} - ${match.awayTeam.name}</li>
    [/#items]
</ul>
</form>
[#else]
    No matches at this time, sorry!
[/#list]
</div>

</body>
</html>