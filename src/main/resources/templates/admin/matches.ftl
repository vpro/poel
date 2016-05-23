[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>

<html lang="nl">

    [@headUtil.head title="wedstrijden beheren" /]
    <body>

    [@navigationUtil.navigation title='Admin' subtitle='wedstrijden' back='/' /]

        <div class="grid">

            [@layout.sectionWithLayout
                content={'layout': '100'}
                title='wedstrijden'
                backGroundColor="bg-darkgreen"
            ]

                [#list matches]

                <form action="#" class="form" method="post">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="matches">

                        [#items as match]
                            <div class="match row">

                                <!-- TODO: move this into a macro-->
                                <!-- or (even better) render this through match.hbs as we do when adding new matches -->
                                <!-- so this markup has a single source in stead of two -->

                                [#if match.matchResult ? has_content]
                                    [#assign hasMatchResult = true/]
                                [#else]
                                    [#assign hasMatchResult = false/]
                                [/#if]

                                <input type="hidden" name="matches[${match?index}].id" value="${match.id}" />

                                <input type="text" class="col-12-3" name="matches[${match?index}].homeTeam" value="${match.homeTeam}" />
                                <input type="text" class="col-12-3" name="matches[${match?index}].awayTeam" value="${match.awayTeam}" />
                                <input type="datetime-local" class="col-12-3" name="matches[${match?index}].start" value="${match.start?string["yyyy-MM-dd'T'hh:mm"]}" />

                                <input type="number" class="col-12-1" name="matches[${match?index}].homeTeamGoals" [#if hasMatchResult]value="${match.matchResult.homeTeamGoals!}"[/#if] />
                                <input type="number" class="col-12-1" name="matches[${match?index}].awayTeamGoals" [#if hasMatchResult]value="${match.matchResult.awayTeamGoals!}"[/#if] />

                                <i class="glyph glyph-close c-greygrizzly delete-match col-12-1"></i>

                            </div>
                        [/#items]

                    </div>


                    <input type="button" class="sort-matches" value="Sorteren (datum)"/>

                    <input type="button" class="add-match" value="Toevoegen"/>

                    <input type="submit" value="Opslaan" />
                    <input type="reset" value="Annuleren (-achtig)" />

                </form>

                [#else]
                    Hier is (nog) niets
                [/#list]

            [/@layout.sectionWithLayout]

        </div>

    [@footerUtil.footer /]

    <script>
        System.import( '/js/form/controllers/MatchController.js' ).then( function ( matchControllerModule ) {

            new matchControllerModule.default( document.querySelectorAll( 'form' ) );

        } );
    </script>

    </body>

</html>