[#macro formEntry match prediction="" outcome="" score=0]

<div class="grid grid-gutter">
    <h1 class="h5">${ match.homeTeam.name } - ${ match.awayTeam.name }</h1>

    <h2 class="h6">Your prediction:</h2>

    <hr />
    Indien een match voorspeld kan worden
    [#-- [#if match.canBePredictedAt( ... )] --]
    <input type="text" class="prediction" value="" name="prediction-{matchId}-home'" id="prediction-{matchId}-home" />
    -
    <input type="text" class="prediction" value="" name="prediction-{matchId}-away'" id="prediction-{matchId}-away" />

    [#-- [#else] --]
    <hr />
    Anders

    [#--[#if prediction ? has_content]--]
        {prediction.home} - {prediction.away} <br />
    [#--[#else]--]
        Of Non entered.
    [#--[/#if]--]

    [#-- [/#if] --]

</div>
[/#macro]

[#macro predictionEntry matchId prediction=""]

[/#macro]