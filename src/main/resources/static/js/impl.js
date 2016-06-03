import $ from 'jquery';

import CollapseController from 'js/util/CollapseController.js';

import EntityListController from 'js/controllers/EntityListController.js';
import FormController from 'js/controllers/FormController.js';
import MatchController from 'js/controllers/MatchController.js';
import RankingController from 'js/controllers/RankingController.js';
import UserFormController from 'js/controllers/UserFormController.js';

import messageTemplate from 'js/views/message.hbs!';
import matchDayTemplate from 'js/views/matchday.hbs!';
import userGroupTemplate from 'js/views/user-group.hbs!';
import bonusChoiceTemplate from 'js/views/bonuschoice.hbs!';
import bonusTemplate from 'js/views/bonus.hbs!';


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
    new EntityListController( $('.messages-form'), $( '.alert-overlay' ), messageTemplate, 'messages' );
}

if ( $('.matchdays-form').length ) {
    new EntityListController( $('.matchdays-form'), $( '.alert-overlay' ), matchDayTemplate, 'matchDays' );
}

if ( $('.bonus-choices-form').length ) {
    new EntityListController( $('.bonus-choices-form'), $( '.alert-overlay' ), bonusChoiceTemplate, 'choices' );
}

if ( $('.bonuses-form').length ) {
    new EntityListController( $('.bonuses-form'), $( '.alert-overlay' ), bonusTemplate, 'bonuses' );
}