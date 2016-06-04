[#import 'layout.ftl' as layout]

[#assign currentLabel = ""]

[#macro reset]
    [#assign currentLabel = ""]
[/#macro]

[#-- only show a round label if it hasn't been shown --]
[#macro showOptionalRoundLabel object]

    [#if object.round ? has_content]
        [#if object.round.name != currentLabel]
            [#assign currentLabel = object.round.name]
            [@renderLabel label=currentLabel /]
        [/#if]
    [/#if]

[/#macro]

[#macro renderLabel label='']
    [@layout.sectionWithLayout content={ 'layout':'100' } title=label addCss='round component-theme' backGroundColor='green' /]
[/#macro]

[#macro roundSelection rounds formPath="" selectedRoundId=-1 addCss='']
<select class="match-admin__round-selection ${ addCss }" name="${ formPath }">
    <option valuu="">Kies een speelronde...</option>
    [#list rounds as round]
        <option value="${ round.id }" [#if round.id == selectedRoundId]selected="selected"[/#if]>${ round.name }</option>
    [/#list]
</select>
[/#macro]