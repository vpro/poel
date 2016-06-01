import $ from 'jquery';

import CollapseController from 'js/util/CollapseController.js';

import FormController from 'js/controllers/FormController.js';
import MatchController from 'js/controllers/MatchController.js';
import RankingController from 'js/controllers/RankingController.js';
import UserFormController from 'js/controllers/UserFormController.js';
import UserGroupController from 'js/controllers/UserGroupController.js';
import MessageController from 'js/controllers/MessageController.js';



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
    new UserGroupController( $('.usergroup-form'), $( '.alert-overlay' ) );
}

if ( $('.messages-form').length ) {
    new MessageController( $('.messages-form'), $( '.alert-overlay' ) );
}