import $ from 'jquery';

import CollapseController from 'js/util/CollapseController.js';

import EntityListController from 'js/controllers/EntityListController.js';
import SortableController from 'js/controllers/SortableController.js';
import FormController from 'js/controllers/FormController.js';
import MatchController from 'js/controllers/MatchController.js';
import RankingController from 'js/controllers/RankingController.js';
import UserFormController from 'js/controllers/UserFormController.js';

import messageTemplate from 'js/views/message.html!hbs';
import matchDayTemplate from 'js/views/matchday.html!hbs';
import userGroupTemplate from 'js/views/user-group.html!hbs';
import bonusChoiceTemplate from 'js/views/bonuschoice.html!hbs';
import bonusTemplate from 'js/views/bonus.html!hbs';
import bulletinTemplate from 'js/views/bulletin.html!hbs';

import Handlebars from 'handlebars';
import HandlebarsRuntime from 'handlebars-runtime';

if ( $('#matchDaySelection').length ) {
    HandlebarsRuntime.registerPartial( 'matchDaySelection', Handlebars.compile( $('#matchDaySelection').html() ) );
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
    new MatchController( $('.matches-form'), $( '.alert-overlay' ) );
}

if ( $('.usergroup-form').length ) {
    new EntityListController( $('.usergroup-form'), $( '.alert-overlay' ), userGroupTemplate, 'userGroups' );
}

if ( $('.messages-form').length ) {
    new MessageController( $('.messages-form'), $( '.alert-overlay' ) );
}

if ( $('.bulletins-form').length ) {
    new EntityListController( $('.bulletins-form'), $( '.alert-overlay' ), bulletinTemplate, 'bulletins' );
}

if ( $('.matchdays-form').length ) {
    new EntityListController( $('.matchdays-form'), $( '.alert-overlay' ), matchDayTemplate, 'matchDays' );
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