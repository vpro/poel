[#import 'layout.ftl' as layout]

[#assign preliminaryShown = false]
[#assign eighthShown = false]
[#assign quarterShown = false]
[#assign semiShown = false]

[#assign eighthsStart = 20160602]
[#assign quartersStart = 20160612]
[#assign semisStart = 20160616]
[#assign finalsStart = 20160620]

[#function isPreliminary date ]
    [#local dateNr = date ? string( 'yyyyMMdd' ) ? number ]
    [#if dateNr lte eighthsStart ]
        [#return true]
    [/#if]
    [#return false]
[/#function]

[#function isEighthFinals date ]
    [#local dateNr = date ? string( 'yyyyMMdd' ) ? number ]
    [#if dateNr gte eighthsStart && dateNr lt quartersStart ]
        [#return true]
    [/#if]
    [#return false]
[/#function]

[#function isQuarterFinals date ]
    [#local dateNr = date ? string( 'yyyyMMdd' ) ? number ]
    [#if dateNr gte quartersStart && dateNr lt semisStart ]
        [#return true]
    [/#if]
    [#return false]
[/#function]

[#function isSemiFinals date ]
    [#local dateNr = date ? string( 'yyyyMMdd' ) ? number ]
    [#if dateNr gte semisStart && dateNr lt finalsStart ]
        [#return true]
    [/#if]
    [#return false]
[/#function]

[#function isFinals date ]
    [#local dateNr = date ? string( 'yyyyMMdd' ) ? number ]
    [#if dateNr gte finalsStart ]
        [#return true]
    [/#if]
    [#return false]
[/#function]

[#macro reset]
    [#assign preliminaryShown = false]
    [#assign eighthShown = false]
    [#assign quarterShown = false]
    [#assign semiShown = false]
[/#macro]

[#-- only show a matchday label if it hasn't been shown --]
[#macro showOptionalMatchDayLabel date]

    [#if isPreliminary( date ) && ! preliminaryShown ]
        [#assign preliminaryShown = true]
        [@layout.sectionWithLayout content={ 'layout':'100' } title='Voorrondes' addCss='matchday component-theme' backGroundColor='green' /]

    [#elseif isEighthFinals( date ) && ! eighthShown ]
        [#assign eighthShown = true]
        [@layout.sectionWithLayout content={ 'layout':'100' } title='Achtste finales' addCss='matchday component-theme' backGroundColor='green'   /]

    [#elseif isQuarterFinals( date ) && ! quarterShown ]
        [#assign quarterShown = true]
        [@layout.sectionWithLayout content={ 'layout':'100' } title='Kwartfinales' addCss='matchday component-theme' backGroundColor='green'   /]

    [#elseif isSemiFinals( date ) && ! semiShown ]
        [#assign semiShown = true]
        [@layout.sectionWithLayout content={ 'layout':'100' } title='Halve finales' addCss='matchday component-theme' backGroundColor='green'   /]

    [#elseif isFinals( date ) ]
        [@layout.sectionWithLayout content={ 'layout':'100' } title='Finale' addCss='matchday component-theme' backGroundColor='green'   /]

    [/#if]

[/#macro]