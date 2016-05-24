[#include "countries.ftl"]

[#macro showMatch status prediction parity index]

    [#assign predictionId = prediction.id !]
    [#assign match = prediction.match]
    [#assign matchResult = prediction.match.matchResult ! ]
    [#assign predictedResult = prediction.matchResult !]
    [#assign hasPrediction = predictedResult ? has_content]
    [#assign multiplier = prediction.multiplier]
    [#assign score = prediction.score !]
    [#assign predictionIndex = index !]

<table class="predictions match-prediction">
    <tbody>

    <tr class="prediction__row prediction__row-${ parity }">
        <td class="prediction__game">

            <span class="prediction__game-home">
                <span class="flag-icon flag-icon-${ countryCodes[match.homeTeam] !}"></span>
                ${ match.homeTeam }
            </span>
            <span class="prediction__game-divider">
                -
            </span>
            <span class="prediction__game-away">
                <span class="flag-icon flag-icon-${ countryCodes[match.awayTeam] !}"></span>
                ${ match.awayTeam }
            </span>
            [#if status == 'future']
                <input type="hidden" name="predictions[${predictionIndex}].matchId" value="${match.id}"/>
                [#if predictionId?has_content]
                <input type="hidden" name="predictions[${predictionIndex}].predictionId" value="${predictionId}"/>
                [/#if]
            [/#if]
        </td>

        <td class="prediction__predicted">
            <input class="prediction home-prediction" type="number" min="0" name="predictions[${predictionIndex}].homeTeamGoals" [#if hasPrediction]value="${predictedResult.homeTeamGoals}" [/#if] [#if status != 'future' ]disabled [/#if] />
            <input class="prediction away-prediction" type="number" min="0" name="predictions[${predictionIndex}].awayTeamGoals" [#if hasPrediction]value="${predictedResult.awayTeamGoals}" [/#if] [#if status != 'future' ]disabled [/#if] />
        </td>

        <td>
            <input id="predictionMultiplier${predictionIndex}" type="checkbox" name="predictions[${predictionIndex}].multiplier" value="true" [#if multiplier]checked[/#if] />
            <label for="predictionMultiplier${predictionIndex}">Joker</label>
        </td>

        <td class="prediction__score">
            [#if score ? has_content]
                <span class='prediction__points prediction__points-${score} '>${score}</span>
            [/#if]
        </td>

    </tr>


    <tr class="prediction__row prediction__row-result prediction__row-${ parity }">
        <td class="prediction__result-title" >

            [#if status == 'future']
                Wedstrijd op: ${match.start?string["dd-MM, HH:mm"]}

                [#if match.start?long - .now?long < 10800000 && !hasPrediction ]
                <br><span class="prediction-deadline c-white bg-red"><i class="glyph glyph-alert c-white"></i> Let op: wedstrijd begint bijna! </span>
                [/#if]
            [#elseif status == 'finished']
                Uitslag:
            [#else]
                Wedstrijd gestart op: ${match.start?string["dd-MM, HH:mm"]}
            [/#if]



        </td>
        <td class="prediction__result" >
            [#if matchResult ? has_content]
                <input type="number" value="${matchResult.homeTeamGoals}" disabled />
                <input type="number" value="${matchResult.awayTeamGoals}" disabled />
            [/#if]
        </td>
        <td class="prediction__score">
        </td>
    </tr>

    </tbody>
</table>
[/#macro]
