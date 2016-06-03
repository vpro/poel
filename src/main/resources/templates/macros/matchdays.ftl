[#import 'layout.ftl' as layout]

[#assign currentLabel = ""]

[#macro reset]
    [#assign currentLabel = ""]
[/#macro]

[#-- only show a matchday label if it hasn't been shown --]
[#macro showOptionalMatchDayLabel object]

    [#if object.matchDay ? has_content]
        [#if object.matchDay.name != currentLabel]
            [#assign currentLabel = object.matchDay.name]
            [@renderLabel label=currentLabel /]
        [/#if]
    [/#if]

[/#macro]

[#macro renderLabel label='']
    [@layout.sectionWithLayout content={ 'layout':'100' } title=label addCss='matchday component-theme' backGroundColor='green' /]
[/#macro]

[#macro matchDaySelection matchDays formPath="" selectedMatchDayId=-1 addCss='']
<select class="match-admin__matchday-selection ${ addCss }" name="${ formPath }">
    <option valuu="">Kies een speelronde...</option>
    [#list matchDays as matchDay]
        <option value="${ matchDay.id }" [#if matchDay.id == selectedMatchDayId]selected="selected"[/#if]>${ matchDay.name }</option>
    [/#list]
</select>
[/#macro]