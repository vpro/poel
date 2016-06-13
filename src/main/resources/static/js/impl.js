import $ from 'jquery';

import CollapseController from 'js/util/CollapseController.js';

import EntityListController from 'js/controllers/EntityListController.js';
import SortableController from 'js/controllers/SortableController.js';
import FormController from 'js/controllers/FormController.js';
import RankingController from 'js/controllers/RankingController.js';
import UserFormController from 'js/controllers/UserFormController.js';
import NavigationController from 'js/controllers/NavigationController.js';

import bonusChoiceTemplate from 'js/views/bonuschoice.html!hbs';
import bonusTemplate from 'js/views/bonus.html!hbs';
import bulletinTemplate from 'js/views/bulletin.html!hbs';
import matchTemplate from 'js/views/match.html!hbs';
import messageTemplate from 'js/views/message.html!hbs';
import roundTemplate from 'js/views/round.html!hbs';
import userGroupTemplate from 'js/views/user-group.html!hbs';

import Handlebars from 'handlebars';
import HandlebarsRuntime from 'handlebars-runtime';

if ( $('#roundSelection').length ) {
    HandlebarsRuntime.registerPartial( 'roundSelection', Handlebars.compile( $('#roundSelection').html() ) );
}

if ( $('#bonusCategorySelection').length ) {
    HandlebarsRuntime.registerPartial( 'bonusCategorySelection', Handlebars.compile( $('#bonusCategorySelection').html() ) );
}

if ( $('#bonusAnswerSelectionCOUNTRY').length ) {
    window.bonusAnswerSelectionCOUNTRY = Handlebars.compile( $('#bonusAnswerSelectionCOUNTRY').html() );
}

if ( $('#bonusAnswerSelectionPLAYER').length ) {
    window.bonusAnswerSelectionPLAYER = Handlebars.compile( $('#bonusAnswerSelectionPLAYER').html() );
}

if ( $('#bonusAnswerSelectionSCORE').length ) {
    window.bonusAnswerSelectionSCORE = Handlebars.compile( $('#bonusAnswerSelectionSCORE').html() );
}

new CollapseController( document.querySelectorAll( '.collapsible-section') );

if ( $('.prediction-form').length ) {
    new FormController( $('.prediction-form'), $( '.alert-overlay' ) );
}

if ( $('#ranking__groupfilter[data-id]').length ) {
    new RankingController( $( '#ranking__groupfilter' ) );
}

if ( $( '.alert-overlay' ).length ) {
    new UserFormController( $( '.alert-overlay' ) );
}

if ( $('.matches-form').length ) {

    new EntityListController( $('.matches-form'), $( '.alert-overlay' ), matchTemplate, 'matches' );
    new SortableController( $('.matches-form .sort-entities'), $('.matches-form .entities'), '.entity-admin' );
}

if ( $('.usergroup-form').length ) {
    new EntityListController( $('.usergroup-form'), $( '.alert-overlay' ), userGroupTemplate, 'userGroups' );
}

if ( $('.messages-form').length ) {
    new EntityListController( $('.messages-form'), $( '.alert-overlay' ), messageTemplate, 'messages' );
}

if ( $('.bulletins-form').length ) {
    new EntityListController( $('.bulletins-form'), $( '.alert-overlay' ), bulletinTemplate, 'bulletins' );
}

if ( $('.rounds-form').length ) {
    new EntityListController( $('.rounds-form'), $( '.alert-overlay' ), roundTemplate, 'rounds' );
}

if ( $('.bonus-choices-form').length ) {
    new EntityListController( $('.bonus-choices-form'), $( '.alert-overlay' ), bonusChoiceTemplate, 'choices' );
}

if ( $('.bonuses-form').length ) {
    new EntityListController( $('.bonuses-form'), $( '.alert-overlay' ), bonusTemplate, 'bonuses' );
    new SortableController( $('.bonuses-form .sort-entities'), $('.bonuses-form .entities'), '.bonus-admin' );

    // switch answer selection pulldowns for existing bonuses when categories change
    $('.bonus-choice-admin__category-selection').on( 'change', function ( e ) {
        var $select = $( e.currentTarget );

        if ( $select.next().is('.bonus-admin__answer-selection') ) {
            if ( $select.val() == '' ) {
                $select.next().empty();
            } else {
                // render the contents of the specific category pulldown in the live form
                $select.next().html( window[ 'bonusAnswerSelection'+ $select.val() ]({}) );
            }
        }
    });
}


if ( $('.top-navigation').length ) {
    new NavigationController( $('.top-navigation') );
}
